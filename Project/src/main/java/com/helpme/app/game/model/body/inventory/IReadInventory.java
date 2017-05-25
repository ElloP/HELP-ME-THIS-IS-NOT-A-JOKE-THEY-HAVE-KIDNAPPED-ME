package com.helpme.app.game.model.body.inventory;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IReadItem;
import com.helpme.app.utils.maybe.Maybe;

import java.util.List;

/**
 * Created by kopa on 2017-04-21.
 */
public interface IReadInventory {
    int readSize();
    int readActiveItemIndex();
    List<Maybe<IReadItem>> readItems();
    List<Maybe<IReadItem>> readKeychain();
    Maybe<IReadItem> readItem(int index);
    Maybe<IReadItem> readDefaultItem();
    boolean hasItem(IItem item);
}
