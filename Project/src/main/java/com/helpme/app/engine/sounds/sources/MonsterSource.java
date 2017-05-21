package com.helpme.app.engine.sounds.sources;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.body.IReadBody;

/**
 * Created by Jesper on 2017-04-24.
 */
public class MonsterSource extends AbstractMonsterSource {

    public MonsterSource(IReadBody monster, Source source, int walking, int breathing, int hurting, float x, float y, float z) {
        super(monster, source, walking, breathing, hurting, x, y, z);
    }

    public MonsterSource(IReadBody monster, Source source, int walking, int breathing, int hurting, Vector2f position) {
        super(monster, source, walking, breathing, hurting, position);
    }

}
