package com.helpme.app.world.handler;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.character.IMonster;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.world.character.target.ITarget;
import com.helpme.app.world.item.IItem;
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

    public IReadMonster getMonster(){
        return monster;
    }

    public void moveMonsterForward() {
        if (!isMovementAllowed(monster, monster.readDirection())) {
            return;
        }
        monster.moveForward();
    }

    public void moveMonsterRight() {
        if (!isMovementAllowed(monster, monster.readDirection().right())) {
            return;
        }
        monster.moveRight();
    }

    private boolean isMovementAllowed(IMonster monster, Vector2f direction) {
        Vector2f position = monster.readPosition();
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
        if (!isMovementAllowed(monster, monster.readDirection().backward())) {
            return;
        }
        monster.moveBackward();
    }

    public void moveMonsterLeft() {
        if (!isMovementAllowed(monster, monster.readDirection().left())) {
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
        if (level.isTileValid(monster.readPosition())) {
            Maybe<IItem> maybeItem = monster.dropItem(index);
            maybeItem.run(i -> level.addTileItem(monster.readPosition(), i));
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
        Vector2f direction = monster.readDirection();
        Maybe<ITarget> maybeTarget = level.getTarget(monster, direction);
        maybeTarget.run(t -> monster.attack(t));
        if(maybeTarget.check(t -> t.isDead())){
            level.updateDeadMonster(Vector2f.add(monster.readPosition(),monster.readDirection()));
        }
    }

    public void useMonsterPickupAll() {
        Vector2f position = monster.readPosition();
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
        Vector2f position = monster.readPosition();
        IItem item = level.removeTileItem(position, index);
        if (item == null) {
            return;
        }
        monster.pickupItem(item);
    }

    protected Maybe<IReadMonster> getFacingMonster(){
        Vector2f position = monster.readPosition();
        Vector2f direction = monster.readDirection();
        Vector2f destination = Vector2f.add(position, direction);

        if (level.isMonsterBlockedByEdge(monster, direction)) {
            return new Nothing();
        }

        return level.readMonster(destination);
    }
}
