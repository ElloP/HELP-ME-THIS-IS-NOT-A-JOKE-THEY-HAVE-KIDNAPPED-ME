package com.helpme.app.world.character.behaviour.memories;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kopa on 2017-05-17.
 */
public class Memory implements ILongTerm {
    Map<String, Integer> shortterm;
    Map<String, Integer> longterm;


    @Override
    public Map<String, Integer> readMemory() {
        Map<String, Integer> combined = new HashMap<>();
        for(Map.Entry<String, Integer> entry : longterm.entrySet()){
            combined.put(entry.getKey(), entry.getValue());
        }

        for(Map.Entry<String, Integer> entry : shortterm.entrySet()){
            combined.put(entry.getKey(), entry.getValue());
        }

        return combined;
    }

    @Override
    public void updateShortTermMemory(String name, int value) {
        shortterm.put(name, value);
    }

    @Override
    public void removeShortTermMemory(String name) {
        shortterm.remove(name);
    }

    @Override
    public void removeLongTermMemory(String name) {
        longterm.remove(name);
    }

    @Override
    public void updateLongTermMemory(String name, int value) {

    }
}
