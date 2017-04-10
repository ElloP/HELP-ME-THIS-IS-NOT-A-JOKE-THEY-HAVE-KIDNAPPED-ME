package com.helpme.app.item;

import com.helpme.app.item.effect.IEffectFactory;

/**
 * Created by kopa on 2017-04-10.
 */
public interface IItemFactory {

    static IItem fists() {
        return new Item("Fists", IEffectFactory.damage(1), IEffectFactory.damage(1));
    }

    static IItem club() {
        return new Item("Club", IEffectFactory.damage(10), IEffectFactory.damage(5));
    }

    static IItem redKey() {
        return new Key("Red Key");
    }

    static IItem skeletonKey() {
        return new Key("Skeleton Key");
    }
}
