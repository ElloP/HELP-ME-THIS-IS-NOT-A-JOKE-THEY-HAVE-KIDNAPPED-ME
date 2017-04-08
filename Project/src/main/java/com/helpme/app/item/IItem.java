package com.helpme.app.item;

/**
 * Created by kopa on 2017-04-08.
 */
public interface IItem{
    @Override
    boolean equals(Object o);

    Item clone();
}
