package com.helpme.app.item.effect;

import com.helpme.app.character.ITarget;
import com.helpme.app.utils.functions.Action;

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
