package com.helpme.app.game.controller;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.input.InputKey;
import com.helpme.app.game.model.body.concrete.BodyEvent;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.view.HealthView;
import com.helpme.app.game.view.camera.PlayerCameraView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jesper on 2017-05-20.
 */
public class PlayerController extends GameObject implements Observer {
    private PlayerCameraView playerCamera;
    private IConsciousness player;
    private HealthView healthView;

    public PlayerController(PlayerCameraView playerCameraView, IConsciousness player, HealthView healthView) {
        this.playerCamera = playerCameraView;
        this.player = player;
        this.healthView = healthView;
        addChild(healthView);

        player.addObserver(this);
        playerCameraView.addObserver(this);
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
                case HEALTH:
                    healthView.setHealth(player.readBody().readCurrentHitpoints());

            }
        } else if (arg instanceof InputKey && o instanceof PlayerCameraView) {
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
                case ATTACK:
                    player.useAttack();
                    break;
                case SELECT:
                    player.usePickupAll();
                    break;
                case SELFIE:
                    player.useSelfie();
                    break;
            }
        }
    }

    @Override
    public void draw(ICamera camera) {

    }
}
