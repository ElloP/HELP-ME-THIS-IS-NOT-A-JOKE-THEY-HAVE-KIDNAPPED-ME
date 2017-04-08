package com.helpme.app.world;

import com.helpme.app.character.IMonster;
import com.helpme.app.utils.Vector2f;

/**
 * Created by kopa on 2017-04-08.
 */
public interface ILevel {
    boolean isEdgeBlocked(IMonster monster, Vector2f position, Vector2f direction);
    boolean isTileOccupied(Vector2f position);
    boolean isTileValid(Vector2f position);
}
