package com.helpme.app.item.visitor;

import com.helpme.app.item.Consumable;
import com.helpme.app.item.Item;
import com.helpme.app.item.Key;

/**
 * Created by kopa on 2017-04-10.
 */
public class Stack implements IItemVisitor {
    private final int amount;

    public Stack(int amount){
        this.amount = amount;
    }

    @Override
    public boolean visit(Consumable consumable) {
        consumable.addStack(amount);
        return true;
    }

    @Override
    public boolean visit(Item item) {
        return false;
    }

    @Override
    public boolean visit(Key key) {
        return false;
    }
}
