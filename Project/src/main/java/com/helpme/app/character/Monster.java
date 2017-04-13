package com.helpme.app.character;

import com.helpme.app.item.IItem;
import com.helpme.app.item.visitor.Attack;
import com.helpme.app.item.visitor.Pickup;
import com.helpme.app.item.visitor.Selfie;
import com.helpme.app.tile.edge.IEdge;
import com.helpme.app.tile.edge.visitor.Traverse;
import com.helpme.app.utils.Tuple.Tuple2;
import com.helpme.app.utils.Vector2f;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Monster implements IMonster {
    private IInventory inventory;
    private Vector2f position;
    private Vector2f direction;
    private Vector2f hitpoints; // NOTE (Jacob): (maxHitpoints, currentHitpoints)

    private boolean dead;

    private IDialogue dialogue;

    //Right now just for testing
    public Monster(Vector2f position, Vector2f direction, IDialogue dialogue){
        this.dialogue = dialogue;
        this.position = position;
        this.direction = direction;

    }

    public Monster(IInventory inventory, Vector2f position, Vector2f direction, float hitpoints) {
        this(inventory, position, direction, new Vector2f(hitpoints, hitpoints));
    }

    public Monster(IInventory inventory, Vector2f position, Vector2f direction, Vector2f hitpoints) {
        this.inventory = inventory;
        this.position = position;
        this.direction = direction;
        this.hitpoints = hitpoints;
    }

    @Override
    public void attack(ITarget target) {
        IItem activeItem = inventory.getActiveItem();
        activeItem.accept(new Attack(target));
    }

    @Override
    public void selfie() {
        IItem activeItem = inventory.getActiveItem();
        activeItem.accept(new Selfie(this));
    }

    @Override
    public Vector2f targetTile() {
        return Vector2f.add(position, direction);
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
    public Vector2f getPosition() {
        return position.clone();
    }

    @Override
    public Vector2f getDirection() {
        return direction.clone();
    }

    @Override
    public boolean traverse(IEdge edge) {
        return edge.accept(new Traverse(this.inventory));
    }

    @Override
    public boolean pickupItem(IItem item) {
        return item.accept(new Pickup(this.inventory));
    }

    @Override
    public IItem dropItem(int index) {
        return inventory.dropItem(index);
    }

    @Override
    public Monster clone() {
        return new Monster(inventory.clone(), position.clone(), direction.clone(), getHitpoints());
    }

    @Override
    public Tuple2<String,String[]> initiateDialogue() {
        return dialogue.initiateDialogue();
    }
    @Override
    public Tuple2<String,String[]> getResponse(int i){
        return dialogue.chooseDialogue(i);
    }

    @Override
    public Vector2f getHitpoints(){
        return hitpoints.clone();
    }

    @Override
    public IInventory getInventory() {
        return inventory.clone();
    }

    @Override
    public void setItems(IItem[] items) {
        inventory.setItems(items);
    }

    @Override
    public void damage(float amount) {
        amount = Math.abs(amount);
        hitpoints.y -= amount;
        if (hitpoints.y <= 0) {
            dead = true;
        }
    }

    @Override
    public void heal(float amount) {
        amount = Math.abs(amount);
        hitpoints.y = hitpoints.y + amount > hitpoints.x ? hitpoints.x : hitpoints.y + amount;
    }
}
