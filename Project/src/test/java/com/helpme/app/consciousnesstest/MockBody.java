package com.helpme.app.consciousnesstest;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.body.concrete.Body;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.body.inventory.IInventory;
import com.helpme.app.world.body.inventory.IReadInventory;
import com.helpme.app.world.item.effect.ITarget;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.tile.edge.IEdge;

import java.util.List;

/**
 * Created by kopa on 2017-05-14.
 */
public class MockBody implements IBody {
    public int selfied;
    public boolean full;
    public int movedForward;
    public int movedRight;
    public int movedBackward;
    public int movedLeft;

    public int items;

    public MockBody() {
        this.items = 0;
    }

    @Override
    public void damage(float amount) {

    }

    @Override
    public void heal(float amount) {

    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public void rotateRight() {

    }

    @Override
    public void rotateLeft() {

    }

    @Override
    public void moveForward() {
        movedForward++;
    }

    @Override
    public void moveRight() {
        movedRight++;
    }

    @Override
    public void moveBackward() {
        movedBackward++;
    }

    @Override
    public void moveLeft() {
        movedLeft++;
    }

    @Override
    public void setPosition(Vector2f position) {

    }

    @Override
    public void changeActiveItem(int itemIndex) {

    }

    @Override
    public void attack(ITarget target) {
        target.damage(10);
    }

    @Override
    public void selfie() {
        kill();
    }

    @Override
    public boolean pickupItem(IItem item) {
        if (full) {
            return false;
        }
        items++;
        return true;
    }

    @Override
    public Maybe<IItem> dropItem(int index) {
        return null;
    }

    @Override
    public IInventory getInventory() {
        return null;
    }

    @Override
    public void setItems(IItem[] items) {

    }

    @Override
    public Body clone() {
        return null;
    }

    @Override
    public List<Maybe<IItem>> dropAllItems() {
        return null;
    }

    @Override
    public void kill() {
        selfied++;
    }

    @Override
    public Vector2f readPosition() {
        return Vector2f.zero;
    }

    @Override
    public Vector2f readDirection() {
        return Vector2f.zero;
    }

    @Override
    public Vector2f readHitpoints() {
        return null;
    }

    @Override
    public float readMaxHp() {
        return 0;
    }

    @Override
    public float readCurrentHp() {
        return 0;
    }

    @Override
    public Vector2f readStartingPosition() {
        return null;
    }

    @Override
    public Tuple2<String, String[]> getResponse(int i) {
        return null;
    }

    @Override
    public Tuple2<String, String[]> getDialogue() {
        return null;
    }

    @Override
    public boolean isTraversable(IEdge edge) {
        return false;
    }

    @Override
    public IReadInventory readInventory() {
        return null;
    }
}
