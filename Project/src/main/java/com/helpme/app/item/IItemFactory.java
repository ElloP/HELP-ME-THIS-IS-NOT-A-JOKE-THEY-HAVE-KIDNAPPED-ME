package com.helpme.app.item;

import com.helpme.app.item.effect.IEffectFactory;

/**
 * Created by kopa on 2017-04-10.
 */
public interface IItemFactory {

    static IItem fists() {
        return new Item("Fists", IEffectFactory.damage(1), IEffectFactory.damage(1));
    }

    static IItem nothing(){
        return new Item("Nothing", stats -> {}, stats -> {});
    }

    static IItem club() {
        return new Item("Club", IEffectFactory.damage(10), IEffectFactory.damage(5));
    }
}
