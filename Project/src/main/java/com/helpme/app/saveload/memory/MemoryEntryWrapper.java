package com.helpme.app.saveload.memory;

import com.helpme.app.saveload.ILoadable;

import javax.xml.bind.annotation.XmlElement;
import java.util.AbstractMap;
import java.util.Map;

/**
 * Created by kopa on 2017-05-20.
 */
public class MemoryEntryWrapper implements ILoadable<Map.Entry<String,Integer>>{
    String name;
    int value;

    public MemoryEntryWrapper(){

    }

    public MemoryEntryWrapper(String name, int value){
        this.name = name;
        this.value = value;
    }

    @XmlElement(name = "name")
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    @XmlElement(name = "value")
    public int getValue() { return value;}
    public void setValue(int value){this.value = value;}

    @Override
    public Map.Entry<String, Integer> getObject() {
        return new AbstractMap.SimpleEntry<>(name, value);
    }
}
