package com.helpme.app.world.sounds.Source;

import com.helpme.app.world.character.IReadBody;

/**
 * Created by Jesper on 2017-04-24.
 */
public class MonsterSource extends AbstractMonsterSource{

    public MonsterSource(IReadBody monster, Source source, int walking, int breathing, int hurting, float x, float y, float z) {
        super(monster, source, walking, breathing, hurting, x, y, z);
    }

}