package com.helpme.app.utils.interfaces;

import java.util.Observer;

/**
 * Created by kopa on 2017-04-14.
 */
public interface IObservable {
    void addObserver(Observer o);
    void deleteObserver(Observer o);
}
