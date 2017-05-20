package com.helpme.app.world.consciousness.concrete;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.consciousness.ISurroundings;
import com.helpme.app.world.consciousness.behaviour.IBehaviour;
import com.helpme.app.world.consciousness.behaviour.memories.IMemory;
import javafx.collections.transformation.SortedList;

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

    @Override
    public void update() {
        Maybe<String> maybeAction = new Nothing<>();
        for (IBehaviour behaviour : behaviours) {
            if (behaviour.valid(memory.readMemory())) {
                behaviour.reset(memory);
                maybeAction = behaviour.execute(body, surroundings, memory);
                break;
            }
        }
        maybeAction.run(this::executeAction);
    }

    private void executeAction(String action){
        switch (action){
            case "move_forward" : moveForward(); break;
            case "move_right" : moveRight(); break;
            case "move_backward" : moveBackward(); break;
            case "move_left" : moveLeft(); break;
            case "rotate_right" : rotateRight(); break;
            case "rotate_left" : rotateLeft(); break;
            case "attack" : useAttack(); break;
            case "selfie" : useSelfie(); break;
        }
    }


    private Maybe<IReadBody> getPlayer() {
        return surroundings.readPlayer();
    }
}
