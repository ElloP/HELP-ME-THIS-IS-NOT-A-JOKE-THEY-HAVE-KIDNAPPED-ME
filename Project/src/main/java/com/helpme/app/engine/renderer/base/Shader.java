package com.helpme.app.engine.renderer.base;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.Transform;
import com.helpme.app.engine.renderer.exceptions.ShaderCreationException;
import com.helpme.app.engine.renderer.exceptions.ShaderLinkingException;
import com.helpme.app.engine.renderer.exceptions.ShaderLoadingException;
import com.helpme.app.engine.renderer.exceptions.ShaderUniformNotFoundException;
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

    private void createShaderProgram() throws ShaderCreationException {
        shaderProgram = glCreateProgram();

        if(shaderProgram == 0) {
            throw new ShaderCreationException("Thrown from Shader.createShaderProgram");
        }
    }

    public void useProgram() {
        glUseProgram(shaderProgram);
    }

    private void linkProgram() {
        try {
            glLinkProgram(shaderProgram);

            if (glGetProgrami(shaderProgram, GL_LINK_STATUS) == 0) {
                throw new ShaderLinkingException("LINKING_ERROR::" + glGetProgramInfoLog(shaderProgram, 1024));
            }
        } catch(ShaderLinkingException e) {
            System.err.println("ShaderLinkingException in Shader.linkProgram::" + e.getMessage());
        }
    }

    public abstract void updateUniforms(Transform transform, ICamera camera);

    public abstract void updateUniforms(Matrix4f model, Transform transform, ICamera camera);

    private String readShader(String fileName) {
        String shaderCode = "";
        try {
            shaderCode = ShaderLoader.readShader(SHADERPATH + fileName);
        } catch (ShaderLoadingException e) {
            System.err.println("ShaderLoadingException in Shader.readShader::" + e.getMessage());
        }
        return shaderCode;
    }

    private void createVertexShader(String fileName) {
        createShader(fileName, GL_VERTEX_SHADER);
    }

    private void createFragmentShader(String fileName) {
        createShader(fileName, GL_FRAGMENT_SHADER);
    }

    private void createShader(String fileName, int shaderType) {
        try {
            String source = readShader(fileName);

            int shader = glCreateShader(shaderType);

            if (shader == 0) {
                throw new ShaderCreationException("CREATE_SHADER_ERROR:: shader creation failed for type: " + shaderType);
            }

            glShaderSource(shader, source);
            glCompileShader(shader);

            if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
                throw new ShaderCreationException("GET_SHADER_ERROR:: " + glGetShaderInfoLog(shader, 1024));
            }

            glAttachShader(shaderProgram, shader);
        } catch (ShaderCreationException e) {
            System.err.println("ShaderCreationException in Shader.createShader::" + e.getMessage());
        }
    }

    // ----------- Shader usage functions -----------
    public void addUniform(String uniform) {
        try {
            int uniformLocation = glGetUniformLocation(shaderProgram, uniform);

            if (uniformLocation == -1) {
                throw new ShaderUniformNotFoundException("UNIFORM_COULD_NOT_BE_FOUND::" + uniform);
            }
            uniforms.put(uniform, uniformLocation);
        } catch (ShaderUniformNotFoundException e) {
            System.err.println("ShaderUniformNotFoundException in Shader.addUniform::" + e.getMessage());
        }
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
        MemoryUtil.memFree(fb);
    }

    public void setUniform(String uniformName, Vector3f value) {
        FloatBuffer fb = MemoryUtil.memAllocFloat(3);
        value.get(fb);
        glUniform3fv(uniforms.get(uniformName), fb);
        MemoryUtil.memFree(fb);
    }
}
