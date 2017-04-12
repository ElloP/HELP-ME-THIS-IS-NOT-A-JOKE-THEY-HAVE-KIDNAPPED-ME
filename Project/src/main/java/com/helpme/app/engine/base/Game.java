package com.helpme.app.engine.base;

import com.helpme.app.engine.renderer.base.Mesh;
import com.helpme.app.engine.renderer.base.Shader;
import com.helpme.app.utils.mathl.Matrix4f;
import com.helpme.app.utils.mathl.Vector3f;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;

/**
 * Authored by Olle on 2017-04-05.
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
        shader.addUniform("transform");
    }

    public void input() {
        if(InputHandler.isKeyboardKeyPress(GLFW_KEY_A))
            System.out.println("A PRESSED!");
        if(InputHandler.isKeyboardKeyRelease(GLFW_KEY_A))
            System.out.println("A Release!");
    }

    float test = 0.0f;
    Transform t = new Transform();

    public void update() {
        //TODO(Olle): update game
        test += Time.deltaTime;
        t.rotate(0,0, 90*(float) Math.cos(test));
        t.setPosition((float) Math.sin(test), 0.0f, 0.0f);
        t.scale((float) Math.sin(test));
        t.getTransformMatrix().logMatrix();
        shader.setUniform("test", (float) Math.abs(Math.sin(test)));
        shader.setUniform("transform", t.getTransformMatrix());

    }

    public void draw() {
        shader.useProgram();
        mesh.draw();
    }
}
