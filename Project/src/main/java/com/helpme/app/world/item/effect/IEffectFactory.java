package com.helpme.app.world.item.effect;

/**
 * Created by Jacob on 2017-04-10.
 */
public interface IEffectFactory {
    static IEffect damage(float amount){
        return target -> target.damage(amount);
    }

    static IEffect heal(float amount){
        return target -> target.heal(amount);
    }
}
