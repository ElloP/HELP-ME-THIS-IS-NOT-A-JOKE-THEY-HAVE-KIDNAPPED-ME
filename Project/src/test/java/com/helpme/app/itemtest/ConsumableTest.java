package com.helpme.app.itemtest;

import com.helpme.app.game.model.item.concrete.Consumable;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-24.
 */
public class ConsumableTest {
    Consumable consumable;

    @Before
    public void setup() {
        consumable = new Consumable("Test", 2, t -> {
        }, t -> {
        });
    }

    @Test
    public void testAddStacks(){
        consumable.addStacks(3);
        assert (consumable.getStacks() == 5);
    }

    /**
     * Tests that it's not possible to add negative numbers
     */
    @Test
    public void testAddStacksNegativeNumber(){
        consumable.addStacks(-1);
        assert (consumable.getStacks() == 2);
    }

    /**
     * Tests that removeStack only decrements 1 stack
     */
    @Test
    public void removeStack(){
        consumable.removeStack();
        assert (consumable.getStacks() == 1);
    }

    @Test
    public void getStacks(){
        assert (consumable.getStacks() == 2);
    }


}
