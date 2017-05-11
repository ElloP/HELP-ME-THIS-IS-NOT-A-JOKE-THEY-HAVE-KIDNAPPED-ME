package com.helpme.app.world.item;

import com.helpme.app.world.item.visitor.IItemVisitor;
import com.helpme.app.utils.interfaces.ICloneable;

/**
 * Created by kopa on 2017-04-08.
 */
public interface IItem extends ICloneable<IItem>, IReadItem {
    <T> T accept(IItemVisitor<T> visitor);
}
