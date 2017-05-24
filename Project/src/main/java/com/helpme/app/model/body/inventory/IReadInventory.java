package com.helpme.app.model.body.inventory;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IReadItem;

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
    boolean hasItem(IItem item);
}
