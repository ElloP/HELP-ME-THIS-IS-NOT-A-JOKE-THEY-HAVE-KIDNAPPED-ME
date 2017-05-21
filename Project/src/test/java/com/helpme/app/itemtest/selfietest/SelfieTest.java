package com.helpme.app.itemtest.selfietest;


import com.helpme.app.model.body.concrete.visitor.Selfie;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-20.
 */
public class SelfieTest {
    MockKey mockKey;
    MockSingle mockSingle;
    MockConsumable mockConsumable;
    MockTarget mockTarget;

    @Before
    public void setup(){
        mockKey = new MockKey();
        mockSingle = new MockSingle();
        mockConsumable = new MockConsumable();
        mockTarget = new MockTarget();
    }

    @Test
    public void testSelfieWithKey(){
        assert (!mockKey.accept(new Selfie(mockTarget)) && mockTarget.selfied == 0);
    }

    @Test
    public void testSelfieWithSingle(){
        assert (mockSingle.accept(new Selfie(mockTarget)) && mockTarget.selfied == 1);
    }

    @Test
    public void testSelfieWithConsumable(){
        assert (mockConsumable.accept(new Selfie(mockTarget)) && mockTarget.selfied == 1 && mockConsumable.stacks == -1);
    }

    @Test
    public void testSelfieWithNoConsumable(){
        mockConsumable.empty = true;
        assert (!mockConsumable.accept(new Selfie(mockTarget)) && mockTarget.selfied == 0 && mockConsumable.stacks == 0);
    }


}
