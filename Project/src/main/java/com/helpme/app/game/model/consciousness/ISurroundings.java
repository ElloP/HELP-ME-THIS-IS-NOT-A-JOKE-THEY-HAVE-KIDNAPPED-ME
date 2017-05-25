package com.helpme.app.game.model.consciousness;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Maybe;

import java.util.List;

/**
 * Created by kopa on 2017-04-14.
 */
public interface ISurroundings extends IReadSurroundings {
    boolean addTileItem(Vector2f position, IItem item);
    boolean addTileItems(Vector2f position, List<Maybe<IItem>> items);
    Maybe<List<Maybe<IItem>>> removeTileItems(Vector2f position);
    Maybe<IItem> removeTileItem(Vector2f position, int index);
    void updateTile(Vector2f position);

}
