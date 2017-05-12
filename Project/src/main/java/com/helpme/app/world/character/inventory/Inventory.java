package com.helpme.app.world.character.inventory;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IItemFactory;
import com.helpme.app.world.item.IReadItem;
import com.helpme.app.world.item.visitor.Stack;
import com.helpme.app.utils.Clone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 2017-04-09.
 */
public class Inventory implements IInventory {
    private IItem defaultItem;
    private List<IItem> keychain;
    private IItem[] items;
    private int activeItemIndex = -1;

    public Inventory(IItem[] items, IItem defaultItem, IItem[] keychain) {
        this.defaultItem = defaultItem == null ? IItemFactory.nothing() : defaultItem.clone();
        this.items = items == null ? new IItem[0] : Clone.array(items);
        this.keychain = keychain == null ? new ArrayList<>() : Clone.toList(keychain);
    }

    @Override
    public boolean hasItem(IItem item) {
        for (IItem monsterItem : items) {
            if (item.equals(monsterItem)) return true;
        }
        return false;
    }

    @Override
    public Maybe<IItem> getItem(int index) {
        return Maybe.wrap(index < 0 || index >= items.length ? null : items[index]);
    }

    @Override
    public Maybe<IItem> getActiveItem() {
        if(activeItemIndex < 0 || items == null || activeItemIndex >= items.length) return new Nothing();
        return Maybe.wrap(items[activeItemIndex]);
    }

    @Override
    public Maybe<IItem> getDefaultItem() {
        return Maybe.wrap(defaultItem);
    }

    @Override
    public void addKey(IItem key) {
        keychain.add(key);
    }

    @Override
    public boolean addItem(IItem item) {
        return setItem(null, item);
    }

    @Override
    public boolean deleteItem(IItem item) {
        return setItem(item, null);
    }

    @Override
    public Maybe<IItem> dropItem(int index) {
        if(index < 0 || index >= items.length) return new Nothing();
        IItem item = items[index];
        items[index] = null;
        return Maybe.wrap(item);
    }

    private boolean setItem(IItem from, IItem to) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == from) {
                items[i] = to;
                return true;
            }
        }
        return false;
    }

    @Override
    public void setItems(Maybe<IItem[]> items) {
        items.run(is -> this.items = is);
    }

    @Override
    public boolean addStack(IItem item, int amount) {
        for (IItem stack : items) {
            if (item.equals(stack)) {
                stack.accept(new Stack(amount));
                return true;
            }
        }
        return false;
    }


    @Override
    public void changeActiveItem(int itemIndex) {
        activeItemIndex = itemIndex == -1 ? -1 : Math.floorMod(itemIndex, items.length);
    }

    @Override
    public int getSize() {
        return items.length;
    }

    @Override
    public IItem[] dropItems() {
        return cloneItems();
    }

    @Override
    public Maybe<IReadItem[]> readItems() {
        return Maybe.wrap(items);
    }

    @Override
    public Maybe<IReadItem[]> readKeychain() {
        return Maybe.wrap((IReadItem[])keychain.toArray());
    }

    @Override
    public Maybe<IReadItem> readItem(int index) {
        return Maybe.wrap(getItem(index));
    }

    @Override
    public boolean hasKey(IItem key) {
        return keychain.contains(key);
    }

    @Override
    public IInventory clone() {
        return new Inventory(cloneItems(), defaultItem.clone(), cloneKeychain());
    }

    private IItem[] cloneItems() {
        if (items == null) {
            return null;
        }
        IItem[] clonedItems = new IItem[items.length];
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                continue;
            }
            clonedItems[i] = items[i].clone();
        }
        return clonedItems;
    }

    private IItem[] cloneKeychain() {
        IItem[] clonedKeychain = new IItem[keychain.size()];
        for (int i = 0; i < keychain.size(); i++) {
            clonedKeychain[i] = keychain.get(i).clone();
        }
        return clonedKeychain;
    }
}
