package com.helpme.app.utils.maybe;

/**
 * Created by kopa on 2017-04-20.
 */
public class Just<T> extends Maybe<T> {
    public Just(T value) {
        super(value);
    }

    @Override
    public boolean equals(Object o) {
        return o.equals(getValue());
    }
}
