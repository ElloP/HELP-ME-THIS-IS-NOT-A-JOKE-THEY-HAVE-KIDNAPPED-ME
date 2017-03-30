package com.helpme.app.tile.edge;

import com.helpme.app.item.Item;

/**
 * Created by kopa on 2017-03-30.
 */
public class Door implements Edge {
    public boolean isUnlocked;

    public boolean traverse() {
        return isUnlocked;
    }

    public boolean unlock(Item item) {
        return false;
    }
}
