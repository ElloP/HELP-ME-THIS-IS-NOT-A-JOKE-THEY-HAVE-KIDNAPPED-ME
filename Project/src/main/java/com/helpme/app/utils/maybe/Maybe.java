package com.helpme.app.utils.maybe;

/**
 * Created by kopa on 2017-04-20.
 */
public abstract class Maybe<T> {
    public static <T> Maybe<T> create(T value){
        if(value == null){
            return new Nothing();
        }
        return new Just(value);
    }
}
