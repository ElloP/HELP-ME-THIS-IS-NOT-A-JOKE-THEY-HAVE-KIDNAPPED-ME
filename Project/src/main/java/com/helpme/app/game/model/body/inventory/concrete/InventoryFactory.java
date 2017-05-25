package com.helpme.app.game.model.body.inventory.concrete;

import com.helpme.app.game.model.body.inventory.IInventory;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.concrete.ItemFactory;
import com.helpme.app.utils.Copy;

import java.util.ArrayList;

/**
 * Created by kopa on 2017-05-12.
 */
public final class InventoryFactory {
    private InventoryFactory(){

    }

    public static IInventory createInventory(IItem[] items, IItem defaultItem, IItem[] keychain){
        return new Inventory(items == null ? new ArrayList<>() : Copy.toMaybeList(items), defaultItem == null ? ItemFactory.nothing() : defaultItem, keychain == null ? new ArrayList<>() : Copy.toMaybeList(keychain));
    }
}
