package com.helpme.app.game.model.body.inventory.concrete;

import com.helpme.app.game.model.body.inventory.IInventory;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.concrete.ItemFactory;
import com.helpme.app.utils.Copy;
import com.helpme.app.utils.maybe.Maybe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kopa on 2017-05-12.
 */
public final class InventoryFactory {
    private InventoryFactory(){

    }

    public static IInventory createInventory(IItem[] items, IItem defaultItem, IItem[] keychain){
        return createInventory(items == null ? new ArrayList<>() : Copy.toMaybeList(items), defaultItem, keychain == null ? new ArrayList<>() : Copy.toMaybeList(keychain));
    }

    public static IInventory createInventory(List<Maybe<IItem>> items, IItem defaultItem, List<Maybe<IItem>> keychain){
        return new Inventory(items == null ? new ArrayList<>() : items,
                defaultItem == null ? ItemFactory.nothing() : defaultItem,
                keychain == null ? new ArrayList<>() : keychain);
    }
}
