package com.helpme.app.character;

import com.helpme.app.item.IItem;
import com.helpme.app.item.Item;
import com.helpme.app.tile.edge.IEdge;
import com.helpme.app.tile.edge.Traverse;
import com.helpme.app.utils.Vector2f;
import com.sun.javafx.geom.Edge;

import java.util.List;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Monster implements IMonster, IStats{
    private IInventory inventory;
    private Vector2f position;
    private Vector2f direction;

    private boolean dead;
    private float hitpoints;

    public Monster(IInventory inventory, Vector2f position, Vector2f direction) {
        this.inventory = inventory;
        this.position = position;
        this.direction = direction;
    }

    @Override
    public void attack(IStats target){
        IItem activeItem = inventory.getActiveItem();
        activeItem.attack(target);
    }

    @Override
    public void selfie(){
        IItem activeItem = inventory.getActiveItem();
        activeItem.selfie(this);
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
    public boolean traverse(IEdge edge){
        return edge.accept(new Traverse(this.inventory));
    }

    @Override
    public Monster clone() {
        return new Monster(inventory, position, direction);
    }

    @Override
    public void damage(float amount) {
        hitpoints -= amount <= 0 ? 0 : amount;
        if(hitpoints <= 0) {
            dead = true;
        }
    }

    @Override
    public void heal(float amount) {
        hitpoints += amount <= 0 ? 0 : amount;
    }
}
