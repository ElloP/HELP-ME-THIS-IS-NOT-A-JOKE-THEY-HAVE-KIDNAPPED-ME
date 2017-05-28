package com.helpme.app.game.model.consciousness.memory;

import com.helpme.app.utils.interfaces.ICopyable;

import java.util.Map;

/**
 * Created by kopa on 2017-05-19.
 */
public interface IMemory extends ILongTerm, IShortTerm, ICopyable<IMemory> {
    Map<String, Integer> readMemory();
}
