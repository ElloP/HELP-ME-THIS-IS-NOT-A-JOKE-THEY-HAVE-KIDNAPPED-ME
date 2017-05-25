package com.helpme.app.consciousnesstest;

import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.concrete.Player;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple2;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-11.
 */
public class ConsciousnessTest {
    private IConsciousness player;
    private MockBody mockBody;
    private MockTarget mockTarget;
    private MockSurroundings mockSurroundings;

    @Before
    public void setup() {
        mockTarget = new MockTarget();
        mockBody = new MockBody();
        mockSurroundings = new MockSurroundings(mockTarget);
        player = new Player(mockBody, mockSurroundings);
    }

    @Test
    public void testMoveForwardAllowed() {
        mockSurroundings.movementAllowed = true;
        player.moveForward();
        assert (mockBody.movedForward == 1);
    }

    @Test
    public void testMoveForwardDisallowed() {
        mockSurroundings.movementAllowed = false;
        player.moveForward();
        assert (mockBody.movedForward == 0);
    }

    @Test
    public void testMoveRightAllowed() {
        mockSurroundings.movementAllowed = true;
        player.moveRight();
        assert (mockBody.movedRight == 1);
    }

    @Test
    public void testMoveRightDisallowed() {
        mockSurroundings.movementAllowed = false;
        player.moveRight();
        assert (mockBody.movedRight == 0);
    }

    @Test
    public void testMoveBackwardAllowed() {
        mockSurroundings.movementAllowed = true;
        player.moveBackward();
        assert (mockBody.movedBackward == 1);
    }

    @Test
    public void testMoveBackwardDisallowed() {
        mockSurroundings.movementAllowed = false;
        player.moveBackward();
        assert (mockBody.movedBackward == 0);
    }

    @Test
    public void testMoveLeftAllowed() {
        mockSurroundings.movementAllowed = true;
        player.moveLeft();
        assert (mockBody.movedLeft == 1);
    }

    @Test
    public void testMoveLeftDisallowed() {
        mockSurroundings.movementAllowed = false;
        player.moveLeft();
        assert (mockBody.movedLeft == 0);
    }

    @Test
    public void testAttack() {
        player.useAttack();
        assert (mockTarget.attacked == 1);
    }

    @Test
    public void testSelfie() {
        player.useSelfie();
        assert (mockBody.selfied == 1);
    }

    @Test
    public void testPickupAllNotFull() {
        mockSurroundings.tileItems = 3;
        mockBody.full = false;
        player.usePickupAll();
        assert (mockBody.items == 3 && mockSurroundings.tileItems == 0);
    }

    @Test
    public void testPickupAllFull() {
        mockSurroundings.tileItems = 3;
        mockBody.full = true;
        player.usePickupAll();
        assert (mockBody.items == 0 && mockSurroundings.tileItems == 3);
    }

    @Test
    public void testPickupSingle() {
        mockSurroundings.tileItems = 3;
        mockBody.full = false;
        player.usePickupSingle(0);
        assert (mockBody.items == 1 && mockSurroundings.tileItems == 2);
    }

    @Test
    public void testDialogue() {
        Maybe<Tuple2<String, String[]>> dialogue = player.useTalk();
        assert (dialogue.isJust());
    }

    @Test
    public void testResponse() {
        Maybe<Tuple2<String, String[]>> response = player.useTalk(0);
        assert (response.isJust());
    }
}
