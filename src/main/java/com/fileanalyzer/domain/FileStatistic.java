/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileanalyzer.domain;

import java.util.Objects;

/**
 *
 * @author Dimon
 */
public class FileStatistic {
    private Long id;
    private Files fileInDB;
    private Long fileId;
    private String line;
    private Long maxWord;
    private Long minWord;
    private Long lengthLine; 
    private Double avgWord;

    public static class FileStatisticKey{
    public static final String ID="ID", FILEID="FILEID", LINE="LINE", MAXWORD="MAXWORD", 
            MINWORD="MINWORD", LENGTHLINE="LENGTHLINE", AVGWORD="AVGWORD", TABLE="PUBLIC.FILESTATISTIC";
    }
    public FileStatistic(){}
    public FileStatistic(String line, Long maxWord, Long minWord, Long lengthLine, Double avgWord) {
        this.line = line;
        this.maxWord = maxWord;
        this.minWord = minWord;
        this.lengthLine = lengthLine;
        this.avgWord = avgWord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public Files getFileInDB() {
        return fileInDB;
    }

    public void setFileInDB(Files fileInDB) {
        this.fileInDB = fileInDB;
    }
    
    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Long getMaxWord() {
        return maxWord;
    }

    public void setMaxWord(Long maxWord) {
        this.maxWord = maxWord;
    }

    public Long getMinWord() {
        return minWord;
    }

    public void setMinWord(Long minWord) {
        this.minWord = minWord;
    }

    public Long getLengthLine() {
        return lengthLine;
    }

    public void setLengthLine(Long lengthLine) {
        this.lengthLine = lengthLine;
    }

    public Double getAvgWord() {
        return avgWord;
    }

    public void setAvgWord(Double avgWord) {
        this.avgWord = avgWord;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.fileInDB);
        hash = 29 * hash + Objects.hashCode(this.fileId);
        hash = 29 * hash + Objects.hashCode(this.line);
        hash = 29 * hash + Objects.hashCode(this.maxWord);
        hash = 29 * hash + Objects.hashCode(this.minWord);
        hash = 29 * hash + Objects.hashCode(this.lengthLine);
        hash = 29 * hash + Objects.hashCode(this.avgWord);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileStatistic other = (FileStatistic) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.fileInDB, other.fileInDB)) {
            return false;
        }
        if (!Objects.equals(this.fileId, other.fileId)) {
            return false;
        }
        if (!Objects.equals(this.line, other.line)) {
            return false;
        }
        if (!Objects.equals(this.maxWord, other.maxWord)) {
            return false;
        }
        if (!Objects.equals(this.minWord, other.minWord)) {
            return false;
        }
        if (!Objects.equals(this.lengthLine, other.lengthLine)) {
            return false;
        }
        if (!Objects.equals(this.avgWord, other.avgWord)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FileStatistic{" + "id=" + id + ", fileInDB=" + fileInDB + ", fileId=" + fileId + ", maxWord=" + maxWord + ", minWord=" + minWord + ", lengthLine=" + lengthLine + ", avgWord=" + avgWord + '}';
    }

    
}
