package com.helpme.app.world.sounds.Source;

import com.helpme.app.world.character.IReadBody;

/**
 * Created by Jesper on 2017-04-24.
 */
public abstract class AbstractMonsterSource {
    protected IReadBody monster;
    protected Source source;
    protected int walking;
    protected int breathing;
    protected int hurting;
    protected float x;
    protected float y;
    protected float z;

    public AbstractMonsterSource(IReadBody monster, Source source, int walking, int breathing, int hurting, float x, float y, float z) {
        this.monster = monster;
        this.source = source;
        this.walking = walking;
        this.breathing = breathing;
        this.hurting = hurting;
        this.x = x;
        this.y = y;
        this.z = z;
        source.setPosition(x, y, z);
    }

    public boolean equals(IReadBody otherMonster) {
        return monster.equals(otherMonster);
    }
    public void setPosition(float x, float y, float z) {
        source.setPosition(x, y ,z);
    }

    public void playWalking() {
        source.setPosition(monster.readPosition().x, monster.readPosition().y, 0);
        source.play(walking);
    }

    public void playBreathing() {
        source.play(breathing);
    }

    public void playHurting() {
        source.play(hurting);
    }
}