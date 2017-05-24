package com.helpme.app.controller;

import com.helpme.app.engine.game.controls.GameEvent;
import com.helpme.app.engine.game.controls.PlayerCamera;
import com.helpme.app.world.body.concrete.Body;
import com.helpme.app.world.body.concrete.visitor.WorldEvent;
import com.helpme.app.world.consciousness.concrete.Player;
import com.helpme.app.world.level.ILevel;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jesper on 2017-05-20.
 */
public class PlayerController implements IController {
    private PlayerCamera playerCamera;
    private Player player;
    //private ILevel level;

    public PlayerController(PlayerCamera playerCamera, Player player, ILevel level) {
        this.playerCamera = playerCamera;
        this.player = player;
        //TODO (Jesper): Should not add itself as an observer
        //playerCamera.addObserver(this);
        //level.readPlayer().run(b -> ((Body) b).addObserver(this));
        //this.level = level;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof WorldEvent) {
            WorldEvent worldEvent = (WorldEvent) arg;
            switch (worldEvent) {
                case MOV_FORWARD:
                    playerCamera.moveForward();
                    break;
                case MOV_BACK:
                    playerCamera.moveBack();
                    break;
                case MOV_LEFT:
                    playerCamera.moveLeft();
                    break;
                case MOV_RIGHT:
                    playerCamera.moveRight();
                    break;
                case ROT_LEFT:
                    playerCamera.rotateLeft();
                    break;
                case ROT_RIGHT:
                    playerCamera.rotateRight();
                    break;

            }
        } else if (arg instanceof GameEvent && o instanceof PlayerCamera) {
            GameEvent gameEvent = (GameEvent) arg;
            switch (gameEvent) {
                case MOV_FORWARD:
                    player.moveForward();
                    break;
                case MOV_BACK:
                    player.moveBackward();
                    break;
                case MOV_LEFT:
                    player.moveLeft();
                    break;
                case MOV_RIGHT:
                    player.moveRight();
                    break;
                case ROT_LEFT:
                    player.rotateLeft();
                    break;
                case ROT_RIGHT:
                    player.rotateRight();
                    break;

            }
        }
    }
}
