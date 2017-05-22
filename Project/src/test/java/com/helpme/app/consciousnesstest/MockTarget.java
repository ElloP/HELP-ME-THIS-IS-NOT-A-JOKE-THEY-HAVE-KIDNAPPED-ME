package com.helpme.app.consciousnesstest;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.model.body.concrete.Body;
import com.helpme.app.model.body.IBody;
import com.helpme.app.model.body.inventory.IInventory;
import com.helpme.app.model.body.inventory.IReadInventory;
import com.helpme.app.model.item.effect.ITarget;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.tile.edge.IEdge;

import java.util.List;

/**
 * Created by kopa on 2017-05-14.
 */
public class MockTarget implements IBody {
    public int attacked;

    @Override
    public void damage(float amount) {
        attacked++;
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
    public List<Maybe<IItem>> dropAllItems() {
        return null;
    }

    @Override
    public void kill() {

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
        return new Tuple2<>(null, null);
    }

    @Override
    public Tuple2<String, String[]> getDialogue() {
        return new Tuple2<>(null, null);
    }

    @Override
    public boolean traverse(IEdge edge) {
        return false;
    }

    @Override
    public IReadInventory readInventory() {
        return null;
    }
}
