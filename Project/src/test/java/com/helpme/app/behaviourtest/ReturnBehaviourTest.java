package com.helpme.app.behaviourtest;

import com.helpme.app.model.consciousness.behaviour.concrete.Return;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.model.consciousness.IConsciousness;
import com.helpme.app.model.consciousness.behaviour.IBehaviour;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by kopa on 2017-05-21.
 */
public class ReturnBehaviourTest {
    private IBehaviour returnBehaviour;
    private MockBody mockBody;
    private MockPlayer mockPlayer;
    private MockSurroundings mockSurroundings;
    private MockMemory mockMemory;
    private MockConsciousness mockConsciousness;

    @Before
    public void setup(){
        mockBody = new MockBody();
        mockPlayer = new MockPlayer();
        mockSurroundings = new MockSurroundings(mockPlayer);
        mockMemory = new MockMemory();
        mockConsciousness = new MockConsciousness();
        returnBehaviour = new Return(
                0,
                null,
                "returning",
                "returned");
    }

    @Test
    public void testReturnReturned() {
        mockMemory.memory = new HashMap<>();
        mockSurroundings.pathCost = 0;

        Maybe<IAction<IConsciousness>> maybeAction = returnBehaviour.execute(mockBody, mockSurroundings, mockMemory);

        assert (maybeAction.isNothing() &&
                mockMemory.readMemory().size() == 1 &&
                mockMemory.readMemory().get("returned").equals(1));
    }

    @Test
    public void testReturnReturningFacing() {
        mockMemory.memory = new HashMap<>();
        mockSurroundings.pathCost = 2;
        mockSurroundings.pathNextPosition = Vector2f.NORTH;

        Maybe<IAction<IConsciousness>> maybeAction = returnBehaviour.execute(mockBody, mockSurroundings, mockMemory);
        maybeAction.run(action -> action.apply(mockConsciousness));

        assert (maybeAction.isJust() &&
                mockConsciousness.movedForward == 1 &&
                mockMemory.readMemory().size() == 1 &&
                mockMemory.readMemory().get("returning").equals(1));
    }

    @Test
    public void testReturnReturningRightOf() {
        mockMemory.memory = new HashMap<>();
        mockSurroundings.pathCost = 2;
        mockSurroundings.pathNextPosition = Vector2f.EAST;

        Maybe<IAction<IConsciousness>> maybeAction = returnBehaviour.execute(mockBody, mockSurroundings, mockMemory);
        maybeAction.run(action -> action.apply(mockConsciousness));

        assert (maybeAction.isJust() &&
                mockConsciousness.rotatedRight == 1 &&
                mockMemory.readMemory().size() == 1 &&
                mockMemory.readMemory().get("returning").equals(1));
    }

    @Test
    public void testReturnReturningBehind() {
        mockMemory.memory = new HashMap<>();
        mockSurroundings.pathCost = 2;
        mockSurroundings.pathNextPosition = Vector2f.SOUTH;

        Maybe<IAction<IConsciousness>> maybeAction = returnBehaviour.execute(mockBody, mockSurroundings, mockMemory);
        maybeAction.run(action -> action.apply(mockConsciousness));

        assert (maybeAction.isJust() &&
                mockConsciousness.rotatedLeft == 1 &&
                mockMemory.readMemory().size() == 1 &&
                mockMemory.readMemory().get("returning").equals(1));
    }

    @Test
    public void testReturnReturningLeftOf() {
        mockMemory.memory = new HashMap<>();
        mockSurroundings.pathCost = 2;
        mockSurroundings.pathNextPosition = Vector2f.WEST;

        Maybe<IAction<IConsciousness>> maybeAction = returnBehaviour.execute(mockBody, mockSurroundings, mockMemory);
        maybeAction.run(action -> action.apply(mockConsciousness));

        assert (maybeAction.isJust() &&
                mockConsciousness.rotatedLeft == 1 &&
                mockMemory.readMemory().size() == 1 &&
                mockMemory.readMemory().get("returning").equals(1));
    }
}
