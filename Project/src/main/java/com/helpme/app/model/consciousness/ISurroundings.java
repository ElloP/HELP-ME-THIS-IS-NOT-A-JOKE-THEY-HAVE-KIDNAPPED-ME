package com.helpme.app.model.consciousness;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.model.item.IItem;

import java.util.List;

/**
 * Created by kopa on 2017-04-14.
 */
public interface ISurroundings extends IReadSurroundings {
    void addTileItem(Vector2f position, IItem item);
    void addTileItems(Vector2f position, List<Maybe<IItem>> items);
    Maybe<List<Maybe<IItem>>> removeTileItems(Vector2f position);
    Maybe<IItem> removeTileItem(Vector2f position, int index);
    void updateDeadBody(Vector2f position);

}
