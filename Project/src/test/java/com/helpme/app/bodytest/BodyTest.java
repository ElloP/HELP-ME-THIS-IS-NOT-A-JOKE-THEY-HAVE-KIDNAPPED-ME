package com.helpme.app.bodytest;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.character.Body;
import com.helpme.app.world.character.BodyFactory;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.target.ITarget;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jacob on 2017-04-11.
 */
public class BodyTest {
    private IBody body0;
    private IBody body1;
    private IBody body2;
    private IBody body3;
    private ITarget mockTarget;

    @Before
    public void setup() {
        body0 = BodyFactory.createBody(new MockInventory(MockItem.activeWeapon(), null), new Vector2f(0, 0), Vector2f.up, 100);
        body1 = BodyFactory.createBody(new MockInventory(null, MockItem.defaultWeapon()), new Vector2f(0, 0), Vector2f.up, new Vector2f(100, 1));
        body2 = BodyFactory.createBody(new MockInventory(MockItem.strongPotion(), null), new Vector2f(0, 0), Vector2f.up, new Vector2f(100, 50));
        body3 = BodyFactory.createBody(new MockInventory(MockItem.strongWeapon(), null), new Vector2f(0, 0), Vector2f.up, new Vector2f(100, 50));
        mockTarget = new MockTarget();
    }

    @Test
    public void testRotateRight() {
        body0.rotateRight();
        assert (body0.readDirection().equals(Vector2f.right));
        body0.rotateRight();
        assert (body0.readDirection().equals(Vector2f.down));
        body0.rotateRight();
        assert (body0.readDirection().equals(Vector2f.left));
        body0.rotateRight();
        assert (body0.readDirection().equals(Vector2f.up));
    }

    @Test
    public void testRotateLeft() {
        body0.rotateLeft();
        assert (body0.readDirection().equals(Vector2f.left));
        body0.rotateLeft();
        assert (body0.readDirection().equals(Vector2f.down));
        body0.rotateLeft();
        assert (body0.readDirection().equals(Vector2f.right));
        body0.rotateLeft();
        assert (body0.readDirection().equals(Vector2f.up));
    }

    @Test
    public void testAttack() {
        body0.attack(mockTarget);
        assert (mockTarget.isDead());
    }

    @Test
    public void testSelfie() {
        body0.selfie();
        assert (body0.readHitpoints().y == 99);
    }

    /**
     * This is designed to test if a default activeWeapon is chosen if there is no active activeWeapon
     */
    @Test
    public void testNoActive() {
        body1.selfie();
        assert (body1.readHitpoints().y == 2);
    }

    @Test
    public void testMaxHealth() {
        body2.selfie();
        assert (body2.readHitpoints().y == 100);
    }

    @Test
    public void testDying() {
        assert (!body3.isDead());
        body3.selfie();
        assert (body3.isDead());
    }

    @Test
    public void testMoveForward() {
        body0.moveForward();
        assert (body0.readPosition().equals(new Vector2f(0, 1)));
    }

    @Test
    public void testMoveRight() {
        body0.moveRight();
        assert (body0.readPosition().equals(new Vector2f(1, 0)));
    }

    @Test
    public void testMoveBackward() {
        body0.moveBackward();
        assert (body0.readPosition().equals(new Vector2f(0, -1)));
    }

    @Test
    public void testMoveLeft() {
        body0.moveLeft();
        assert (body0.readPosition().equals(new Vector2f(-1, 0)));
    }

    @Test
    public void testSetPosition() {
        body0.setPosition(new Vector2f(4, 20));
        assert (body0.readPosition().equals(new Vector2f(4, 20)));
    }
}
