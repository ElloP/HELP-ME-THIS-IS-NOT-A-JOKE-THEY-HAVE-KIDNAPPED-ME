package com.helpme.app.engine.game;

import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.renderer.base.Texture;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.mathl.Quaternion;
import com.helpme.app.utils.mathl.Vector3f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.tile.ITile;
import com.helpme.app.world.tile.edge.Door;
import com.helpme.app.world.tile.edge.IEdge;
import com.helpme.app.world.tile.edge.Wall;
import com.helpme.app.world.tile.edge.visitor.Traverse;

/**
 * Authored by Olle on 2017-05-02.
 */
public class TileController extends GameObject {
    ITile model;

    public TileController(ITile model, Vector2f position) {
        this.model = model;
        this.transform.setPosition(new Vector3f(-position.x * 6, 0, position.y * 6));


        Vector2f[] directions = new Vector2f[]{Vector2f.up, Vector2f.left, Vector2f.down, Vector2f.right};

        for (int i = 0; i < 4; i++) {
            Maybe<IEdge> maybeEdge = this.model.getEdge(directions[i]);

            //TODO (Jacob) fix print out of walls and doors
            //Maybe<Texture> maybeTexture = new Nothing();
            //if(maybeEdge.check(e -> e.accept(new Traverse(null)))) continue;

            /*if (maybeEdge.check(e -> e instanceof Wall)){
                maybeTexture = Resources.getTexture("wall");
            }
            else if(maybeEdge.check(e -> e instanceof Door)){
                maybeTexture = Resources.getTexture("door");
            }*/

            int rotation = 90 * i;
            Resources.getTexture("default").run(t -> {
                Quad edge = new Quad(t, Resources.wall, Resources.wallVert());
                edge.transform.rotate(0, rotation, 0);
                addChild(edge);
            });
        }

        Resources.getTexture("floor").run(t -> {
            Quad floor = new Quad(t, Resources.floor, Resources.floorVert());
            addChild(floor);
        });
    }

    @Override
    public void draw(Camera camera) {

    }

    /*public Quad(Shader shader) {
        this.shader = shader;
    }*/
}
