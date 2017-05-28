package com.helpme.app.effecttest;

import com.helpme.app.game.model.item.effect.ITarget;

/**
 * Created by kopa on 2017-05-28.
 */
public class MockTarget implements ITarget {
    float damage;
    float heal;

    @Override
    public void damage(float amount) {
        this.damage = amount;
    }

    @Override
    public void heal(float amount) {
        this.heal = amount;
    }

    @Override
    public boolean isDead() {
        return false;
    }
}
