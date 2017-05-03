package com.helpme.app.world.item;

/**
 * Created by Jacob on 2017-04-09.
 */
public interface ITarget {
    void damage(float amount);
    void heal(float amount);
    boolean isDead();
}
