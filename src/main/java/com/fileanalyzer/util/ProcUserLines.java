/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileanalyzer.util;

import com.fileanalyzer.FileAnalyzer;
import com.fileanalyzer.config.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Dimon
 */
public class ProcUserLines {
    public Long proc(final String lines, final String fileNameInDb){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        Configuration conf = (Configuration) ctx.getBean("configuration");
        System.out.println(conf.getNumPoolThread());
        FileAnalyzer fl = (FileAnalyzer) ctx.getBean("fileAnalyzer");
        fl.shutDown();
        return fl.analizeString(lines, fileNameInDb);
    }
  
}
