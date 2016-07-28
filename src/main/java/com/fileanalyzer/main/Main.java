/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileanalyzer.main;

import com.fileanalyzer.FileAnalyzer;
import com.fileanalyzer.config.Configuration;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Dimon
 */
public class Main {
    private static final Logger log = Logger.getLogger(Main.class);
    public static void main(String[] args) throws IOException {
        FileAnalyzer fl = null;
        try {               
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
            Configuration conf = (Configuration) ctx.getBean("configuration");
            fl = (FileAnalyzer) ctx.getBean("fileAnalyzer");
            if (args.length == 3
                    && args[1].equalsIgnoreCase("s")) {
                log.info("Added file id: "+fl.analizeString(args[0], args[2]));
            } else if (args.length == 2
                    && args[1].equalsIgnoreCase("a")) {
                fl.analizeFile(args[0], true);
            } else if(args.length == 1)
                fl.analizeFile(args[0], false);
            else {
                log.info(conf.getWrongParams());
                Thread.sleep(3000);
                System.exit(1);
            }
            log.info("Press any key to exit...");
            System.in.read();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fl != null) {
                fl.shutDown();
            }
            System.exit(0);
        }
    }
}
