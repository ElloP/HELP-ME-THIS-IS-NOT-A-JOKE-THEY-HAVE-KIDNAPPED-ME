package com.helpme.app.world.consciousness.behaviour;

import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.either.Right;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.consciousness.IReadSurroundings;

/**
 * Created by Jesper on 2017-04-20.
 */
public final class Action {

    public static Either moveForwardAction(){
        return new Right <IBehaviour, IAction<IBody>> (m -> m.moveForward());
    }

    public static Either moveRightAction(){
        return new Right <IBehaviour, IAction<IBody>> (m -> m.moveRight());
    }

    public static Either moveLeftAction(){
        return new Right <IBehaviour, IAction<IBody>> (m -> m.moveRight());
    }

    public static Either attackAction(IReadSurroundings surroundings){
        return new Right<IBehaviour, IAction<IBody>> (m -> surroundings.readPlayer().run(p -> m.attack(p)));
    }

    public static Either rotateRight(){
        return new Right<IBehaviour, IAction<IBody>>(m -> m.rotateRight());
    }

    public static Either rotateLeft(){
        return new Right<IBehaviour, IAction<IBody>>(m -> m.rotateLeft());
    }

}