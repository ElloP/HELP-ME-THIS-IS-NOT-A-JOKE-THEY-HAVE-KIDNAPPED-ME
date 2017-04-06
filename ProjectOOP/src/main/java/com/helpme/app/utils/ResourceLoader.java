package com.helpme.app.utils;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Authored by Olle on 2017-04-04.
 */
public class ResourceLoader {

    public static String readFile(String fileName) {
        StringBuilder fileText = new StringBuilder();
        BufferedReader fileReader = null;

        try {
            fileReader = new BufferedReader(new FileReader(fileName));

            String line;

            boolean readingFile = true;

            while(readingFile) {
                line = fileReader.readLine();
                if(line != null) {
                    fileText.append(line).append("\n");
                }
                else {
                    readingFile = false;
                }
            }
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return fileText.toString();
    }
}
