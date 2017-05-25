package com.helpme.app.game.view;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.renderer.base.*;
import com.helpme.app.utils.mathl.Matrix4f;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.mathl.Vector3f;

/**
 * Authored by Olle on 2017-05-09.
 */

//TODO(Olle):Maybe better name
public class BodyView extends GameObject {
    private Mesh3D mesh3D;
    private Shader shader;
    private Matrix4f modelMatrix;

    private static Texture texture = TextureLoader.loadTexture("default.png");

    private static Vertex3D[] vertices = new Vertex3D[] {
            new Vertex3D(-1.5f, -1.0f,  0.0f, 0.0f, 0.0f),
            new Vertex3D(1.5f, -1.0f,  0.0f, 1.0f, 0.0f),
            new Vertex3D(1.5f,  2.0f,  0.0f, 1.0f, 1.0f),
            new Vertex3D(-1.5f,  2.0f,  0.0f, 0.0f,1.0f)
    };

    private static int[] indices = new int[] {
            2, 1, 0,
            0, 3, 2
    };

    public BodyView(float x, float y) {
        modelMatrix = new Matrix4f();
        this.mesh3D = new Mesh3D(vertices, indices);
        this.shader = DefaultShader.getDefaultShader();
        this.transform.setPosition(-6f * x, 0, 6f * y);
    }

    public BodyView() {
        this(0, 0);
    }

    public void setPosition(Vector2f position) {
        this.transform.setPosition(-position.x * 6, 0, 6 * position.y);
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
        mesh3D.draw();
    }
}
