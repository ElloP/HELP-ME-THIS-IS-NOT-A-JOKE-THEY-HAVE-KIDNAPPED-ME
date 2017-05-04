package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.either.Left;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IMonster;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.world.level.read.IReadLevel;

/**
 * Created by kopa on 2017-04-14.
 */
public class DoNothing implements IBehaviour {
    @Override
    public Maybe update(IReadMonster monster, IReadLevel level) {
        return new Just(new Left<>(new DoNothing()));
    }
}
