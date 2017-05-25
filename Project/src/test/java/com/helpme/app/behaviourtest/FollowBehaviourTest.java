package com.helpme.app.behaviourtest;

import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.game.model.consciousness.behaviour.concrete.Follow;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kopa on 2017-05-21.
 */
public class FollowBehaviourTest {
    private Follow followConcrete;
    private IBehaviour followBehaviour;
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
        followConcrete = new Follow(
                0,
                null,
                5,
                "found",
                "following",
                "lost");

        followBehaviour = followConcrete;
    }

    @Test
    public void testGetFollowingDistance(){
        assert (followConcrete.getFollowingDistance() == 5);
    }

    @Test
    public void testGetFoundEvent(){
        assert (followConcrete.getFoundEvent().equals("found"));
    }

    @Test
    public void getFollowingEvent(){
        assert (followConcrete.getFollowingEvent().equals("following"));
    }

    @Test
    public void getLostEvent(){
        assert (followConcrete.getLostEvent().equals("lost"));
    }

    @Test
    public void testReset() {
        Map<String, Integer> memory;
        mockMemory.memory = new HashMap<>();
        followBehaviour.reset(mockMemory);
        memory = mockMemory.readMemory();

        assert (memory.size() == 3 &&
                memory.get("found") == 0 &&
                memory.get("following") == 0 &&
                memory.get("lost") == 0);
    }

    @Test
    public void testFound() {
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
    public void testLost() {
        mockSurroundings.pathCost = 3;
        mockMemory.memory = new HashMap<>();
        followBehaviour = new Follow(
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
    public void testFollowingFacing() {
        mockMemory.memory = new HashMap<>();
        mockSurroundings.pathNextPosition = Vector2f.NORTH;

        Maybe<IAction<IConsciousness>> maybeAction = followBehaviour.execute(mockBody, mockSurroundings, mockMemory);
        maybeAction.run(action -> action.apply(mockConsciousness));

        assert (maybeAction.isJust() &&
                mockConsciousness.movedForward == 1 &&
                mockMemory.readMemory().size() == 1 &&
                mockMemory.readMemory().get("following").equals(1));
    }

    @Test
    public void testFollowingRightOf() {
        mockMemory.memory = new HashMap<>();
        mockSurroundings.pathNextPosition = Vector2f.EAST;

        Maybe<IAction<IConsciousness>> maybeAction = followBehaviour.execute(mockBody, mockSurroundings, mockMemory);
        maybeAction.run(action -> action.apply(mockConsciousness));

        assert (maybeAction.isJust() &&
                mockConsciousness.rotatedRight == 1 &&
                mockMemory.readMemory().size() == 1 &&
                mockMemory.readMemory().get("following").equals(1));
    }

    @Test
    public void testFollowingBehind() {
        mockMemory.memory = new HashMap<>();
        mockSurroundings.pathNextPosition = Vector2f.SOUTH;

        Maybe<IAction<IConsciousness>> maybeAction = followBehaviour.execute(mockBody, mockSurroundings, mockMemory);
        maybeAction.run(action -> action.apply(mockConsciousness));

        assert (maybeAction.isJust() &&
                mockConsciousness.rotatedLeft == 1 &&
                mockMemory.readMemory().size() == 1 &&
                mockMemory.readMemory().get("following").equals(1));
    }

    @Test
    public void testFollowingLeftOf() {
        mockMemory.memory = new HashMap<>();

        mockSurroundings.pathNextPosition = Vector2f.WEST;
        Maybe<IAction<IConsciousness>> maybeAction = followBehaviour.execute(mockBody, mockSurroundings, mockMemory);
        maybeAction.run(action -> action.apply(mockConsciousness));

        assert (maybeAction.isJust() &&
                mockConsciousness.rotatedLeft == 1 &&
                mockMemory.readMemory().size() == 1 &&
                mockMemory.readMemory().get("following").equals(1));
    }
}
