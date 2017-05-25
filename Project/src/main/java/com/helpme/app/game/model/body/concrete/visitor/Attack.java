package com.helpme.app.game.model.body.concrete.visitor;

import com.helpme.app.game.model.item.*;
import com.helpme.app.game.model.item.effect.IEffect;
import com.helpme.app.game.model.item.effect.ITarget;

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
