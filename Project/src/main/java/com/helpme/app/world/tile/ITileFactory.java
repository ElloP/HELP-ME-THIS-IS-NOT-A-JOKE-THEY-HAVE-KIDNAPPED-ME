package com.helpme.app.world.tile;

import com.helpme.app.world.item.IItem;

/**
 * Created by kopa on 2017-04-10.
 */
public interface ITileFactory {
    static ITile tile(IItem[] items) {
        return new Tile(items);
    }
}
