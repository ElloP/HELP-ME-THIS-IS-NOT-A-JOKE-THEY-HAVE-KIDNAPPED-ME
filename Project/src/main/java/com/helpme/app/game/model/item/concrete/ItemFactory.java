package com.helpme.app.game.model.item.concrete;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.effect.IEffect;
import com.helpme.app.game.model.item.effect.concrete.EffectFactory;

/**
 * Created by kopa on 2017-04-10.
 *
 * Factory for creating items
 *
 */
public final class ItemFactory {
    private ItemFactory(){

    }

    public static IItem fists() {
        return new Single("Fists", EffectFactory.createDamage(1), EffectFactory.createDamage(1));
    }

    public static IItem nothing(){
        return new Single("Nothing", stats -> {}, stats -> {});
    }

    public static IItem club() {
        return new Single("Club", EffectFactory.createDamage(10), EffectFactory.createDamage(5));
    }

    public static IItem potion() { return new Consumable("Potion", 1, EffectFactory.createHeal(10), EffectFactory.createHeal(10)); }

    public static IItem createConsumable(String name, int stacks, IEffect attack, IEffect selfie){
        return new Consumable(name, stacks, attack == null ? EffectFactory.createEmpty() : attack, selfie == null ? EffectFactory.createEmpty() : selfie);
    }

    public static IItem createSingle(String name, IEffect attack, IEffect selfie){
        return new Single(name, attack == null ? EffectFactory.createEmpty() : attack, selfie == null ? EffectFactory.createEmpty() : selfie);
    }

    public static IItem createKey(String name){
        return new Key(name);
    }
}
