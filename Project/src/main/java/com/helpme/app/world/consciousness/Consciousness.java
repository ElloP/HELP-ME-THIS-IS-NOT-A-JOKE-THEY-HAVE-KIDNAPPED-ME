package com.helpme.app.world.consciousness;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.character.target.ITarget;
import com.helpme.app.world.item.IItem;
import com.helpme.app.utils.Vector2f;

/**
 * Created by Jesper on 2017-04-12.
 */

public abstract class Consciousness implements IConsciousness {
    protected IBody monster;
    protected ISurroundings surroundings;

    @Override
    public abstract void update();

    public Consciousness(IBody monster, ISurroundings level){
        this.monster = monster;
        this.surroundings = level;
    }

    public IReadBody readMonster(){
        return monster;
    }

    public void moveMonsterForward() {
        if (!surroundings.isMovementAllowed(monster, monster.readDirection())) {
            return;
        }
        monster.moveForward();
    }

    public void moveMonsterRight() {
        if (!surroundings.isMovementAllowed(monster, monster.readDirection().right())) {
            return;
        }
        monster.moveRight();
    }

    public void moveMonsterBackward() {
        if (!surroundings.isMovementAllowed(monster, monster.readDirection().backward())) {
            return;
        }
        monster.moveBackward();
    }

    public void moveMonsterLeft() {
        if (!surroundings.isMovementAllowed(monster, monster.readDirection().left())) {
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
        if (surroundings.isTileValid(monster.readPosition())) {
            Maybe<IItem> maybeItem = monster.dropItem(index);
            maybeItem.run(i -> surroundings.addTileItem(monster.readPosition(), i));
        }

    }

    public void useMonsterSelfie() {
        monster.selfie();
    }

    public void changeMonsterActiveItem(int index) {
        monster.changeActiveItem(index);
    }


    public void setMonsterPosition(Vector2f position) {
        if (!surroundings.isTileValid(position)) {
            return;
        }
        monster.setPosition(position);
    }

    public void useMonsterAttack() {
        Vector2f direction = monster.readDirection();
        Maybe<ITarget> maybeTarget = surroundings.getTarget(monster, direction);
        maybeTarget.run(t -> monster.attack(t));
        if(maybeTarget.check(t -> t.isDead())){
            surroundings.updateDeadMonster(Vector2f.add(monster.readPosition(),monster.readDirection()));
        }
    }

    public void useMonsterPickupAll() {
        Vector2f position = monster.readPosition();
        IItem[] items = surroundings.removeTileItems(position);

        if (items == null) {
            return;
        }

        for (IItem item : items) {
            if (item == null) {
                continue;
            }

            if (!monster.pickupItem(item)) {
                surroundings.addTileItem(position, item);
            }
        }
    }

    public void useMonsterPickupSingle(int index) {
        Vector2f position = monster.readPosition();
        IItem item = surroundings.removeTileItem(position, index);
        if (item == null) {
            return;
        }
        monster.pickupItem(item);
    }

}
