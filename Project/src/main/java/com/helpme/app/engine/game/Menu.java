package com.helpme.app.engine.game;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.Scene;
import com.helpme.app.engine.base.Time;
import com.helpme.app.engine.input.Input;
import com.helpme.app.engine.input.InputKey;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.level.ILevel;

/**
 * Created by Klas on 2017-05-20.
 */
public class Menu extends Scene{
    private UIRenderer menu;
    private String[] options;

    private int current;
    private final String LOAD = "menuload";
    private final String NEW = "menunew";

    //private GameLoader gameLoader;

    public Menu(){
        //this.gameLoader = new GameLoader();
        this.options = new String[2];
        options[0] = LOAD;
        options[1] = NEW;
        current = 0;
        this.menu = new UIRenderer(options[current], new Vector2f(800, 450), 2);
    }

    public void up(){
        if(current > 0){
            current--;
            menu.setTexture(options[current]);
        }
    }

    public void down(){
        if(current < options.length-1){
            current++;
            menu.setTexture(options[current]);
        }
    }
    public Scene getSelected(){
        if(options[current] == "menuload") return loadScene();
        //else { return loadNewGame();}
        return null;
    }
    public Scene getSelected(ILevel level, Vector2f playerPos) {
        if(options[current] == "menuload") return loadScene();
        else { return loadNewGame(level, playerPos);}
    }

    private Scene loadScene(){
        Scene scene = new Scene();
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
    private Scene loadNewGame(ILevel level, Vector2f playerPos){
        Scene scene = new Scene();

        scene.addChild(new LevelController(level));
        for (IReadBody body : level.readBodies()) {
            scene.addChild(new NPCView(body.readPosition().x, body.readPosition().y));
        }
        //addUI(scene);

        return scene;

    }
    public void input(Time time) {
        if(Input.isKeyboardKeyPress(InputKey.MoveForward)){
            up();
        }
        if(Input.isKeyboardKeyPress(InputKey.MoveBackward)) {
            down();
        }
        if(Input.isKeyboardKeyPress(InputKey.Select)){
            setChanged();
            notifyObservers();
        }
    }

    public int getCurrent() {
        return current;
    }

    /*private void addUI(Scene scene){
        UIRenderer health = new UIRenderer("health", new Vector2f(1300, 800), 2);
        scene.addChild(health);
    }*/

    @Override
    public void draw(ICamera camera) {
        menu.draw(camera);
    }

}
