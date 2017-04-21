package com.helpme.app.world.character;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.dialogue.IDialogue;
import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.character.inventory.IReadInventory;
import com.helpme.app.world.character.inventory.Inventory;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.visitor.Attack;
import com.helpme.app.world.item.visitor.Pickup;
import com.helpme.app.world.item.visitor.Selfie;
import com.helpme.app.world.tile.edge.IEdge;
import com.helpme.app.world.tile.edge.visitor.Traverse;
import com.helpme.app.utils.Tuple.Tuple2;
import com.helpme.app.utils.Vector2f;

import java.util.Observable;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Monster extends Observable implements IMonster {
    private IInventory inventory;
    private final Vector2f startingPosition;
    private Vector2f position;
    private Vector2f direction;
    private Vector2f hitpoints; // NOTE (Jacob): (maxHitpoints, currentHitpoints)

    private boolean dead;

    private IDialogue dialogue;

    //Right now just for testing
    public Monster(Vector2f position, Vector2f direction, IDialogue dialogue) {
        this.dialogue = dialogue;
        this.position = position;
        this.direction = direction;
        this.startingPosition = position;

    }

    public Monster(IInventory inventory, Vector2f position, Vector2f direction, float hitpoints) {
        this(inventory, position, direction, new Vector2f(hitpoints, hitpoints));
    }

    public Monster(IInventory inventory, Vector2f position, Vector2f direction, Vector2f hitpoints) {
        this.inventory = inventory == null ? new Inventory(null, null, null) : inventory;
        this.position = position == null ? Vector2f.zero : position;
        this.direction = direction == null ? Vector2f.up : direction;
        this.hitpoints = hitpoints == null ? Vector2f.zero : hitpoints;
        this.startingPosition = position;
    }

    @Override
    public void attack(ITarget target) {
        if (target == null) return;
        IItem activeItem = inventory.getActiveItem();
        activeItem.accept(new Attack(target));
    }

    @Override
    public void selfie() {
        IItem activeItem = inventory.getActiveItem();
        activeItem.accept(new Selfie(this));
    }

    @Override
    public void rotateRight() {
        direction = direction.rotateRightAngle(1);
    }

    @Override
    public void rotateLeft() {
        direction = direction.rotateRightAngle(-1);
    }

    private void move(Vector2f direction) {
        position = Vector2f.add(position, direction);
        setChanged();
        notifyObservers(position);
    }

    @Override
    public void moveForward() {
        move(direction.forward());
    }

    @Override
    public void moveRight() {
        move(direction.right());
    }

    @Override
    public void moveBackward() {
        move(direction.backward());
    }

    @Override
    public void moveLeft() {
        move(direction.left());
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
    public Monster clone() {
        return new Monster(inventory.clone(), position.clone(), direction.clone(), readHitpoints());
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
        inventory.setItems(Maybe.wrap(items));
    }

    @Override
    public void damage(float amount) {
        amount = Math.abs(amount);
        hitpoints.y -= amount;
        if (hitpoints.y <= 0) {
            setDead();
        }
    }

    @Override
    public void dropAllItems() {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i).isJust()) {
                dropItem(i);
            }
        }
    }

    @Override
    public void heal(float amount) {
        amount = Math.abs(amount);
        hitpoints.y = hitpoints.y + amount > hitpoints.x ? hitpoints.x : hitpoints.y + amount;
        setChanged();
        notifyObservers();
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
    public void setDead() {
        dead = true;
    }

    public Vector2f readStartingPosition() {
        return startingPosition;
    }
}
