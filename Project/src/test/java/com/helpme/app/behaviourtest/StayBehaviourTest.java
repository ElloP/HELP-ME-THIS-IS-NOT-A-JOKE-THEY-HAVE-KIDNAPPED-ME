package com.helpme.app.behaviourtest;

import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.consciousness.behaviour.IBehaviour;
import com.helpme.app.world.consciousness.behaviour.concrete.BehaviourFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by kopa on 2017-05-21.
 */
public class StayBehaviourTest {
    private MockBody mockBody;
    private MockPlayer mockPlayer;
    private MockMemory mockMemory;
    private IBehaviour stayBehaviour;

    @Before
    public void setup(){
        mockBody = new MockBody();
        mockPlayer = new MockPlayer();
        mockMemory = new MockMemory();

        stayBehaviour = BehaviourFactory.createStay(
                0,
                null);
    }


    @Test
    public void testStay() {
        mockMemory.memory = new HashMap<>();

        Maybe<IAction<IConsciousness>> maybeAction = stayBehaviour.execute(mockBody, null, null);
        assert (maybeAction.isNothing() && mockMemory.readMemory().size() == 0);
    }
}
