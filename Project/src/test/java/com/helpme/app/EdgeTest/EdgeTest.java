package com.helpme.app.EdgeTest;

import com.helpme.app.model.tile.edge.IEdge;
import com.helpme.app.model.tile.edge.concrete.EdgeFactory;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-21.
 */
public class EdgeTest {

    @Test
    public void testOpening(){
        IEdge opening = EdgeFactory.createOpening();
        assert (opening.accept(new MockVisitor()));
    }

    @Test
    public void testWall(){
        IEdge wall = EdgeFactory.createWall();
        assert (wall.accept(new MockVisitor()));
    }

    @Test
    public void testDoor(){
        IEdge door = EdgeFactory.createDoor(false, null);
        assert (door.accept(new MockVisitor()));
    }
}
