package com.helpme.app.item.effect;

/**
 * Created by kopa on 2017-04-10.
 */
public interface IEffectFactory {
    static IEffect damage(float amount){
        return stats -> stats.damage(amount);
    }

    static IEffect heal(float amount){
        return stats -> stats.heal(amount);
    }
}
