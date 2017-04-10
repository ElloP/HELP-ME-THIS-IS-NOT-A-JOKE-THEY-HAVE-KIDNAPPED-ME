package com.helpme.app.item.visitor;

import com.helpme.app.character.IStats;
import com.helpme.app.item.Consumable;
import com.helpme.app.item.Item;
import com.helpme.app.item.Key;
import com.helpme.app.item.effect.IEffect;

/**
 * Created by kopa on 2017-04-10.
 */
public class Selfie implements IItemVisitor {
    private final IStats self;

    public Selfie(IStats self){
        this.self = self;
    }

    @Override
    public boolean visit(Consumable consumable) {
        if(consumable.isEmpty()) return false;
        consumable.removeStack();
        visit(consumable);
        return true;
    }

    @Override
    public boolean visit(Item item) {
        IEffect effect = item.getSelfieEffect();
        effect.apply(self);
        return true;
    }

    @Override
    public boolean visit(Key key) {
        return false;
    }
}
