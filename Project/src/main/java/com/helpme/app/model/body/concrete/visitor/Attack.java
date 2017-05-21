package com.helpme.app.model.body.concrete.visitor;

import com.helpme.app.model.item.*;
import com.helpme.app.model.item.effect.ITarget;
import com.helpme.app.model.item.effect.IEffect;

/**
 * Created by kopa on 2017-04-10.
 */
public final class Attack implements IItemVisitor<Boolean> {
    private final ITarget target;

    public Attack(ITarget target) {
        this.target = target;
    }

    @Override
    public Boolean visit(IConsumable consumable) {
        if(consumable.isEmpty()) return false;
        consumable.removeStack();
        return attack(consumable);
    }

    @Override
    public Boolean visit(ISingle item) {
        return attack(item);
    }

    @Override
    public Boolean visit(IKey key) {
        return false;
    }

    private Boolean attack(IApply item){
        IEffect effect = item.getAttackEffect();
        effect.apply(target);
        return true;
    }
}
