package com.helpme.app.model.item;

import com.helpme.app.utils.interfaces.ICopyable;

/**
 * Created by kopa on 2017-04-08.
 */
public interface IItem extends ICopyable<IItem>, IReadItem {
    <T> T accept(IItemVisitor<T> visitor);
}
