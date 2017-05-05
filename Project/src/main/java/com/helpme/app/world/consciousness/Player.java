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

    public Player(IBody monster, ISurroundings level) {
        super(monster, level);
    }

    public void update() {

    }

    public IReadBody getPlayer() {
        return super.readMonster();
    }

    public void movePlayerForward() {
        moveMonsterForward();
    }

    public void movePlayerRight() {
        moveMonsterRight();
    }

    public void movePlayerBackward() {
        moveMonsterBackward();
    }

    public void movePlayerLeft() {
        moveMonsterLeft();
    }

    public void rotatePlayerRight() {
        rotateMonsterRight();
    }

    public void rotatePlayerLeft() {
        rotateMonsterLeft();
    }

    public void setPlayerPosition(Vector2f position) {
        setMonsterPosition(position);
    }

    public void usePlayerAttack() {
        useMonsterAttack();
    }

    public void usePlayerPickupAll() {
        useMonsterPickupAll();
    }

    public void usePlayerPickupSingle(int index) {
        useMonsterPickupSingle(index);
    }

    public void setPlayerItems(IItem[] items) {
        setMonsterItems(items);
    }

    public void dropPlayerItem(int index) {
        dropMonsterItem(index);
    }

    public void usePlayerSelfie() {
        useMonsterSelfie();
    }

    public void changePlayerActiveItem(int index) {
        changeMonsterActiveItem(index);
    }

    public Maybe<Tuple2<String, String[]>> usePlayerTalk() {

        Maybe<IReadBody> maybeMonster = surroundings.readFacing(monster);

        return maybeMonster.chain(m -> m.getDialogue());
    }

    public Maybe<Tuple2<String, String[]>> usePlayerTalk(int dialogueSelect) throws IllegalArgumentException {
        Maybe<IReadBody> maybeMonster = surroundings.readFacing(monster);

        return maybeMonster.chain(m -> m.getResponse(dialogueSelect));

    }

}
