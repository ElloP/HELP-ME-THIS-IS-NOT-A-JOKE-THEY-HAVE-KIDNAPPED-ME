package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.either.Right;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.functions.IAction2;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.consciousness.IReadSurroundings;
import com.helpme.app.world.consciousness.ISurroundings;

/**
 * Created by Jesper on 2017-04-20.
 */
public final class ActionFactory {

    public static Maybe<IAction2<IBody, ISurroundings>> createAction(String name){
        switch (name){
            case "move_forward" : return Maybe.wrap((body, surroundings) -> body.moveForward());
            case "move_right" : return Maybe.wrap((body, surroundings) -> body.moveRight());
            case "move_left" : return Maybe.wrap((body, surroundings) -> body.moveLeft());
            case "attack" : return Maybe.wrap((body, surroundings) -> surroundings.readPlayer().run(player -> body.attack(player)));
            case "rotate_right" : return Maybe.wrap((body, surroundings) -> body.rotateRight());
            case "rotate_left" : return Maybe.wrap((body, surroundings) -> body.rotateLeft());
        }

        return new Nothing<>();
    }
}
