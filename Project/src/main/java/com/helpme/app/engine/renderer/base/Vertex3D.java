package com.helpme.app.engine.renderer.base;

import com.helpme.app.engine.renderer.Vertex;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.mathl.Vector3f;

import java.nio.FloatBuffer;

/**
 * Authored by Olle on 2017-04-19.
 */
public class Vertex3D implements Vertex {
    public final static int VERTEXSIZE = 5; //NOTE(Olle): Number of floats in a vertex

    public Vector3f position; //Note(Olle): Vertex3D position in 3d space
    public Vector2f texCoords; //Note(Olle): 2d position of texture on the object

    public Vertex3D(Vector3f position, Vector2f texCoords) {
        this.position = position;
        this.texCoords = texCoords;
    }

    public Vertex3D(float x, float y, float z, float s, float t) { //Note(Olle): s = x and t = y in texture space
        this.position = new Vector3f(x, y, z);
        this.texCoords = new Vector2f(s, t);
    }

    public FloatBuffer get(FloatBuffer fb) {
        position.get(fb);
        texCoords.get(fb);
        return fb;
    }
}
