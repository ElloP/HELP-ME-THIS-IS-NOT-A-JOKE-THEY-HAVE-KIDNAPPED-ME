package com.helpme.app.game.saveload.visitor;

import com.helpme.app.game.model.item.IReadItem;
import com.helpme.app.game.model.tile.edge.IDoor;
import com.helpme.app.game.model.tile.edge.IEdgeVisitor;
import com.helpme.app.game.model.tile.edge.IOpening;
import com.helpme.app.game.model.tile.edge.IWall;
import com.helpme.app.utils.tuple.Tuple3;

/**
 * Created by kopa on 2017-05-15.
 */
public final class GetEdgeInfo implements IEdgeVisitor<Tuple3<String, Boolean, IReadItem>> {

    @Override
    public Tuple3<String, Boolean, IReadItem> visit(IDoor door) {
        return new Tuple3("door", door.isLocked(), door.getKey());
    }

    @Override
    public Tuple3<String, Boolean, IReadItem> visit(IWall wall) {
        return new Tuple3("wall",null,null);
    }

    @Override
    public Tuple3<String, Boolean, IReadItem> visit(IOpening opening) {
        return new Tuple3("opening",null,null);
    }
}
