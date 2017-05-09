package com.helpme.app.world.item.visitor;

import com.helpme.app.world.character.target.ITarget;
import com.helpme.app.world.item.Consumable;
import com.helpme.app.world.item.Item;
import com.helpme.app.world.item.Key;
import com.helpme.app.world.item.effect.IEffect;

/**
 * Created by kopa on 2017-04-10.
 */
public class Attack implements IItemVisitor {
    private final ITarget target;

    public Attack(ITarget target) {
        this.target = target;
    }

    @Override
    public boolean visit(Consumable consumable) {
        if(consumable.isEmpty()) return false;
        consumable.removeStack();
        return visit(consumable);
    }

    @Override
    public boolean visit(Item item) {
        IEffect effect = item.getAttackEffect();
        effect.apply(target);
        return true;
    }

    @Override
    public boolean visit(Key key) {
        return false;
    }
}
