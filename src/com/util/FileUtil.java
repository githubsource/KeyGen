/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author tranngocdien
 */
public class FileUtil {
    
    public static void writeFile(String file, String txt) {
        try {
             FileWriter fileWriter = new FileWriter(file);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write(txt); 
            bufferedWriter.close();
        } catch(Exception ex) {

        }
    }
}
