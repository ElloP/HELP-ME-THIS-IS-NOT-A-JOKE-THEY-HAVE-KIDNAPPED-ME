package com.helpme.app.inventorytest;

import com.helpme.app.world.item.concrete.Consumable;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.concrete.Item;
import com.helpme.app.world.item.concrete.Key;
import com.helpme.app.world.item.concrete.Single;

/**
 * Created by kopa on 2017-05-12.
 */
public final class MockItem {
    private MockItem() {

    }

    static IItem defaultItem() {
        return new Single("default", t -> {
        }, t -> {
        });
    }

    static IItem item() {
        return new Single("item", t -> {
        }, t -> {
        });
    }

    static IItem pickup() {
        return new Single("pickup", t -> {
        }, t -> {
        });
    }

    static IItem consumable() {
        return new Consumable("consumable, ", 2, t -> {
        }, t -> {
        });
    }

    static IItem key1(){
        return new Key("Key1");
    }

    static IItem key2(){
        return new Key("Key2");
    }

    static IItem key3(){
        return new Key("Key3");
    }

}
