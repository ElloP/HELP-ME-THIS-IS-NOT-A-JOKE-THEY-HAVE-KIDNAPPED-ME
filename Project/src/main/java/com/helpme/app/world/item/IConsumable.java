package com.helpme.app.world.item;

/**
 * Created by kopa on 2017-05-18.
 */
public interface IConsumable extends IItem, IApply {
    void addStack(int amount);
    void removeStack();
    boolean isEmpty();
    int getStacks();
}
