package com.helpme.app.behaviourtest;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.model.consciousness.IConsciousness;
import com.helpme.app.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.model.consciousness.behaviour.concrete.BehaviourFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by kopa on 2017-05-21.
 */
public class FollowBehaviourTest {
    private IBehaviour followBehaviour;
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

        followBehaviour = BehaviourFactory.createFollow(
                0,
                null,
                5,
                "found",
                "following",
                "lost");

    }

    @Test
    public void testFollowFound() {
        mockPlayer.position = new Vector2f(0, 2);
        mockBody.position = new Vector2f(0, 1);
        mockBody.direction = new Vector2f(0, 1);
        mockMemory.memory = new HashMap<>();

        Maybe<IAction<IConsciousness>> maybeAction = followBehaviour.execute(mockBody, mockSurroundings, mockMemory);

        assert (maybeAction.isNothing() &&
                mockMemory.readMemory().size() == 1 &&
                mockMemory.readMemory().get("found").equals(1));
    }

    @Test
    public void testFollowLost() {
        mockSurroundings.pathCost = 3;
        mockMemory.memory = new HashMap<>();
        followBehaviour = BehaviourFactory.createFollow(
                0,
                null,
                0,
                "found",
                "following",
                "lost");

        Maybe<IAction<IConsciousness>> maybeAction = followBehaviour.execute(mockBody, mockSurroundings, mockMemory);

        assert (maybeAction.isNothing() &&
                mockMemory.readMemory().size() == 1 &&
                mockMemory.readMemory().get("lost").equals(1));
    }

    @Test
    public void testFollowFollowingFacing() {
        mockMemory.memory = new HashMap<>();
        mockSurroundings.pathNextPosition = Vector2f.north;

        Maybe<IAction<IConsciousness>> maybeAction = followBehaviour.execute(mockBody, mockSurroundings, mockMemory);
        maybeAction.run(action -> action.apply(mockConsciousness));

        assert (maybeAction.isJust() &&
                mockConsciousness.movedForward == 1 &&
                mockMemory.readMemory().size() == 1 &&
                mockMemory.readMemory().get("following").equals(1));
    }

    @Test
    public void testFollowFollowingRightOf() {
        mockMemory.memory = new HashMap<>();
        mockSurroundings.pathNextPosition = Vector2f.east;

        Maybe<IAction<IConsciousness>> maybeAction = followBehaviour.execute(mockBody, mockSurroundings, mockMemory);
        maybeAction.run(action -> action.apply(mockConsciousness));

        assert (maybeAction.isJust() &&
                mockConsciousness.rotatedRight == 1 &&
                mockMemory.readMemory().size() == 1 &&
                mockMemory.readMemory().get("following").equals(1));
    }

    @Test
    public void testFollowFollowingBehind() {
        mockMemory.memory = new HashMap<>();
        mockSurroundings.pathNextPosition = Vector2f.south;

        Maybe<IAction<IConsciousness>> maybeAction = followBehaviour.execute(mockBody, mockSurroundings, mockMemory);
        maybeAction.run(action -> action.apply(mockConsciousness));

        assert (maybeAction.isJust() &&
                mockConsciousness.rotatedLeft == 1 &&
                mockMemory.readMemory().size() == 1 &&
                mockMemory.readMemory().get("following").equals(1));
    }

    @Test
    public void testFollowFollowingLeftOf() {
        mockMemory.memory = new HashMap<>();

        mockSurroundings.pathNextPosition = Vector2f.west;
        Maybe<IAction<IConsciousness>> maybeAction = followBehaviour.execute(mockBody, mockSurroundings, mockMemory);
        maybeAction.run(action -> action.apply(mockConsciousness));

        assert (maybeAction.isJust() &&
                mockConsciousness.rotatedLeft == 1 &&
                mockMemory.readMemory().size() == 1 &&
                mockMemory.readMemory().get("following").equals(1));
    }
}
