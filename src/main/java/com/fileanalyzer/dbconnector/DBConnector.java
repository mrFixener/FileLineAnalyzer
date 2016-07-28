/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileanalyzer.dbconnector;

import com.fileanalyzer.config.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dimon
 */
@Component
public class DBConnector {
    private static Configuration conf;
    private static Logger log = Logger.getLogger(DBConnector.class);
    private static Connection con;

    public static void setConf(Configuration conf) {
        DBConnector.conf = conf;
    }

    public static Connection getConnection() {
        try {
            Class.forName(conf.getDriverName());
            try {
                con = DriverManager.getConnection(conf.getUrl(), conf.getUsernsme(), conf.getPasword());
            } catch (SQLException ex) {
                log.error("Failed to create the database connection.", ex);
            }
        } catch (ClassNotFoundException ex) {
            log.error("Driver not found.", ex);
        }
        return con;
    }
}
