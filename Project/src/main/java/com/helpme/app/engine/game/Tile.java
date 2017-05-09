package com.helpme.app.engine.game;

import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.mathl.Vector3f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.tile.ITile;
import com.helpme.app.world.tile.edge.IEdge;
import com.helpme.app.world.tile.edge.visitor.Traverse;

/**
 * Authored by Olle on 2017-05-02.
 */
public class Tile extends GameObject {
    ITile model;

    public Tile(ITile model, Vector2f position) {
        this.model = model;
        this.transform.setPosition(new Vector3f(position.x*6, 0, position.y*6));
        addChild(new Floor());

        Vector2f[] directions = new Vector2f[]{Vector2f.up, Vector2f.right, Vector2f.down, Vector2f.left};

        for (int i = 0; i < 4; i++) {
            Maybe<IEdge> edge = this.model.getEdge(directions[i]);

            if(edge.check(e -> e.accept(new Traverse(null)))) continue;
            Wall wall = new Wall();
            wall.transform.rotate(0, 90 * i, 0);
            addChild(wall);

        }
    }

    @Override
    public void draw(Camera camera) {

    }

    /*public Floor(Shader shader) {
        this.shader = shader;
    }*/
}
