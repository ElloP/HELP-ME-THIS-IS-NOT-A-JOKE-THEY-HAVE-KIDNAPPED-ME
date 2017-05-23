package com.helpme.app.utils;

import com.helpme.app.utils.interfaces.ICopyable;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-04-11.
 */
public abstract class Copy {

    private Copy(){

    }

    public static <T extends ICopyable<T>> List<T> toList(T[] array) {
        if (array == null) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>();
        for (T a : array) {
            if (a == null) {
                list.add(null);
                continue;
            }
            list.add(a.copy());
        }
        return list;
    }

    public static <T extends ICopyable<T>> List<Maybe<T>> toMaybeList(T[] array) {
        if (array == null) {
            return new ArrayList<>();
        }
        List<Maybe<T>> list = new ArrayList<>();
        for (T a : array) {
            if (a == null) {
                list.add(new Nothing<>());
                continue;
            }
            list.add(Maybe.wrap(a.copy()));
        }
        return list;
    }

    public static <T extends ICopyable<T>> T[] array(T[] array) {
        if (array == null) {
            return array;
        }

        array = array.clone();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                continue;
            }
            array[i] = array[i].copy();
        }
        return array;
    }

    public static <T extends ICopyable<T>> List<T> list(List<T> list){
        List<T> copy = new ArrayList<>();
        for(T element : list){
            copy.add(element.copy());
        }
        return copy;
    }

    public static <T extends ICopyable<T>> List<Maybe<T>> maybeList(List<Maybe<T>> list){
        List<Maybe<T>> copy = new ArrayList<>();
        for(Maybe<T> element : list){
            element.run(e -> copy.add(Maybe.wrap(e.copy())));
        }
        return copy;
    }

    public static <K extends ICopyable<K>, V extends ICopyable<V>> Map<K, V> map(Map<K, V> map){
        Map<K, V> copy = new HashMap<>();
        for(Map.Entry<K, V> entry : map.entrySet()){
            copy.put(entry.getKey(), entry.getValue());
        }
        return copy;
    }
}
