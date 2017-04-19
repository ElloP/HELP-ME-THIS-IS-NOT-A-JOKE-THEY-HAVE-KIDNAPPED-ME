package com.helpme.app.engine.renderer.base;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.mathl.Vector3f;

import java.nio.FloatBuffer;

/**
 * Created by Olle on 2017-04-19.
 */
public class Vertex {
    //TODO(Olle): Convert every place you use float[] to render to vertex
    public final static int VERTEXSIZE = 5; //NOTE(Olle): Number of floats in a vertex

    public Vector3f position; //Note(Olle): Vertex position in 3d space
    public Vector2f texCoords; //Note(Olle): 2d position of texture on the object

    public Vertex(Vector3f position, Vector2f texCoords) {
        this.position = position;
        this.texCoords = texCoords;
    }

    public FloatBuffer get(FloatBuffer fb) {
        position.get(fb);
        texCoords.get(fb);
        return fb;
    }
}
