package com.helpme.app.game.saveload;

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

    private String name = null;



    public ItemWrapper(){}

    public ItemWrapper(Maybe<IReadItem> maybeItem){
        maybeItem.run(item -> name = item.readName());
    }
    public ItemWrapper(String name){
        this.name = name;
    }

    @XmlElement(name="name")
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public Maybe<IItem> getObject() {
        //If the item has a name, it creates one from the ItemFactory
        return name == null ? new Nothing<>() : new Just<>(ItemFactory.createItem(name));
    }
}
