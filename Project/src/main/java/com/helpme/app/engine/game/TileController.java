package com.helpme.app.engine.game;

import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.game.visitor.GenerateEdge;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.mathl.Vector3f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.tile.ITile;
import com.helpme.app.world.tile.edge.IEdge;

/**
 * Authored by Olle on 2017-05-02.
 */
public class TileController extends GameObject {
    ITile model;

    public TileController(ITile model, Vector2f position) {
        this.model = model;
        this.transform.setPosition(new Vector3f(-position.x * 6, 0, position.y * 6));

        Resources.getTexture("floor").run(t -> {
            Quad floor = new Quad(t, Resources.floor(), Resources.floorVert());
            addChild(floor);
        });

        Vector2f[] directions = new Vector2f[]{Vector2f.up, Vector2f.left, Vector2f.down, Vector2f.right};

        for (int i = 0; i < 4; i++) {
            int rotation = 90 * i;
            this.model.getEdge(directions[i]).run(e -> e.accept(new GenerateEdge(this, rotation)));
        }
    }

    @Override
    public void draw(Camera camera) {

    }

    /*public Quad(Shader shader) {
        this.shader = shader;
    }*/
}
