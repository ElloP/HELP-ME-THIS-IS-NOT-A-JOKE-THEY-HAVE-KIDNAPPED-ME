package com.helpme.app.engine.game;

import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.renderer.base.*;
import com.helpme.app.engine.utils.TextureLoader;

/**
 * Authored by Olle on 2017-05-02.
 */
public class Quad extends GameObject {
    private Mesh mesh;
    private Shader shader;
    private Texture texture;

    public Quad(Texture texture, int[] indices, Vertex[] vertices) {
        this(texture, DefaultShader.getDefaultShader(), indices, vertices);
    }

    public Quad(Texture texture, Shader shader, int[] indices, Vertex[] vertices) {
        this.mesh = new Mesh(vertices, indices);
        this.shader = shader;
        this.texture = texture;
    }

    @Override
    public void draw(Camera camera) {
        shader.updateUniforms(transform, camera);
        texture.bind();
        shader.useProgram();
        mesh.draw();
    }
}
