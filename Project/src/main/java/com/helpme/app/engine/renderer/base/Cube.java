package com.helpme.app.engine.renderer.base;

/**
 * Authored by Olle on 2017-04-12.
 */
public class Cube extends Mesh {

    private static Vertex[] vertices = new Vertex[] { //NOTE(Olle): Testing cube

            new Vertex(-1.0f, -1.0f,  1.0f, 0.0f, 0.0f),
            new Vertex(1.0f, -1.0f,  1.0f, 1.0f, 0.0f),
            new Vertex(1.0f,  1.0f,  1.0f, 1.0f, 1.0f),
            new Vertex(-1.0f,  1.0f,  1.0f, 0.0f,1.0f),

            new Vertex(-1.0f, -1.0f,  -1.0f, 0.0f, 0.0f),
            new Vertex(1.0f, -1.0f,  -1.0f, 1.0f, 0.0f),
            new Vertex(1.0f,  1.0f,  -1.0f, 1.0f, 1.0f),
            new Vertex(-1.0f,  1.0f,  -1.0f, 0.0f,1.0f),

            new Vertex(1.0f,  1.0f,  1.0f, 0.0f, 1.0f),
            new Vertex(1.0f,  1.0f,  -1.0f, 1.0f, 1.0f),
            new Vertex(1.0f, -1.0f,  1.0f, 0.0f, 0.0f),
            new Vertex(1.0f, -1.0f,  -1.0f, 1.0f, 0.0f),

            new Vertex(-1.0f,  1.0f,  1.0f, 0.0f, 1.0f),
            new Vertex(-1.0f,  1.0f,  -1.0f, 1.0f, 1.0f),
            new Vertex(-1.0f, -1.0f,  1.0f, 0.0f, 0.0f),
            new Vertex(-1.0f, -1.0f,  -1.0f, 1.0f, 0.0f),

            new Vertex(-1.0f, -1.0f,  1.0f, 0.0f, 0.0f),
            new Vertex(-1.0f, -1.0f,  -1.0f, 0.0f, 1.0f),
            new Vertex(1.0f, -1.0f,  1.0f, 1.0f, 0.0f),
            new Vertex(1.0f, -1.0f,  -1.0f, 1.0f, 1.0f),

            new Vertex(-1.0f, 1.0f,  1.0f, 0.0f, 0.0f),
            new Vertex(-1.0f, 1.0f,  -1.0f, 0.0f, 1.0f),
            new Vertex(1.0f, 1.0f,  1.0f, 1.0f, 0.0f),
            new Vertex(1.0f, 1.0f,  -1.0f, 1.0f, 1.0f),


    };

    private static int[] indices = new int[] {
            2, 1, 0,
            0, 3, 2,

            6, 7, 4,
            4, 5, 6,

            8, 9, 11,
            11, 10, 8,

            12, 14, 15,
            15, 13, 12,

            16, 18, 19,
            19, 17, 16,

            20, 21, 23,
            23, 22, 20

    };

    public Cube() {
        super(vertices, indices);
    }
}
