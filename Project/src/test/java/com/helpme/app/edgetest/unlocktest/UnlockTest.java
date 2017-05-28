package com.helpme.app.edgetest.unlocktest;

import com.helpme.app.game.model.body.concrete.visitor.Unlock;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-25.
 */
public class UnlockTest {
    MockKeyChain mockKeyChain;

    @Before
    public void setup(){
        mockKeyChain = new MockKeyChain();
    }

    /**
     * Testing that attempting to unlock a wall, given the correct key, yields false
     * (You can't unlock a wall)
     */
    @Test
    public void testUnlockWallWithKey(){
        mockKeyChain.hasKey = true;
        assert (!new MockWall().accept(new Unlock(mockKeyChain)));
    }

    /**
     * Testing that attempting to unlock a wall, given you don't have the correct key, yields false
     * (You can't unlock a wall)
     */
    @Test
    public void testUnlockWallWithoutKey(){
        assert (!new MockWall().accept(new Unlock(mockKeyChain)));
    }

    /**
     * Testing that attempting to unlock an opening, given the correct key, yields false
     * (You can't unlock an opening)
     */
    @Test
    public void testUnlockOpeningWithKey(){
        mockKeyChain.hasKey = true;
        assert (!new MockOpening().accept(new Unlock(mockKeyChain)));
    }

    /**
     * Testing that attempting to unlock an opening, given you don't have the correct key, yields false
     * (You can't unlock an opening)
     */
    @Test
    public void testUnlockOpeningWithoutKey(){
        mockKeyChain.hasKey = false;
        assert (!new MockOpening().accept(new Unlock(mockKeyChain)));
    }

    /**
     * Testing that attempting to unlock a locked door, given the correct key, yields true
     */
    @Test
    public void testUnlockLockedDoorWithKey(){
        MockDoor mockDoor = new MockDoor();
        mockDoor.locked = true;
        mockKeyChain.hasKey = true;
        assert (mockDoor.accept(new Unlock(mockKeyChain)));
    }

    /**
     * Testing that attempting to unlock a locked door, given you don't have the correct key, yields false
     */
    @Test
    public void testUnlockLockedDoorWithoutKey(){
        MockDoor mockDoor = new MockDoor();
        mockDoor.locked = true;
        assert (!mockDoor.accept(new Unlock(mockKeyChain)));
    }

    /**
     * Testing that attempting to unlock an unlocked door, given the correct key, yields false
     * (You can't unlock something that's unlocked already)
     */
    @Test
    public void testUnlockUnlockedDoorWithKey(){
        mockKeyChain.hasKey = true;
        assert (!new MockDoor().accept(new Unlock(mockKeyChain)));
    }

    /**
     * Testing that attempting to unlock an unlocked door, given you don't have the correct key, yields false
     * (You can't unlock something that's unlocked already)
     */
    @Test
    public void testUnlockUnlockedDoorWithoutKey(){
        assert (!new MockDoor().accept(new Unlock(mockKeyChain)));
    }
}
