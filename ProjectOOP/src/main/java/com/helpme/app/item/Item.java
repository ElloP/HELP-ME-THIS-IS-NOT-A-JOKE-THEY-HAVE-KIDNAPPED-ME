package com.helpme.app.item;

/**
 * Created by kopa on 2017-03-30.
 */
public abstract class Item {
    private int damage;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public abstract void useOnSelf();
    public abstract void attackWith();
}
