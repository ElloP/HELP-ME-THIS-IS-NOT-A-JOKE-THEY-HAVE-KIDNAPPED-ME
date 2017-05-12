package com.helpme.app.world.character.inventory;

import com.helpme.app.utils.Clone;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.ItemFactory;

import java.util.ArrayList;

/**
 * Created by kopa on 2017-05-12.
 */
public final class InventoryFactory {
    private InventoryFactory(){

    }

    public static IInventory createInventory(IItem[] items, IItem defaultItem, IItem[] keychain){
        return new Inventory(items == null ? new IItem[0] : items, defaultItem == null ? ItemFactory.nothing() : defaultItem, keychain == null ? new ArrayList<>() : Clone.toList(keychain));
    }
}
