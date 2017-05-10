package com.helpme.app.world.item.visitor;

import com.helpme.app.world.character.target.ITarget;
import com.helpme.app.world.item.Consumable;
import com.helpme.app.world.item.Item;
import com.helpme.app.world.item.Key;
import com.helpme.app.world.item.effect.IEffect;

/**
 * Created by kopa on 2017-04-10.
 */
public final class Selfie implements IItemVisitor {
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
