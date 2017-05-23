package com.helpme.app.model.consciousness.concrete;

import com.helpme.app.model.body.IBody;
import com.helpme.app.model.consciousness.IConsciousness;
import com.helpme.app.model.consciousness.ISurroundings;
import com.helpme.app.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.model.consciousness.behaviour.memory.IMemory;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kopa on 2017-05-14.
 */
public final class ConsciousnessFactory {
    private ConsciousnessFactory() {

    }

    public static IConsciousness createEnemy(IBody body, ISurroundings surroundings, IMemory memory, SortedList<IBehaviour> behaviours) {
        return new Enemy(body, surroundings, memory, behaviours == null ? new SortedList<>(FXCollections.observableList(new ArrayList<>())) : behaviours);
    }

    public static IConsciousness createEnemy(IBody body, ISurroundings surroundings, IMemory memory, List<IBehaviour> behaviours) {
        return new Enemy(body, surroundings, memory, new SortedList<>(FXCollections.observableList(behaviours), Comparator.comparingInt(IBehaviour::getPriority)));
    }

    public static IConsciousness createPlayer(IBody body, ISurroundings surroundings) {
        return new Player(body, surroundings);
    }

}
