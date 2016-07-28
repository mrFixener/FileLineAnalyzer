/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileanalyzer.dao;

import com.fileanalyzer.domain.FileStatistic;
import java.util.List;

/**
 *
 * @author Dimon
 */
public interface FileStatisticDAO extends CommonDAO{
    public void add(FileStatistic fStat);
    
    public void add(StringBuilder fStat);
    
    public void add(List<FileStatistic> fStat);
    
    public void update(FileStatistic fStat);

    public void delete(FileStatistic fStat);
    
    public  FileStatistic getFileStatisticById(Long id);

}
