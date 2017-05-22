package com.helpme.app.engine.game;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.*;
import com.helpme.app.engine.game.controls.CameraController;
import com.helpme.app.engine.game.controls.PlayerCamera;

import com.helpme.app.utils.Vector2f;

import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.level.*;

import com.helpme.app.engine.input.Input;
import com.helpme.app.engine.input.InputKey;

/**
 * Authored by Olle on 2017-04-21.
 */
public class GameInstance extends Game {
    private ICamera playerCamera; // = new Camera();
    private CameraController cameraController;

    //private Menu menu;
    //private boolean loaded = false;
    private UIRenderer health;

    //TODO (Jesper): Just for getting it to work with model
    private ILevel level;
    private Vector2f playerPos;

    public GameInstance(ILevel level, Time time, Vector2f playerPos) {

        this.playerCamera = new Camera(playerPos);
        activeCamera = playerCamera;
        if (cameraController == null) {
            cameraController = new PlayerCamera(activeCamera, time);
        }

        scene.addChild(new LevelController(level));
        for (IReadBody body : level.readBodies()) {
            scene.addChild(new NPCView(body.readPosition().x, body.readPosition().y));
        }
        health = new UIRenderer("health", new Vector2f(1300, 800), 2);
        scene.addChild(health);
        //this.menu = new Menu();
        //scene.addChild(menu);
        this.level = level;
        this.playerPos = playerPos;
    }

    /*public GameInstance() {
        this.menu = new Menu();
        scene.addChild(menu);
    }'/

    public GameInstance(Scene scene) {
        this.scene = scene;
    }
    /*public CameraController getCameraController() {
        return cameraController;
    }*/

    private ILevel testLevel(){
        /*List<Tuple2<Vector2f, IItem[]>> tiles = new ArrayList<>();
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


        /*ILevel level = LevelFactory.createLevel(tiles, doors, monsters, Vector2f.zero);
        return level;*/
        return null;
    }



    public void input(Time time) {
        //activeScene.input(time);
        /*if(!loaded){
            if(Input.isKeyboardKeyPress(InputKey.MoveForward)){
                menu.up();
            }
            if(Input.isKeyboardKeyPress(InputKey.MoveBackward)) {
                menu.down();
            }
            if(Input.isKeyboardKeyPress(InputKey.Select)){
                setActiveScene(menu.getSelected(level, playerPos));
                activeCamera = playerCamera;
                loaded = true;
            }
        } else{
            if(cameraController == null) {
                cameraController = new PlayerCamera(activeCamera, time);
            }
            cameraController.update();
        }*/

    }


    public void update(Time time) {
        //TODO(Olle): update game

    }
}
