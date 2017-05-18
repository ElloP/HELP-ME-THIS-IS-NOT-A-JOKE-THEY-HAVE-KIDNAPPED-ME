package com.helpme.app.world.consciousness.concrete;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.item.effect.ITarget;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.consciousness.ISurroundings;
import com.helpme.app.world.item.IItem;
import com.helpme.app.utils.Vector2f;

import java.util.List;
import java.util.Observable;

/**
 * Created by Jesper on 2017-04-12.
 */

public abstract class Consciousness extends Observable implements IConsciousness {
    protected IBody body;
    protected ISurroundings surroundings;

    @Override
    public abstract void update();

    public Consciousness(IBody body, ISurroundings surroundings) {
        this.body = body;
        this.surroundings = surroundings;
    }


    @Override
    public IBody readBody() {
        return body;
    }

    @Override
    public void moveForward() {
        if (!surroundings.isMovementAllowed(body, body.readDirection())) {
            return;
        }
        body.moveForward();
    }

    @Override
    public void moveRight() {
        if (!surroundings.isMovementAllowed(body, body.readDirection().right())) {
            return;
        }
        body.moveRight();
    }

    @Override
    public void moveBackward() {
        if (!surroundings.isMovementAllowed(body, body.readDirection().backward())) {
            return;
        }
        body.moveBackward();
    }

    @Override
    public void moveLeft() {
        if (!surroundings.isMovementAllowed(body, body.readDirection().left())) {
            return;
        }
        body.moveLeft();
    }

    @Override
    public void rotateRight() {
        body.rotateRight();
    }

    @Override
    public void rotateLeft() {
        body.rotateLeft();
    }

    @Override
    public void setItems(IItem[] items) {
        body.setItems(items);
    }

    @Override
    public void dropItem(int index) {
        if (surroundings.isTileValid(body.readPosition())) {
            Maybe<IItem> maybeItem = body.dropItem(index);
            maybeItem.run(i -> surroundings.addTileItem(body.readPosition(), i));
        }

    }

    @Override
    public void changeActiveItem(int index) {
        body.changeActiveItem(index);
    }

    @Override
    public void setPosition(Vector2f position) {
        if (!surroundings.isTileValid(position)) {
            return;
        }
        body.setPosition(position);
    }

    @Override
    public void useSelfie() {
        body.selfie();
    }

    @Override
    public void useAttack() {
        Vector2f direction = body.readDirection();
        Maybe<ITarget> maybeTarget = surroundings.getTarget(body, direction);
        maybeTarget.run(t -> body.attack(t));
        if (maybeTarget.check(t -> t.isDead())) {
            surroundings.updateDeadBody(Vector2f.add(body.readPosition(), body.readDirection()));
        }
    }

    @Override
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

    @Override
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

    @Override
    public Maybe<Tuple2<String, String[]>> useTalk() {
        return new Nothing<>();
    }

    @Override
    public Maybe<Tuple2<String, String[]>> useTalk(int dialogueSelect) throws IllegalArgumentException {
        return new Nothing<>();
    }
}
