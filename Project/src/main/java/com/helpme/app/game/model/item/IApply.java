package com.helpme.app.game.model.item;

import com.helpme.app.game.model.item.effect.IEffect;

/**
 * Created by kopa on 2017-05-18.
 *
 * Interface for checking the effect of an item
 *
 */
public interface IApply {
    IEffect getAttackEffect();
    IEffect getSelfieEffect();
}
