package com.helpme.app.tile.edge;

import com.helpme.app.item.Item;

/**
 * Created by Jacob on 2017-03-30.
 */
public interface Edge {
    boolean traverse();
    boolean unlock(Item[] potentialKeys); // NOTE (Jacob): Not sure how to treat this exceptional door case; how to know a door is a door?
}
