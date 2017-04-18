package com.helpme.app.engine.base;

import com.helpme.app.engine.input.InputHandler;
import com.helpme.app.engine.input.InputKey;
import com.helpme.app.engine.renderer.base.Cube;
import com.helpme.app.engine.renderer.base.Mesh;
import com.helpme.app.engine.renderer.base.Shader;
import com.helpme.app.utils.mathl.Matrix4f;
import com.helpme.app.utils.mathl.Quaternion;
import com.helpme.app.utils.mathl.Vector3f;

/**
 * Authored by Olle on 2017-04-05.
 */
public class Game {
    private Mesh mesh;

    private Shader shader;

    public Game() {
        mesh = new Cube();
        shader = new Shader("vertexShader.vs", "fragmentShader.fs");
        shader.addUniform("test");
        shader.addUniform("transform");
        shader.addUniform("projection");
        shader.addUniform("view");
    }

    public void input() {
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
        /*if(InputHandler.isKeyboardKeyDown(InputKey.RotateLeft))
            c.rotate(0.0f, -rotAmt, 0.0f);
        if(InputHandler.isKeyboardKeyDown(InputKey.RotateRight))
            c.rotate(0.0f, rotAmt, 0.0f);*/
    }

    float test = 0.0f;
    Transform t = new Transform();
    Matrix4f perspective = Transform.getPerspectiveMatrix(70f, Window.width, Window.height, 0.1f, 1000);
    Camera c = new Camera();
    Quaternion q;
    Vector3f v = new Vector3f();

    public void update() {
        //TODO(Olle): update game
        test += Time.deltaTime;

        Vector3f v = new Vector3f((float) Math.sin(Time.getTimeInSeconds()), 0.0f, 0.0f);

        q = new Quaternion().rotate(test, 0.0f, 0.0f);
        c.rotate(new Quaternion().rotate((float)Time.deltaTime, 0.0f, 0.0f));

        t.rotate(q);
        t.setPosition(t.getPosition().x(), t.getPosition().y(), -5.0f);
        t.scale(2.0f);
        //t.getTransformMatrix().logMatrix();
        shader.setUniform("test", test);
        shader.setUniform("projection", perspective);
        shader.setUniform("transform", t.getTransformMatrix());
        shader.setUniform("view", c.getViewMatrix());

    }

    public void draw() {
        shader.useProgram();
        mesh.draw();
    }
}
