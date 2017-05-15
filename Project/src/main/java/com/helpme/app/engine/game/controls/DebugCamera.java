package com.helpme.app.engine.game.controls;

import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.Time;
import com.helpme.app.engine.input.Input;
import com.helpme.app.engine.input.InputKey;

/**
 * Authored by Olle on 2017-05-15.
 */
public class DebugCamera extends CameraController {
    public DebugCamera(Camera camera, Time time) {
        super(camera, time);
    }

    public void update() {
        float movAmt = (float) (10 * getTime().getDeltaTime());
        float rotAmt = (float) (250 * getTime().getDeltaTime());

        if(Input.isKeyboardKeyDown(InputKey.MoveForward))
            getCamera().moveForward(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.MoveLeft))
            getCamera().moveLeft(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.MoveRight))
            getCamera().moveRight(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.MoveBackward))
            getCamera().moveBackward(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.RotateLeft))
            getCamera().rotate(0.0f, rotAmt, 0.0f);
        if(Input.isKeyboardKeyDown(InputKey.RotateRight))
            getCamera().rotate(0.0f, -rotAmt, 0.0f);
        if(Input.isKeyboardKeyDown(InputKey.Attack))
            getCamera().rotate(-rotAmt, 0.0f, 0.0f);
        if(Input.isKeyboardKeyDown(InputKey.Selfie))
            getCamera().rotate(rotAmt, 0.0f, 0.0f);
    }
}
