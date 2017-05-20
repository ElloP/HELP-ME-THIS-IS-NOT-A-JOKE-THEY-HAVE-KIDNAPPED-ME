package com.helpme.app.behaviourtest;

import com.helpme.app.world.consciousness.behaviour.memories.IMemory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kopa on 2017-05-20.
 */
public class MockMemory implements IMemory {
    Map<String, Integer> memory;

    public MockMemory(){
        memory = new HashMap<String, Integer>(){
            {
                put("memory0", 4);
                put("memory2", 1);
                put("memory3", 7);
                put("memory1", 5);
            }
        };
    }

    @Override
    public IMemory clone() {
        return null;
    }

    @Override
    public Map<String, Integer> readLongTerm() {
        return null;
    }

    @Override
    public void removeLongTermMemory(String name) {

    }

    @Override
    public void updateLongTermMemory(String name, int value) {

    }

    @Override
    public Map<String, Integer> readShortTerm() {
        return null;
    }

    @Override
    public void updateShortTermMemory(String name, int value) {
        memory.put(name, value);
    }

    @Override
    public void removeShortTermMemory(String name) {

    }

    @Override
    public Map<String, Integer> readMemory() {
        return memory;
    }
}
