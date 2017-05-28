package com.helpme.app.game.view.sources;

import com.helpme.app.game.model.body.IReadBody;

import java.util.HashMap;


/**
 * Created by Jesper on 2017-04-24.
 */
public abstract class AbstractBodySource {
    public static final String WALKING = "walking";
    public static final String BREATHING = "breathing";
    public static final String HURTING = "hurting";
    public static final String BLOCKED = "blocked";
    public static final String UNLOCK = "unlock";
    protected IReadBody body;

    private HashMap<String, Source> sourceMap;
    private Source defaultSource;

    public AbstractBodySource(IReadBody body, float x, float y, float z, HashMap<String, Source> sourceMap, Source defaultSource) {
        this.body = body;
        this.sourceMap = sourceMap;
        this.defaultSource = defaultSource;
    }

    public boolean equals(IReadBody other) {
        return body.equals(other);
    }
    public void setPosition(float x, float y, float z, Source source) {
        source.setPosition(x, y ,z);
    }


    public void play(String stringSource) {
        Source source = sourceMap.getOrDefault(stringSource, defaultSource);
        source.setPosition(body.readPosition().x, body.readPosition().y, 0);
        source.play();
    }
}
