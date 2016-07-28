/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileanalyzer.service;

import com.fileanalyzer.dao.FileStatisticDAO;
import com.fileanalyzer.domain.FileStatistic;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dimon
 */
@Service
public class FileStatisticService {
    @Autowired
    FileStatisticDAO fileStatisticDAO;
    private static final Logger log = Logger.getLogger(FileStatisticService.class);
    
    public void add(FileStatistic fStat) {
        if(fileStatisticDAO == null)
            return;
        fileStatisticDAO.add(fStat);
    }

    
    public void add(StringBuilder fStat) {
        if (fileStatisticDAO == null) {
            return;
        }
        fileStatisticDAO.add(fStat);
    }

    
    public void add(List<FileStatistic> fStat) {
        if(fStat == null) return;
        for(FileStatistic fs : fStat)
            add(fs);
    }
    
    public void update(FileStatistic fStat) {
        if (fStat == null
                || fStat.getId() == null) {
            return;
        }
        fileStatisticDAO.update(fStat);
    }

    
    public void delete(FileStatistic fStat) {
        if (fStat == null
                || fStat.getId() == null) {
            return;
        }
        fileStatisticDAO.delete(fStat);
    }

    
    public FileStatistic getFileStatisticById(Long id) {
        if (id == null) {
            return new FileStatistic();
        }
        return fileStatisticDAO.getFileStatisticById(id);
    }
    
    public Long getMaxId() {
        return fileStatisticDAO.getMaxId();
    }

}
