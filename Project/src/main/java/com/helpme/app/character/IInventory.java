package com.helpme.app.character;

import com.helpme.app.item.IItem;
import com.helpme.app.item.IKey;
import com.helpme.app.item.Item;

/**
 * Created by kopa on 2017-04-08.
 */
public interface IInventory {
    boolean hasItem(Item item);
    IItem getItem(Item item);
    IItem getItem(int index);
    IItem getActiveItem();
    boolean addItem(IItem item);
    boolean removeItem(IItem item);
    void addKey(IKey key);
    void changeActiveItem(int itemIndex);
    int itemLimit();
}
