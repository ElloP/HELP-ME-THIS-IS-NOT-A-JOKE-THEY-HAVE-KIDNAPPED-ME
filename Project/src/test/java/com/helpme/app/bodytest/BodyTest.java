package com.helpme.app.bodytest;

import com.helpme.app.model.body.concrete.Body;
import com.helpme.app.model.item.IItem;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.model.body.IBody;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 2017-04-11.
 */
public class BodyTest {
    private IBody body0;
    private IBody body1;
    private IBody body2;
    private IBody body3;
    private MockTarget mockTarget;
    private MockInventory mockInventory;

    @Before
    public void setup() {
        mockInventory = new MockInventory(new MockItem(t -> t.damage(101), t -> t.damage(100)), null);
        body0 = new Body(
                new MockInventory(new MockItem(t -> t.damage(1), t -> t.damage(1)), null),
                new Vector2f(0, 0),
                Vector2f.NORTH,
                new Vector2f(100, 100),
                Vector2f.ZERO,
                null);
        body1 = new Body(
                new MockInventory(null, new MockItem(t -> t.heal(1), t -> t.heal(1))),
                new Vector2f(0, 0),
                Vector2f.NORTH,
                new Vector2f(100, 1),
                Vector2f.ZERO,
                null);
        body2 = new Body(
                new MockInventory(new MockItem(t -> t.heal(101), t -> t.heal(101)), null),
                new Vector2f(0, 0),
                Vector2f.NORTH,
                new Vector2f(100, 50),
                Vector2f.ZERO,
                null);
        body3 = new Body(
                mockInventory,
                new Vector2f(0, 0),
                Vector2f.NORTH,
                new Vector2f(100, 50),
                new Vector2f(30,30),
                null);
        mockTarget = new MockTarget();
    }

    @Test
    public void testReadMaxHitpoints(){
        assert (body1.readMaxHitpoints() == 100);
    }

    @Test
    public void testReadCurrentHitpoints(){
        assert (body1.readCurrentHitpoints() == 1);
    }

    @Test
    public void testSetActiveItem(){
        body3.setActiveItem(3);
        assert (mockInventory.setActiveItem == 3);
    }

    @Test
    public void testGetInventory(){
        assert (body3.getInventory().equals(mockInventory));
    }

    @Test
    public void testReadInventory(){
        System.out.println(mockInventory.copy);
        assert (body3.readInventory().equals(mockInventory) && mockInventory.copy == 1);
    }

    @Test
    public void testPickupItemSuccess(){
        MockSingle pickup = new MockSingle();
        mockInventory.itemAdded = true;
        assert (body3.pickupItem(pickup));
    }

    @Test
    public void testPickupItemFailure(){
        MockSingle pickup = new MockSingle();
        mockInventory.itemAdded = false;
        assert (!body3.pickupItem(pickup));
    }

    @Test
    public void testDropAllItems(){
        List<Maybe<IItem>> items = new ArrayList<>();
        mockInventory.items = new ArrayList<>();
        assert (body3.dropAllItems().equals(items));
    }

    @Test
    public void testDropItem(){
        List<Maybe<IItem>> items = new ArrayList<>();
        Maybe<IItem> drop = new Just<>(new MockSingle());
        items.add(new Just<>(new MockSingle()));
        items.add(drop);
        mockInventory.items = items;
        assert (body3.dropItem(1).equals(drop));
    }

    @Test
    public void testSetItems(){
        IItem item = new MockSingle();
        IItem[] items = new IItem[]{item};

        body3.setItems(items);
        assert (mockInventory.items.size() == 1 && mockInventory.items.get(0).equals(new Just<>(item)));
    }

    @Test
    public void testGetStartingPosition() {
        assert (body3.readStartingPosition().equals(new Vector2f(30,30)));
    }

    @Test
    public void testRotateRight() {
        body0.rotateRight();
        assert (body0.readDirection().equals(Vector2f.EAST));
        body0.rotateRight();
        assert (body0.readDirection().equals(Vector2f.SOUTH));
        body0.rotateRight();
        assert (body0.readDirection().equals(Vector2f.WEST));
        body0.rotateRight();
        assert (body0.readDirection().equals(Vector2f.NORTH));
    }

    @Test
    public void testRotateLeft() {
        body0.rotateLeft();
        assert (body0.readDirection().equals(Vector2f.WEST));
        body0.rotateLeft();
        assert (body0.readDirection().equals(Vector2f.SOUTH));
        body0.rotateLeft();
        assert (body0.readDirection().equals(Vector2f.EAST));
        body0.rotateLeft();
        assert (body0.readDirection().equals(Vector2f.NORTH));
    }

    @Test
    public void testAttack() {
        body0.attack(mockTarget);
        assert (mockTarget.attacked == 1);
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
