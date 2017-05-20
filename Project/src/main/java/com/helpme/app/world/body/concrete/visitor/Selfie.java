package com.helpme.app.world.body.concrete.visitor;

import com.helpme.app.world.item.*;
import com.helpme.app.world.item.effect.ITarget;
import com.helpme.app.world.item.effect.IEffect;

/**
 * Created by kopa on 2017-04-10.
 */
public final class Selfie implements IItemVisitor<Boolean> {
    private final ITarget self;

    public Selfie(ITarget self){
        this.self = self;
    }

    @Override
    public Boolean visit(IConsumable consumable) {
        if(consumable.isEmpty()) return false;
        consumable.removeStack();
        return apply(consumable);
    }

    @Override
    public Boolean visit(ISingle item) {
        return apply(item);
    }

    @Override
    public Boolean visit(IKey key) {
        return false;
    }

    public boolean apply(IApply item){
        IEffect effect = item.getSelfieEffect();
        effect.apply(self);
        return true;
    }
}
