package com.helpme.app.game.view;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.game.model.tile.ITile;
import com.helpme.app.game.view.visitor.GenerateEdge;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.mathl.Vector3f;

/**
 * Authored by Olle on 2017-05-02.
 */
public class TileView extends GameObject {
    ITile model;

    public TileView(ITile model, Vector2f position) {
        this.model = model;
        this.transform.setPosition(new Vector3f(-position.x * 6, 0, position.y * 6));

        Resources.getTexture("floor").run(t -> {
            QuadView floor = new QuadView(t, Resources.floor(), Resources.floorVert());
            addChild(floor);
        });

        Vector2f[] directions = new Vector2f[]{Vector2f.NORTH, Vector2f.WEST, Vector2f.SOUTH, Vector2f.EAST};

        for (int i = 0; i < 4; i++) {
            int rotation = 90 * i;
            this.model.getEdge(directions[i]).run(e -> e.accept(new GenerateEdge(this, rotation)));
        }
    }

    @Override
    public void draw(ICamera camera) {

    }
}
