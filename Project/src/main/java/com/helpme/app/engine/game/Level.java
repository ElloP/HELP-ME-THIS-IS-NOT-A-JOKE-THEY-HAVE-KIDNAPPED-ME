package com.helpme.app.engine.game;

import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.level.ILevel;
import com.helpme.app.world.tile.ITile;

import java.util.Map;

/**
 * Created by kopa on 2017-05-04.
 */
public class Level extends GameObject {
    private ILevel model;

    public Level(ILevel model){
        this.model = model;
        for(Map.Entry<Vector2f, ITile> entry : model.getTiles().entrySet()){
            addChild(new Tile(entry.getValue(), entry.getKey()));
        }
    }

    @Override
    public void draw(Camera camera) {

    }
}
