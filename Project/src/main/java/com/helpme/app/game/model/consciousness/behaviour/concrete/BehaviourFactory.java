package com.helpme.app.game.model.consciousness.behaviour.concrete;

import com.helpme.app.game.model.consciousness.behaviour.Comparison;
import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.utils.tuple.Tuple2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kopa on 2017-05-17.
 */
public final class BehaviourFactory {
    private BehaviourFactory(){

    }

    public static IBehaviour createAttack(int priority, Map<String, Tuple2<Integer, Comparison>> preconditions, String attackEvent) {
        return new Attack(priority, preconditions == null ? new HashMap<>() : preconditions, attackEvent);
    }

    public static IBehaviour createFollow(int priority, Map<String, Tuple2<Integer, Comparison>> preconditions, int followingDistance, String foundEvent, String followingEvent, String lostEvent){
        return new Follow(priority, preconditions == null ? new HashMap<>() : preconditions, followingDistance, foundEvent, followingEvent, lostEvent);
    }

    public static IBehaviour createReturn(int priority, Map<String, Tuple2<Integer, Comparison>> preconditions, String returningEvent, String returnedEvent){
        return new Return(priority, preconditions == null ? new HashMap<>() : preconditions, returningEvent, returnedEvent);
    }

    public static IBehaviour createStay(int priority, Map<String, Tuple2<Integer, Comparison>> preconditions){
        return new Stay(priority, preconditions == null ? new HashMap<>() : preconditions);
    }
}
