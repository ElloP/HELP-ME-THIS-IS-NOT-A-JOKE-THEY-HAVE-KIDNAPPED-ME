package com.helpme.app.utils.maybe;

/**
 * Created by kopa on 2017-04-20.
 */
public class Just<T> extends Maybe<T> {
    public Just(T value) {
        super(value);
    }

    @Override
    public String toString(){
        return "Just(" + getValue() + ")";
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o.equals(getValue());
    }

    @Override
    public int hashCode(){
        return getValue().hashCode();
    }
}
