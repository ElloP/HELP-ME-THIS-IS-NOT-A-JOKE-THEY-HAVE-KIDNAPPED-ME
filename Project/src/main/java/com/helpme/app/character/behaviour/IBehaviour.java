package com.helpme.app.character.behaviour;

import com.helpme.app.character.IMonster;
import com.helpme.app.world.IReadLevel;

/**
 * Created by kopa on 2017-04-14.
 */
public interface IBehaviour {
    void update(IMonster monster, IReadLevel level);
}
