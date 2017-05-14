package com.helpme.app.consciousnesstest;

import com.helpme.app.world.consciousness.ConsciousnessFactory;
import com.helpme.app.world.consciousness.IThought;
import com.helpme.app.world.consciousness.IConsciousness;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-11.
 */
public class ConsciousnessTest {
    IConsciousness player;
    MockBody mockBody;
    MockTarget mockTarget;
    MockSurroundings mockSurroundings;

    @Before
    public void setup() {
        mockTarget = new MockTarget();
        mockBody = new MockBody();
        mockSurroundings = new MockSurroundings(mockTarget);
        player = ConsciousnessFactory.createPlayer(mockBody, mockSurroundings);
    }

    @Test
    public void testAttack() {
        player.useAttack();
        assert (mockTarget.attacked);
    }

    @Test
    public void testSelfie() {
        player.useSelfie();
        assert (mockBody.selfied);
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


}
