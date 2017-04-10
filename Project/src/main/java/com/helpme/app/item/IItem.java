package com.helpme.app.item;

import com.helpme.app.item.visitor.IItemVisitor;

/**
 * Created by kopa on 2017-04-08.
 */
public interface IItem extends Cloneable{
    @Override
    boolean equals(Object o);

    @Override
    String toString();

    boolean accept(IItemVisitor visitor);
}
