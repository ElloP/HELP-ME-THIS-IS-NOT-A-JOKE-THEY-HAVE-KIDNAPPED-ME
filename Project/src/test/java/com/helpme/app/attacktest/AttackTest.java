package com.helpme.app.attacktest;

import com.helpme.app.model.body.concrete.visitor.Attack;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-20.
 */
public class AttackTest {
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
    public void testAttackWithKey(){
        assert (!mockKey.accept(new Attack(mockTarget))&& mockTarget.attacked == 0);
    }

    @Test
    public void testAttackWithSingle(){
        assert (mockSingle.accept(new Attack(mockTarget)) && mockTarget.attacked == 1);
    }

    @Test
    public void testAttackWithConsumable(){
        assert (mockConsumable.accept(new Attack(mockTarget)) && mockTarget.attacked == 1 && mockConsumable.stacks == -1);
    }

    @Test
    public void testAttackWithNoConsumable(){
        mockConsumable.empty = true;
        assert (!mockConsumable.accept(new Attack(mockTarget)) && mockTarget.attacked == 0 && mockConsumable.stacks == 0);
    }


}
