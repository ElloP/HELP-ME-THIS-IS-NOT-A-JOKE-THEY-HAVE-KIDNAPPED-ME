package com.helpme.app.inventorytest;

import com.helpme.app.game.model.item.IConsumable;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IItemVisitor;
import com.helpme.app.game.model.item.effect.IEffect;

/**
 * Created by kopa on 2017-05-19.
 */
public class MockConsumable implements IConsumable {
    private String name;
    int stacks;

    public MockConsumable(String name){
        this.name = name;
    }

    @Override
    public String readName() {
        return name;
    }

    @Override
    public void addStacks(int amount) {
        stacks += amount;
    }

    @Override
    public void removeStack() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getStacks() {
        return 0;
    }

    @Override
    public IEffect getAttackEffect() {
        return null;
    }

    @Override
    public IEffect getSelfieEffect() {
        return null;
    }

    @Override
    public IItem copy() {
        return new MockConsumable(name);
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof IConsumable)) {
            return false;
        }

        return ((IConsumable)o).readName().equals(name);
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }
}
