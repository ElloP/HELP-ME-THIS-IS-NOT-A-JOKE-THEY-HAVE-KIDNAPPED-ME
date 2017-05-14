package com.helpme.app.consciousnesstest;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.character.Body;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.character.inventory.IReadInventory;
import com.helpme.app.world.character.target.ITarget;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.tile.edge.IEdge;

/**
 * Created by kopa on 2017-05-14.
 */
public class MockTarget implements IBody {
    private boolean dead;
    private Vector2f hitpoints = new Vector2f(100,100);

    @Override
    public void damage(float amount) {
        hitpoints.y -= amount;
    }

    @Override
    public void heal(float amount) {

    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public void rotateRight() {

    }

    @Override
    public void rotateLeft() {

    }

    @Override
    public void moveForward() {

    }

    @Override
    public void moveRight() {

    }

    @Override
    public void moveBackward() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void setPosition(Vector2f position) {

    }

    @Override
    public void changeActiveItem(int itemIndex) {

    }

    @Override
    public void attack(ITarget target) {

    }

    @Override
    public void selfie() {

    }

    @Override
    public boolean pickupItem(IItem item) {
        return false;
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
    public void dropAllItems() {

    }

    @Override
    public void kill() {
        dead = true;
    }

    @Override
    public Vector2f readPosition() {
        return null;
    }

    @Override
    public Vector2f readDirection() {
        return null;
    }

    @Override
    public Vector2f readHitpoints() {
        return hitpoints;
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
