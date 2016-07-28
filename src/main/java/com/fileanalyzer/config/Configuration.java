/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileanalyzer.config;

import java.util.Objects;

/**
 *
 * @author Dimon
 */
public class Configuration {
    private String fileMask;
    private String numPoolThread;
    private String wrongParams;
    private  String url;    
    private  String driverName;   
    private  String usernsme;   
    private  String pasword;
    private Integer numFlush;
    public Configuration(String fileMask, String numPoolThread) {
        this.fileMask = fileMask;
        this.numPoolThread = numPoolThread;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUsernsme() {
        return usernsme;
    }

    public void setUsernsme(String usernsme) {
        this.usernsme = usernsme;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    
    public String getWrongParams() {
        return wrongParams;
    }

    public void setWrongParams(String wrongParams) {
        this.wrongParams = wrongParams;
    }

    public String getFileMask() {
        return fileMask;
    }

    public void setFileMask(String fileMask) {
        this.fileMask = fileMask;
    }

    public String getNumPoolThread() {
        return numPoolThread;
    }

    public void setNumPoolThread(String numPoolThread) {
        this.numPoolThread = numPoolThread;
    }

    public Integer getNumFlush() {
        return numFlush;
    }

    public void setNumFlush(Integer numFlush) {
        this.numFlush = numFlush;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.fileMask);
        hash = 37 * hash + Objects.hashCode(this.numPoolThread);
        hash = 37 * hash + Objects.hashCode(this.url);
        hash = 37 * hash + Objects.hashCode(this.driverName);
        hash = 37 * hash + Objects.hashCode(this.usernsme);
        hash = 37 * hash + Objects.hashCode(this.pasword);
        hash = 37 * hash + Objects.hashCode(this.numFlush);
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
        final Configuration other = (Configuration) obj;
        if (!Objects.equals(this.fileMask, other.fileMask)) {
            return false;
        }
        if (!Objects.equals(this.numPoolThread, other.numPoolThread)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.driverName, other.driverName)) {
            return false;
        }
        if (!Objects.equals(this.usernsme, other.usernsme)) {
            return false;
        }
        if (!Objects.equals(this.pasword, other.pasword)) {
            return false;
        }
        if (!Objects.equals(this.numFlush, other.numFlush)) {
            return false;
        }
        return true;
    }

    
   
    
}
