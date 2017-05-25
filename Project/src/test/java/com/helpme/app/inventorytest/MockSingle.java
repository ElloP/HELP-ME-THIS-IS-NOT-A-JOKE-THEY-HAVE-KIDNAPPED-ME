package com.helpme.app.inventorytest;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IItemVisitor;
import com.helpme.app.game.model.item.ISingle;
import com.helpme.app.game.model.item.effect.IEffect;

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
    public IItem copy() {
        return new MockSingle(name);
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof ISingle)) {
            return false;
        }

        return ((ISingle)o).readName().equals(name);
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }
}
