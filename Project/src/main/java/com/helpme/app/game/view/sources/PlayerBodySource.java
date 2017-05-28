package com.helpme.app.game.view.sources;

import com.helpme.app.engine.audio.AudioHandler;
import com.helpme.app.game.model.body.IReadBody;

import java.util.HashMap;

/**
 * Created by Jesper on 2017-04-25.
 */
public class PlayerBodySource extends AbstractBodySource {
    public PlayerBodySource(IReadBody body, float x, float y, float z, HashMap sources, Source defaultSource) {
        super(body, x, y, z, sources, defaultSource);
    }

    @Override
    public void setPosition(float x, float y, float z, Source source) {
        AudioHandler.setListenerPos(x, y, z);
        source.setPosition(x, y, z);
    }
}
