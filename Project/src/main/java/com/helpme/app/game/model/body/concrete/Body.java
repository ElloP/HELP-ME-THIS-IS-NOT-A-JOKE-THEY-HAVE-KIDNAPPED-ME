package com.helpme.app.game.model.body.concrete;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.body.concrete.visitor.*;
import com.helpme.app.game.model.body.dialogue.IDialogue;
import com.helpme.app.game.model.body.inventory.IInventory;
import com.helpme.app.game.model.body.inventory.IReadInventory;
import com.helpme.app.game.model.body.inventory.concrete.Inventory;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.effect.ITarget;
import com.helpme.app.game.model.tile.edge.IEdge;
import com.helpme.app.utils.Copy;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple2;

import java.util.List;
import java.util.Observable;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Body extends Observable implements IBody {
    private IInventory inventory;
    private final Vector2f startingPosition;
    private Vector2f position;
    private Vector2f direction;
    private Vector2f hitpoints; // NOTE (Jacob): (maxHitpoints, currentHitpoints)
    private IDialogue dialogue;

    private boolean dead;

    public Body(IInventory inventory, Vector2f position, Vector2f direction, Vector2f hitpoints, Vector2f startingPosition, IDialogue dialogue){
        this.inventory = inventory == null ? new Inventory(null, null, null) : inventory;
        this.position = position == null ? Vector2f.ZERO : position;
        this.direction = direction == null ? Vector2f.NORTH : direction;
        this.hitpoints = hitpoints == null ? Vector2f.ZERO : hitpoints;
        this.startingPosition = startingPosition;
        this.dialogue = dialogue;

    }

    @Override
    public void attack(ITarget target) {
        if (target == null) return;
        Maybe<IItem> maybeItem = getCurrentItem();
        maybeItem.run(i -> i.accept(new Attack(target)));
    }

    private Maybe<IItem> getCurrentItem(){
        Maybe<IItem> maybeItem = inventory.getActiveItem();
        if(maybeItem.isJust()){
            return maybeItem;
        }
        return inventory.getDefaultItem();
    }

    @Override
    public void selfie() {
        Maybe<IItem> activeItem = getCurrentItem();
        activeItem.run(i -> i.accept(new Selfie(this)));
    }

    @Override
    public void rotateRight() {
        direction = direction.rotateRightAngle(1);
        notifyEvent(BodyEvent.ROTATE_RIGHT);
    }

    @Override
    public void rotateLeft() {
        direction = direction.rotateRightAngle(-1);
        notifyEvent(BodyEvent.ROTATE_LEFT);
    }

    private void move(Vector2f direction) {
        position = Vector2f.add(position, direction);
        notifyEvent(BodyEvent.POSITION);
    }

    @Override
    public void moveForward() {
        move(direction.forward());
        notifyEvent(BodyEvent.MOVE_FORWARD);
    }

    @Override
    public void moveRight() {
        move(direction.right());
        notifyEvent(BodyEvent.MOVE_RIGHT);
    }

    @Override
    public void moveBackward() {
        move(direction.backward());
        notifyEvent(BodyEvent.MOVE_BACKWARD);
    }

    @Override
    public void moveLeft() {
        move(direction.left());
        notifyEvent(BodyEvent.MOVE_LEFT);
    }

    @Override
    public void setPosition(Vector2f position) {
        this.position = position.toInt();
    }

    @Override
    public void setActiveItem(int itemIndex) {
        inventory.setActiveItem(itemIndex);
    }

    @Override
    public Vector2f readPosition() {
        return position.copy();
    }

    @Override
    public Vector2f readDirection() {
        return direction.copy();
    }

    @Override
    public boolean traverse(IEdge edge) {
        return edge.accept(new Traverse());
    }

    @Override
    public boolean unlock(IEdge edge) {
        return edge.accept(new Unlock(this.inventory));
    }

    @Override
    public boolean pickupItem(IItem item) {
        return item.accept(new Pickup(this.inventory));
    }

    @Override
    public Maybe<IItem> dropItem(int index) {
        return inventory.dropItem(index);
    }

    @Override
    public Body copy() {
        return new Body(inventory.copy(), position.copy(), direction.copy(), readHitpoints(), startingPosition.copy(), dialogue.copy());
    }

    @Override
    public float readMaxHitpoints() {
        return hitpoints.x;
    }

    @Override
    public float readCurrentHitpoints() {
        return hitpoints.y;
    }

    @Override
    public Tuple2<String, String[]> getDialogue() {
        return dialogue.initiateDialogue();
    }

    @Override
    public Tuple2<String, String[]> getResponse(int i) {
        return dialogue.chooseDialogue(i);
    }

    @Override
    public Vector2f readHitpoints() {
        return hitpoints.copy();
    }

    @Override
    public IInventory getInventory() {
        return inventory;
    }

    @Override
    public void setItems(IItem[] items) {
        inventory.setItems(Copy.toMaybeList(items));
    }

    @Override
    public void damage(float amount) {
        System.out.println("OWWW I AM BEING HURT");
        amount = Math.abs(amount);
        hitpoints.y -= amount;
        notifyEvent(BodyEvent.HEALTH);
        if (hitpoints.y <= 0) {
            kill();
        }
    }

    @Override
    public List<Maybe<IItem>> dropAllItems() {
        return inventory.dropItems();
    }

    @Override
    public void heal(float amount) {
        amount = Math.abs(amount);
        hitpoints.y = hitpoints.y + amount > hitpoints.x ? hitpoints.x : hitpoints.y + amount;
        notifyEvent(BodyEvent.HEALTH);
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public IReadInventory readInventory() {
        return inventory.copy();
    }

    @Override
    public void kill() {
        dead = true;
        notifyEvent(BodyEvent.DEAD);
    }

    private void notifyEvent(BodyEvent event){
        setChanged();
        notifyObservers(event);
    }

    public Vector2f readStartingPosition() {
        return startingPosition;
    }
}
