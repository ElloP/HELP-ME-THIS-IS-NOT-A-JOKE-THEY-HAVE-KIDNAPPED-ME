package com.helpme.app.world.character.behaviour.memories;
import java.util.List;
import java.util.function.Function;

/**
 * Created by kopa on 2017-05-17.
 */
public interface IMemory<T> extends IReadMemory<T> {
    void remove(String name);
    void add(String name, T value);
    void update(String name, Function<T,T> value);
}
