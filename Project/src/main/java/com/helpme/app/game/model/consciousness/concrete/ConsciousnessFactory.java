package com.helpme.app.game.model.consciousness.concrete;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.body.concrete.BodyFactory;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.ISurroundings;
import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.game.model.consciousness.behaviour.memory.IMemory;
import com.helpme.app.game.model.consciousness.behaviour.memory.concrete.MemoryFactory;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kopa on 2017-05-14.
 */
public final class ConsciousnessFactory {
    private ConsciousnessFactory() {

    }

    public static IConsciousness createEnemy(IBody body, ISurroundings surroundings, IMemory memory, SortedList<IBehaviour> behaviours) {
        return new Enemy(
                body,
                surroundings,
                memory == null ? MemoryFactory.createMemory() : memory,
                behaviours == null ? new SortedList<>(FXCollections.observableList(new ArrayList<>())) : behaviours);
    }

    public static IConsciousness createEnemy(IBody body, ISurroundings surroundings, IMemory memory, List<IBehaviour> behaviours) {
        return createEnemy(
                body,
                surroundings,
                memory,
                new SortedList<>(
                        FXCollections.observableList(behaviours == null ? new ArrayList<>() : behaviours),
                        Comparator.comparingInt(IBehaviour::getPriority)));
    }

    public static IConsciousness createPlayer(IBody body, ISurroundings surroundings) {
        return new Player(body, surroundings);
    }

}
