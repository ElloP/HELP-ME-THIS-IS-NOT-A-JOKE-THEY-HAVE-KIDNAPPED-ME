package com.helpme.app.edgetest;

import com.helpme.app.model.tile.edge.IDoor;
import com.helpme.app.model.tile.edge.IEdgeVisitor;
import com.helpme.app.model.tile.edge.IOpening;
import com.helpme.app.model.tile.edge.IWall;

/**
 * Created by kopa on 2017-05-21.
 */
public class MockVisitor implements IEdgeVisitor<Boolean> {
    @Override
    public Boolean visit(IDoor door) {
        return true;
    }

    @Override
    public Boolean visit(IWall wall) {
        return true;
    }

    @Override
    public Boolean visit(IOpening opening) {
        return true;
    }
}
