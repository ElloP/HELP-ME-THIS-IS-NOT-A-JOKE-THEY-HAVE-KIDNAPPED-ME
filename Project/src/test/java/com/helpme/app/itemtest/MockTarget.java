package com.helpme.app.itemtest;

import com.helpme.app.game.model.item.effect.ITarget;

/**
 * Created by kopa on 2017-05-24.
 */
public class MockTarget implements ITarget {

    @Override
    public void damage(float amount) {

    }

    @Override
    public void heal(float amount) {

    }

    @Override
    public boolean isDead() {
        return false;
    }
}
