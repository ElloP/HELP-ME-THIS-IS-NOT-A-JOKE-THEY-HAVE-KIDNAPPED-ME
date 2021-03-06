package com.helpme.app.game.model.consciousness;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple2;

import java.util.Observer;

/**
 * Created by kopa on 2017-04-15.
 */
public interface IConsciousness extends IThought, IReadConsciousness, Observer {

    void moveForward();

    void moveRight();

    void moveBackward();

    void moveLeft();

    void rotateRight();

    void rotateLeft();

    void setPosition(Vector2f position);

    void useAttack();

    void usePickupAll();

    void usePickupSingle(int index);

    void setItems(IItem[] items);

    void dropItem(int index);

    void useSelfie();

    void changeActiveItem(int index);

    IBody getBody();

    Maybe<Tuple2<String, String[]>> useTalk();

    Maybe<Tuple2<String, String[]>> useTalk(int dialogueSelect) throws IllegalArgumentException;

}
