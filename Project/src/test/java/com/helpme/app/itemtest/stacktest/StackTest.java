package com.helpme.app.itemtest.stacktest;

import com.helpme.app.model.body.concrete.visitor.Stack;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-23.
 */
public class StackTest {
    MockConsumable mockConsumable;
    MockSingle mockSingle;
    MockKey mockKey;



    @Before
    public void setup(){
        mockKey = new MockKey();
        mockSingle = new MockSingle();
        mockConsumable = new MockConsumable();
    }

    @Test
    public void testStackKey(){
        assert (!mockKey.accept(new Stack(1)));
    }

    @Test
    public void testStackSingle(){
        assert (!mockSingle.accept(new Stack(1)));
    }

    @Test
    public void testStackConsumable(){
        assert (mockConsumable.accept(new Stack(2)) && mockConsumable.stacks == 2);
    }

    @Test
    public void testStackConsumableNegativeNumber(){
        assert (mockConsumable.accept(new Stack(-4)) && mockConsumable.stacks == 0);
    }
}
