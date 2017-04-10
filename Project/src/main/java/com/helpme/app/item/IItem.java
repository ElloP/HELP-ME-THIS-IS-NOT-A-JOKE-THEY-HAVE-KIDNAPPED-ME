package com.helpme.app.item;

import com.helpme.app.character.IStats;

/**
 * Created by kopa on 2017-04-08.
 */
public interface IItem{
    @Override
    boolean equals(Object o);
    Item clone();

    void attack(IStats stats);
    void selfie(IStats stats);

    boolean addStack();
    boolean removeStack();
}
