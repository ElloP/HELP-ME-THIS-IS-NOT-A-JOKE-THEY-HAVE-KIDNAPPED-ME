package com.helpme.app.engine.game;

import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.renderer.base.Texture;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.tile.edge.*;
import com.helpme.app.world.tile.edge.visitor.IEdgeVisitor;

/**
 * Created by kopa on 2017-05-10.
 */
public final class GenerateEdge implements IEdgeVisitor {
    private GameObject tile;
    private int rotation;

    public GenerateEdge(GameObject tile, int rotation){
        this.tile = tile;
        this.rotation = rotation;
    }

    @Override
    public boolean visit(Door door) {
        return generate("door");
    }

    @Override
    public boolean visit(Wall wall) {
        return generate("wall");
    }

    @Override
    public boolean visit(Opening opening) {
        return false;
    }

    private boolean generate(String name){
        Maybe<Texture> texture = Resources.getTexture(name);
        texture.run(t -> {
            Quad edge = new Quad(t, Resources.wall(), Resources.wallVert());
            edge.transform.rotate(0, rotation, 0);
            tile.addChild(edge);
        });
        return true;
    }
}
