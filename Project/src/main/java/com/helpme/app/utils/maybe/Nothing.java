package com.helpme.app.utils.maybe;

/**
 * Created by kopa on 2017-04-20.
 */
public class Nothing<T> extends Maybe<T> {
    public Nothing() {
        super();
    }

    @Override
    public T getValue() {
        throw new NullPointerException("Trying to access value from Nothing");
    }

    @Override
    public String toString(){
        return "Nothing";
    }

    @Override
    public boolean equals(Object o){
        return o instanceof Nothing;
    }
}
