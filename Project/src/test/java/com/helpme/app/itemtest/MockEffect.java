package com.helpme.app.itemtest;

import com.helpme.app.game.model.item.effect.IEffect;
import com.helpme.app.game.model.item.effect.ITarget;

/**
 * Created by kopa on 2017-05-28.
 */
public class MockEffect implements IEffect {
    private boolean equals;

    public MockEffect(boolean equals){
        this.equals = equals;
    }

    @Override
    public void apply(ITarget arg) {

    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof MockEffect)) {
            return false;
        }
        return equals;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
