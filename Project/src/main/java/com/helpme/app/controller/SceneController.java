package com.helpme.app.controller;

import com.helpme.app.engine.base.Scene;
import com.helpme.app.engine.game.*;
import com.helpme.app.saveload.GameLoader;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.level.ILevel;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jesper on 2017-05-22.
 */
public class SceneController implements Observer {
    private GameInstance gameInstance;
    private GameLoader gameLoader;

    public SceneController(GameInstance gameInstance, GameLoader gameLoader) {
        this.gameInstance = gameInstance;
        this.gameLoader = gameLoader;
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Menu) {
            if (((Menu) o).getCurrent() == 0) {
                Tuple3<ILevel,IBody,IConsciousness[]> newGame = gameLoader.loadGame("");
                //TODO (Jesper): Add all constructor arguments
                gameInstance.setActiveScene(new LevelScene(newGame.a, newGame.b));
            } else if (((Menu) o).getCurrent() == 1) {
                Tuple3<ILevel,IBody,IConsciousness[]> game = gameLoader.loadGame("test.xml");
                IBody player = game.b;


                Vector2f playerPos = player.readPosition();
                // activeCamera.setPosition(-6*playerPos.x,0.5f,6*playerPos.y); //TODO set camera at players position
                scene.addChild(new LevelController(game.a));
                for(IConsciousness e : game.c){
                    Vector2f enemyPos = e.readBody().readPosition();
                    NPCView tmp = new NPCView();
                    tmp.transform.setPosition(-6*enemyPos.x,0,6*enemyPos.y);
                    scene.addChild(tmp);
                }
                //addUI(scene);
                return scene;
            }
            //TODO (Jesper): Should add case for when user presses ESC
        }
    }
}
