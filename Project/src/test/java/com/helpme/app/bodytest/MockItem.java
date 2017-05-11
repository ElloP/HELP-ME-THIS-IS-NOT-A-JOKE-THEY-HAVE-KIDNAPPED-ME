package com.helpme.app.bodytest;

import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.Item;
import com.helpme.app.world.item.visitor.IItemVisitor;

/**
 * Created by kopa on 2017-05-11.
 */
public interface MockItem {
    static IItem weapon() {
        return new Item("Club", target -> target.damage(1), target -> target.damage(1));
    }
}
