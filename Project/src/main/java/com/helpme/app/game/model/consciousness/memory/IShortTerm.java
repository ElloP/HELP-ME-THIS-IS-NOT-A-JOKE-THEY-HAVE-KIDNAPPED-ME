package com.helpme.app.game.model.consciousness.memory;

import java.util.Map;

/**
 * Created by kopa on 2017-05-17.
 */
public interface IShortTerm {
    Map<String, Integer> readShortTerm();
    void updateShortTermMemory(String name, int value);
    void removeShortTermMemory(String name);
}
