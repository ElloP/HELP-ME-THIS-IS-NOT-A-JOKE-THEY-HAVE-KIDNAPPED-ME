package com.helpme.app.item;

import com.helpme.app.character.IStats;
import com.helpme.app.item.effect.IEffect;

/**
 * Created by kopa on 2017-04-09.
 */
public class Consumable extends Item {


    public Consumable(String name, int stacks, IEffect attackEffect, IEffect selfieEffect) {
        super(name, attackEffect, selfieEffect);
        setStacks(stacks);
    }

    private void setStacks(int stacks){
        this.stacks = stacks <= 0 ? 0 : stacks;
    }

    @Override
    public boolean addStack(){
        stacks++;
        return true;
    }

    @Override
    public boolean removeStack(){
        stacks--;
        return true;
    }


    private boolean isEmpty(){
        return stacks <= 0;
    }

    @Override
    public void attack(IStats stats) {
        if(isEmpty()) return;
        removeStack();
        super.attack(stats);
    }

    @Override
    public void selfie(IStats stats){
        if(isEmpty()) return;
        removeStack();
        super.selfie(stats);
    }
}
