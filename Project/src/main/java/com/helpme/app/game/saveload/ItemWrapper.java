package com.helpme.app.game.saveload;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IReadItem;
import com.helpme.app.game.model.item.concrete.ItemFactory;
import com.helpme.app.utils.interfaces.ILoadable;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Klas on 2017-04-29.
 */
public class ItemWrapper implements ILoadable<IItem> {

    private String name;



    public ItemWrapper(){}

    public ItemWrapper(IReadItem item){
        name = item == null ? "empty" : item.readName();
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
    public IItem getObject() {
        return ItemFactory.createItem(name); //(TODO) klas. Create item from name
    }
}
