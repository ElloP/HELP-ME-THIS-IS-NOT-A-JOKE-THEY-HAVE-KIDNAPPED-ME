package com.helpme.app.utils.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 2017-04-10.
 */

public interface ICloneable<T> {
    T clone();

    static <T extends ICloneable<T>> List<T> cloneToList(T[] array) {
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

    static <T extends ICloneable<T>> T[] cloneArray(T[] array) {
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
