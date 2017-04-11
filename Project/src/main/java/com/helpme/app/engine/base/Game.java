package com.helpme.app.engine.base;

import com.helpme.app.engine.renderer.base.Mesh;
import com.helpme.app.engine.renderer.base.Shader;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;

/**
 * Created by Olle on 2017-04-05.
 */
public class Game {
    private Mesh mesh;

    private Shader shader;

    private float[] vertices = new float[] { //NOTE(Olle): Testing triangle
            -0.5f, -0.5f, 0.0f,
            -0.5f, 0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            0.5f, 0.5f, 0.0f
    };

    private int[] indices = new int[] {
            0,1,2,
            3,2,1
    };

    public Game() {
        mesh = new Mesh(vertices, indices);
        shader = new Shader("vertexShader.vs", "fragmentShader.fs");
        shader.addUniform("test");
    }

    public void input() {
        if(InputHandler.isKeyboardKeyPress(GLFW_KEY_A))
            System.out.println("A PRESSED!");
        if(InputHandler.isKeyboardKeyRelease(GLFW_KEY_A))
            System.out.println("A Release!");
    }

    float test = 0.0f;

    public void update() {
        //TODO(Olle): update game
        test += Time.deltaTime;
        shader.setUniform("test", (float) Math.abs(Math.sin(test)));
    }

    public void draw() {
        shader.useProgram();
        mesh.draw();
    }
}
