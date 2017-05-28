package com.helpme.app.game.model.consciousness.memory.concrete;

import com.helpme.app.game.model.consciousness.memory.IMemory;

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

    @Override
    public boolean equals(Object o) {
        Memory other;

        if (!(o instanceof Memory)) {
            return false;
        }

        other = (Memory) o;

        if (other.longTerm.size() != longTerm.size() || other.shortTerm.size() != shortTerm.size()) {
            return false;
        }

        for (Map.Entry<String, Integer> entry : other.longTerm.entrySet()){
            if(!longTerm.get(entry.getKey()).equals(entry.getValue())){
                return false;
            }
        }

        for (Map.Entry<String, Integer> entry : other.shortTerm.entrySet()){
            if(!shortTerm.get(entry.getKey()).equals(entry.getValue())){
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }
}
