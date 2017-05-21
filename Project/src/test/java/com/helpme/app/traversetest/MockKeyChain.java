package com.helpme.app.traversetest;

import com.helpme.app.world.body.inventory.IKeyChain;
import com.helpme.app.world.item.IItem;

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
