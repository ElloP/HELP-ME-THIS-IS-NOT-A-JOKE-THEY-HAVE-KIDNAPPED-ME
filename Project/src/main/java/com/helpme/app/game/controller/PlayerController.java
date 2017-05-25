package com.helpme.app.game.controller;

import com.helpme.app.engine.input.InputKey;
import com.helpme.app.game.model.body.concrete.BodyEvent;
import com.helpme.app.game.model.consciousness.concrete.Player;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.game.view.camera.PlayerCamera;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jesper on 2017-05-20.
 */
public class PlayerController implements Observer {
    private PlayerCamera playerCamera;
    private Player player;
    //private ILevel level;

    public PlayerController(PlayerCamera playerCamera, Player player, ILevel level) {
        this.playerCamera = playerCamera;
        this.player = player;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof BodyEvent) {
            BodyEvent bodyEvent = (BodyEvent) arg;
            switch (bodyEvent) {
                case MOVE_FORWARD:
                    playerCamera.moveForward();
                    break;
                case MOVE_BACKWARD:
                    playerCamera.moveBack();
                    break;
                case MOVE_LEFT:
                    playerCamera.moveLeft();
                    break;
                case MOVE_RIGHT:
                    playerCamera.moveRight();
                    break;
                case ROTATE_LEFT:
                    playerCamera.rotateLeft();
                    break;
                case ROTATE_RIGHT:
                    playerCamera.rotateRight();
                    break;

            }
        } else if (arg instanceof InputKey && o instanceof PlayerCamera) {
            InputKey gameEvent = (InputKey) arg;
            switch (gameEvent) {
                case MOVE_FORWARD:
                    player.moveForward();
                    break;
                case MOVE_BACKWARD:
                    player.moveBackward();
                    break;
                case MOVE_LEFT:
                    player.moveLeft();
                    break;
                case MOVE_RIGHT:
                    player.moveRight();
                    break;
                case ROTATE_LEFT:
                    player.rotateLeft();
                    break;
                case ROTATE_RIGHT:
                    player.rotateRight();
                    break;
            }
        }
    }
}
