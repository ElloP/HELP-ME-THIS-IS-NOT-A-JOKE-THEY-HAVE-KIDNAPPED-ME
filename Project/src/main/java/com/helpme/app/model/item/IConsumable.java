package com.helpme.app.model.item;

/**
 * Created by kopa on 2017-05-18.
 */
public interface IConsumable extends IItem, IApply {
    void addStacks(int amount);
    void removeStack();
    boolean isEmpty();
    int getStacks();
}
