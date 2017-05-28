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
 * Created by kopa on 2017-05-27.
 */
public class KeyWrapper implements ILoadable<Maybe<IItem>> {
    @XmlElement(name = "name")
    private String name = null;



    public KeyWrapper(){}

    public KeyWrapper(Maybe<IReadItem> maybeKey){
        maybeKey.run(key -> name = key.readName());
    }
    public KeyWrapper(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public Maybe<IItem> getObject() {
        return name == null ? new Nothing<>() : new Just<>(ItemFactory.createKey(name));
    }
}
