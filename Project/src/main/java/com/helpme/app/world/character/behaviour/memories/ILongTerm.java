package com.helpme.app.world.character.behaviour.memories;
import java.util.function.Function;

/**
 * Created by kopa on 2017-05-17.
 */
public interface ILongTerm extends IShortTerm {
    void removeLongTermMemory(String name);
    void updateLongTermMemory(String name, int value);
}
