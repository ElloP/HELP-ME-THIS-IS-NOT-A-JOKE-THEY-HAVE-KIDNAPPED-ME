package com.helpme.app.model.item;

import com.helpme.app.model.item.effect.IEffect;

/**
 * Created by kopa on 2017-05-18.
 */
public interface IApply {
    IEffect getAttackEffect();
    IEffect getSelfieEffect();
}
