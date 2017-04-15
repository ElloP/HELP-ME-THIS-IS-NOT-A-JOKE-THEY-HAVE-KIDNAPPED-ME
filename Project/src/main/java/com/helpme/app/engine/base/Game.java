package com.helpme.app.engine.base;

import com.helpme.app.engine.input.Input;
import com.helpme.app.engine.input.InputKey;
import com.helpme.app.engine.renderer.base.Cube;
import com.helpme.app.engine.renderer.base.Mesh;
import com.helpme.app.engine.renderer.base.Shader;
import com.helpme.app.utils.mathl.Matrix4f;


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
    }

    public void input() {

        if(Input.isKeyboardKeyPress(InputKey.MoveForward))
            System.out.println("MOVEFORWARD PRESSED!");
        if(Input.isKeyboardKeyRelease(InputKey.MoveForward))
            System.out.println("MOVEFORWARD Release!");
    }

    float test = 0.0f;
    Transform t = new Transform();
    Matrix4f perspective = Transform.getPerspectiveMatrix(70f, Window.width, Window.height, 0.1f, 1000);

    public void update() {
        //TODO(Olle): update game
        test += Time.deltaTime;
        t.rotate(0,test * 50, test * 50);
        t.setPosition((float) Math.sin(test), 0.0f, -5.0f);
        //t.scale((float) Math.sin(test));
        t.getTransformMatrix().logMatrix();
        shader.setUniform("test", test);
        shader.setUniform("projection", perspective);
        shader.setUniform("transform", t.getTransformMatrix());

    }

    public void draw() {
        shader.useProgram();
        mesh.draw();
    }
}
