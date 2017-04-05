package com.helpme.app.tile.edge;

import com.helpme.app.item.Item;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Door implements Edge{
    private boolean locked;
    private Item key;

    public boolean traverse() {
        return locked;
    }
}
