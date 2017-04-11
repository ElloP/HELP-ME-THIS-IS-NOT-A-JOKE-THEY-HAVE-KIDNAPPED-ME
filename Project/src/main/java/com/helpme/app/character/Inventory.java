package com.helpme.app.character;

import com.helpme.app.item.IItem;
import com.helpme.app.item.IItemFactory;
import com.helpme.app.item.visitor.Stack;
import com.helpme.app.utils.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
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
        this.items = Utilities.cloneArray(items);
        this.keychain = keychain == null ? new ArrayList<>() : Utilities.toList(keychain);
    }

    @Override
    public boolean hasItem(IItem item) {
        for (IItem monsterItem : items) {
            if (item.equals(monsterItem)) return true;
        }
        return false;
    }

    @Override
    public IItem getItem(int index) {
        try {
            return items[index];
        } catch (NullPointerException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public IItem getActiveItem() {
        try {
            IItem item = items[activeItemIndex];
            return item == null ? defaultItem : item;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
            return defaultItem;
        }
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
    public IItem dropItem(int index) {
        try {
            IItem item = items[index];
            items[index] = null;
            return item;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
            return null;
        }
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
    public void setItems(IItem[] items) {
        this.items = items;
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
    public int itemLimit() {
        return items.length;
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
