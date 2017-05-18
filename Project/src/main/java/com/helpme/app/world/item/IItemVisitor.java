package com.helpme.app.world.item;

/**
 * Created by kopa on 2017-04-10.
 */
public interface IItemVisitor <T> {
    T visit(IConsumable consumable);
    T visit(ISingle item);
    T visit(IKey key);
}
