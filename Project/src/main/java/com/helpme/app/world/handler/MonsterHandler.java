package com.helpme.app.world.handler;

import com.helpme.app.character.IMonster;
import com.helpme.app.character.ITarget;
import com.helpme.app.character.Monster;
import com.helpme.app.item.IItem;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.level.ILevel;

/**
 * Created by Jesper on 2017-04-12.
 */

public abstract class MonsterHandler implements IHandler {
    protected IMonster monster;
    protected ILevel level;


    @Override
    public abstract void update();

    public MonsterHandler(IMonster monster, ILevel level){
        this.monster = monster.clone();
        this.level = level;
        level.addMonster(this.monster);
    }

    public Monster getMonster(){
        return monster.clone();
    }

    public void moveMonsterForward() {
        if (!isMovementAllowed(monster, monster.getDirection())) {
            return;
        }
        monster.moveForward();
    }

    public void moveMonsterRight() {
        if (!isMovementAllowed(monster, monster.getDirection().right())) {
            return;
        }
        monster.moveRight();
    }

    private boolean isMovementAllowed(IMonster monster, Vector2f direction) {
        Vector2f position = monster.getPosition();
        Vector2f destination = Vector2f.add(position, direction);

        if (level.isMonsterBlockedByEdge(monster, direction)) {
            return false;
        }
        if (level.isTileOccupied(destination)) {
            return false;
        }

        return true;
    }

    public void moveMonsterBackward() {
        if (!isMovementAllowed(monster, monster.getDirection().backward())) {
            return;
        }
        monster.moveBackward();
    }

    public void moveMonsterLeft() {
        if (!isMovementAllowed(monster, monster.getDirection().left())) {
            return;
        }
        monster.moveLeft();
    }

    public void rotateMonsterRight() {
        monster.rotateRight();
    }

    public void rotateMonsterLeft() {
        monster.rotateLeft();
    }

    public void setMonsterItems(IItem[] items) {
        monster.setItems(items);
    }

    public void dropMonsterItem(int index) {
        if (level.isTileValid(monster.getPosition())) {
            IItem item = monster.dropItem(index);
            if (item == null) {
                return;
            }
            level.addTileItem(monster.getPosition(), item);
        }

    }

    public void useMonsterSelfie() {
        monster.selfie();
    }

    public void changeMonsterActiveItem(int index) {
        monster.changeActiveItem(index);
    }


    public void setMonsterPosition(Vector2f position) {
        if (!level.isTileValid(position)) {
            return;
        }
        monster.setPosition(position);
    }

    public void useMonsterAttack() {
        Vector2f direction = monster.getDirection();
        ITarget target = level.getTarget(monster, direction);
        monster.attack(target);
    }

    public void useMonsterPickupAll() {
        Vector2f position = monster.getPosition();
        IItem[] items = level.removeTileItems(position);

        if (items == null) {
            return;
        }

        for (IItem item : items) {
            if (item == null) {
                continue;
            }

            if (!monster.pickupItem(item)) {
                level.addTileItem(position, item);
            }
        }
    }

    public void useMonsterPickupSingle(int index) {
        Vector2f position = monster.getPosition();
        IItem item = level.removeTileItem(position, index);
        if (item == null) {
            return;
        }
        monster.pickupItem(item);
    }

    protected IMonster getFacingMonster(){
        Vector2f position = monster.getPosition();
        Vector2f direction = monster.getDirection();
        Vector2f destination = Vector2f.add(position, direction);

        if (level.isMonsterBlockedByEdge(monster, direction)) {
            return null;
        }

        return level.getMonster(destination);
    }
}
