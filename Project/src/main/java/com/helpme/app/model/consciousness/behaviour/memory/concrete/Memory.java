package com.helpme.app.model.consciousness.behaviour.memory.concrete;

import com.helpme.app.model.consciousness.behaviour.memory.IMemory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kopa on 2017-05-17.
 */
public class Memory implements IMemory {
    private Map<String, Integer> shortTerm;
    private Map<String, Integer> longTerm;

    public Memory(Map<String, Integer> shortTerm, Map<String, Integer> longTerm) {
        this.shortTerm = shortTerm;
        this.longTerm = longTerm;
    }

    @Override
    public Map<String, Integer> readMemory() {
        Map<String, Integer> combined = new HashMap<>();
        combined.putAll(longTerm);
        combined.putAll(shortTerm);
        return combined;
    }

    @Override
    public Map<String, Integer> readShortTerm() {
        Map<String, Integer> copy = new HashMap<>();
        copy.putAll(shortTerm);
        return copy;
    }

    @Override
    public Map<String, Integer> readLongTerm() {
        Map<String, Integer> copy = new HashMap<>();
        copy.putAll(longTerm);
        return copy;
    }

    @Override
    public void updateShortTermMemory(String name, int value) {
        shortTerm.put(name, value);
    }

    @Override
    public void removeShortTermMemory(String name) {
        shortTerm.remove(name);
    }


    @Override
    public void removeLongTermMemory(String name) {
        longTerm.remove(name);
    }

    @Override
    public void updateLongTermMemory(String name, int value) {
        longTerm.put(name, value);
    }

    @Override
    public IMemory copy() {
        return new Memory(readShortTerm(), readLongTerm());
    }
}
