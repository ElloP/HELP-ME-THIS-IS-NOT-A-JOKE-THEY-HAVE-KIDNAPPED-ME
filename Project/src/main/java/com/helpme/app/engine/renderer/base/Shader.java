package com.helpme.app.engine.renderer.base;

import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.Transform;
import com.helpme.app.engine.base.Window;
import com.helpme.app.engine.utils.ResourceLoader;
import com.helpme.app.utils.mathl.Matrix4f;
import com.helpme.app.utils.mathl.Vector3f;
import org.lwjgl.system.MemoryUtil;

import java.io.File;
import java.nio.FloatBuffer;
import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;

/**
 * Authored by Olle on 2017-04-05.
 */

public abstract class Shader {
    // ----------- Shader variables -----------

    //TODO(Olle): Find better way of finding the path string
    private final String SHADERPATH = new File("").getAbsolutePath() + "/src/main/java/com/helpme/app/engine/renderer/shaders/";

    private int shaderProgram;
    private HashMap<String, Integer> uniforms;

    public int getShaderProgram() {
        return shaderProgram;
    }

    // ----------- Shader creation functions and constructor -----------
    public Shader(String vertexShaderPath, String fragmentShaderPath) {
        uniforms = new HashMap<>();

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

    public abstract void updateUniforms(Transform transform, Camera camera);

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

    // ----------- Shader usage functions -----------
    public void addUniform(String uniform) {
        int uniformLocation = glGetUniformLocation(shaderProgram, uniform);

        if(uniformLocation == -1) {
            System.err.println("UNIFORM_COULD_NOT_BE_FOUND::" + uniform);
            new Exception().printStackTrace();
            System.exit(1);
        }
        uniforms.put(uniform, uniformLocation);
    }

    public void setUniform(String uniformName, int value) {
        glUniform1i(uniforms.get(uniformName), value);
    }

    public void setUniform(String uniformName, float value) {
        glUniform1f(uniforms.get(uniformName), value);
    }

    public void setUniform(String uniformName, Matrix4f value) {
        FloatBuffer fb = MemoryUtil.memAllocFloat(16);
        value.get(fb);
        glUniformMatrix4fv(uniforms.get(uniformName), false, fb);
    }

    public void setUniform(String uniformName, Vector3f value) {
        FloatBuffer fb = MemoryUtil.memAllocFloat(3);
        value.get(fb);
        glUniform3fv(uniforms.get(uniformName), fb);
    }
}
