package com.helpme.app.savetest;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.body.inventory.IInventory;
import com.helpme.app.game.model.body.inventory.IReadInventory;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.effect.ITarget;
import com.helpme.app.game.model.tile.edge.IEdge;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple2;

import java.util.List;
import java.util.Observable;

/**
 * Created by kopa on 2017-05-15.
 */
public class MockBody extends Observable implements IBody {
    private IInventory mockInventory;

    public MockBody(IInventory inventory){
        this.mockInventory = inventory;
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
    public void setActiveItem(int itemIndex) {

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
    public List<Maybe<IItem>> dropAllItems() {
        return null;
    }

    @Override
    public IInventory getInventory() {
        return mockInventory;
    }

    @Override
    public void setItems(IItem[] items) {

    }

    @Override
    public IBody copy(){
        return null;
    }

    @Override
    public void kill() {

    }

    @Override
    public Vector2f readPosition() {
        return Vector2f.ZERO;
    }

    @Override
    public Vector2f readDirection() {
        return new Vector2f(0,1);
    }

    @Override
    public Vector2f readHitpoints() {
        return new Vector2f(100,100);
    }

    @Override
    public float readMaxHitpoints() {
        return 0;
    }

    @Override
    public float readCurrentHitpoints() {
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
    public boolean traverse(IEdge edge) {
        return false;
    }

    @Override
    public IReadInventory readInventory() {
        return mockInventory;
    }
}
