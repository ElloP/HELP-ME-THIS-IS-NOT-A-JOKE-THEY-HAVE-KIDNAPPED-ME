package com.helpme.app.world.body.inventory;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.item.IItem;
import com.helpme.app.utils.interfaces.ICloneable;

import java.util.List;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface IInventory extends IKeyChain, ICloneable<IInventory>, IReadInventory {
    Maybe<IItem> getItem(int index);
    Maybe<IItem> getActiveItem();
    Maybe<IItem> getDefaultItem();
    void setItems(List<Maybe<IItem>> items);
    boolean addItem(IItem item);
    boolean deleteItem(IItem item);
    Maybe<IItem> dropItem(int index);
    boolean addStack(IItem item, int amount);
    void addKey(IItem key);
    List<Maybe<IItem>> dropItems();
    void changeActiveItem(int itemIndex);
}