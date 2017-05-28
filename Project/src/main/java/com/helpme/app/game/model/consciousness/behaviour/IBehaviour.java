package com.helpme.app.game.model.consciousness.behaviour;

import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.IReadSurroundings;
import com.helpme.app.game.model.consciousness.memory.IShortTerm;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple2;

import java.util.Map;

/**
 * Created by kopa on 2017-04-14.
 */
public interface IBehaviour{
    int getPriority();
    Map<String, Tuple2<Integer, Comparison>> getPreconditions();

    void setPriority(int priority);
    void setPreconditions(Map<String, Tuple2<Integer, Comparison>> preconditions);

    boolean valid(Map<String, Integer> conditions);
    void reset(IShortTerm memory);
    Maybe<IAction<IConsciousness>> execute(IReadBody body, IReadSurroundings surroundings, IShortTerm memory);
}
