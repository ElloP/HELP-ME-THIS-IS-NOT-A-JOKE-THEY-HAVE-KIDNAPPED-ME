package com.helpme.app.world.character.behaviour.memories;

import java.util.List;
import java.util.function.Function;

/**
 * Created by kopa on 2017-05-17.
 */
public class Memories<T> implements IMemories<T> {
    @Override
    public List<IMemory<T>> readMemories() {
        return null;
    }

    @Override
    public void removeMemory(String name) {

    }

    @Override
    public void addMemory(IMemory memory) {

    }

    @Override
    public void updateMemory(String name, Function<T, T> value) {

    }
}
