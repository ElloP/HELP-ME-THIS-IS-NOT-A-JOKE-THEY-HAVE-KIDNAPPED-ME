package com.helpme.app.utils;

import com.helpme.app.utils.interfaces.ICloneable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kopa on 2017-04-11.
 */
public interface Clone {

    static <T extends ICloneable<T>> List<T> toList(T[] array) {
        if (array == null) {
            return null;
        }
        List<T> list = new ArrayList<>();
        for (T a : array) {
            if (a == null) {
                continue;
            }
            list.add(a.clone());
        }
        return list;
    }

    static <T extends ICloneable<T>> T[] array(T[] array) {
        if (array == null) {
            return null;
        }
        array = array.clone();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                continue;
            }
            array[i] = array[i].clone();
        }
        return array;
    }
}
