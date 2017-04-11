package com.helpme.app.item.visitor;

import com.helpme.app.item.Consumable;
import com.helpme.app.item.Item;
import com.helpme.app.item.Key;

/**
 * Created by kopa on 2017-04-10.
 */
public interface IItemVisitor {
    boolean visit(Consumable consumable);
    boolean visit(Item item);
    boolean visit(Key key);
}
