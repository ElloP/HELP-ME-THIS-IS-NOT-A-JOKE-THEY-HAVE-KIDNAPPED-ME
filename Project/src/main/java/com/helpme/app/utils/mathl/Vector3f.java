package com.helpme.app.utils.mathl;

import java.nio.FloatBuffer;

/**
 * Authored by Olle on 2017-04-11.
 */
// ----------- Wrapper for Vector4f -----------
public class Vector3f {
    // ----------- Variables -----------
    protected org.joml.Vector3f vector;

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

    public void add(float x, float y, float z) {
        this.vector.add(x, y, z);
    }

    public void add(Vector3f vec) {
        this.vector.add(vec.vector);
    }

    public float angle(Vector3f vec) {
        return this.vector.angle(vec.vector);
    }

    public void cross(float x, float y, float z) {
        this.vector.cross(x, y, z);
    }

    public void cross(Vector3f vec) {
        this.vector.cross(vec.vector);
    }

    public void cross(Vector3f vec, Vector3f dest) { //sets dest to the cross product of this and vec
        this.vector.cross(vec.vector, dest.vector);
    }

    public float distance(float x, float y, float z) {
        return this.vector.distance(x, y, z);
    }

    public float distance(Vector3f vec) {
        return this.vector.distance(vec.vector);
    }

    public void divide(float scalar) {
        this.vector.div(scalar);
    }

    public void divide(Vector3f vec) {
        this.vector.div(vec.vector);
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

    public void get(FloatBuffer fb) { //gets the vector values and writes it to the floatbuffer
        this.vector.get(fb);
    }

    public float length() {
        return this.vector.length();
    }

    public void lerp(Vector3f other, float t) {
        this.vector.lerp(other.vector, t);
    }

    public void multiply(float scalar) {
        this.vector.mul(scalar);
    }

    public void multiply(float x, float y, float z) {
        this.vector.mul(x, y, z);
    }

    public void multiply(Vector3f vec) {
        this.vector.mul(vec.vector); //component wise multiplication (hadamard product)
    }

    public void negate() {
        this.vector.negate();
    }

    public void normalize() {
        this.vector.normalize();
    }

    public String toString() {
        return this.vector.toString();
    }

    public void subtract(float x, float y, float z) {
        this.vector.sub(x, y, z);
    }

    public void subtract(Vector3f vec) {
        this.vector.sub(vec.vector);
    }

    public void zero() {
        this.vector.zero();
    }
}
