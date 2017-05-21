package com.helpme.app.engine.game;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.base.Scene;
import com.helpme.app.saveload.GameLoader;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.consciousness.concrete.Enemy;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.level.ILevel;
import com.helpme.app.world.level.concrete.LevelFactory;
import com.helpme.app.world.tile.edge.concrete.Door;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Klas on 2017-05-20.
 */
public class Menu extends GameObject{
    private UIRenderer menu;
    private String[] options;
    private int curr;
    private GameLoader gameLoader;

    public Menu(){
        this.gameLoader = new GameLoader();
        this.options = new String[2];
        options[0] = "menuload";
        options[1] = "menunew";
        curr = 0;
        this.menu = new UIRenderer(options[curr], new Vector2f(800, 450), 2);
    }

    public void up(){
        if(curr > 0){
            curr--;
            menu.setTexture(options[curr]);
        }
    }
    public void down(){
        if(curr < options.length-1){
            curr++;
            menu.setTexture(options[curr]);
        }
    }
    public Scene getSelected(){
        if(options[curr] == "menuload") return loadScene();
        else { return loadNewGame();}
    }
    private Scene loadScene(){
        Scene scene = new Scene();
        Tuple3<ILevel,IBody,IConsciousness[]> game = gameLoader.loadGame("test.xml");
       // IBody player = game.b;

       // Vector2f playerPos = player.readPosition();
       // activeCamera.setPosition(-6*playerPos.x,0.5f,6*playerPos.y); //TODO set camera at players position
        scene.addChild(new LevelController(game.a));
        for(IConsciousness e : game.c){
            Vector2f enemyPos = e.readBody().readPosition();
            NPCView tmp = new NPCView();
            tmp.transform.setPosition(-6*enemyPos.x,0,6*enemyPos.y);
            scene.addChild(tmp);
        }
        addUI(scene);
        return scene;
    }
    private Scene loadNewGame(){
        Scene scene = new Scene();

        scene.addChild(new LevelController(testLevel()));
        scene.addChild(new NPCView());
        addUI(scene);
        //health.setTexture("health80");

        return scene;

    }

    private void addUI(Scene scene){
        UIRenderer health = new UIRenderer("health", new Vector2f(1300, 800), 2);
        scene.addChild(health);
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

        doors.add(new Tuple3<>(new Vector2f(6, 2), Vector2f.east, new Door(true, null)));
        doors.add(new Tuple3<>(new Vector2f(8, 2), Vector2f.west, new Door(false, null)));
        doors.add(new Tuple3<>(new Vector2f(8, 2), Vector2f.east, new Door(true, null)));

        doors.add(new Tuple3<>(new Vector2f(7, 0), Vector2f.east, new Door(true, null)));

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

    @Override
    public void draw(ICamera camera) {
        menu.draw(camera);
    }
}
