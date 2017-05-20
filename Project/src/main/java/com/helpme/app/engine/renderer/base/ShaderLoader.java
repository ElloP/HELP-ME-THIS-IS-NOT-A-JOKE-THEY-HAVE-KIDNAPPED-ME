package com.helpme.app.engine.renderer.base;

import com.helpme.app.engine.renderer.exceptions.ShaderLoadingException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Authored by Olle on 2017-04-04.
 */
public class ShaderLoader {
    public static String readShader(String fileName) throws ShaderLoadingException {
        StringBuilder fileText = new StringBuilder();
        try {
            BufferedReader fileReader = null;
            try {
                fileReader = new BufferedReader(new FileReader(fileName));

                String line;

                boolean readingFile = true;

                while (readingFile) {
                    line = fileReader.readLine();
                    if (line != null) {
                        fileText.append(line).append("\n");
                    } else {
                        readingFile = false;
                    }
                }
            } catch (IOException e) {
                throw new ShaderLoadingException("IOException in ShaderLoad.readShader");
            } finally {
                fileReader.close();
            }
        } catch (IOException e) {
            System.err.println("IOException in ShaderLoad.readShader when trying to close fileReader::" + e.getMessage());
        }
        return fileText.toString();
    }
}
