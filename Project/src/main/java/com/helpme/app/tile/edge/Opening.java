package com.helpme.app.tile.edge;

import com.helpme.app.item.Item;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Opening implements Edge {
    public boolean traverse() {
        return true;
    }

    @Override
    public boolean unlock(Item[] potentialKeys) {
        return false;
    }
}
