package com.helpme.app.engine.renderer.base;

import com.helpme.app.engine.renderer.Vertex;
import com.helpme.app.utils.mathl.Vector2f;

import java.nio.FloatBuffer;

/**
 * Created by Jesper on 2017-05-14.
 */

public class Vertex2D implements Vertex {
    public static final int VERTEXSIZE = 4;

    private Vector2f position;
    private Vector2f texCoords;

    public Vertex2D(float x, float y, float s, float t) {
        this.position = new Vector2f(x, y);
        this.texCoords = new Vector2f(s, t);
    }

    public FloatBuffer get(FloatBuffer fb) {
        position.get(fb);
        texCoords.get(fb);
        return fb;
    }
}
