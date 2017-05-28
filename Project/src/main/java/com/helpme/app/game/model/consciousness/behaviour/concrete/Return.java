package com.helpme.app.game.model.consciousness.behaviour.concrete;

import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.IReadSurroundings;
import com.helpme.app.game.model.consciousness.behaviour.Comparison;
import com.helpme.app.game.model.consciousness.memory.IShortTerm;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple2;

import java.util.Map;

/**
 * Created by Jesper on 2017-04-20.
 */
public class Return extends Behaviour {
    private String returnedEvent;
    private String returningEvent;

    public Return(int priority, Map<String, Tuple2<Integer, Comparison>> preconditions, String returningEvent, String returnedEvent) {
        super(priority, preconditions);
        this.returnedEvent = returnedEvent;
        this.returningEvent = returningEvent;
    }

    public String getReturnedEvent() {
        return returnedEvent;
    }

    public String getReturningEvent() {
        return returningEvent;
    }

    @Override
    public void reset(IShortTerm memory) {
        memory.updateShortTermMemory(returnedEvent, 0);
        memory.updateShortTermMemory(returningEvent, 0);
    }

    @Override
    public Maybe<IAction<IConsciousness>> execute(IReadBody body, IReadSurroundings surroundings, IShortTerm memory) {
        if (returned(body, surroundings)) {
            memory.updateShortTermMemory(returnedEvent, 1);
            return new Nothing<>();
        }

        memory.updateShortTermMemory(returningEvent, 1);
        Vector2f nextPosition = surroundings.getPath(body.readPosition(), body.readStartingPosition()).b;
        return new Just<>(moveTowards(body, nextPosition));
    }

    private boolean returned(IReadBody body, IReadSurroundings surroundings) {
        int cost = surroundings.getPath(body.readPosition(), body.readStartingPosition()).c;
        return cost <= 0;
    }

    @Override
    public boolean equals(Object o) {
        Return other;

        if (!(o instanceof Return)) {
            return false;
        }

        other = (Return) o;

        return other.returnedEvent.equals(returnedEvent) &&
                other.returningEvent.equals(returningEvent) &&
                super.equals(o);
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }
}
