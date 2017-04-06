package com.helpme.app.utils;

/**
 * Created by Olle on 2017-03-30.
 */
public class Vector2f {
    private final float RIGHT_ANGLE = (float) Math.PI / 2;

    public float x;
    public float y;

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

    public Vector2f forward(){
        return this.clone();
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
        Vector2f vec0 = (Vector2f) o;
        return vec0.x == x && vec0.y == y;
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
    public Vector2f clone(){
        return new Vector2f(x, y);
    }


    //NOTE(Olle): normalized direction vectors
    public static final Vector2f up = new Vector2f(0, 1);
    public static final Vector2f right = new Vector2f(1, 0);
    public static final Vector2f down = new Vector2f(0, -1);
    public static final Vector2f left = new Vector2f(-1, 0);
    public static final Vector2f zero = new Vector2f(0, 0);

    public static boolean equals(Vector2f vec0, Vector2f vec1) {
        return vec0.x == vec1.x && vec0.y == vec1.y;
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

    public static Vector2f multiply(Matrix2f mat0, Vector2f vec0) {
        Vector2f result = new Vector2f(0, 0);
        result.x = mat0.matrix[0][0] * vec0.x + mat0.matrix[1][0] * vec0.y;
        result.y = mat0.matrix[0][1] * vec0.x + mat0.matrix[1][1] * vec0.y;
        return result;
    }

    public static Vector2f rotateI(Vector2f vec0, float radians) {
        radians = -radians; // NOTE (Jacob): To make the default rotation clockwise
        Matrix2f rotationMatrix = new Matrix2f(
                (float) Math.cos(radians), (float) -Math.sin(radians),
                (float) Math.sin(radians), (float) Math.cos(radians));
        return Vector2f.multiply(rotationMatrix, vec0).toInt();
    }
}