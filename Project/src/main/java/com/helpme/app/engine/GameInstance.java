package com.helpme.app.engine;

import com.helpme.app.engine.base.*;
import com.helpme.app.engine.input.Input;
import com.helpme.app.engine.input.InputKey;
import com.helpme.app.engine.renderer.base.*;
import com.helpme.app.engine.utils.TextureLoader;
import com.helpme.app.utils.mathl.Matrix4f;
import com.helpme.app.utils.mathl.Quaternion;

/**
 * Authored by Olle on 2017-04-21.
 */
public class GameInstance extends Game {
    private Mesh mesh;

    private Shader shader;

    public GameInstance() {
        mesh = new Cube();
        shader = new DefaultShader();
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
    }

    public void draw() {
        shader.updateUniforms(t, c);
        shader.useProgram();
        texture.bind();
        mesh.draw();
    }

    private void testingCameraInput() {
        float movAmt = (float) (10 * Time.deltaTime);
        float rotAmt = (float) (10 * Time.deltaTime);

        if(Input.isKeyboardKeyDown(InputKey.MoveForward))
            c.moveForward(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.MoveLeft))
            c.moveLeft(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.MoveRight))
            c.moveRight(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.MoveBackward))
            c.moveBackward(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.RotateLeft))
            c.rotate(0.0f, rotAmt, 0.0f);
        if(Input.isKeyboardKeyDown(InputKey.RotateRight))
            c.rotate(0.0f, -rotAmt, 0.0f);
    }
}
