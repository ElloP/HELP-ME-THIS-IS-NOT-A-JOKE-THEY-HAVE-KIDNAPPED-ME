package com.helpme.app.engine.screen;

import com.helpme.app.utils.interfaces.IObservable;

/**
 * Created by kopa on 2017-04-17.
 */
public interface IScreen extends IObservable {
    void update();
    void render();
    void input();
}
