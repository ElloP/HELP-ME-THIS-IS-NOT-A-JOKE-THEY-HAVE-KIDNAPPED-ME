package com.helpme.app.character;

import com.helpme.app.utils.Tuple.Tuple2;
import com.helpme.app.utils.Vector2f;

/**
 * Created by kopa on 2017-04-14.
 */
public interface IReadMonster {
    Vector2f getPosition();
    Vector2f getDirection();
    Vector2f getHitpoints();
    Vector2f getStartingPosition();
    Tuple2<String,String[]> getResponse(int i);
}
