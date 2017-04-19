package com.helpme.app.engine.base;

import com.helpme.app.engine.input.InputHandler;
import com.helpme.app.engine.input.InputKey;
import com.helpme.app.engine.input.KeyState;
import com.helpme.app.engine.renderer.base.*;
import com.helpme.app.engine.utils.TextureLoader;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.mathl.Matrix4f;
import com.helpme.app.utils.mathl.Quaternion;

/**
 * Authored by Olle on 2017-04-05.
 */
public class Game {
    private Mesh mesh;

    private Shader shader;

    public Game() {
        mesh = new Cube();
        shader = new Shader("vertexShader.vs", "fragmentShader.fs");
        shader.addUniform("model");
        shader.addUniform("projection");
        shader.addUniform("view");
    }

    public void input() {
        testingCameraInput();
    }

    float test = 0.0f;
    Transform t = new Transform();
    Matrix4f perspective = Transform.getPerspectiveMatrix(70f, Window.width, Window.height, 0.1f, 1000);
    Camera c = new Camera();
    Quaternion q;
    Texture texture = TextureLoader.loadTexture("default.png");

    public void update() {
        //TODO(Olle): update game
        test += Time.deltaTime;

        q = new Quaternion().rotate(test,test,0);

        t.rotate(q);
        t.setPosition(t.getPosition().x(), t.getPosition().y(), -5.0f);
        shader.setUniform("projection", perspective);
        shader.setUniform("model", t.getTransformMatrix());
        shader.setUniform("view", c.getViewMatrix());
    }

    public void draw() {
        shader.useProgram();
        texture.bind();
        mesh.draw();
    }

    private void testingCameraInput() {
        float movAmt = (float) (10 * Time.deltaTime);
        float rotAmt = (float) (10 * Time.deltaTime);

        if(InputHandler.isKeyboardKeyDown(InputKey.MoveForward))
            c.moveForward(movAmt);
        if(InputHandler.isKeyboardKeyDown(InputKey.MoveLeft))
            c.moveLeft(movAmt);
        if(InputHandler.isKeyboardKeyDown(InputKey.MoveRight))
            c.moveRight(movAmt);
        if(InputHandler.isKeyboardKeyDown(InputKey.MoveBackward))
            c.moveBackward(movAmt);
        if(InputHandler.isKeyboardKeyDown(InputKey.RotateLeft))
            c.rotate(0.0f, rotAmt, 0.0f);
        if(InputHandler.isKeyboardKeyDown(InputKey.RotateRight))
            c.rotate(0.0f, -rotAmt, 0.0f);
    }
}
