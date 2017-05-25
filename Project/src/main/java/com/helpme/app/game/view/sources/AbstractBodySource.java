package com.helpme.app.game.view.sources;

import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.utils.mathl.Vector2f;


/**
 * Created by Jesper on 2017-04-24.
 */
public abstract class AbstractBodySource {
    protected IReadBody body;
    protected Source source;
    protected int walking;
    protected int breathing;
    protected int hurting;
    protected float x;
    protected float y;
    protected float z;

    public AbstractBodySource(IReadBody body, Source source, int walking, int breathing, int hurting, float x, float y, float z) {
        this.body = body;
        this.source = source;
        this.walking = walking;
        this.breathing = breathing;
        this.hurting = hurting;
        this.x = x;
        this.y = y;
        this.z = z;
        source.setPosition(this.x, this.y, this.z);
    }
    public AbstractBodySource(IReadBody body, Source source, int walking, int breathing, int hurting, Vector2f position) {
        this(body, source, walking, breathing, hurting, position.x, position.y, 0);
    }

    public boolean equals(IReadBody other) {
        return body.equals(other);
    }
    public void setPosition(float x, float y, float z) {
        source.setPosition(x, y ,z);
    }

    public void playWalking() {
        source.setPosition(body.readPosition().x, body.readPosition().y, 0);
        source.play(walking);
    }

    public void playBreathing() {
        source.play(breathing);
    }

    public void playHurting() {
        source.play(hurting);
    }
}
