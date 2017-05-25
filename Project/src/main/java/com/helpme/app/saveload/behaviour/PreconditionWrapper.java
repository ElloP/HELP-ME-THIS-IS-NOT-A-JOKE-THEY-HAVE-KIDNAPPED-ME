package com.helpme.app.saveload.behaviour;

import com.helpme.app.game.model.consciousness.behaviour.Comparison;
import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.utils.tuple.Tuple2;

import javax.xml.bind.annotation.XmlElement;
import java.util.AbstractMap;
import java.util.Map;

/**
 * Created by kopa on 2017-05-19.
 */
public class PreconditionWrapper implements ILoadable<Map.Entry<String, Tuple2<Integer, Comparison>>> {
    String name;
    int value;
    Comparison comparison;


    public PreconditionWrapper() {

    }

    public PreconditionWrapper(String name, int value, Comparison comparison) {
        this.name = name;
        this.value = value;
        this.comparison = comparison;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "value")
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @XmlElement(name = "comparison")
    public Comparison getComparison() {
        return comparison;
    }
    public void setComparison(Comparison comparison){
        this.comparison = comparison;
    }


    @Override
    public Map.Entry<String, Tuple2<Integer, Comparison>> getObject() {
        return new AbstractMap.SimpleEntry<>(name, new Tuple2<>(value, comparison));
    }
}
