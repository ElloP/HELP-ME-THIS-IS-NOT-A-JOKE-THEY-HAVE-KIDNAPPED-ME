package com.helpme.app.tile;

import com.helpme.app.item.IItem;

import java.util.List;

/**
 * Created by kopa on 2017-04-10.
 */
public interface ITileFactory {
    static ITile tile(List<IItem> items) {
        return new Tile(items);
    }
}
