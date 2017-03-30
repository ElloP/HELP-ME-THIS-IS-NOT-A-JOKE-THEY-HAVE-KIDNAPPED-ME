package com.helpme.app.item;

/**
 * Created by kopa on 2017-03-30.
 */

import com.helpme.app.character.Character;

public abstract class Item {
    String name;

    public abstract void applyAttackEffect(Character target);
    public abstract void applySelfEffect(Character self);
}
