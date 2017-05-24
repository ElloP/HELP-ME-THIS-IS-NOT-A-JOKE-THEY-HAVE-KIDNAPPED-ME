package com.helpme.app.utils;

import com.helpme.app.utils.interfaces.ICopyable;

import java.nio.FloatBuffer;

/**
 * Authored by Olle on 2017-03-30.
 */
public class Vector2f implements ICopyable {
    public static final Vector2f NORTH = new Vector2f(0, 1);
    public static final Vector2f EAST = new Vector2f(1, 0);
    public static final Vector2f SOUTH = new Vector2f(0, -1);
    public static final Vector2f WEST = new Vector2f(-1, 0);
    public static final Vector2f ZERO = new Vector2f(0, 0);

    public float x;
    public float y;

    private static final float RIGHT_ANGLE = (float) Math.PI / 2;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(Vector2f old) {
        this(old.x, old.y);
    }

    public Vector2f toInt(){
        x = (int)x;
        y = (int)y;
        return this;
    }

    public FloatBuffer get(FloatBuffer fb) {
        fb.put(this.x);
        fb.put(this.y);
        return fb;
    }

    public Vector2f forward(){
        return this.copy();
    }

    public Vector2f right(){
        return Vector2f.rotateI(this, RIGHT_ANGLE);
    }

    public Vector2f backward(){
        return Vector2f.rotateI(this, RIGHT_ANGLE * 2);
    }

    public Vector2f left(){
        return Vector2f.rotateI(this, RIGHT_ANGLE * 3);
    }

    public Vector2f rotateRightAngle(int times) {
        return Vector2f.rotateI(this, RIGHT_ANGLE * times);
    }

    @Override
    public boolean equals(Object o){
        if(o == null || !(o instanceof Vector2f)) {
            return false;
        }

        Vector2f vec0 = (Vector2f) o;
        return Math.abs(vec0.x - x) < .0000001 && Math.abs(vec0.y - y) < .0000001;
    }

    @Override
    public int hashCode(){
        return (x + "," + y).hashCode();
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    @Override
    public Vector2f copy(){
        return new Vector2f(x, y);
    }

    public static boolean equals(Vector2f vec0, Vector2f vec1) {
        return vec0.equals(vec1);
    }

    public static Vector2f add(Vector2f vec0, Vector2f vec1) {
        return new Vector2f(vec0.x + vec1.x, vec0.y + vec1.y);
    }

    public static Vector2f add(Vector2f vec0, int scalar) {
        return new Vector2f(vec0.x + scalar, vec0.y + scalar);
    }

    public static Vector2f subtract(Vector2f vec0, Vector2f vec1) {
        return new Vector2f(vec0.x - vec1.x, vec0.y - vec1.y);
    }

    public static Vector2f subtract(Vector2f vec0, int scalar) {
        return new Vector2f(vec0.x - scalar, vec0.y - scalar);
    }

    //NOTE(Olle): vector multiplication using the hadamard product
    public static Vector2f multiply(Vector2f vec0, Vector2f vec1) {
        return new Vector2f(vec0.x * vec1.x, vec0.y * vec1.y);
    }

    public static Vector2f multiply(Vector2f vec0, int scalar) {
        return new Vector2f(vec0.x * scalar, vec0.y * scalar);
    }

    //Note(Olle): simulates multiplication with a 2x2 matrix
    public static Vector2f multiply(float[][] mat0, Vector2f vec0) {
        Vector2f result = new Vector2f(0, 0);
        result.x = mat0[0][0] * vec0.x + mat0[0][1] * vec0.y;
        result.y = mat0[1][0] * vec0.x + mat0[1][1] * vec0.y;
        return result;
    }

    public static Vector2f rotateI(Vector2f vec0, float radians) {
        radians = -radians; // NOTE (Jacob): To make the default rotation clockwise
        float[][] rotationMatrix = new float[][] {
                {
                    (float) Math.cos(radians), (float) -Math.sin(radians)
                },
                {
                    (float) Math.sin(radians), (float) Math.cos(radians)
                }
        };
        return Vector2f.multiply(rotationMatrix, vec0).toInt();
    }

    public static Vector2f[] getNeighbours(Vector2f vec0){
        Vector2f[] vectors = {add(vec0, NORTH), add(vec0, EAST), add(vec0, SOUTH), add(vec0, WEST)};
        return vectors;
    }
}