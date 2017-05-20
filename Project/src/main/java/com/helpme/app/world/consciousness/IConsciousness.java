package com.helpme.app.world.consciousness;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.consciousness.behaviour.IBehaviour;
import com.helpme.app.world.consciousness.behaviour.memories.IMemory;
import com.helpme.app.world.item.IItem;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.utils.Vector2f;

import java.util.List;

/**
 * Created by kopa on 2017-04-15.
 */
public interface IConsciousness extends IThought {
    IBody readBody();

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

    Maybe<Tuple2<String, String[]>> useTalk();

    Maybe<Tuple2<String, String[]>> useTalk(int dialogueSelect) throws IllegalArgumentException;

}
