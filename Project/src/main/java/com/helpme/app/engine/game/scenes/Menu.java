package com.helpme.app.engine.game.scenes;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.Scene;
import com.helpme.app.engine.base.Time;
import com.helpme.app.engine.game.UIController;
import com.helpme.app.engine.input.Input;
import com.helpme.app.engine.input.InputKey;
import com.helpme.app.utils.Vector2f;

/**
 * Created by Klas on 2017-05-20.
 */
public class Menu extends Scene{
    private UIController menu;

    private String[] options;

    private int current;

    private MenuEvent menuEvent;

    private final String LOAD = "menuload";
    private final String NEW = "menunew";
    public Menu(){
        this.options = new String[2];
        options[0] = LOAD;
        options[1] = NEW;
        current = 0;
        this.menu = new UIController(options[current], new Vector2f(0, 0), 1600, 900);
        this.menuEvent = MenuEvent.NEW;
    }

    @Override
    public void input(Time time) {
        if(Input.isKeyboardKeyPress(InputKey.MoveForward)){
            up();
        }
        if(Input.isKeyboardKeyPress(InputKey.MoveBackward)) {
            down();
        }
        if(Input.isKeyboardKeyPress(InputKey.Select)){
            setChanged();
            notifyObservers(menuEvent);
        }
    }


    public void up(){
        current = 0;
        //menuEvent.getPrevious
        menu.setTexture(options[current]);
    }

    public void down(){
        current = 1;
        //menuEvent.getNext
        menu.setTexture(options[current]);
    }

    @Override
    public void draw(ICamera camera) {
        menu.draw(camera);
    }

    public MenuEvent getMenuEvent() {
        return menuEvent;
    }

    public int getCurrent() {
        return current;
    }
}
