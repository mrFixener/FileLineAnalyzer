/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileanalyzer.domain;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Dimon
 */
public class Files {
    private Long id;
    private String fileName;
    private Date procDate = new Date();

    public static class FilesFieldsKey{
    public static final String ID = "ID", FILENAME = "FILENAME", PROCDATE = "PROCDATE", TABLE="PUBLIC.FILES";
    }
    public Files(){}
    public Files(Long id, String fileName, Date procDate) {
        this.id = id;
        this.fileName = fileName;
        this.procDate = procDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getProcDate() {
        return procDate;
    }

    public void setProcDate(Date procDate) {
        this.procDate = procDate;
    }

    @Override
    public String toString() {
        return "Files{" + "id=" + id + ", fileName=" + fileName + ", procDate=" + procDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.fileName);
        hash = 67 * hash + Objects.hashCode(this.procDate);
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
        final Files other = (Files) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.fileName, other.fileName)) {
            return false;
        }
        if (!Objects.equals(this.procDate, other.procDate)) {
            return false;
        }
        return true;
    }
    
}
