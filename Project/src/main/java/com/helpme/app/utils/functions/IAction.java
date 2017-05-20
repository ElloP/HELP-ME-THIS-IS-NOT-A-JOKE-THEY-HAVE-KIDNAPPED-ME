package com.helpme.app.utils.functions;

/**
 * Created by kopa on 2017-04-12.
 */
public interface IAction<T> {
    void apply(T arg);

    static <T> IAction<T> empty(){
        return t -> {};
    }
}
