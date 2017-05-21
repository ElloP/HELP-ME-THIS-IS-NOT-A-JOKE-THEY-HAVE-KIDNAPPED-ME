package com.helpme.app.model.tile.edge;

import com.helpme.app.model.item.IItem;

/**
 * Created by kopa on 2017-05-18.
 */
public interface IDoor extends IEdge {
    boolean isLocked();
    IItem getKey();
    void unlock();
}
