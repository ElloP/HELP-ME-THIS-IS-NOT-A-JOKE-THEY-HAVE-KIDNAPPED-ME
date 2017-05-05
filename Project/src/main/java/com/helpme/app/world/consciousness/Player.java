package com.helpme.app.world.consciousness;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.item.IItem;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.utils.Vector2f;

/**
 * Created by Jacob on 2017-04-08.
 */
public class Player extends Consciousness implements IPlayer {

    public Player(IBody body, ISurroundings level) {
        super(body, level);
    }

    public void update() {

    }

    public IReadBody getPlayer() {
        return super.readBody();
    }

    public void movePlayerForward() {
        moveForward();
    }

    public void movePlayerRight() {
        moveRight();
    }

    public void movePlayerBackward() {
        moveBackward();
    }

    public void movePlayerLeft() {
        moveLeft();
    }

    public void rotatePlayerRight() {
        rotateRight();
    }

    public void rotatePlayerLeft() {
        rotateLeft();
    }

    public void setPlayerPosition(Vector2f position) {
        setPosition(position);
    }

    public void usePlayerAttack() {
        useAttack();
    }

    public void usePlayerPickupAll() {
        usePickupAll();
    }

    public void usePlayerPickupSingle(int index) {
        usePickupSingle(index);
    }

    public void setPlayerItems(IItem[] items) {
        setItems(items);
    }

    public void dropPlayerItem(int index) {
        dropItem(index);
    }

    public void usePlayerSelfie() {
        useSelfie();
    }

    public void changePlayerActiveItem(int index) {
        changeActiveItem(index);
    }

    public Maybe<Tuple2<String, String[]>> usePlayerTalk() {

        Maybe<IReadBody> maybeBody = surroundings.readFacing(body);

        return maybeBody.chain(m -> m.getDialogue());
    }

    public Maybe<Tuple2<String, String[]>> usePlayerTalk(int dialogueSelect) throws IllegalArgumentException {
        Maybe<IReadBody> maybeBody = surroundings.readFacing(body);

        return maybeBody.chain(m -> m.getResponse(dialogueSelect));

    }

}
