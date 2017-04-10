package com.helpme.app.character;

import com.helpme.app.item.IItem;
import com.helpme.app.item.IKey;
import com.helpme.app.item.Item;

import java.util.List;

/**
 * Created by kopa on 2017-04-09.
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
    public boolean hasItem(Item item) {
        for (IItem monsterItem : items) {
            if (item.equals(monsterItem)) return true;
        }
        return false;
    }

    @Override
    public IItem getItem(Item item) {
        for (IItem inventoryItem : items) {
            if(item.equals(inventoryItem)) {
                return inventoryItem;
            }
        }
        return null;
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
    public void changeActiveItem(int itemIndex) {
        if (itemIndex < 0 || itemIndex >= itemLimit()) return;
        activeItemIndex = itemIndex;
    }

    @Override
    public int itemLimit() {
        return items.length;
    }
}
