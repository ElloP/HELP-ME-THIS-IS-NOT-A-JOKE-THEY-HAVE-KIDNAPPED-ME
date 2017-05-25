package com.helpme.app.engine.sounds.sources;

import com.helpme.app.engine.sounds.audio.AudioHandler;
import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.utils.Vector2f;

/**
 * Created by Jesper on 2017-04-25.
 */
public class PlayerSource extends AbstractBodySource {
    public PlayerSource(IReadBody body, Source source, int walking, int breathing, int hurting, float x, float y, float z) {
        super(body, source, walking, breathing, hurting, x, y, z);
    }

    public PlayerSource(IReadBody body, Source source, int walking, int breathing, int hurting, Vector2f position) {
        super(body, source, walking, breathing, hurting, position);
    }

    @Override
    public void setPosition(float x, float y, float z) {
        AudioHandler.setListenerPos(x, y, z);
        source.setPosition(x, y, z);
    }
}
