package com.helpme.app.engine.game;

import com.helpme.app.engine.base.*;
import com.helpme.app.engine.input.Input;
import com.helpme.app.engine.input.InputKey;
import com.helpme.app.engine.renderer.base.*;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.mathl.Vector3f;

import java.util.ArrayList;
import java.util.List;

/**
 * Authored by Olle on 2017-04-21.
 */
public class GameInstance extends Game {
    private Camera playerCamera = new Camera();

    Tile tile = new Tile();
    public GameInstance() {
        activeCamera = playerCamera;

        //scene.addChild(tile);
        NPCView npcView = new NPCView();
        scene.addChild(npcView);

    }

    public void input(Time time) {
        playerCameraInput(time);
    }

    Vector3f t = new Vector3f();
    float xy = 0;
    public void update(Time time) {
        //TODO(Olle): update game

        xy += time.getDeltaTime();
        t = new Vector3f(0,xy,0);
        //tile.transform.rotate(t);
    }
    private void playerCameraInput(Time time) {
        float movAmt = (float) (10 * time.getDeltaTime());
        float rotAmt = (float) (250 * time.getDeltaTime());

        if(Input.isKeyboardKeyDown(InputKey.MoveForward))
            playerCamera.moveForward(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.MoveLeft))
            playerCamera.moveLeft(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.MoveRight))
            playerCamera.moveRight(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.MoveBackward))
            playerCamera.moveBackward(movAmt);
        if(Input.isKeyboardKeyDown(InputKey.RotateLeft))
            playerCamera.rotate(0.0f, rotAmt, 0.0f);
        if(Input.isKeyboardKeyDown(InputKey.RotateRight))
            playerCamera.rotate(0.0f, -rotAmt, 0.0f);
    }
}
