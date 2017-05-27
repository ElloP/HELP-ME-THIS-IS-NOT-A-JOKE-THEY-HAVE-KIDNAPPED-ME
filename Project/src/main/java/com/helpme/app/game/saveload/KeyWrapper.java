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
 * Created by kopa on 2017-05-27.
 */
public class KeyWrapper implements ILoadable<Maybe<IItem>> {
    private String name;



    public KeyWrapper(){}

    public KeyWrapper(IReadItem item){
        name = item == null ? "Nothing" : item.readName();
    }
    public KeyWrapper(String name){
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
        return name == "Nothing" ? new Nothing<>() : new Just<>(ItemFactory.createKey(name)); //(TODO) klas. Create item from name
    }
}
