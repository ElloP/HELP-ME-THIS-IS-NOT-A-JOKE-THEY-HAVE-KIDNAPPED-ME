package com.helpme.app.engine.sounds.sources;

import com.helpme.app.game.model.body.IReadBody;

/**
 * Created by Jesper on 2017-04-24.
 */
public class BodySource extends AbstractBodySource {

    public BodySource(IReadBody body, Source source, int walking, int breathing, int hurting, float x, float y, float z) {
        super(body, source, walking, breathing, hurting, x, y, z);
    }

}
