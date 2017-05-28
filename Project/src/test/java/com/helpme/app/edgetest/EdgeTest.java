package com.helpme.app.edgetest;

import com.helpme.app.game.model.tile.edge.IEdge;
import com.helpme.app.game.model.tile.edge.concrete.Door;
import com.helpme.app.game.model.tile.edge.concrete.Opening;
import com.helpme.app.game.model.tile.edge.concrete.Wall;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-21.
 */
public class EdgeTest {

    /**
     * Testing that accept works
     */

    @Test
    public void testOpening(){
        IEdge opening = new Opening();
        assert (opening.accept(new MockVisitor()));
    }

    @Test
    public void testWall(){
        IEdge wall = new Wall();
        assert (wall.accept(new MockVisitor()));
    }

    @Test
    public void testDoor(){
        IEdge door = new Door(false, null);
        assert (door.accept(new MockVisitor()));
    }
}
