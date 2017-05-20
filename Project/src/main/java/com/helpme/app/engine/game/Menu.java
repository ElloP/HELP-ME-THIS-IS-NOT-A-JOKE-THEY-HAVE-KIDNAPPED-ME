package com.helpme.app.engine.game;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.utils.Vector2f;

/**
 * Created by Klas on 2017-05-20.
 */
public class Menu extends GameObject{
    private UIRenderer menu;
    private String[] options;
    private int curr;

    public Menu(){
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


    @Override
    public void draw(ICamera camera) {
        menu.draw(camera);
    }
}
