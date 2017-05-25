package com.helpme.app.game.view.camera;

import com.helpme.app.engine.base.Time;
import com.helpme.app.engine.input.Input;
import com.helpme.app.engine.input.InputKey;

/**
 * Authored by Olle on 2017-05-15.
 */
public class DebugCamera extends Camera {
    public DebugCamera(com.helpme.app.engine.base.Camera camera, Time time) {
        super(camera, time);
    }

    public void update() {
        float movAmt = (float) (10 * getTime().getDeltaTime());
        float rotAmt = (float) (250 * getTime().getDeltaTime());

        if(Input.isKeyboardKeyDown(InputKey.MOVE_FORWARD))
            getCamera().moveForward(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.MOVE_LEFT))
            getCamera().moveLeft(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.MOVE_RIGHT))
            getCamera().moveRight(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.MOVE_BACKWARD))
            getCamera().moveBackward(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.ROTATE_LEFT))
            getCamera().rotate(0.0f, rotAmt, 0.0f);
        if(Input.isKeyboardKeyDown(InputKey.ROTATE_RIGHT))
            getCamera().rotate(0.0f, -rotAmt, 0.0f);
        if(Input.isKeyboardKeyDown(InputKey.ATTACK))
            getCamera().rotate(-rotAmt, 0.0f, 0.0f);
        if(Input.isKeyboardKeyDown(InputKey.SELFIE))
            getCamera().rotate(rotAmt, 0.0f, 0.0f);
    }
}
