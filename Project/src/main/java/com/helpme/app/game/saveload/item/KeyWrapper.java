package com.helpme.app.game.saveload.item;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IReadItem;
import com.helpme.app.game.model.item.concrete.ItemFactory;
import com.helpme.app.game.model.item.concrete.Key;
import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by kopa on 2017-05-27.
 */
public class KeyWrapper implements ILoadable<IItem> {
    @XmlElement(name = "name")
    private String name;

    public KeyWrapper(){}

    public KeyWrapper(Key key){
        name = key.readName();
    }

    public KeyWrapper(String name){
        this.name = name;
    }

    @Override
    public IItem getObject() {
        return ItemFactory.createKey(name);
    }
}
