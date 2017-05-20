package com.helpme.app.inventorytest;

import com.helpme.app.world.item.IItemVisitor;
import com.helpme.app.world.item.ISingle;
import com.helpme.app.world.item.concrete.Consumable;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.concrete.Item;
import com.helpme.app.world.item.concrete.Key;
import com.helpme.app.world.item.concrete.Single;
import com.helpme.app.world.item.effect.IEffect;

/**
 * Created by kopa on 2017-05-12.
 */
public final class MockSingle implements ISingle {

    private String name;

    public MockSingle(String name){
        this.name = name;
    }

    @Override
    public String readName() {
        return name;
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
    public IItem clone() {
        return new MockSingle(name);
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
