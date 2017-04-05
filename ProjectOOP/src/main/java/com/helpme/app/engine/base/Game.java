package com.helpme.app.engine.base;

import com.helpme.app.engine.renderer.base.Mesh;
import com.helpme.app.engine.renderer.base.Shader;

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

    double test = 0.0f;

    public Game() {
        shader = new Shader("vertexShader.vs", "fragmentShader.fs");
        mesh = new Mesh(vertices, indices);
        shader.addUniform("test");
    }

    public void input() {
        //TODO(Olle): Handle input
    }

    public void update() {
        //TODO(Olle): update game

        test += Time.deltaTime;

        shader.setUniform("test", (float) Math.sin(test));
    }

    public void draw() {
        shader.useProgram();
        mesh.draw();
    }
}
