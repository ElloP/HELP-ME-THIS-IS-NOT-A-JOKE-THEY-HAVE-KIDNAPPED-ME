package com.helpme.app.world.character.behaviour.memories;

import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-05-17.
 */
public interface IShortTerm {
    Map<String, Integer> readMemory();
    void updateShortTermMemory(String name, int value);
    void removeShortTermMemory(String name);
}
