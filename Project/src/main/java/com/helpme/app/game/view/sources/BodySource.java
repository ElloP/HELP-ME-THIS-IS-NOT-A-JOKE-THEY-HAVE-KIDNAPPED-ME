package com.helpme.app.game.view.sources;

import com.helpme.app.game.model.body.IReadBody;

import java.util.HashMap;

/**
 * Created by Jesper on 2017-04-24.
 */
public class BodySource extends AbstractBodySource {

    public BodySource(IReadBody body, float x, float y, float z, HashMap sources, Source defaultSource) {
        super(body, x, y, z, sources, defaultSource);
    }
}
