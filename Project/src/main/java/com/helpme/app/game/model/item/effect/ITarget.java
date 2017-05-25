package com.helpme.app.game.model.item.effect;

/**
 * Created by Jacob on 2017-04-09.
 */
public interface ITarget {
    void damage(float amount);
    void heal(float amount);
    boolean isDead();
}
