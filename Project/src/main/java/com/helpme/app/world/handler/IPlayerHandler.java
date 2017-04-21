package com.helpme.app.world.handler;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IMonster;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.world.item.IItem;
import com.helpme.app.utils.Tuple.Tuple2;
import com.helpme.app.utils.Vector2f;

/**
 * Created by kopa on 2017-04-15.
 */
public interface IPlayerHandler {
    IReadMonster getPlayer();

    void movePlayerForward();

    void movePlayerRight();

    void movePlayerBackward();

    void movePlayerLeft();

    void rotatePlayerRight();

    void rotatePlayerLeft();

    void setPlayerPosition(Vector2f position);

    void usePlayerAttack();

    void usePlayerPickupAll();

    void usePlayerPickupSingle(int index);

    void setPlayerItems(IItem[] items);

    void dropPlayerItem(int index);

    void usePlayerSelfie();

    void changePlayerActiveItem(int index);

    Maybe<Tuple2<String, String[]>> usePlayerTalk();

    Maybe<Tuple2<String, String[]>> usePlayerTalk(int dialogueSelect) throws IllegalArgumentException;

}
