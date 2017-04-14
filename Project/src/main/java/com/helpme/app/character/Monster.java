package com.helpme.app.character;

import com.helpme.app.item.IItem;
import com.helpme.app.item.visitor.Attack;
import com.helpme.app.item.visitor.Pickup;
import com.helpme.app.item.visitor.Selfie;
import com.helpme.app.tile.edge.IEdge;
import com.helpme.app.tile.edge.visitor.Traverse;
import com.helpme.app.utils.Tuple.Tuple2;
import com.helpme.app.utils.Vector2f;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Monster extends Observable implements IMonster {
    private IInventory inventory;
    private final Vector2f startPosition;
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
        this.startPosition = position;

    }

    public Monster(IInventory inventory, Vector2f position, Vector2f direction, float hitpoints) {
        this(inventory, position, direction, new Vector2f(hitpoints, hitpoints));
    }

    public Monster(IInventory inventory, Vector2f position, Vector2f direction, Vector2f hitpoints) {
        this.inventory = inventory;
        this.position = position;
        this.direction = direction;
        this.hitpoints = hitpoints;
        this.startPosition = position;
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
        setChanged();
        notifyObservers(position);
    }

    @Override
    public void moveForward() {
        move(direction.forward());
        setChanged();
        notifyObservers();
    }

    @Override
    public void moveRight() {
        move(direction.right());
        setChanged();
        notifyObservers();
    }

    @Override
    public void moveBackward() {
        move(direction.backward());
        setChanged();
        notifyObservers();
    }

    @Override
    public void moveLeft() {
        move(direction.left());
        setChanged();
        notifyObservers();
    }

    @Override
    public void setPosition(Vector2f position) {
        this.position = position.toInt();
        setChanged();
        notifyObservers();
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
    public Vector2f cloneForward() {
        Monster monster = this.clone();
        monster.moveForward();
        return monster.getPosition();
    }

    @Override
    public Vector2f cloneRight() {
        Monster monster = this.clone();
        monster.moveRight();
        return monster.getPosition();
    }

    @Override
    public Vector2f cloneLeft() {
        Monster monster = this.clone();
        monster.moveLeft();
        return monster.getPosition();
    }

    @Override
    public Vector2f cloneBackward() {
        Monster monster = this.clone();
        monster.moveBackward();
        return monster.getPosition();
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
        setChanged();
        notifyObservers();
    }

    @Override
    public void heal(float amount) {
        amount = Math.abs(amount);
        hitpoints.y = hitpoints.y + amount > hitpoints.x ? hitpoints.x : hitpoints.y + amount;
        setChanged();
        notifyObservers();
    }

    public boolean inVicinity(Vector2f to, int longestDistance){
        if (Vector2f.equals(startPosition, to))
            longestDistance--;
        ArrayList<Vector2f> positions = new ArrayList<>();
        for (int i = 1; i <= longestDistance; i++) {
            ArrayList<Vector2f> temp = new ArrayList<>();
            for (Vector2f pos : positions) {
                for (Vector2f neighbour : Vector2f.getNeighbors(pos))
                    temp.add(neighbour);
            }
            positions.addAll(temp);
        }
        return positions.contains(to);
    }

    public Vector2f getStartPosition(){
        return startPosition;
    }

}
