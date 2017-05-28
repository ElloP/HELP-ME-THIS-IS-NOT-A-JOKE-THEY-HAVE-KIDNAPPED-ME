package com.helpme.app.game.model.item.effect.concrete;

import com.helpme.app.game.model.item.effect.IEffect;
import com.helpme.app.game.model.item.effect.ITarget;

/**
 * Created by kopa on 2017-05-28.
 */
public class Heal implements IEffect {
    float healing;

    public Heal(float healing) {
        this.healing = Math.max(healing, 0);
    }

    public float getAmount() {
        return healing;
    }

    @Override
    public void apply(ITarget target) {
        target.heal(healing);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Heal &&
                Math.abs(((Heal) o).healing - healing) < .0000001;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
