package com.helpme.app.game.model.body.concrete.visitor;

import com.helpme.app.game.model.tile.edge.IDoor;
import com.helpme.app.game.model.tile.edge.IEdgeVisitor;
import com.helpme.app.game.model.tile.edge.IOpening;
import com.helpme.app.game.model.tile.edge.IWall;

/**
 * Created by Jacob on 2017-04-08.
 */
public final class Traverse implements IEdgeVisitor<Boolean> {


    @Override
    public Boolean visit(IDoor door) {
        return !door.isLocked();
    }

    @Override
    public Boolean visit(IWall wall) {
        return false;
    }

    @Override
    public Boolean visit(IOpening opening) {
        return true;
    }
}
