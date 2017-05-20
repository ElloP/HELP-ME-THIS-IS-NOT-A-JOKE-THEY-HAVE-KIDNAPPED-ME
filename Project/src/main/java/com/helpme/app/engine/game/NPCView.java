package com.helpme.app.engine.game;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.renderer.base.*;
import com.helpme.app.engine.renderer.base.TextureLoader;
import com.helpme.app.utils.mathl.Matrix4f;
import com.helpme.app.utils.mathl.Vector3f;

/**
 * Authored by Olle on 2017-05-09.
 */

//TODO(Olle):Maybe better name
public class NPCView extends GameObject {
    private Mesh mesh;
    private Shader shader;
    private Matrix4f modelMatrix;

    private static Texture texture = TextureLoader.loadTexture("default.png");

    private static Vertex[] vertices = new Vertex[] {
            new Vertex(-1.5f, -1.0f,  0.0f, 0.0f, 0.0f),
            new Vertex(1.5f, -1.0f,  0.0f, 1.0f, 0.0f),
            new Vertex(1.5f,  2.0f,  0.0f, 1.0f, 1.0f),
            new Vertex(-1.5f,  2.0f,  0.0f, 0.0f,1.0f)
    };

    private static int[] indices = new int[] {
            2, 1, 0,
            0, 3, 2
    };

    public NPCView(float x, float y) {
        modelMatrix = new Matrix4f();
        this.mesh = new Mesh(vertices, indices);
        this.shader = DefaultShader.getDefaultShader();
        this.transform.setPosition(-6f * x, 0, 6f * y);
    }

    private void setModelMatrix(ICamera camera) {
        modelMatrix.billboard(transform.getPosition(),camera.getPosition(), Vector3f.UP);
    }
    //TODO(Olle): decide how to represent model rotation in npcs
    @Override
    public void draw(ICamera camera) {
        shader.useProgram();
        setModelMatrix(camera);
        shader.updateUniforms(modelMatrix, transform, camera);
        texture.bind();
        mesh.draw();
    }
}
