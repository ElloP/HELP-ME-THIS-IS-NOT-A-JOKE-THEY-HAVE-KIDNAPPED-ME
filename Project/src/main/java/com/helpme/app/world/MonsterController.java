package com.helpme.app.world;

import com.helpme.app.character.IMonster;
import com.helpme.app.character.ITarget;
import com.helpme.app.item.IItem;
import com.helpme.app.utils.Vector2f;

/**
 * Created by Jesper on 2017-04-12.
 */
public class MonsterController implements IController {
    IMonster monster;
    IMonster player;
    ILevel level;

    @Override
    public void update() {

    }

    public MonsterController(IMonster player, IMonster monster, ILevel level){
        this.player = player;
        this.monster = monster;
        this.level = level;
    }

    public IMonster getMonster(){
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

        if (level.isEdgeBlocked(monster, position, direction)) {
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
        player.moveBackward();
    }

    public void moveMonsterLeft() {
        if (!isMovementAllowed(monster, monster.getDirection().left())) {
            return;
        }
        player.moveLeft();
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
}
