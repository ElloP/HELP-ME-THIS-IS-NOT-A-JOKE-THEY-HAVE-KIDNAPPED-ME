package com.helpme.app.behaviourtest;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.consciousness.behaviour.IBehaviour;
import com.helpme.app.world.consciousness.behaviour.concrete.Behaviour;
import com.helpme.app.world.consciousness.behaviour.concrete.BehaviourFactory;
import com.helpme.app.world.consciousness.behaviour.concrete.Comparison;
import com.helpme.app.world.consciousness.behaviour.memories.IMemory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kopa on 2017-05-15.
 */
public class BehaviourTest {
    MockBody mockBody;
    MockPlayer mockPlayer;
    MockSurroundings mockSurroundings;
    MockMemory mockMemory;

    @Before
    public void setup() {
        mockBody = new MockBody();
        mockPlayer = new MockPlayer();
        mockSurroundings = new MockSurroundings(mockPlayer);
        mockMemory = new MockMemory();

    }

    @Test
    public void testFollowFound() {
        IBehaviour behaviour = BehaviourFactory.createFollow(
                0,
                null,
                0,
                "found",
                "following",
                "lost");
        mockPlayer.position = new Vector2f(0, 2);
        mockBody.position = new Vector2f(0, 1);
        mockBody.direction = new Vector2f(0, 1);

        Maybe<String> maybeAction = behaviour.execute(mockBody, mockSurroundings, mockMemory);
        assert (maybeAction.isNothing() && mockMemory.readMemory().get("found").equals(1));
    }

    @Test
    public void testFollowLost() {
        IBehaviour behaviour = BehaviourFactory.createFollow(
                0,
                null,
                1,
                "found",
                "following",
                "lost");

        mockSurroundings.pathCost = 3;

        Maybe<String> maybeAction = behaviour.execute(mockBody, mockSurroundings, mockMemory);
        assert (maybeAction.isNothing() && mockMemory.readMemory().get("lost").equals(1));
    }

    @Test
    public void testFollowFollowing() {
        IBehaviour behaviour = BehaviourFactory.createFollow(
                0,
                null,
                3,
                "found",
                "following",
                "lost");

        mockSurroundings.pathNextPosition = Vector2f.up;
        Maybe<String> maybeAction = behaviour.execute(mockBody, mockSurroundings, mockMemory);
        assert (maybeAction.isJust() && maybeAction.getValue().equals("move_forward") && mockMemory.readMemory().get("following").equals(1));

        mockSurroundings.pathNextPosition = Vector2f.right;
        maybeAction = behaviour.execute(mockBody, mockSurroundings, mockMemory);
        assert (maybeAction.isJust() && maybeAction.getValue().equals("rotate_right") && mockMemory.readMemory().get("following").equals(1));

        mockSurroundings.pathNextPosition = Vector2f.down;
        maybeAction = behaviour.execute(mockBody, mockSurroundings, mockMemory);
        assert (maybeAction.isJust() && maybeAction.getValue().equals("rotate_left") && mockMemory.readMemory().get("following").equals(1));

        mockSurroundings.pathNextPosition = Vector2f.left;
        maybeAction = behaviour.execute(mockBody, mockSurroundings, mockMemory);
        assert (maybeAction.isJust() && maybeAction.getValue().equals("rotate_left") && mockMemory.readMemory().get("following").equals(1));
    }

    @Test
    public void testReturnReturned() {
        IBehaviour behaviour = BehaviourFactory.createReturn(
                0,
                null,
                "returning",
                "returned");

        mockSurroundings.pathCost = 0;
        Maybe<String> maybeAction = behaviour.execute(mockBody, mockSurroundings, mockMemory);

        assert (maybeAction.isNothing() && mockMemory.readMemory().get("returned").equals(1));
    }

    @Test
    public void testReturnReturning() {
        IBehaviour behaviour = BehaviourFactory.createReturn(
                0,
                null,
                "returning",
                "returned");

        mockSurroundings.pathCost = 2;

        mockSurroundings.pathNextPosition = Vector2f.up;
        Maybe<String> maybeAction = behaviour.execute(mockBody, mockSurroundings, mockMemory);
        assert (maybeAction.isJust() && maybeAction.getValue().equals("move_forward") && mockMemory.readMemory().get("returning").equals(1));

        mockSurroundings.pathNextPosition = Vector2f.right;
        maybeAction = behaviour.execute(mockBody, mockSurroundings, mockMemory);
        assert (maybeAction.isJust() && maybeAction.getValue().equals("rotate_right") && mockMemory.readMemory().get("returning").equals(1));

        mockSurroundings.pathNextPosition = Vector2f.down;
        maybeAction = behaviour.execute(mockBody, mockSurroundings, mockMemory);
        assert (maybeAction.isJust() && maybeAction.getValue().equals("rotate_left") && mockMemory.readMemory().get("returning").equals(1));

        mockSurroundings.pathNextPosition = Vector2f.left;
        maybeAction = behaviour.execute(mockBody, mockSurroundings, mockMemory);
        assert (maybeAction.isJust() && maybeAction.getValue().equals("rotate_left") && mockMemory.readMemory().get("returning").equals(1));
    }

    @Test
    public void testStay() {

    }

    @Test
    public void testAttack() {
        //IBehaviour attack = BehaviourFactory.createAttack(0, )
    }


    @Test
    public void testMoreThanValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<String, Tuple2<Integer, Comparison>>() {
            {
                put("memory0", new Tuple2<>(5, Comparison.MORE_THAN));
            }
        };

        IBehaviour behaviour = BehaviourFactory.createStay(0, preconditions);
        assert (behaviour.valid(mockMemory.readMemory()));
    }

    @Test
    public void testLessThanValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<String, Tuple2<Integer, Comparison>>() {
            {
                put("memory1", new Tuple2<>(3, Comparison.LESS_THAN));
            }
        };

        IBehaviour behaviour = BehaviourFactory.createStay(0, preconditions);
        assert (behaviour.valid(mockMemory.readMemory()));
    }

    @Test
    public void testEqualValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<String, Tuple2<Integer, Comparison>>() {
            {
                put("memory2", new Tuple2<>(1, Comparison.EQUAL));
            }
        };

        IBehaviour behaviour = BehaviourFactory.createStay(0, preconditions);
        assert (behaviour.valid(mockMemory.readMemory()));
    }

    @Test
    public void testNotEqualValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<String, Tuple2<Integer, Comparison>>() {
            {
                put("memory3", new Tuple2<>(6, Comparison.NOT_EQUAL));
            }
        };

        IBehaviour behaviour = BehaviourFactory.createStay(0, preconditions);
        assert (behaviour.valid(mockMemory.readMemory()));
    }

    @Test
    public void testMoreThanNotValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<String, Tuple2<Integer, Comparison>>() {
            {
                put("memory0", new Tuple2<>(1, Comparison.MORE_THAN));
            }
        };

        IBehaviour behaviour = BehaviourFactory.createStay(0, preconditions);
        assert (!behaviour.valid(mockMemory.readMemory()));
    }

    @Test
    public void testLessThanNotValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<String, Tuple2<Integer, Comparison>>() {
            {
                put("memory1", new Tuple2<>(5, Comparison.LESS_THAN));
            }
        };

        IBehaviour behaviour = BehaviourFactory.createStay(0, preconditions);
        assert (!behaviour.valid(mockMemory.readMemory()));
    }

    @Test
    public void testEqualNotValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<String, Tuple2<Integer, Comparison>>() {
            {
                put("memory2", new Tuple2<>(2, Comparison.EQUAL));
            }
        };

        IBehaviour behaviour = BehaviourFactory.createStay(0, preconditions);
        assert (!behaviour.valid(mockMemory.readMemory()));
    }


    @Test
    public void testNotEqualNotValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<String, Tuple2<Integer, Comparison>>() {
            {
                put("memory3", new Tuple2<>(7, Comparison.NOT_EQUAL));
            }
        };

        IBehaviour behaviour = BehaviourFactory.createStay(0, preconditions);
        assert (!behaviour.valid(new MockMemory().readMemory()));
    }


    @Test
    public void testNoMatchNotValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<String, Tuple2<Integer, Comparison>>() {
            {
                put("memory4", new Tuple2<>(6, Comparison.NOT_EQUAL));
            }
        };

        IBehaviour behaviour = BehaviourFactory.createStay(0, preconditions);
        assert (!behaviour.valid(new MockMemory().readMemory()));
    }
}
