package com.helpme.app.Utils;

/**
 * Created by Olle on 2017-03-30.
 */
public class Vector2 {
    public int x;
    public int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 old) {
        this(old.x,old.y);
    }


    //NOTE(Olle): normalized direction vectors
    public static final Vector2 NORTH = new Vector2(0, 1);
    public static final Vector2 EAST = new Vector2(1, 0);
    public static final Vector2 SOUTH = new Vector2(0, -1);
    public static final Vector2 WEST = new Vector2(-1, 0);

    public static boolean equals(Vector2 vec0, Vector2 vec1){
        return vec0.x == vec1.x && vec0.y == vec1.y;
    }

    public static Vector2 add(Vector2 vec0, Vector2 vec1) {
        return new Vector2(vec0.x + vec1.x, vec0.y + vec1.y);
    }

    public static Vector2 add(Vector2 vec0, int scalar) {
        return new Vector2(vec0.x + scalar, vec0.y + scalar);
    }

    public static Vector2 subtract(Vector2 vec0, Vector2 vec1) {
        return new Vector2(vec0.x - vec1.x, vec0.y - vec1.y);
    }

    public static Vector2 subtract(Vector2 vec0, int scalar) {
        return new Vector2(vec0.x - scalar, vec0.y - scalar);
    }

    //NOTE(Olle): vector multiplication using the hadamard product
    public static Vector2 multiply(Vector2 vec0, Vector2 vec1) {
        return new Vector2(vec0.x * vec1.x, vec0.y * vec1.y);
    }

    public static Vector2 multiply(Vector2 vec0, int scalar) {
        return new Vector2(vec0.x * scalar, vec0.y * scalar);
    }
}