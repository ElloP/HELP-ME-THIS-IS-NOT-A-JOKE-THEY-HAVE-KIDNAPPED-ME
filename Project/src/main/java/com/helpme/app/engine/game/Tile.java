package com.helpme.app.engine.game;

import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.renderer.base.*;
import com.helpme.app.engine.utils.TextureLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Authored by Olle on 2017-05-02.
 */
public class Tile extends GameObject {
    public Floor floor;
    public List<Wall> walls;

    public Tile() {
        walls = new ArrayList<Wall>();
        floor = new Floor();
        addChild(floor);

        for(int i = 0; i < 4; i++) {
            Wall wall = new Wall();
            wall.transform.rotate(0, 90 * i, 0);
            walls.add(new Wall());
            addChild(wall);
        }
    }

    /*public Floor(Shader shader) {
        this.shader = shader;
    }*/

    @Override
    public void draw(Camera camera) {
    }
}
