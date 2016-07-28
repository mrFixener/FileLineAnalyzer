/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileanalyzer.analitics;

import com.fileanalyzer.domain.FileStatistic;
import com.fileanalyzer.util.LineStatisticCalculator;
import java.util.Arrays;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.springframework.web.util.HtmlUtils;

/**
 *
 * @author Dimon
 */
public class StatisticCalculatorTest {
    private static final String CONONIC_STR = "Create Hiberante mapping  for tables";
    private static final Logger log = Logger.getLogger(StatisticCalculatorTest.class);
    private LineStatisticCalculator statCalc = new LineStatisticCalculator(CONONIC_STR);
    public StatisticCalculatorTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    @Ignore
    public void statisticCalculatorTest(){
        FileStatistic canonicfileStatistic = new FileStatistic(statCalc.getLine(),
                                  9l, 3l, 36l, new Double(31/5));
        log.info(canonicfileStatistic);
        
        FileStatistic fileStatistic = statCalc.getFileStatistic();
        log.info(fileStatistic);
        assertEquals("Canonic FileStatistic object must be equals calculated object statistics", canonicfileStatistic, fileStatistic);
    }
    
    @Test
    public void test2(){
        FileStatistic canonic =  new LineStatisticCalculator("# Pattern to output the caller's file name and line number.").getFileStatistic();
        log.info(canonic);
        FileStatistic notCanonic =  new LineStatisticCalculator(HtmlUtils.htmlUnescape("# Pattern to output the caller&#39;s file name and line number.")).getFileStatistic();
        log.info(notCanonic);
        assertEquals(canonic, notCanonic);
    }
}
