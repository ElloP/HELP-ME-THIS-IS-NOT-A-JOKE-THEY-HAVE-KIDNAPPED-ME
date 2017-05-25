package com.helpme.app.behaviourtest;

import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.game.model.consciousness.behaviour.concrete.Attack;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by kopa on 2017-05-21.
 */
public class AttackBehaviourTest {
    private Attack attackConcrete;
    private IBehaviour attackBehaviour;
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
        attackConcrete = new Attack(
                0,
                null,
                "attack");
        attackBehaviour = attackConcrete;
    }

    @Test
    public void testGetAttackEvent(){
        assert (attackConcrete.getAttackEvent().equals("attack"));
    }

    @Test
    public void testReset(){
        mockMemory.memory = new HashMap<>();
        attackBehaviour.reset(mockMemory);
        assert (mockMemory.memory.size() == 1 && mockMemory.memory.get("attack") == 0);
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

}

