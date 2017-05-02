package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.either.Right;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.world.character.IMonster;
import com.helpme.app.world.level.IReadLevel;

/**
 * Created by Jesper on 2017-04-20.
 */
public final class Action {

    public static Either moveForwardAction(){
        return new Right <IBehaviour, IAction<IMonster>> (m -> m.moveForward());
    }

    public static Either moveRightAction(){
        return new Right <IBehaviour, IAction<IMonster>> (m -> m.moveRight());
    }

    public static Either moveLeftAction(){
        return new Right <IBehaviour, IAction<IMonster>> (m -> m.moveRight());
    }

    public static Either attackAction(IReadLevel level){
        return new Right<IBehaviour, IAction<IMonster>> (m -> level.readPlayer().run(p -> m.attack(p)));
    }

    public static Either rotateRight(){
        return new Right<IBehaviour, IAction<IMonster>>(m -> m.rotateRight());
    }

    public static Either rotateLeft(){
        return new Right<IBehaviour, IAction<IMonster>>(m -> m.rotateLeft());
    }

}
