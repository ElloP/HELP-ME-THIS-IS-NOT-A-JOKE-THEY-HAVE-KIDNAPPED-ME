package com.helpme.app.engine.game;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.model.level.ILevel;
import com.helpme.app.model.tile.ITile;

import java.util.Map;

/**
 * Created by kopa on 2017-05-04.
 */
public class LevelController extends GameObject {
    public LevelController(ILevel model) {
        for (Map.Entry<Vector2f, ITile> entry : model.getTiles().entrySet()) {
            addChild(new TileController(entry.getValue(), entry.getKey()));

        }
    }

    @Override
    public void draw(ICamera camera) {

    }
}
