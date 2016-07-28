/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileanalyzer.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dimon
 */
public final class Helpers {
    public static String getSuffixUserFileName(){
        return new SimpleDateFormat("_yyyy-MM-dd=hh^mm^ss.ms").format(new Date());
    }
}
