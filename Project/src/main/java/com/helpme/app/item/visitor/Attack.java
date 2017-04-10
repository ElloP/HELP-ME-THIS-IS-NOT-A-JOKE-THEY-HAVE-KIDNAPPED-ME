package com.helpme.app.item.visitor;

import com.helpme.app.character.IStats;
import com.helpme.app.item.Consumable;
import com.helpme.app.item.Item;
import com.helpme.app.item.Key;
import com.helpme.app.item.effect.IEffect;

/**
 * Created by kopa on 2017-04-10.
 */
public class Attack implements IItemVisitor {
    private final IStats target;

    public Attack(IStats target) {
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
