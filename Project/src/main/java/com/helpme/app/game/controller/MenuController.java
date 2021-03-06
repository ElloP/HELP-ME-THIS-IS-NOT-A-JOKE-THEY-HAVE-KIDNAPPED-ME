package com.helpme.app.game.controller;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.Scene;
import com.helpme.app.engine.base.Time;
import com.helpme.app.engine.base.Window;
import com.helpme.app.engine.input.Input;
import com.helpme.app.engine.input.InputKey;
import com.helpme.app.game.view.UIObjectView;
import com.helpme.app.utils.mathl.Vector2f;

/**
 * Created by Klas on 2017-05-20.
 *
 * Created to handle the menu options
 *
 */
public class MenuController extends Scene{
    private UIObjectView menu;

    private String[] options;

    private int current;

    private MenuEvent menuEvent;

    private static final String LOAD = "menuload";
    private static final String NEW = "menunew";
    public MenuController(){
        this.options = new String[2];
        options[0] = LOAD;
        options[1] = NEW;
        current = 0;
        this.menu = new UIObjectView(options[current], new Vector2f(0, 0), Window.getWidth(), Window.getHeight());
        this.menuEvent = MenuEvent.NEW;
    }

    @Override
    public void input(Time time) {
        if(Input.isKeyboardKeyPress(InputKey.MOVE_FORWARD)){
            up();
        }
        if(Input.isKeyboardKeyPress(InputKey.MOVE_BACKWARD)) {
            down();
        }
        if(Input.isKeyboardKeyPress(InputKey.SELECT)){
            setChanged();
            notifyObservers(menuEvent);
        }
    }


    public void up(){
        current = 0;
        menu.setTexture(options[current]);
    }

    public void down(){
        current = 1;
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
