package com.helpme.app.engine.renderer.base;

import com.helpme.app.utils.ResourceLoader;

import java.io.File;

import static org.lwjgl.opengl.GL20.*;

/**
 * Authored by Olle on 2017-04-05.
 */

public class Shader {
    private final String SHADERPATH = new File("").getAbsolutePath() + "/src/main/java/com/helpme/app/engine/renderer/shaders/";

    private int shaderProgram;

    public int getShaderProgram() {
        return shaderProgram;
    }

    public Shader(String vertexShaderPath, String fragmentShaderPath) {
        createShaderProgram();

        createVertexShader(vertexShaderPath);
        createFragmentShader(fragmentShaderPath);

        linkProgram();
    }

    private void createShaderProgram() {
        shaderProgram = glCreateProgram();

        if(shaderProgram == 0) {
            System.err.println("Failed to create a shader program!");
            System.exit(1);
        }
    }

    public void useProgram() {
        glUseProgram(shaderProgram);
    }

    private void linkProgram() {
        glLinkProgram(shaderProgram);

        if(glGetProgrami(shaderProgram, GL_LINK_STATUS) == 0) {
            System.err.println("LINKING_ERROR::" + glGetProgramInfoLog(shaderProgram, 1024));
            System.exit(1);
        }
    }

    private String readShader(String fileName) {
        return ResourceLoader.readFile(SHADERPATH + fileName);
    }

    private void createVertexShader(String fileName) {
        createShader(fileName, GL_VERTEX_SHADER);
    }

    private void createFragmentShader(String fileName) {
        createShader(fileName, GL_FRAGMENT_SHADER);
    }

    private void createShader(String fileName, int shaderType) {
        String source = readShader(fileName);

        int shader = glCreateShader(shaderType);

        if(shader == 0) {
            System.err.println("CREATE_SHADER_ERROR:: shader creation failed for type: " + shaderType);
            System.exit(1);
        }

        glShaderSource(shader, source);
        glCompileShader(shader);

        if(glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
            System.err.println("SHADER_ERROR:: " + glGetShaderInfoLog(shader,1024));
            System.exit(1);
        }

        glAttachShader(shaderProgram, shader);
    }
}
