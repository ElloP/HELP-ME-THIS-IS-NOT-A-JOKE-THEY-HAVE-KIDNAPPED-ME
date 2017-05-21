package com.helpme.app.model.consciousness.behaviour.memories;

import java.util.Map;

/**
 * Created by kopa on 2017-05-17.
 */
public interface ILongTerm {
    Map<String, Integer> readLongTerm();
    void removeLongTermMemory(String name);
    void updateLongTermMemory(String name, int value);
}
