package com.helpme.app.utils.mathl;

import com.helpme.app.utils.Vector2f;

import java.nio.FloatBuffer;

/**
 * Authored by Olle on 2017-04-11.
 */
// ----------- Wrapper for Vector4f -----------
public class Vector3f {
    // ----------- Variables -----------
    protected org.joml.Vector3f vector;

    // ----------- Standard vectors -----------

    public static final Vector3f UP = new Vector3f(0.0f, 1.0f, 0.0f);
    public static final Vector3f DOWN = new Vector3f(0.0f, -1.0f, 0.0f);
    public static final Vector3f LEFT = new Vector3f(-1.0f, 0.0f, 0.0f);
    public static final Vector3f RIGHT = new Vector3f(1.0f, 0.0f, 0.0f);
    public static final Vector3f FORWARD = new Vector3f(0.0f, 0.0f, -1.0f); // z is negative due to the right handed coordinate system of OpenGL
    public static final Vector3f BACK = new Vector3f(0.0f, 0.0f, 1.0f);

    // ----------- Constructors -----------

    public Vector3f() {
        this.vector = new org.joml.Vector3f();
    }

    public Vector3f(float x) {
        this.vector = new org.joml.Vector3f(x);
    }

    public Vector3f(FloatBuffer fb) {
        this.vector = new org.joml.Vector3f(fb);
    }

    public Vector3f(float x, float y, float z) {
        this.vector = new org.joml.Vector3f(x, y, z);
    }

    private Vector3f(org.joml.Vector3f vec) {
        this.vector = new org.joml.Vector3f(vec);
    }

    public Vector3f(Vector3f vec) {
        this(vec.vector);
    }

    // ----------- Getters/Setters -----------

    public Vector3f toRadians() {
        return new Vector3f((float) Math.toRadians(x()),
                            (float) Math.toRadians(y()),
                            (float) Math.toRadians(z())
        );
    }

    public float x() {
        return this.vector.x();
    }

    public float y() {
        return this.vector.y();
    }

    public float z() {
        return this.vector.z();
    }

    public void set(float x) {
        this.vector.set(x);
    }

    public void set(float x, float y, float z) {
        this.vector.set(x, y, z);
    }

    // ----------- Operations/functions -----------

    public Vector3f add(float x, float y, float z) {
        this.vector.add(x, y, z);
        return this;
    }

    public Vector3f add(Vector3f vec) {
        this.vector.add(vec.vector);
        return this;
    }

    public float angle(Vector3f vec) {
        return this.vector.angle(vec.vector);
    }

    public Vector3f cross(float x, float y, float z) {
        this.vector.cross(x, y, z);
        return this;
    }

    public Vector3f cross(Vector3f vec) {
        this.vector.cross(vec.vector);
        return this;
    }

    public Vector3f cross(Vector3f vec, Vector3f dest) { //sets dest to the cross product of this and vec
        this.vector.cross(vec.vector, dest.vector);
        return this;
    }

    public float distance(float x, float y, float z) {
        return this.vector.distance(x, y, z);
    }

    public float distance(Vector3f vec) {
        return this.vector.distance(vec.vector);
    }

    public Vector3f divide(float scalar) {
        this.vector.div(scalar);
        return this;
    }

    public Vector3f divide(Vector3f vec) {
        this.vector.div(vec.vector);
        return this;
    }

    public float dot(float x, float y, float z) {
        return this.vector.dot(x, y, z);
    }

    public float dot(Vector3f vec) {
        return this.vector.dot(vec.vector);
    }

    public boolean equals(Vector3f vec) {
        return this.vector.equals(vec.vector);
    }

    public FloatBuffer get(FloatBuffer fb) { //gets the vector values and writes it to the floatbuffer
        this.vector.get(fb);
        return fb;
    }

    public float length() {
        return this.vector.length();
    }

    public Vector3f lerp(Vector3f other, float t) {
        this.vector.lerp(other.vector, t);
        return this;
    }

    public Vector3f logVector() {
        System.out.println(vector);
        return this;
    }

    public Vector3f multiply(float scalar) {
        this.vector.mul(scalar);
        return this;
    }

    public Vector3f multiply(float x, float y, float z) {
        this.vector.mul(x, y, z);
        return this;
    }

    public Vector3f multiply(Vector3f vec) {
        this.vector.mul(vec.vector); //component wise multiplication (hadamard product)
        return this;
    }

    public Vector3f negate() {
        this.vector.negate();
        return this;
    }

    public Vector3f normalize() {
        this.vector.normalize();
        return this;
    }

    public String toString() {
        return this.vector.toString();
    }

    public Vector3f subtract(Vector3f vec) {
        this.vector.sub(vec.vector);
        return this;
    }

    public Vector3f subtract(float x, float y, float z) {
        this.vector.sub(x, y, z);
        return this;
    }

    public Vector3f subtract(Vector3f v, Vector3f dest) {
        this.vector.sub(v.vector, dest.vector);
        return dest;
    }

    public Vector3f subtract(float x, float y, float z, Vector3f dest) {
        this.vector.sub(x, y, z, dest.vector);
        return dest;
    }

    public Vector3f zero() {
        this.vector.zero();
        return this;
    }
}
