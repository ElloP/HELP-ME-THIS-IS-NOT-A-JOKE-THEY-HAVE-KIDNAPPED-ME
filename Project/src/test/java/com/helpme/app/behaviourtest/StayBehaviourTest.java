package com.helpme.app.behaviourtest;

import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.game.model.consciousness.behaviour.concrete.Stay;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by kopa on 2017-05-21.
 */
public class StayBehaviourTest {
    private MockBody mockBody;
    private MockMemory mockMemory;
    private IBehaviour stayBehaviour;

    @Before
    public void setup(){
        mockBody = new MockBody();
        mockMemory = new MockMemory();

        stayBehaviour = new Stay(
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
