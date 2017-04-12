package com.helpme.app.engine.renderer.base;

/**
 * Authored by Olle on 2017-04-12.
 */
public class Cube extends Mesh {

    private static float[] vertices = new float[] { //NOTE(Olle): Testing cube
            -1.0f, -1.0f,  1.0f,
            1.0f, -1.0f,  1.0f,
            1.0f,  1.0f,  1.0f,
            -1.0f,  1.0f,  1.0f,
            // back
            -1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            1.0f,  1.0f, -1.0f,
            -1.0f,  1.0f, -1.0f
    };

    private static int[] indices = new int[] {
            2, 1, 0,
            0, 3, 2,
            // top
            6, 5, 1,
            1, 2, 6,
            // back
            5, 6, 7,
            7, 4, 5,
            // bottom
            3, 0, 4,
            4, 7, 3,
            // left
            1, 5, 4,
            4, 0, 1,
            // right
            6, 2, 3,
            3, 7, 6
    };

    public Cube() {
        super(vertices, indices);
    }
}
