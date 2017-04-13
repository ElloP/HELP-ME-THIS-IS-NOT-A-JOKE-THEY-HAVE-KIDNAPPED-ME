package com.helpme.app.character.inventory;

import com.helpme.app.item.IItem;
import com.helpme.app.utils.interfaces.ICloneable;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface IInventory extends IKeyChain, ICloneable<IInventory> {
    boolean hasItem(IItem item);
    IItem getItem(int index);
    IItem getActiveItem();
    void setItems(IItem[] items);
    boolean addItem(IItem item);
    boolean deleteItem(IItem item);
    IItem dropItem(int index);
    boolean addStack(IItem item, int amount);
    void addKey(IItem key);
    void changeActiveItem(int itemIndex);
    int itemLimit();
}
