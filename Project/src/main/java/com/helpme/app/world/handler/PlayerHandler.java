package com.helpme.app.world.handler;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IMonster;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.world.item.IItem;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.level.ILevel;

/**
 * Created by Jacob on 2017-04-08.
 */
public class PlayerHandler extends MonsterHandler implements IPlayerHandler {

    public PlayerHandler(IMonster player, ILevel level) {
        super(player, level);
        level.setPlayer(monster);
    }

    public void update() {

    }

    public IReadMonster getPlayer() {
        return super.getMonster();
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

        Maybe<IReadMonster> maybeMonster = getFacingMonster();

        return maybeMonster.chain(m -> m.getDialogue());
    }

    public Maybe<Tuple2<String, String[]>> usePlayerTalk(int dialogueSelect) throws IllegalArgumentException {
        Maybe<IReadMonster> maybeMonster = getFacingMonster();

        return maybeMonster.chain(m -> m.getResponse(dialogueSelect));

    }

}
