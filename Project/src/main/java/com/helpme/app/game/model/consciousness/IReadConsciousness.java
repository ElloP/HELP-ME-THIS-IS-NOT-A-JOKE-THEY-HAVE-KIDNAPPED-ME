package com.helpme.app.game.model.consciousness;

import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.utils.interfaces.IObservable;

/**
 * Created by kopa on 2017-05-25.
 */
public interface IReadConsciousness extends IObservable {
    IReadBody readBody();
}
