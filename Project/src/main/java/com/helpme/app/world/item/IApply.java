package com.helpme.app.world.item;

import com.helpme.app.world.item.effect.IEffect;

/**
 * Created by kopa on 2017-05-18.
 */
public interface IApply {
    IEffect getAttackEffect();
    IEffect getSelfieEffect();
}
