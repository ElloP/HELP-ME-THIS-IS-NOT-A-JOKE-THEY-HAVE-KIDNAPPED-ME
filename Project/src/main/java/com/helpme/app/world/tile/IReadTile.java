package com.helpme.app.world.tile;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.item.IReadItem;

/**
 * Created by kopa on 2017-04-21.
 */
public interface IReadTile {
    Maybe<IReadItem[]> readItems();
    Maybe<IReadItem> readItem(int index);
}
