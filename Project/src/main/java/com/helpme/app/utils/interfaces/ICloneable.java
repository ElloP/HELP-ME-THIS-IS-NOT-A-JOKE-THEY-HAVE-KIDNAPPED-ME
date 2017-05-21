package com.helpme.app.utils.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 2017-04-10.
 */

public interface ICloneable<T> extends Cloneable {
    T clone();
}
