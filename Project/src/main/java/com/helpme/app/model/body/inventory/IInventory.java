package com.helpme.app.model.body.inventory;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.model.item.IItem;
import com.helpme.app.utils.interfaces.ICopyable;

import java.util.List;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface IInventory extends IKeyChain, ICopyable<IInventory>, IReadInventory {
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
    void setActiveItem(int itemIndex);
}
