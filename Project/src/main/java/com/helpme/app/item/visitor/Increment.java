package com.helpme.app.item.visitor;

import com.helpme.app.character.IInventory;
import com.helpme.app.item.Consumable;
import com.helpme.app.item.Item;
import com.helpme.app.item.Key;

/**
 * Created by kopa on 2017-04-10.
 */
public class Increment implements IItemVisitor {
    @Override
    public boolean visit(Consumable consumable) {
        consumable.addStack();
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
