package com.helpme.app.engine.game;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.renderer.base.*;

/**
 * Authored by Olle on 2017-05-02.
 */
public class Quad extends GameObject {
    private Mesh3D mesh3D;
    private Shader shader;
    private Texture texture;

    public Quad(Texture texture, int[] indices, Vertex3D[] vertices) {
        this(texture, DefaultShader.getDefaultShader(), indices, vertices);
    }

    public Quad(Texture texture, Shader shader, int[] indices, Vertex3D[] vertices) {
        this.mesh3D = new Mesh3D(vertices, indices);
        this.shader = shader;
        this.texture = texture;
    }

    @Override
    public void draw(ICamera camera) {
        shader.useProgram();
        shader.updateUniforms(transform, camera);
        texture.bind();
        mesh3D.draw();
        texture.unBind();
    }
}
