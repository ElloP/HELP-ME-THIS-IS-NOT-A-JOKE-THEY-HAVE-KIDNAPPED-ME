package com.helpme.app.item;

/**
 * Created by kopa on 2017-03-30.
 */

import com.helpme.app.character.Monster;

public abstract class Item {
    String name;

    public abstract void applyAttackEffect(Monster target);
    public abstract void applySelfEffect(Monster self);
}
