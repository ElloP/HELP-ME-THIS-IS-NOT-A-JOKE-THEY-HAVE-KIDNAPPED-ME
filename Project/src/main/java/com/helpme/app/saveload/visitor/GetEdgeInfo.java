package com.helpme.app.saveload.visitor;

import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IReadItem;
import com.helpme.app.world.tile.edge.Door;
import com.helpme.app.world.tile.edge.Opening;
import com.helpme.app.world.tile.edge.Wall;
import com.helpme.app.world.tile.edge.visitor.IEdgeVisitor;

/**
 * Created by kopa on 2017-05-15.
 */
public class GetEdgeInfo implements IEdgeVisitor<Tuple3<String, Boolean, IReadItem>> {

    @Override
    public Tuple3<String, Boolean, IReadItem> visit(Door door) {
        return new Tuple3("door", door.isLocked(), door.getKey());
    }

    @Override
    public Tuple3<String, Boolean, IReadItem> visit(Wall wall) {
        return new Tuple3("wall",null,null);
    }

    @Override
    public Tuple3<String, Boolean, IReadItem> visit(Opening opening) {
        return new Tuple3("opening",null,null);
    }
}
