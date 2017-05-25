package com.helpme.app.game.view;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.game.model.tile.ITile;
import com.helpme.app.utils.Vector2f;

import java.util.Map;

/**
 * Created by kopa on 2017-05-04.
 */
public class LevelView extends GameObject {
    public LevelView(ILevel model) {
        for (Map.Entry<Vector2f, ITile> entry : model.getTiles().entrySet()) {
            addChild(new TileView(entry.getValue(), entry.getKey()));

        }
    }

    @Override
    public void draw(ICamera camera) {

    }
}
