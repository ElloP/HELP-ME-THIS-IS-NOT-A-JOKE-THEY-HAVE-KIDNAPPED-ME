package com.helpme.app.game.model.item;

/**
 * Created by kopa on 2017-05-18.
 *
 * Interface for consumable items
 * They can be stacked on top of each other
 *
 */
public interface IConsumable extends IItem, IApply {
    void addStacks(int amount);
    void removeStack();
    boolean isEmpty();
    int getStacks();
}
