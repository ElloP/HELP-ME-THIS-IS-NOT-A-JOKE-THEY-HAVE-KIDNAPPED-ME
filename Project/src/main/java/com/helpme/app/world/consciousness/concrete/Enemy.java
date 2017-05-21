package com.helpme.app.world.consciousness.concrete;

import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.consciousness.ISurroundings;
import com.helpme.app.world.consciousness.behaviour.IBehaviour;
import com.helpme.app.world.consciousness.behaviour.memories.IMemory;
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
        return memory.clone();
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


    private Maybe<IReadBody> getPlayer() {
        return surroundings.readPlayer();
    }
}
