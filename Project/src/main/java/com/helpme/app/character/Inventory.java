package com.helpme.app.character;

import com.helpme.app.item.IItem;
import com.helpme.app.item.IKey;
import com.helpme.app.item.visitor.Increment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 2017-04-09.
 */
public class Inventory implements IInventory {
    private IItem defaultItem;
    private List<IKey> keychain;
    private IItem[] items;
    private int activeItemIndex = -1;

    public Inventory(IItem[] items, IItem defaultItem, List<IKey> keychain) {
        this.defaultItem = defaultItem;
        this.items = items;
        this.keychain = keychain;
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
        return null;
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
    public void addKey(IKey key) {
        keychain.add(key);
    }

    @Override
    public boolean addItem(IItem item) {
        return setItem(null, item);
    }

    @Override
    public boolean removeItem(IItem item) {
        return setItem(item, null);
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
    public boolean addStack(IItem item) {
        for (IItem stack : items) {
            if (item.equals(stack)) {
                stack.accept(new Increment());
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
    public boolean hasKey(IKey key) {
        return keychain.contains(key);
    }

    @Override
    public IInventory clone() {
        return new Inventory(cloneItems(), defaultItem.clone(), cloneKeychain());
    }

    private IItem[] cloneItems() {
        IItem[] cloneItems = new IItem[items.length];
        for (int i = 0; i < items.length; i++) {
            cloneItems[i] = items[i].clone();
        }
        return cloneItems;
    }

    private List<IKey> cloneKeychain() {
        List<IKey> cloneKeychain = new ArrayList<>();
        for (IKey key : keychain) {
            cloneKeychain.add(key.copy());
        }
        return cloneKeychain;
    }
}
