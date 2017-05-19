package com.helpme.app.world.sounds;

import com.helpme.app.world.body.IReadBody;

/**
 * Created by Jesper on 2017-04-25.
 */
public class PlayerSource extends AbstractMonsterSource {
    public PlayerSource(IReadBody monster, Source source, int walking, int breathing, int hurting, float x, float y, float z) {
        super(monster, source, walking, breathing, hurting, x, y, z);
    }

    @Override
    public void setPosition(float x, float y, float z) {
        AudioHandler.setListenerPos(x, y, z);
        source.setPosition(x, y, z);
    }
}
