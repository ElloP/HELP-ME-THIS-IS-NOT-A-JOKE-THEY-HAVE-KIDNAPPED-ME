package com.helpme.app.game.model.consciousness.concrete;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.ISurroundings;
import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.game.model.consciousness.behaviour.memory.IMemory;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import javafx.collections.transformation.SortedList;

import java.util.List;

/**
 * Created by Jesper on 2017-04-12.
 */
public class Enemy extends Consciousness {
    private SortedList<IBehaviour> behaviours;
    private IMemory memory;

    public Enemy(IBody body, ISurroundings surroundings, IMemory memory, SortedList<IBehaviour> behaviours) {
        super(body, surroundings);
        this.memory = memory;
        this.behaviours = behaviours;
    }

    public IMemory readMemory() {
        return memory.copy();
    }

    public List<IBehaviour> getBehaviours() {
        return behaviours;
    }

    @Override
    public void update() {
        Maybe<IAction<IConsciousness>> maybeAction = new Nothing<>();
        for (IBehaviour behaviour : behaviours) {
            if (behaviour.valid(memory.readMemory())) {
                behaviour.reset(memory);
                maybeAction = behaviour.execute(body, surroundings, memory);
                break;
            }
        }
        maybeAction.run(action -> action.apply(this));
    }
}
