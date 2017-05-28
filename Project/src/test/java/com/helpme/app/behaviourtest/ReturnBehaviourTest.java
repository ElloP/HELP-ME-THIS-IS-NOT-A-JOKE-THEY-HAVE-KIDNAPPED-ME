package com.helpme.app.behaviourtest;

import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.game.model.consciousness.behaviour.concrete.Return;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by kopa on 2017-05-21.
 */
public class ReturnBehaviourTest {
    private Return returnConcrete;
    private IBehaviour returnBehaviour;
    private MockBody mockBody;
    private MockPlayer mockPlayer;
    private MockSurroundings mockSurroundings;
    private MockMemory mockMemory;
    private MockConsciousness mockConsciousness;

    @Before
    public void setup() {
        mockBody = new MockBody();
        mockPlayer = new MockPlayer();
        mockSurroundings = new MockSurroundings(mockPlayer);
        mockMemory = new MockMemory();
        mockConsciousness = new MockConsciousness();
        returnConcrete = new Return(
                0,
                null,
                "returning",
                "returned");
        returnBehaviour = returnConcrete;
    }

    @Test
    public void testGetReturnedEvent() {
        assert (returnConcrete.getReturnedEvent().equals("returned"));
    }

    @Test
    public void testGetReturningEvent() {
        assert (returnConcrete.getReturningEvent().equals("returning"));
    }

    @Test
    public void testReset() {
        mockMemory.memory = new HashMap<>();
        returnBehaviour.reset(mockMemory);
        assert (mockMemory.memory.size() == 2 &&
                mockMemory.memory.get("returning") == 0 &&
                mockMemory.memory.get("returned") == 0);
    }


    /**
     * Tests that the returnedEvent is set to 1 if returned to the startPosition
     */
    @Test
    public void testReturned() {
        mockMemory.memory = new HashMap<>();
        mockSurroundings.pathCost = 0;

        Maybe<IAction<IConsciousness>> maybeAction = returnBehaviour.execute(mockBody, mockSurroundings, mockMemory);

        assert (maybeAction.isNothing() &&
                mockMemory.readMemory().size() == 1 &&
                mockMemory.readMemory().get("returned").equals(1));
    }

    /**
     * Tests that the returningEvent is set to 1 if returning and that
     * it returns an action that actually moves it closer to the startPosition
     */
    @Test
    public void testReturningFacing() {
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
    public void testReturningRightOf() {
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
    public void testReturningBehind() {
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
    public void testReturningLeftOf() {
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
