package com.helpme.app.character;

import com.helpme.app.item.IItem;
import com.helpme.app.item.IKey;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface IInventory extends IKeyChain {
    boolean hasItem(IItem item);
    IItem getItem(int index);
    IItem getActiveItem();
    boolean addItem(IItem item);
    boolean removeItem(IItem item);
    boolean addStack(IItem item);
    void addKey(IKey key);
    void changeActiveItem(int itemIndex);
    int itemLimit();
}
