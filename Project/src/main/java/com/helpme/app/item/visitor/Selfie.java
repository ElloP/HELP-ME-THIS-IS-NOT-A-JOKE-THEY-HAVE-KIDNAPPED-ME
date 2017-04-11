package com.helpme.app.item.visitor;

import com.helpme.app.character.ITarget;
import com.helpme.app.item.Consumable;
import com.helpme.app.item.Item;
import com.helpme.app.item.Key;
import com.helpme.app.item.effect.IEffect;

/**
 * Created by kopa on 2017-04-10.
 */
public class Selfie implements IItemVisitor {
    private final ITarget self;

    public Selfie(ITarget self){
        this.self = self;
    }

    @Override
    public boolean visit(Consumable consumable) {
        if(consumable.isEmpty()) return false;
        consumable.removeStack();
        apply(consumable);
        return true;
    }

    @Override
    public boolean visit(Item item) {
        apply(item);
        return true;
    }

    @Override
    public boolean visit(Key key) {
        return false;
    }

    public boolean apply(Item item){
        IEffect effect = item.getSelfieEffect();
        effect.apply(self);
        return true;
    }
}
