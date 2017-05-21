package com.helpme.app.model.body.concrete;

import com.helpme.app.utils.Clone;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.model.body.IBody;
import com.helpme.app.model.body.concrete.visitor.*;
import com.helpme.app.model.body.dialogue.IDialogue;
import com.helpme.app.model.body.inventory.IInventory;
import com.helpme.app.model.body.inventory.IReadInventory;
import com.helpme.app.model.body.inventory.concrete.Inventory;
import com.helpme.app.model.item.effect.ITarget;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.tile.edge.IEdge;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.utils.Vector2f;

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
        this.position = position == null ? Vector2f.zero : position;
        this.direction = direction == null ? Vector2f.north : direction;
        this.hitpoints = hitpoints == null ? Vector2f.zero : hitpoints;
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
        setChanged();
        notifyObservers(Event.Direction);
    }

    @Override
    public void rotateLeft() {
        direction = direction.rotateRightAngle(-1);
        setChanged();
        notifyObservers(Event.Direction);
    }

    private void move(Vector2f direction) {
        position = Vector2f.add(position, direction);
        setChanged();
        notifyObservers(position);
    }

    @Override
    public void moveForward() {
        move(direction.forward());
        setChanged();
        notifyObservers(Event.Position);
    }

    @Override
    public void moveRight() {
        move(direction.right());
        setChanged();
        notifyObservers(Event.Position);
    }

    @Override
    public void moveBackward() {
        move(direction.backward());
        setChanged();
        notifyObservers(Event.Position);
    }

    @Override
    public void moveLeft() {
        move(direction.left());
        setChanged();
        notifyObservers(Event.Position);
    }

    @Override
    public void setPosition(Vector2f position) {
        this.position = position.toInt();
    }

    @Override
    public void changeActiveItem(int itemIndex) {
        inventory.changeActiveItem(itemIndex);
    }

    @Override
    public Vector2f readPosition() {
        return position.clone();
    }

    @Override
    public Vector2f readDirection() {
        return direction.clone();
    }

    @Override
    public boolean isTraversable(IEdge edge) {
        return edge.accept(new Traverse(this.inventory));
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
    public Body clone() {
        return new Body(inventory.clone(), position.clone(), direction.clone(), readHitpoints(), startingPosition.clone(), dialogue.clone());
    }

    @Override
    public float readMaxHp() {
        return hitpoints.x;
    }

    @Override
    public float readCurrentHp() {
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
        return hitpoints.clone();
    }

    @Override
    public IInventory getInventory() {
        return inventory.clone();
    }

    @Override
    public void setItems(IItem[] items) {
        inventory.setItems(Clone.toMaybeList(items));
    }

    @Override
    public void damage(float amount) {
        amount = Math.abs(amount);
        hitpoints.y -= amount;
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
        setChanged();
        notifyObservers(Event.Health);
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public IReadInventory readInventory() {
        return inventory;
    }

    @Override
    public void kill() {
        dead = true;
        setChanged();
        notifyObservers(Event.Dead);
    }

    public Vector2f readStartingPosition() {
        return startingPosition;
    }
}
