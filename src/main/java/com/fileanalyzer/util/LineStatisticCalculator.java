/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileanalyzer.util;

import com.fileanalyzer.domain.FileStatistic;
import com.fileanalyzer.domain.FileStatistic.FileStatisticKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.log4j.Logger;
import org.springframework.web.util.HtmlUtils;

/**
 *
 * @author Dimon
 */
public class LineStatisticCalculator {
    private static final Logger log = Logger.getLogger(LineStatisticCalculator.class);
    private final String line; 
    public static final String regexp = "[\\s,;\n\t]+";
    private Long idFk;
    public LineStatisticCalculator(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }
    
    public FileStatistic getFileStatistic(){
        FileStatistic fileStatis = new FileStatistic();
        
        fileStatis.setLengthLine(new Long(line.length()));
        String strArr[] = line.split(regexp);
        TreeSet<Integer> maxWord = new TreeSet();
        TreeSet<Integer> minWord = new TreeSet();
        long sumWords = 0;
        for (int i = 0 ; i < strArr.length; ++i) {
            int strSize = strArr[i].length();
            sumWords += strSize;
            if(i > 0 && i < strArr.length-1)
                maxWord.add(strSize);
            minWord.add(strSize);
        }
        fileStatis.setLine(HtmlUtils.htmlEscape(line));
        if(sumWords > 0){
            fileStatis.setAvgWord(new Double(sumWords/strArr.length));
            fileStatis.setMinWord(new Long(minWord.first()));
        }
        if(maxWord.size()  > 0)
            fileStatis.setMaxWord(new Long(maxWord.last()));
        if(getIdFk() != null)
            fileStatis.setFileId(getIdFk());
        return fileStatis;
    }
    public StringBuilder getSqlInsertFileStatistic(){
        Map<String, Object> params = new HashMap<>();
        StringBuilder sql =new StringBuilder("INSERT INTO "+FileStatistic.FileStatisticKey.TABLE+" ");
        params.put(FileStatisticKey.LENGTHLINE, new Long(line.length()));
        String strArr[] = line.split(regexp);
        TreeSet<Integer> maxWord = new TreeSet();
        TreeSet<Integer> minWord = new TreeSet();
        long sumWords = 0;
        for (int i = 0 ; i < strArr.length; ++i) {
            int strSize = strArr[i].length();
            sumWords += strSize;
            if(i > 0 && i < strArr.length-1)
                maxWord.add(strSize);
            minWord.add(strSize);
        }
        params.put(FileStatisticKey.LINE, HtmlUtils.htmlEscape(line));
        if(sumWords > 0){
            params.put(FileStatisticKey.AVGWORD, new Double(sumWords/strArr.length));
            params.put(FileStatisticKey.MINWORD, new Long(minWord.first()));
        }
        if(maxWord.size()  > 0)
            params.put(FileStatisticKey.MAXWORD, new Long(new Long(maxWord.last())));
        if(getIdFk() != null)
            params.put(FileStatisticKey.FILEID, getIdFk());
        genParamAndValues(sql, params);
        
        return sql;
    }
    public void genParamAndValues(final StringBuilder sql,final Map<String, Object> params){
        if(params == null)
            return;
        Set<String> keySet = params.keySet();
        Iterator<String> iterator = keySet.iterator();
        StringBuilder strParams = new StringBuilder("("),
                      values= new StringBuilder(" VALUES(");
        for(int i = 0; iterator.hasNext(); ++i){
            String key  = iterator.next();
            strParams.append(key);
            if(params.get(key) instanceof String){
                values.append("'").append(params.get(key)).append("'");
            }else if(params.get(key) instanceof Date)
                values.append("'").append(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.ms").format((Date)params.get(key))).append("'");
             else values.append(params.get(key));
            if(i < params.size()-1){
                strParams.append(",");
                values.append(",");
            }
        }
        sql.append(strParams.append(")").append(values.append(");\n")));
    }

    public Long getIdFk() {
        return idFk;
    }

    public void setIdFk(Long idFk) {
        this.idFk = idFk;
    }
}
