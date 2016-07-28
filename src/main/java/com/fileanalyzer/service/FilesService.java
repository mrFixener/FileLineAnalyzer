/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileanalyzer.service;

import com.fileanalyzer.dao.FilesDAO;
import com.fileanalyzer.dbconnector.DBConnector;
import com.fileanalyzer.domain.Files;
import com.fileanalyzer.util.SqlGenerator;
import static com.fileanalyzer.util.SqlGenerator.ResultSetToFiles;
import static com.fileanalyzer.util.SqlGenerator.ResultSetToListFiles;
import static com.fileanalyzer.util.SqlGenerator.getInsertByFiles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dimon
 */
@Service
public class FilesService {
    @Autowired
    FilesDAO fileDAO;
    private static final Logger log = Logger.getLogger(FilesService.class);
    public Long add(Files fileInDb) {
        if(fileInDb == null)
            return null;
        fileDAO.add(fileInDb);
        return getMaxId();
    }

    public Long add(StringBuilder fileInDb) {
        if(fileInDb == null)
            return null;
        fileDAO.add(fileInDb);    
        return getMaxId();
    }

    
    public Long add(List<Files> fileInDb) {
        if (fileInDb == null)
            return null;
        for(Files f:fileInDb)
            fileDAO.add(f);
        return getMaxId();
    }
    
    public void update(Files fileInDb) {
        if (fileInDb == null) {
            return;
        }
        fileDAO.update(fileInDb);
    }

    
    public void delete(Files fileInDb) {
        if(fileInDb == null || fileInDb.getId() == null)
            return;
        fileDAO.delete(fileInDb);
    }

    
    public Files getFileById(Long id) {
        if(id == null)
            return new Files(); 
        return fileDAO.getFileById(id);
    }

    
    public List<Files> getAll() {
        return fileDAO.getAll();
    }

    
    public Long getMaxId() {
        return fileDAO.getMaxId();
    }
}
