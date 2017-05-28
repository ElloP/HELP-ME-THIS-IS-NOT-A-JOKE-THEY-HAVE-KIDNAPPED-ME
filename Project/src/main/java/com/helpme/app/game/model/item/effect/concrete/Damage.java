package com.helpme.app.game.model.item.effect.concrete;

import com.helpme.app.game.model.item.effect.IEffect;
import com.helpme.app.game.model.item.effect.ITarget;

/**
 * Created by kopa on 2017-05-28.
 */
public class Damage implements IEffect {
    private float damage;

    public Damage(float damage){
        this.damage = Math.max(damage, 0);
    }

    public float getAmount(){
        return damage;
    }

    @Override
    public void apply(ITarget target) {
        target.damage(damage);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Damage && Math.abs(((Damage) o).damage - damage) < .0000001;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
