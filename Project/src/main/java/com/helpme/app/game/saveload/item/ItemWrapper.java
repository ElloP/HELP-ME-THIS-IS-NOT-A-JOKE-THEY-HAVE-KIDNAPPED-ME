package com.helpme.app.game.saveload.item;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IReadItem;
import com.helpme.app.game.model.item.concrete.ItemFactory;
import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Klas on 2017-04-29.
 */
public class ItemWrapper implements ILoadable<Maybe<IItem>> {
    @XmlElement(name="name")
    private String name = null;

    public ItemWrapper(){}

    public ItemWrapper(Maybe<IReadItem> maybeItem){
        maybeItem.run(item -> name = item.readName());
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public Maybe<IItem> getObject() {
        return name == null ? new Nothing<>() : new Just<>(ItemFactory.createItem(name)); //(TODO) klas. Create item from name
    }
}
