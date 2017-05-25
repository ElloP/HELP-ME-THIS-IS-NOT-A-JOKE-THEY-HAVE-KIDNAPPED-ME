package com.helpme.app.itemtest.attacktest;

import com.helpme.app.game.model.item.effect.ITarget;

/**
 * Created by kopa on 2017-05-20.
 */
public class MockTarget implements ITarget {
    int attacked;

    @Override
    public void damage(float amount) {
        attacked++;
    }

    @Override
    public void heal(float amount) {

    }

    @Override
    public boolean isDead() {
        return false;
    }
}
