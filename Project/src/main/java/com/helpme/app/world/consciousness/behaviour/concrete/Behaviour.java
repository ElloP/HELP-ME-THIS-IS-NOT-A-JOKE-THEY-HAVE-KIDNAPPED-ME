package com.helpme.app.world.consciousness.behaviour.concrete;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.consciousness.behaviour.IBehaviour;
import com.helpme.app.world.consciousness.behaviour.memories.IShortTerm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kopa on 2017-05-17.
 */
public abstract class Behaviour implements IBehaviour {
    private int priority;
    private Map<String, Tuple2<Integer, Comparison>> preconditions;

    public Behaviour(int priority, Map<String, Tuple2<Integer, Comparison>> preconditions) {
        this.priority = priority;
        this.preconditions = preconditions;
    }

    @Override
    public int getPriority(){
        return priority;
    }

    @Override
    public Map<String, Tuple2<Integer, Comparison>> getPreconditions(){
        return new HashMap<String, Tuple2<Integer, Comparison>>(){
            {
                for(Entry<String, Tuple2<Integer, Comparison>> entry : preconditions.entrySet()) {
                    int value = entry.getValue().a;
                    Comparison comparison = entry.getValue().b;
                    put(entry.getKey(), new Tuple2<>(value, comparison));
                }
            }
        };
    }

    @Override
    public void setPriority(int priority){
        this.priority = priority;
    }

    @Override
    public void setPreconditions(Map<String, Tuple2<Integer, Comparison>> preconditions){
        this.preconditions = preconditions;
    }


    @Override
    public boolean valid(Map<String, Integer> conditions) {
        for (Map.Entry<String, Tuple2<Integer, Comparison>> entry : preconditions.entrySet()) {
            Integer value = conditions.get(entry.getKey());

            if (value == null) return false;

            switch (entry.getValue().b) {
                case MORE_THAN:
                    if (entry.getValue().a <= value) {
                        return false;
                    }
                case LESS_THAN:
                    if (entry.getValue().a >= value) {
                        return false;
                    }
                case EQUAL:
                    if (!entry.getValue().a.equals(value)) {
                        return false;
                    }
            }
        }

        return true;
    }

    @Override
    public void reset(IShortTerm memory){

    }

    protected String moveTowards(IReadBody body, Vector2f nextPosition) {
        if (Intelligence.isFacing(body, nextPosition)){
            return "move_forward";
        } else if (Intelligence.isLeftOf(body, nextPosition)){
            return "rotate_left";
        } else {
            return "rotate_right";
        }
    }
}
