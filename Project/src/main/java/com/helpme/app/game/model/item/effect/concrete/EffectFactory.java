package com.helpme.app.game.model.item.effect.concrete;

import com.helpme.app.game.model.item.effect.IEffect;

/**
 * Created by Jacob on 2017-04-10.
 */
public final class EffectFactory {
    private EffectFactory() {

    }

    public static IEffect createDamage(float amount) {
        return new Damage(amount);
    }

    public static IEffect createHeal(float amount) {
        return new Heal(amount);
    }

    public static IEffect createEmpty() {
        return t -> {
        };
    }
}
