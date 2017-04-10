package com.helpme.app.character;

import com.helpme.app.item.IItem;
import com.helpme.app.item.Item;

/**
 * Created by kopa on 2017-04-09.
 */
public class Inventory implements IInventory {
    private IItem defaultItem;
    private IItem[] items;
    private int activeItemIndex = -1;

    public Inventory(IItem[] items, IItem defaultItem) {
        this.defaultItem = defaultItem;
        this.items = items;
    }

    @Override
    public boolean hasItem(Item item) {
        for (IItem monsterItem : items) {
            if (item.equals(monsterItem)) return true;
        }
        return false;
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
    public void addItem(IItem item) {

    }

    @Override
    public void removeItem(IItem item) {

    }

    @Override
    public void changeActiveItem(int itemIndex) {
        if(itemIndex < 0 || itemIndex >= itemLimit()) return;
        activeItemIndex = itemIndex;
    }

    @Override
    public int itemLimit() {
        return items.length;
    }
}
