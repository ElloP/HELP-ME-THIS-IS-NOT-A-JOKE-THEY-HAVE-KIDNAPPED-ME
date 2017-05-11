package com.helpme.app.engine.game;

import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.renderer.base.*;
import com.helpme.app.engine.utils.TextureLoader;
import com.helpme.app.utils.mathl.Matrix4f;
import com.helpme.app.utils.mathl.Vector3f;

/**
 * Authored by Olle on 2017-05-09.
 */

//TODO(Olle):Maybe better name
public class NPCView extends GameObject {
    private Mesh mesh;
    private Shader shader;
    Matrix4f modelMatrix;

    private static Texture texture = TextureLoader.loadTexture("default.png");

    private static Vertex[] vertices = new Vertex[] {
            new Vertex(-3.0f, -1.0f,  3.0f, 0.0f, 0.0f),
            new Vertex(3.0f, -1.0f,  3.0f, 1.0f, 0.0f),
            new Vertex(3.0f,  3.0f,  3.0f, 1.0f, 1.0f),
            new Vertex(-3.0f,  3.0f,  3.0f, 0.0f,1.0f)
    };

    private static int[] indices = new int[] {
            2, 1, 0,
            0, 3, 2
    };

    public NPCView() {
        modelMatrix = new Matrix4f();
        this.mesh = new Mesh(vertices, indices);
        this.shader = DefaultShader.getDefaultShader();
    }

    private void setModelMatrix(Camera camera) {
        modelMatrix.billboard(transform.getPosition(),camera.getPosition(), Vector3f.UP);
    }
    //TODO(Olle): decide how to represent model rotation in npcs
    @Override
    public void draw(Camera camera) {
        setModelMatrix(camera);
        shader.updateUniforms(modelMatrix, transform, camera);
        texture.bind();
        shader.useProgram();
        mesh.draw();
    }
}
