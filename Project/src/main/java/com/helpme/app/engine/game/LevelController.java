package com.helpme.app.engine.game;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.level.ILevel;
import com.helpme.app.world.tile.ITile;

import java.util.Map;

/**
 * Created by kopa on 2017-05-04.
 */
public class LevelController extends GameObject {
    private ILevel model;

    public LevelController(ILevel model) {
        this.model = model;
        for (Map.Entry<Vector2f, ITile> entry : model.getTiles().entrySet()) {
            addChild(new TileController(entry.getValue(), entry.getKey()));

        }

        Vector2f[] directions = new Vector2f[]{Vector2f.up, Vector2f.left, Vector2f.down, Vector2f.right};

        ITile tile = model.getTiles().get(new Vector2f(3, 1));
        for (int i = 0; i < 4; i++) {
            System.out.println(directions[i]);
            tile.getEdge(directions[i]).run(e -> System.out.println(e.getClass().toString()));
            System.out.println();
        }
    }

    @Override
    public void draw(ICamera camera) {

    }
}
