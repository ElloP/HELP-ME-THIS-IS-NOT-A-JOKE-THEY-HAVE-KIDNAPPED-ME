package com.helpme.app.world.consciousness;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.character.target.ITarget;
import com.helpme.app.world.item.IItem;
import com.helpme.app.utils.Vector2f;

import java.util.List;

/**
 * Created by Jesper on 2017-04-12.
 */

public abstract class Consciousness implements IConsciousness {
    protected IBody body;
    protected ISurroundings surroundings;

    @Override
    public abstract void update();

    public Consciousness(IBody body, ISurroundings surroundings) {
        this.body = body;
        this.surroundings = surroundings;
    }

    public IReadBody readBody() {
        return body;
    }

    public void moveForward() {
        if (!surroundings.isMovementAllowed(body, body.readDirection())) {
            return;
        }
        body.moveForward();
    }

    public void moveRight() {
        if (!surroundings.isMovementAllowed(body, body.readDirection().right())) {
            return;
        }
        body.moveRight();
    }

    public void moveBackward() {
        if (!surroundings.isMovementAllowed(body, body.readDirection().backward())) {
            return;
        }
        body.moveBackward();
    }

    public void moveLeft() {
        if (!surroundings.isMovementAllowed(body, body.readDirection().left())) {
            return;
        }
        body.moveLeft();
    }

    public void rotateRight() {
        body.rotateRight();
    }

    public void rotateLeft() {
        body.rotateLeft();
    }

    public void setItems(IItem[] items) {
        body.setItems(items);
    }

    public void dropItem(int index) {
        if (surroundings.isTileValid(body.readPosition())) {
            Maybe<IItem> maybeItem = body.dropItem(index);
            maybeItem.run(i -> surroundings.addTileItem(body.readPosition(), i));
        }

    }

    public void changeActiveItem(int index) {
        body.changeActiveItem(index);
    }


    public void setPosition(Vector2f position) {
        if (!surroundings.isTileValid(position)) {
            return;
        }
        body.setPosition(position);
    }

    public void useSelfie() {
        body.selfie();
    }

    public void useAttack() {
        Vector2f direction = body.readDirection();
        Maybe<ITarget> maybeTarget = surroundings.getTarget(body, direction);
        maybeTarget.run(t -> body.attack(t));
        if (maybeTarget.check(t -> t.isDead())) {
            surroundings.updateDeadBody(Vector2f.add(body.readPosition(), body.readDirection()));
        }
    }

    public void usePickupAll() {
        Vector2f position = body.readPosition();
        Maybe<List<Maybe<IItem>>> maybeItems = surroundings.removeTileItems(position);

        maybeItems.run(items -> {
                    for (Maybe<IItem> maybeItem : items) {
                        maybeItem.run(item -> {
                                    if (!body.pickupItem(item)) {
                                        surroundings.addTileItem(position, item);
                                    }
                                }
                        );
                    }
                }
        );
    }

    public void usePickupSingle(int index) {
        Vector2f position = body.readPosition();
        Maybe<IItem> maybeItem = surroundings.removeTileItem(position, index);
        maybeItem.run(item -> {
                    if (!body.pickupItem(item)) {
                        surroundings.addTileItem(position, item);
                    }
                }
        );
    }

}
