package com.helpme.app.edgetest.unlocktest;

import com.helpme.app.game.model.body.inventory.IKeyChain;
import com.helpme.app.game.model.item.IItem;

/**
 * Created by kopa on 2017-05-20.
 */
public class MockKeyChain implements IKeyChain {
    boolean hasKey;

    @Override
    public boolean hasKey(IItem key) {
        return hasKey;
    }
}
