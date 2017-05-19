package com.helpme.app.engine.renderer.base;

import com.helpme.app.engine.renderer.base.Texture;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Authored by Olle on 2017-04-04.
 */
public class ShaderLoader {
    public static String readShader(String fileName) {
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
        } catch (IOException e) {
            System.err.print("IOException in ShaderLoader.readShader::");
            e.printStackTrace();
            System.exit(1);
        }

        return fileText.toString();
    }
}
