package com.helpme.app.game.model.item.concrete;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IItemVisitor;
import com.helpme.app.game.model.item.ISingle;
import com.helpme.app.game.model.item.effect.IEffect;

/**
 * Created by kopa on 2017-05-18.
 */
public class Single extends Item implements ISingle {
    public Single(String name, IEffect attack, IEffect selfie) {
        super(name, attack, selfie);
    }

    @Override
    public IItem copy() {
        return new Single(name, getAttackEffect(), getSelfieEffect());
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        return o != null &&
                o instanceof Single &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return ("single" + name.hashCode()).hashCode();
    }
}
