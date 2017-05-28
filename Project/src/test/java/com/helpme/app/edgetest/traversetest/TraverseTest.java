package com.helpme.app.edgetest.traversetest;

import com.helpme.app.game.model.body.concrete.visitor.Traverse;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-20.
 */
public class TraverseTest {
    private MockOpening mockOpening;
    private MockDoor mockDoor;
    private MockWall mockWall;

    @Before
    public void setup(){
        mockDoor = new MockDoor();
        mockOpening = new MockOpening();
        mockWall = new MockWall();
    }

    /**
     * Testing if attempting to walk through an opening yields true
     */
    @Test
    public void testTraverseOpening(){
        assert (mockOpening.accept(new Traverse()));
    }

    /**
     * Testing if attempting to walk through a wall yields false
     */
    @Test
    public void testTraverseWall(){
        assert (!mockWall.accept(new Traverse()));
    }


    /**
     * Testing if attempting to walk through a locked door without a key yields false
     */
    @Test
    public void testTraverseLockedDoorNoKey(){
        mockDoor.locked = true;
        assert (!mockDoor.accept(new Traverse()));
    }

    /**
     * Testing if attempting to walk through a locked door with the correct key yields false
     * (Unlocking is a separate visitor)
     */
    @Test
    public void testTraverseLockedDoorHasKey(){
        mockDoor.locked = true;
        assert (!mockDoor.accept(new Traverse()));
    }

    /**
     * Testing if attempting to walk through an unlocked door yields true
     */
    @Test
    public void testTraverseUnlockedDoor(){
        mockDoor.locked = false;
        assert (mockDoor.accept(new Traverse()));
    }
}
