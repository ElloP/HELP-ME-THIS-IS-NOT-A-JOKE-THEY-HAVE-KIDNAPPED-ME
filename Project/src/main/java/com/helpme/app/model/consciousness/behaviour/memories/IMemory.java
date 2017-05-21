package com.helpme.app.model.consciousness.behaviour.memories;

import com.helpme.app.utils.interfaces.ICloneable;

import java.util.Map;

/**
 * Created by kopa on 2017-05-19.
 */
public interface IMemory extends ILongTerm, IShortTerm, ICloneable<IMemory> {
    Map<String, Integer> readMemory();
}
