package com.helpme.app.bodytest;

import com.helpme.app.world.character.target.ITarget;

/**
 * Created by kopa on 2017-05-11.
 */
public class MockTarget implements ITarget {
    private boolean dead;

    @Override
    public void damage(float amount) {
        dead = true;
    }

    @Override
    public void heal(float amount) {

    }

    @Override
    public boolean isDead() {
        return dead;
    }
}
