package com.helpme.app.game.model.consciousness.behaviour.concrete;

import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.behaviour.Comparison;
import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.game.model.consciousness.memory.IShortTerm;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.tuple.Tuple2;

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
    public int getPriority() {
        return priority;
    }

    @Override
    public Map<String, Tuple2<Integer, Comparison>> getPreconditions() {
        return new HashMap<String, Tuple2<Integer, Comparison>>() {
            {
                for (Entry<String, Tuple2<Integer, Comparison>> entry : preconditions.entrySet()) {
                    int value = entry.getValue().a;
                    Comparison comparison = entry.getValue().b;
                    put(entry.getKey(), new Tuple2<>(value, comparison));
                }
            }
        };
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public void setPreconditions(Map<String, Tuple2<Integer, Comparison>> preconditions) {
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
                    break;
                case LESS_THAN:
                    if (entry.getValue().a >= value) {
                        return false;
                    }
                    break;
                case EQUAL:
                    if (!entry.getValue().a.equals(value)) {
                        return false;
                    }
                    break;
                case NOT_EQUAL:
                    if (entry.getValue().a.equals(value)) {
                        return false;
                    }
                    break;
            }
        }

        return true;
    }

    @Override
    public void reset(IShortTerm memory) {

    }

    protected IAction<IConsciousness> moveTowards(IReadBody body, Vector2f nextPosition) {
        if (Intelligence.isFacing(body, nextPosition)) {
            return IConsciousness::moveForward;
        } else if (Intelligence.isRightOf(body, nextPosition)) {
            return IConsciousness::rotateRight;
        } else {
            return IConsciousness::rotateLeft;
        }
    }

    @Override
    public boolean equals(Object o) {
        Behaviour other;

        if (!(o instanceof Behaviour)){
            return false;
        }

        other = (Behaviour) o;

        if(((Behaviour) o).preconditions.size() != preconditions.size() || priority != other.priority){
            return false;
        }

        for (Map.Entry<String, Tuple2<Integer, Comparison>> entry : other.preconditions.entrySet()) {
            if (!preconditions.get(entry.getKey()).equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }
}
