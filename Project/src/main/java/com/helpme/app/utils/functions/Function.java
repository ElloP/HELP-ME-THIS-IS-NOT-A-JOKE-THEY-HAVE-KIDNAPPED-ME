package com.helpme.app.utils.functions;

/**
 * Created by Jacob on 2017-04-12.
 */
public interface Function<T, R> {
    R apply(T arg);
}
