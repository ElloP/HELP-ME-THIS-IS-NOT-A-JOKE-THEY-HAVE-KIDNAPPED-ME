package com.helpme.app.itemtest.selfietest;

import com.helpme.app.game.model.item.effect.ITarget;

/**
 * Created by kopa on 2017-05-20.
 */
public class MockTarget implements ITarget {
    int selfied;

    @Override
    public void damage(float amount) {

    }

    @Override
    public void heal(float amount) {
        selfied++;
    }

    @Override
    public boolean isDead() {
        return false;
    }
}
