package com.helpme.app.world.character.inventory;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IReadItem;

/**
 * Created by kopa on 2017-04-21.
 */
public interface IReadInventory {
    int getSize();
    Maybe<IReadItem[]> readItems();
    Maybe<IReadItem> readItem(int index);
    boolean hasItem(IItem item);
}