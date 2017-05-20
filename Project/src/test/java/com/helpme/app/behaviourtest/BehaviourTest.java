package com.helpme.app.behaviourtest;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.consciousness.behaviour.IBehaviour;
import com.helpme.app.world.consciousness.behaviour.concrete.BehaviourFactory;
import com.helpme.app.world.consciousness.behaviour.Comparison;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kopa on 2017-05-15.
 */
public class BehaviourTest {
    private MockBody mockBody;
    private MockPlayer mockPlayer;
    private MockSurroundings mockSurroundings;
    private MockMemory mockMemory;
    private MockConsciousness mockConsciousness;

    private IBehaviour followBehaviour;
    private IBehaviour returnBehaviour;
    private IBehaviour stayBehaviour;
    private IBehaviour attackBehaviour;

    @Before
    public void setup() {
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

        returnBehaviour = BehaviourFactory.createReturn(
                0,
                null,
                "returning",
                "returned");

        stayBehaviour = BehaviourFactory.createStay(
                0,
                null);

        attackBehaviour = BehaviourFactory.createAttack(
                0,
                null,
                "attack");
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
        mockSurroundings.pathNextPosition = Vector2f.north;

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
        mockSurroundings.pathNextPosition = Vector2f.east;

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
        mockSurroundings.pathNextPosition = Vector2f.south;

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
        mockSurroundings.pathNextPosition = Vector2f.west;

        Maybe<IAction<IConsciousness>> maybeAction = returnBehaviour.execute(mockBody, mockSurroundings, mockMemory);
        maybeAction.run(action -> action.apply(mockConsciousness));

        assert (maybeAction.isJust() &&
                mockConsciousness.rotatedLeft == 1 &&
                mockMemory.readMemory().size() == 1 &&
                mockMemory.readMemory().get("returning").equals(1));
    }

    @Test
    public void testStay() {
        mockMemory.memory = new HashMap<>();

        Maybe<IAction<IConsciousness>> maybeAction = stayBehaviour.execute(mockBody, mockSurroundings, mockMemory);
        assert (maybeAction.isNothing() && mockMemory.readMemory().size() == 0);
    }

    @Test
    public void testAttackFacing() {
        mockMemory.memory = new HashMap<>();
        mockSurroundings.facing = true;

        Maybe<IAction<IConsciousness>> maybeAction = attackBehaviour.execute(mockBody, mockSurroundings, mockMemory);
        maybeAction.run(action -> action.apply(mockConsciousness));

        assert (maybeAction.isJust() &&
                mockConsciousness.attacked == 1 &&
                mockMemory.readMemory().get("attack").equals(1));
    }

    @Test
    public void testAttackNotFacing() {
        mockMemory.memory = new HashMap<>();
        mockSurroundings.facing = false;

        Maybe<IAction<IConsciousness>> maybeAction = attackBehaviour.execute(mockBody, mockSurroundings, mockMemory);
        maybeAction.run(action -> action.apply(mockConsciousness));

        assert (maybeAction.isJust() &&
                mockConsciousness.attacked == 1 &&
                mockMemory.readMemory().size() == 0);
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
