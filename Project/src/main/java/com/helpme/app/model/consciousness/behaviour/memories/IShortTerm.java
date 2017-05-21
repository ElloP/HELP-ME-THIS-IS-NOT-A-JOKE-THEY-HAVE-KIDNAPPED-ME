package com.helpme.app.model.consciousness.behaviour.memories;

import java.util.Map;

/**
 * Created by kopa on 2017-05-17.
 */
public interface IShortTerm {
    Map<String, Integer> readShortTerm();
    void updateShortTermMemory(String name, int value);
    void removeShortTermMemory(String name);
}
