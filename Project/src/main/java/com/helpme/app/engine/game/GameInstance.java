package com.helpme.app.engine.game;

import com.helpme.app.engine.base.*;
import com.helpme.app.engine.input.Input;
import com.helpme.app.engine.input.InputKey;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.mathl.Vector3f;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.level.*;
import com.helpme.app.world.level.Level;
import com.helpme.app.world.tile.edge.Door;

import java.util.ArrayList;
import java.util.List;

/**
 * Authored by Olle on 2017-04-21.
 */
public class GameInstance extends Game {
    private Camera playerCamera = new Camera();
    public GameInstance() {
        activeCamera = playerCamera;
        scene.addChild(new LevelController(testLevel()));
        UIRenderer health = new UIRenderer("health", new Vector2f(1200, 200), 2);
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


        ILevel level = new Level(tiles, doors, monsters, Vector2f.zero);
        return level;
    }

    public void input() {
        playerCameraInput();
    }

    Vector3f t = new Vector3f();
    float xy = 0;

    public void update() {
        //TODO(Olle): update game

        // xy += Time.deltaTime;
        // t = new Vector3f(0,xy,0);
        // Vector3f te = new Vector3f(0,-xy,0);
        // tile.transform.rotate(t);
    }
    private void playerCameraInput() {
        float movAmt = (float) (10 * Time.deltaTime);
        float rotAmt = (float) (250 * Time.deltaTime);

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
        if(Input.isKeyboardKeyDown(InputKey.Attack))
            playerCamera.rotate(-rotAmt, 0.0f, 0.0f);
        if(Input.isKeyboardKeyDown(InputKey.Selfie))
            playerCamera.rotate(rotAmt, 0.0f, 0.0f);
    }
}
