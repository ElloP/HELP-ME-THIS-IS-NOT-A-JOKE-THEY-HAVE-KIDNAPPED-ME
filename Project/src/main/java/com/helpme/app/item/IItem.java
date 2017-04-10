package com.helpme.app.item;

import com.helpme.app.item.visitor.IItemVisitor;

/**
 * Created by kopa on 2017-04-08.
 */
public interface IItem{
    @Override
    boolean equals(Object o);
    Item clone();

    boolean accept(IItemVisitor visitor);
}
