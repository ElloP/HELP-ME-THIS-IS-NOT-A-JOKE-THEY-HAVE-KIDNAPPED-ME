package com.helpme.app.character.behaviour;

import com.helpme.app.character.IMonster;
import com.helpme.app.world.level.IReadLevel;

/**
 * Created by kopa on 2017-04-14.
 */
public class DoNothing implements IBehaviour {
    @Override
    public void update(IMonster monster, IReadLevel level) {
        return;
    }
}
