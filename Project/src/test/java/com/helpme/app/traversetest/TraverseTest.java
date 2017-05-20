package com.helpme.app.traversetest;

import com.helpme.app.world.body.concrete.visitor.Traverse;
import com.helpme.app.world.body.inventory.IKeyChain;
import com.helpme.app.world.tile.edge.IEdge;
import com.helpme.app.world.tile.edge.concrete.EdgeFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-20.
 */
public class TraverseTest {
    private MockOpening mockOpening;
    private MockDoor mockDoor;
    private MockWall mockWall;
    private MockKeyChain mockKeyChain;

    @Before
    public void setup(){
        mockKeyChain = new MockKeyChain();
        mockDoor = new MockDoor();
        mockOpening = new MockOpening();
        mockWall = new MockWall();
    }

    @Test
    public void testTraverseOpening(){
        assert (mockOpening.accept(new Traverse(mockKeyChain)));
    }

    @Test
    public void testTraverseWall(){
        assert (!mockWall.accept(new Traverse(mockKeyChain)));
    }

    @Test
    public void testTraverseLockedDoorNoKey(){
        mockDoor.locked = true;
        assert (!mockDoor.accept(new Traverse(mockKeyChain)));
    }

    @Test
    public void testTraverseLockedDoorHasKey(){
        mockDoor.locked = true;
        mockKeyChain.hasKey = true;
        assert (!mockDoor.accept(new Traverse(mockKeyChain)) && !mockDoor.locked);
    }

    @Test
    public void testTraverseUnlockedDoor(){
        mockDoor.locked = false;
        assert (mockDoor.accept(new Traverse(mockKeyChain)));
    }
}
