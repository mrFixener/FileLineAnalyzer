/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileanalyzer.util;

import com.fileanalyzer.domain.FileStatistic;
import com.fileanalyzer.domain.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Dimon
 */
public final class SqlGenerator {
   private static Map<String, Object> getParamsByFileStatistic(final FileStatistic fStat){
       Map<String, Object> params = new HashMap<>();
       if (fStat.getFileId()!= null) {
           params.put(FileStatistic.FileStatisticKey.FILEID, fStat.getFileId());
       }
       if (fStat.getLine() != null) {
           params.put(FileStatistic.FileStatisticKey.LINE, fStat.getLine());
       }
       if (fStat.getLengthLine() != null) {
           params.put(FileStatistic.FileStatisticKey.LENGTHLINE, fStat.getLengthLine());
       }
       if (fStat.getAvgWord() != null) {
           params.put(FileStatistic.FileStatisticKey.AVGWORD, fStat.getAvgWord());
       }
       if (fStat.getMinWord() != null) {
           params.put(FileStatistic.FileStatisticKey.MINWORD, fStat.getMinWord());
       }
       if (fStat.getMaxWord() != null) {
           params.put(FileStatistic.FileStatisticKey.MAXWORD, fStat.getMaxWord());
       }
       return params;
   }
   private static Map<String, Object> getParamsByFiles(final Files fStat){
       Map<String, Object> params = new HashMap<>();
       if (fStat.getFileName() != null) {
           params.put(Files.FilesFieldsKey.FILENAME, fStat.getFileName());
       }
       if (fStat.getProcDate()!= null) {
           params.put(Files.FilesFieldsKey.PROCDATE, fStat.getProcDate());
       }
       return params;
   }
   public static String getUpdateByFileStatistic(FileStatistic fStat){
       StringBuilder sql = new StringBuilder();
       if (fStat == null) {
           return sql.toString();
       }
       Map<String, Object> params = getParamsByFileStatistic(fStat);
       /*Map<String, Object> params = new HashMap<>();

       if (fStat.getLine() != null) {
           params.put(FileStatistic.FileStatisticKey.LINE, fStat.getLine());
       }
       if (fStat.getLengthLine() != null) {
           params.put(FileStatistic.FileStatisticKey.LENGTHLINE, fStat.getLengthLine());
       }
       if (fStat.getAvgWord() != null) {
           params.put(FileStatistic.FileStatisticKey.AVGWORD, fStat.getAvgWord());
       }
       if (fStat.getMinWord() != null) {
           params.put(FileStatistic.FileStatisticKey.MINWORD, fStat.getMinWord());
       }
       if (fStat.getMaxWord() != null) {
           params.put(FileStatistic.FileStatisticKey.MAXWORD, fStat.getMaxWord());
       }*/
       
       sql.append("UPDATE ").append(FileStatistic.FileStatisticKey.TABLE).append(" SET ");
       genValues(sql,params, fStat.getId(), true);
       //new StatisticCalculator("").genParamAndValues(sql,params);
       return sql.toString();
   } 
    public static String getUpdateByFiles(final Files fStat){
       StringBuilder sql = new StringBuilder();
       if (fStat == null) {
           return sql.toString();
       }
       Map<String, Object> params = getParamsByFiles(fStat);
       /*if (fStat.getFileName() != null) {
           params.put(Files.FilesFieldsKey.FILENAME, fStat.getFileName());
       }
       if (fStat.getProcDate()!= null) {
           params.put(Files.FilesFieldsKey.PROCDATE, fStat.getProcDate());
       }*/
       
       sql.append("UPDATE ").append(Files.FilesFieldsKey.TABLE).append(" SET ");
       genValues(sql,params, fStat.getId(), true);
       //new StatisticCalculator("").genParamAndValues(sql,params);
       return sql.toString();
   } 
    
    public static String getInsertByFileStatistic(FileStatistic fStat){
       StringBuilder sql = new StringBuilder();
       if (fStat == null) {
           return sql.toString();
       }
       Map<String, Object> params = getParamsByFileStatistic(fStat);
       
       sql.append("INSERT INTO ").append(FileStatistic.FileStatisticKey.TABLE).append(" ");
       //genValues(sql,params, fStat.getId(), false);
       new LineStatisticCalculator("").genParamAndValues(sql,params);
       return sql.toString();
   } 
    public static String getInsertByFiles(final Files fStat){
       StringBuilder sql = new StringBuilder();
       if (fStat == null) {
           return sql.toString();
       }
       Map<String, Object> params = getParamsByFiles(fStat);
       
       sql.append("INSERT INTO ").append(Files.FilesFieldsKey.TABLE).append(" ");;
       //genValues(sql,params, fStat.getId(), false);
       new LineStatisticCalculator("").genParamAndValues(sql,params);
       return sql.toString();
   } 
    
    
   private static void genValues(final StringBuilder sql,final Map<String, Object> params, Long id, boolean showWhere){
        if(params == null)
            return;
        Set<String> keySet = params.keySet();
        Iterator<String> iterator = keySet.iterator();
        for(int i = 0; iterator.hasNext(); ++i){
            String key  = iterator.next();
            if(params.get(key) instanceof String){
                sql.append(key).append("='").append(params.get(key)).append("'");
            }else if(params.get(key) instanceof Date)
                sql.append(key).append("='").append(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.ms").format(new Date(System.currentTimeMillis()))).append("'");
             else sql.append(key).append("=").append(params.get(key));
            if(i < params.size()-1){
                sql.append(",");
            }
        }
        if(id != null && showWhere)
           sql.append(" where id=").append(id); 
        sql.append(";\n");
        //sql.append("\n");
    }
     
    public static void ResultSetToFileStatistic(final ResultSet rs, final FileStatistic fs) throws SQLException{
        if (rs.next()) {
            fs.setId(rs.getLong(FileStatistic.FileStatisticKey.ID));
            fs.setAvgWord(rs.getDouble(FileStatistic.FileStatisticKey.AVGWORD));
            fs.setFileId(rs.getLong(FileStatistic.FileStatisticKey.FILEID));
            fs.setLengthLine(rs.getLong(FileStatistic.FileStatisticKey.LENGTHLINE));
            fs.setLine(rs.getString(FileStatistic.FileStatisticKey.LINE));
            fs.setMaxWord(rs.getLong(FileStatistic.FileStatisticKey.MAXWORD));
            fs.setMinWord(rs.getLong(FileStatistic.FileStatisticKey.MINWORD));
        }
    }
    
    public static void ResultSetToFiles(final ResultSet rs, final Files fs) throws SQLException {
        if (rs.next()) {
            fs.setId(rs.getLong(Files.FilesFieldsKey.ID));
            fs.setFileName(rs.getString(Files.FilesFieldsKey.FILENAME));
            fs.setProcDate(rs.getDate(Files.FilesFieldsKey.PROCDATE));
        }
    }
    
    public static void ResultSetToListFiles(final ResultSet rs, final List<Files> fs) throws SQLException {
        while (rs.next()) {
            Files dbFile = new Files();
            dbFile.setId(rs.getLong(Files.FilesFieldsKey.ID));
            dbFile.setFileName(rs.getString(Files.FilesFieldsKey.FILENAME));
            dbFile.setProcDate(rs.getDate(Files.FilesFieldsKey.PROCDATE));
            fs.add(dbFile);
        }
    }
}
