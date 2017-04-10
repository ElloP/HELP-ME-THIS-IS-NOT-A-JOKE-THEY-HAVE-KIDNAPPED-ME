package com.helpme.app.character;

import com.helpme.app.item.IItem;
import com.helpme.app.item.Item;

/**
 * Created by kopa on 2017-04-08.
 */
public interface IInventory {
    boolean hasItem(Item item);
    IItem getActiveItem();
    void addItem(IItem item);
    void removeItem(IItem item);
    void changeActiveItem(int itemIndex);
    int itemLimit();
}
