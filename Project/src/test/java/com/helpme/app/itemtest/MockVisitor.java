package com.helpme.app.itemtest;

import com.helpme.app.game.model.item.IConsumable;
import com.helpme.app.game.model.item.IItemVisitor;
import com.helpme.app.game.model.item.IKey;
import com.helpme.app.game.model.item.ISingle;

/**
 * Created by kopa on 2017-05-21.
 */
public class MockVisitor implements IItemVisitor<Boolean> {
    @Override
    public Boolean visit(IConsumable consumable) {
        return true;
    }

    @Override
    public Boolean visit(ISingle item) {
        return true;
    }

    @Override
    public Boolean visit(IKey key) {
        return true;
    }
}
