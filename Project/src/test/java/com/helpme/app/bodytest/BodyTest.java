package com.helpme.app.bodytest;

import com.helpme.app.Mock.MockWorld0;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.Body;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.character.target.ITarget;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IReadItem;
import com.helpme.app.world.item.visitor.Pickup;
import com.helpme.app.world.tile.edge.IEdge;
import com.helpme.app.world.tile.edge.visitor.Traverse;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jacob on 2017-04-11.
 */
public class BodyTest {
    private IBody body;
    private ITarget mockTarget;

    @Before
    public void setUp() {
        body = new Body(new MockInventory(), new Vector2f(0, 0), Vector2f.up, 100);
        mockTarget = new MockTarget();
    }

    @Test
    public void testRotateRight() {
        body.rotateRight();
        assert (body.readDirection().equals(Vector2f.right));
        body.rotateRight();
        assert (body.readDirection().equals(Vector2f.down));
        body.rotateRight();
        assert (body.readDirection().equals(Vector2f.left));
        body.rotateRight();
        assert (body.readDirection().equals(Vector2f.up));
    }

    @Test
    public void testRotateLeft() {
        body.rotateLeft();
        assert (body.readDirection().equals(Vector2f.left));
        body.rotateLeft();
        assert (body.readDirection().equals(Vector2f.down));
        body.rotateLeft();
        assert (body.readDirection().equals(Vector2f.right));
        body.rotateLeft();
        assert (body.readDirection().equals(Vector2f.up));
    }

    @Test
    public void testAttack(){
        body.attack(mockTarget);
        assert(mockTarget.isDead());
    }

    @Test
    public void testSelfie(){
        body.selfie();
        assert (body.readHitpoints().y == 99);
    }

     @Test
     public void testMoveForward(){
        body.moveForward();
        assert(body.readPosition() == new Vector2f(0,1));
     }

     @Test
     public void testMoveRight(){
         body.moveRight();
         assert (body.readPosition() == new Vector2f(1,0));
     }

     @Test void testMoveBackward(){
         body.moveBackward();
         assert (body.readPosition() == new Vector2f(0,-1));
     }

     @Test void testMoveLeft(){
         body.moveLeft();
         assert (body.readPosition() == new Vector2f(-1,0));
     }

     @Test void testSetPosition(){
         body.setPosition(new Vector2f(4,20));
         assert (body.readPosition() == new Vector2f(4, 20));
     }
}
