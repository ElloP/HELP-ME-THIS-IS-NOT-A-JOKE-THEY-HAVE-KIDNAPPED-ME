package com.helpme.app.game.model.consciousness.memory.concrete;

import com.helpme.app.game.model.consciousness.memory.IMemory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kopa on 2017-05-20.
 */
public final class MemoryFactory {
    private MemoryFactory(){

    }

    public static IMemory createMemory(){
        return new Memory(new HashMap<>(), new HashMap<>());
    }

    public static IMemory createMemory(Map<String, Integer> shortTerm, Map<String, Integer> longTerm){
        return new Memory(shortTerm == null ? new HashMap<>() : shortTerm, longTerm == null ? new HashMap<>() : longTerm);
    }
}
