package com.helpme.app;

import com.helpme.app.engine.game.controls.GameEvent;
import com.helpme.app.engine.game.controls.PlayerController;
import com.helpme.app.world.body.concrete.Body;
import com.helpme.app.world.body.concrete.visitor.WorldEvent;
import com.helpme.app.world.consciousness.concrete.Player;
import com.helpme.app.world.level.ILevel;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jesper on 2017-05-20.
 */
public class GameController implements Observer {
    private PlayerController playerController;
    private Player player;
    private Body body123;

    public GameController(PlayerController playerController, Player player, ILevel level) {
        this.playerController = playerController;
        this.player = player;
        playerController.addObserver(this);
        player.addObserver(this);
        level.readPlayer().run(t -> this.body123 = (Body) t);
        body123.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof WorldEvent) {
            WorldEvent worldEvent = (WorldEvent) arg;
            switch (worldEvent) {
                case MOV_FORWARD:
                    playerController.moveForward();
                    break;
                case MOV_BACK:
                    playerController.moveBack();
                    break;
                case MOV_LEFT:
                    playerController.moveLeft();
                    break;
                case MOV_RIGHT:
                    playerController.moveRight();
                    break;
                case ROT_LEFT:
                    playerController.rotateLeft();
                    break;
                case ROT_RIGHT:
                    playerController.rotateRight();
                    break;

            }
        } else if (arg instanceof GameEvent && o instanceof PlayerController) {
            GameEvent gameEvent = (GameEvent) arg;
            switch (gameEvent) {
                case MOV_FORWARD:
                    player.moveForward();
                    System.out.println(gameEvent);
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
