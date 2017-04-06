package com.helpme.app.utils;

/**
 * Created by Jacob on 2017-04-05.
 */
public class Vector4f {
    private final float RIGHT_ANGLE = (float) Math.PI / 2;

    public float x;
    public float y;
    public float z;
    public float w;

    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4f(float x, float y, Vector2f vec0) {
        this.x = x;
        this.y = y;
        this.z = vec0.x;
        this.w = vec0.y;
    }

    public Vector4f(Vector2f vec0, Vector2f vec1) {
        this.x = vec0.x;
        this.y = vec0.y;
        this.z = vec1.x;
        this.w = vec1.y;
    }

    public Vector4f(Vector4f old) {
        this(old.x, old.y, old.z, old.w);
    }

    public Vector4f toInt() {
        x = (int) x;
        y = (int) y;
        z = (int) z;
        w = (int) w;
        return this;
    }


    @Override
    public int hashCode() {
        return (x + "," + y + "," + z + "," + w).hashCode();
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + "," + w + ")";
    }

    @Override
    public Vector4f clone() {
        return new Vector4f(x, y, z, w);
    }

    public static boolean equals(Vector4f vec0, Vector4f vec1) {
        return vec0.x == vec1.x && vec0.y == vec1.y && vec0.z == vec1.z && vec0.w == vec1.w;
    }
}