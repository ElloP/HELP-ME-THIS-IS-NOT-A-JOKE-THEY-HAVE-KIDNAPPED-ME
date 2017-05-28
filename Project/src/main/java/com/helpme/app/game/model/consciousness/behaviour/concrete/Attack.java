package com.helpme.app.game.model.consciousness.behaviour.concrete;

import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.IReadSurroundings;
import com.helpme.app.game.model.consciousness.behaviour.Comparison;
import com.helpme.app.game.model.consciousness.memory.IShortTerm;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple2;

import java.util.Map;

/**
 * Created by kopa on 2017-05-19.
 */
public class Attack extends Behaviour {
    private String attackEvent;

    public Attack(int priority, Map<String, Tuple2<Integer, Comparison>> preconditions, String attackEvent) {
        super(priority, preconditions);
        this.attackEvent = attackEvent;
    }

    public String getAttackEvent(){
        return attackEvent;
    }

    @Override
    public void reset(IShortTerm memory){
        memory.updateShortTermMemory(attackEvent, 0);
    }

    @Override
    public Maybe<IAction<IConsciousness>> execute(IReadBody body, IReadSurroundings surroundings, IShortTerm memory) {
        if(surroundings.readFacing(body).isJust()){
            memory.updateShortTermMemory(attackEvent, 1);
        }
        return new Just<>(IConsciousness::useAttack);
    }

    @Override
    public boolean equals(Object o) {
        Attack other;

        if(!(o instanceof Attack)) {
            return false;
        }

        other = (Attack) o;

        return other.attackEvent.equals(attackEvent) && super.equals(o);
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }
}
