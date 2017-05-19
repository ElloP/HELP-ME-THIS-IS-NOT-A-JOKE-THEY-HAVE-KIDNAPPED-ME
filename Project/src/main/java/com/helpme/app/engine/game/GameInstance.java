package com.helpme.app.engine.game;

import com.helpme.app.engine.base.*;
import com.helpme.app.engine.game.controls.CameraController;
import com.helpme.app.engine.game.controls.PlayerController;
import com.helpme.app.saveload.GameLoader;
import com.helpme.app.saveload.SaveRoot;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.consciousness.concrete.Enemy;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.level.*;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.level.*;
import com.helpme.app.world.level.concrete.LevelFactory;
import com.helpme.app.world.tile.edge.concrete.Door;


import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

/**
 * Authored by Olle on 2017-04-21.
 */
public class GameInstance extends Game {
    private Camera playerCamera = new Camera();
    private CameraController cameraController;
    private GameLoader gameLoader;
    public GameInstance() {
        this.gameLoader = new GameLoader();

        SaveRoot game = loadGame("text.xml");
        IBody player = game.loadPlayer();
        player.setPosition(new Vector2f(10,10));
        activeCamera = playerCamera;
        //activeCamera.setPosition(player.readPosition().x,player.readPosition().y);
        activeCamera.setPosition(-6,0,6);
        //scene.addChild(new LevelController(testLevel()));

      //  scene.addChild(new LevelController(testLevel()));
       // scene.addChild(new NPCView());

        scene.addChild(new LevelController(game.loadLevel()));
    }


    private SaveRoot loadGame(String filePath) {
        try{
            SaveRoot loaded = gameLoader.unmarshall(filePath);
            return loaded;
        } catch (JAXBException e){
            System.out.println("Unable to load game from that filepath");
            System.out.println(e);
            return null;
        }
    }
    private void saveGame(SaveRoot saveRoot, String filePath){
        try{
            gameLoader.marshall(saveRoot, filePath);
        } catch (JAXBException e){
            System.out.println("Unable to save game");
            System.out.println(e);
        }
    }
    private void saveGame(ILevel level, IBody player, Enemy[] enemies, String filePath){
        saveGame(new SaveRoot(level,player,enemies),filePath);
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
        if(cameraController == null) {
            cameraController = new PlayerController(activeCamera, time);
        }
        cameraController.update();
    }

    public void update(Time time) {
        //TODO(Olle): update game

        // xy += Time.deltaTime;
        // t = new Vector3f(0,xy,0);
        // Vector3f te = new Vector3f(0,-xy,0);
        // tile.transform.rotate(t);
    }
}
