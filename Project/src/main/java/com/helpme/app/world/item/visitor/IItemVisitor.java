package com.helpme.app.world.item.visitor;

import com.helpme.app.world.item.Consumable;
import com.helpme.app.world.item.Item;
import com.helpme.app.world.item.Key;

/**
 * Created by kopa on 2017-04-10.
 */
public interface IItemVisitor <T> {
    T visit(Consumable consumable);
    T visit(Item item);
    T visit(Key key);
}
