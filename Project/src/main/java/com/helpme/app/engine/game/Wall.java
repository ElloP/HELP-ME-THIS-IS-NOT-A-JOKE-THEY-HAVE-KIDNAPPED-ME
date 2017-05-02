package com.helpme.app.engine.game;

import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.renderer.base.*;
import com.helpme.app.engine.utils.TextureLoader;

/**
 * Authored by Olle on 2017-05-02.
 */
public class Wall extends GameObject {
    private Mesh mesh;
    private Shader shader;
    private static Texture texture = TextureLoader.loadTexture("default.png");

    private static Vertex[] vertices = new Vertex[] {
            new Vertex(-3.0f, -1.0f,  3.0f, 0.0f, 0.0f),
            new Vertex(3.0f, -1.0f,  3.0f, 1.0f, 0.0f),
            new Vertex(3.0f,  3.0f,  3.0f, 1.0f, 1.0f),
            new Vertex(-3.0f,  3.0f,  3.0f, 0.0f,1.0f)
    };

    private static int[] indices = new int[] {
            0, 1, 2,
            2, 3, 0
    };

    public Wall() {
        this.mesh = new Mesh(vertices, indices);
        this.shader = new DefaultShader();
    }

    public Wall(Shader shader) {
        this.mesh = new Mesh(vertices, indices);
        this.shader = shader;
    }

    @Override
    public void draw(Camera camera) {
        shader.updateUniforms(transform, camera);
        texture.bind();
        shader.useProgram();
        mesh.draw();
    }
}
