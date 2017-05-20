package com.helpme.app.engine.game;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.*;
import com.helpme.app.engine.game.controls.CameraController;
import com.helpme.app.engine.game.controls.PlayerController;
import com.helpme.app.engine.input.Input;
import com.helpme.app.engine.input.InputKey;
import com.helpme.app.saveload.GameLoader;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.consciousness.concrete.Enemy;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.level.*;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.level.concrete.LevelFactory;
import com.helpme.app.world.tile.edge.concrete.Door;


import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.text;

/**
 * Authored by Olle on 2017-04-21.
 */
public class GameInstance extends Game {
    private ICamera playerCamera = new Camera();
    private CameraController cameraController;
    private GameLoader gameLoader;
    private boolean loaded = false;
    public GameInstance() {
        this.gameLoader = new GameLoader();
    }
    private void loadScene(){
        Scene scene = new Scene();
        Tuple3<ILevel,IBody,Enemy[]> game = gameLoader.loadGame("text.xml");
        IBody player = game.b;
        activeCamera = playerCamera;
        Vector2f playerPos = player.readPosition();
        activeCamera.setPosition(-6*playerPos.x,0,6*playerPos.y);
        scene.addChild(new LevelController(game.a));
        for(Enemy e : game.c){
            Vector2f enemyPos = e.readBody().readPosition();
            NPCView tmp = new NPCView();
            tmp.transform.setPosition(-6*enemyPos.x,0,6*enemyPos.y);
            scene.addChild(tmp);
        }
        setActiveScene(scene);
    }
    private void loadNewGame(){
        activeCamera = playerCamera;
        scene.addChild(new LevelController(testLevel()));
        scene.addChild(new NPCView());
        UIRenderer health = new UIRenderer("health", new Vector2f(1300, 800), 2);
        scene.addChild(health);
        health.setTexture("health80");

    }

    private ILevel testLevel(){
        List<Tuple2<Vector2f, IItem[]>> tiles = new ArrayList<>();
        List<Tuple3<Vector2f, Vector2f, Door>> doors = new ArrayList<>();
        List<IBody> monsters = new ArrayList<>();

        tiles.add(new Tuple2<>(new Vector2f(0, 0), null));
        tiles.add(new Tuple2<>(new Vector2f(1, 0), null));
        tiles.add(new Tuple2<>(new Vector2f(2, 0), null));
        tiles.add(new Tuple2<>(new Vector2f(1, 1), null));
        tiles.add(new Tuple2<>(new Vector2f(2, 1), null));
        tiles.add(new Tuple2<>(new Vector2f(3, 1), null));
        tiles.add(new Tuple2<>(new Vector2f(1, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(2, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(3, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(1, 3), null));
        tiles.add(new Tuple2<>(new Vector2f(2, 3), null));
        tiles.add(new Tuple2<>(new Vector2f(3, 3), null));

        tiles.add(new Tuple2<>(new Vector2f(5, 5), null));

        tiles.add(new Tuple2<>(new Vector2f(6, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(7, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(8, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(9, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(10, 2), null));

        tiles.add(new Tuple2<>(new Vector2f(6, 0), null));
        tiles.add(new Tuple2<>(new Vector2f(7, 0), null));
        tiles.add(new Tuple2<>(new Vector2f(8, 0), null));
        tiles.add(new Tuple2<>(new Vector2f(9, 0), null));

        tiles.add(new Tuple2<>(new Vector2f(9, 0), null));

        tiles.add(new Tuple2<>(new Vector2f(7,5),null));
        tiles.add(new Tuple2<>(new Vector2f(8,5),null));

        tiles.add(new Tuple2<>(new Vector2f(1, 5), null));
        tiles.add(new Tuple2<>(new Vector2f(2, 5),null));
        tiles.add(new Tuple2<>(new Vector2f(3, 5), null));

        doors.add(new Tuple3<>(new Vector2f(6, 2), Vector2f.right, new Door(true, null)));
        doors.add(new Tuple3<>(new Vector2f(8, 2), Vector2f.left, new Door(false, null)));
        doors.add(new Tuple3<>(new Vector2f(8, 2), Vector2f.right, new Door(true, null)));

        doors.add(new Tuple3<>(new Vector2f(7, 0), Vector2f.right, new Door(true, null)));

        /**
         *    [ ][ ][ ]   [ ]   [ ][ ]
         *
         *    [ ][ ][ ]
         *    [ ][ ][ ]      [ |[ ]/ ]| ][ ]
         *    [ ][ ][ ]
         * [p][ ][ ]         [ ][ ]| ][ ]
         */


        ILevel level = LevelFactory.createLevel(tiles, doors, monsters, Vector2f.zero);
        return level;
    }

    public void input(Time time) {
        if(!loaded){
            if(Input.isKeyboardKeyPress(InputKey.MoveForward)){
                loaded = true;
                loadScene();
            }
            if(Input.isKeyboardKeyPress(InputKey.MoveBackward)) {
                loaded = true;
                loadNewGame();
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
