package com.helpme.app.utils.maybe;

import com.helpme.app.utils.maybe.Maybe;

/**
 * Created by kopa on 2017-05-10.
 */
public final class Container <T> {
    private Maybe<T> item;

    public Container(T item){
        update(item);
    }

    public void update(T item){
        this.item = Maybe.wrap(item);
    }

    public void update(Maybe<T> item){
        this.item = item;
    }

    public Maybe<T> get(){
        return item;
    }
}
