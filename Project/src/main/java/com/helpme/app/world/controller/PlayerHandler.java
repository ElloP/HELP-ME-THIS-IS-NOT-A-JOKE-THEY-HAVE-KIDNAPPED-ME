package com.helpme.app.world.controller;

import com.helpme.app.character.IMonster;
import com.helpme.app.item.IItem;
import com.helpme.app.utils.Tuple.Tuple2;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.level.ILevel;

/**
 * Created by Jacob on 2017-04-08.
 */
public class PlayerHandler extends MonsterHandler {

    public PlayerHandler(IMonster player, ILevel level) {
        super(player, level);
        level.setPlayer(monster);
    }

    public void update() {

    }

    public IMonster getPlayer() {
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

    public Tuple2<String,String[]> usePlayerTalk() { //TODO (jacob) change name to something better

        IMonster monster = getFacingMonster();

        if(monster == null) return null;

        Tuple2<String,String[]> response = monster.initiateDialogue();

        //if(response == null) System.out.println("response == null in usePlayerTalk(i)");

        return response;
    }
    public Tuple2<String,String[]> usePlayerTalk(int dialogueSelect) throws IllegalArgumentException{
        IMonster monster = getFacingMonster();

        if(monster == null) return null;

        Tuple2<String,String[]> response = monster.getResponse(dialogueSelect);

        return response;
    }

}
