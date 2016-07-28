/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileanalyzer.dao;

import com.fileanalyzer.domain.Files;
import java.util.List;

/**
 *
 * @author Dimon
 */
public interface FilesDAO extends CommonDAO{
    public void add(Files fileInDb);

    public void add(List<Files> fileInDb);
    
    public void add(StringBuilder fStat);
    
    public void update(Files fileInDb);

    public void delete(Files fileInDb);

    public Files getFileById(Long id);
    
    public List<Files> getAll();
}
