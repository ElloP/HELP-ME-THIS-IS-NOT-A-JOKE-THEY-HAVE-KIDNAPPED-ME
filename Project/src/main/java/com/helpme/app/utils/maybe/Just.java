package com.helpme.app.utils.maybe;

/**
 * Created by kopa on 2017-04-20.
 */
public class Just<T> extends Maybe<T> {
    private final T value;

    public Just(T value) {
        if (value == null) {
            throw new NullPointerException("Can't instantiate Just with null");
        }
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
