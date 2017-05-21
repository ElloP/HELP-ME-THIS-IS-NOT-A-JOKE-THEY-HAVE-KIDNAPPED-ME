package com.helpme.app.engine.game;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.*;
import com.helpme.app.engine.game.controls.CameraController;
import com.helpme.app.engine.game.controls.PlayerController;
import com.helpme.app.engine.input.Input;
import com.helpme.app.engine.input.InputKey;

/**
 * Authored by Olle on 2017-04-21.
 */
public class GameInstance extends Game {
    private ICamera playerCamera = new Camera();
    private CameraController cameraController;
    private Menu menu;
    private boolean loaded = false;

    public GameInstance() {
        this.menu = new Menu();
        scene.addChild(menu);
    }


    public void input(Time time) {
        if(!loaded){
            if(Input.isKeyboardKeyPress(InputKey.MoveForward)){
                menu.up();
            }
            if(Input.isKeyboardKeyPress(InputKey.MoveBackward)) {
                menu.down();
            }
            if(Input.isKeyboardKeyPress(InputKey.Select)){
                setActiveScene(menu.getSelected());
                activeCamera = playerCamera;
                loaded = true;
            }
        } else{
            if(cameraController == null) {
                cameraController = new PlayerController(activeCamera, time);
            }
            cameraController.update();
        }

    }

    public void update(Time time) {
        //TODO(Olle): update game

    }
}
