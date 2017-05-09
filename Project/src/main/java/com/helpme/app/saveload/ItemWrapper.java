package com.helpme.app.saveload;

import com.helpme.app.world.item.IReadItem;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Klas on 2017-04-29.
 */
public class ItemWrapper {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public ItemWrapper(){}

    public ItemWrapper(IReadItem item){
        name = item == null? "Empty" : item.readName();
    }
    public ItemWrapper(String name){
        this.name = name;
    }

    @XmlElement(name="name")
    public String getName(){
        return name;
    }
}