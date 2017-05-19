package com.helpme.app.world.item.concrete;

import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.effect.IEffectFactory;

/**
 * Created by kopa on 2017-04-10.
 */
public final class ItemFactory {
    private ItemFactory(){

    }

    public static IItem fists() {
        return new Single("Fists", IEffectFactory.damage(1), IEffectFactory.damage(1));
    }

    public static IItem nothing(){
        return new Single("Nothing", stats -> {}, stats -> {});
    }

    public static IItem club() {
        return new Single("Club", IEffectFactory.damage(10), IEffectFactory.damage(5));
    }

    public static IItem createItem(String item){
        switch (item){
            case "Fists": return fists();
            case "Club": return club();
            default: return nothing();
        }
    }

    public static IItem createKey(String name){
        return new Key(name);
    }
}