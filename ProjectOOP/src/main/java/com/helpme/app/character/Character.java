package com.helpme.app.character;

/**
 * Created by kopa on 2017-03-30.
 */
public abstract class Character {
    float hitpoints;
    float damage;


    public abstract void attack();
    public abstract void self();
    public abstract void move();
}
