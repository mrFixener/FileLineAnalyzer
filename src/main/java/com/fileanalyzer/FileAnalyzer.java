package com.fileanalyzer;


import com.fileanalyzer.config.Configuration;
import com.fileanalyzer.domain.Files;
import com.fileanalyzer.service.FileStatisticService;
import com.fileanalyzer.service.FilesService;
import static com.fileanalyzer.util.Helpers.getSuffixUserFileName;
import com.fileanalyzer.util.LineStatisticCalculator;
import static com.fileanalyzer.util.SqlGenerator.getInsertByFileStatistic;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import java.util.regex.Pattern;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dimon
 */
public class FileAnalyzer {
    @Autowired
    private FileStatisticService fileStatisticService;
    @Autowired
    private FilesService filesService;
    private final Pattern pattern;
    private static final Logger log = Logger.getLogger(FileAnalyzer.class);
    private final ExecutorService pool;
    private final Configuration conf;
    private boolean withSubdirectories = false;
    private int numFiles;
    public FileAnalyzer(final Configuration conf){
        this.conf  = conf;
        pattern = Pattern.compile(conf.getFileMask() != null && conf.getFileMask().trim().length() > 0
                ? conf.getFileMask():".*");
        this.pool = Executors.newFixedThreadPool(Integer.valueOf(this.conf.getNumPoolThread()));
    }

    public void analizeFile(final String filePath, boolean withSubdirectories){
        this.withSubdirectories = withSubdirectories;
        log.info("Start find files...");
        iterateFiles(new File(filePath));
        if (numFiles == 0) {
            log.info("No files found.");
        }
    }
    public Long analizeString(final String words, final String fileName){
        if(words == null || fileName == null)
            return null;
        Files dbFile = new Files();
        dbFile.setFileName(fileName+getSuffixUserFileName());
        Long fileId = null;
        synchronized (this) {
            fileId = filesService.add(dbFile);
        }
        for (String line : words.split("\n")) {
            LineStatisticCalculator statcalc = new LineStatisticCalculator(line);
            statcalc.setIdFk(fileId);
            StringBuilder sql = new StringBuilder(getInsertByFileStatistic(statcalc.getFileStatistic()));
            synchronized (this) {
                fileStatisticService.add(sql);
            }
        }
        return fileId;
    }
    private void iterateFiles(final File dir) {
        File[] files = dir.listFiles();
        if(files == null){
            files = new File[1];
            files[0] = dir; 
        }
        for (File file : files) {
            if (withSubdirectories && file.isDirectory() && file.canRead()) {
                iterateFiles(file);
            } else if (pattern.matcher(file.getName()).matches()) {
                log.info("Finded a file for analyting: " + file.getPath());
                ++numFiles;
                pool.submit(new FileRunnable(file));
            }
        }
    }
    private void processFile(final File file) throws IOException{
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            Files dbFile = new Files();
            dbFile.setFileName(file.getName());
            Long fileId = null;
            synchronized(this){
                fileId = filesService.add(dbFile);
            }
            
            StringBuilder sql =new StringBuilder();
            Integer maxinLineInMemory = (conf.getNumFlush()!= null)?conf.getNumFlush():1000;
            long rowsCounter = 0;        
            while ((line = br.readLine()) != null) {
                LineStatisticCalculator statcalc = new LineStatisticCalculator(line);
                statcalc.setIdFk(fileId);
                sql.append(getInsertByFileStatistic(statcalc.getFileStatistic()));
                ++rowsCounter;
                if(rowsCounter >= maxinLineInMemory){
                    synchronized (this) {
                        fileStatisticService.add(sql);
                    }
                    rowsCounter = 0;
                    sql.setLength(0);
                }
            }
            if(rowsCounter > 0)
                synchronized (this) {
                    fileStatisticService.add(sql);
                }
        } catch (IOException ex) {
            log.error(ex);
        }finally{
            if(br != null)
                br.close();
        }
    }
   
    private class FileRunnable implements Runnable {
        private File file;

        public FileRunnable(File file) {
            this.file = file;
        }

        @Override
        public void run() {
            try {
                log.info("Start processing read file: " + file.getName());
                processFile(file);
                log.info("End processing read file: " + file.getName());
             
            } catch (Throwable e) {
                log.error("Exception when reading file " + file.getName(), e);
                
            }
        }
    }
    @PreDestroy
    public void shutDown() {
        log.info("~ TheadPool from FilesListener was shutdowned.");
        pool.shutdown();
    }
}
