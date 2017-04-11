package com.helpme.app.utils.mathl;

import java.nio.FloatBuffer;

/**
 * Authored by Olle on 2017-04-11.
 */

// ----------- Wrapper for matrix4f -----------
public class Matrix4f {
    // ----------- Variables -----------
    protected org.joml.Matrix4f matrix;

    // ----------- Constructors -----------
    public Matrix4f() {
        matrix = new org.joml.Matrix4f();
    }

    public Matrix4f(FloatBuffer fb) {
        matrix = new org.joml.Matrix4f(fb);
    }

    public Matrix4f(float m00, float m01, float m02, float m03,
                    float m10, float m11, float m12, float m13,
                    float m20, float m21, float m22, float m23,
                    float m30, float m31, float m32, float m33) {

        matrix = new org.joml.Matrix4f(m00, m01, m02, m03,
                                       m10, m11, m12, m13,
                                       m20, m21, m22, m23,
                                       m30, m31, m32, m33);
    }

    public Matrix4f(Matrix4f mat) { //uses the private constructor below
        this(mat.matrix);
    }

    private Matrix4f(org.joml.Matrix4f mat) { //private constructor for copying (and setting) matrices
        this.matrix = new org.joml.Matrix4f(mat);
    }

    public Matrix4f(com.helpme.app.utils.Vector4f col0, com.helpme.app.utils.Vector4f col1, com.helpme.app.utils.Vector4f col2, com.helpme.app.utils.Vector4f col3) {
        //TODO(Olle): finish this constructor (if needed)
    }

    // ----------- Operations/functions -----------

    public void add(Matrix4f other) {
        this.matrix.add(other.matrix);
    }

    public float determinant() {
        if(this.matrix.isAffine()) {
            return this.matrix.determinantAffine();
        } else {
            return this.matrix.determinant();
        }
    }

    public boolean equals(Matrix4f other) {
        return this.matrix.equals(other.matrix);
    }

    public void get(FloatBuffer fb) {
        this.matrix.get(fb);
    }

    public void identity() {
        this.matrix.identity();
    }

    public void invert() {
        if(this.matrix.isAffine()) {
            this.matrix.invertAffine();
        } else {
            this.invert();
        }
    }

    public void lerp(Matrix4f other, float t) {
        this.matrix.lerp(other.matrix, t);
    }

    public void multiply(Matrix4f other) {
        if(this.matrix.isAffine()) {
            this.matrix.mulAffine(other.matrix);
        } else {
            this.matrix.mul(other.matrix);
        }
    }

    public void multiply(Vector3f v) {
        this.matrix.transformPosition(v.vector);
    }

    public void perspective(float fovy, float aspect, float zNear, float zFar) {
        this.matrix.perspective(fovy, aspect, zNear, zFar);
    }

    public float perspectiveFar() {
        return this.matrix.perspectiveFar();
    }

    public float perspectiveFov() {
        return this.matrix.perspectiveFov();
    }

    public void rotate(float angle, float x, float y, float z) {
        if(this.matrix.isAffine()) {
            this.matrix.rotateAffine(angle, x, y, z);
        } else {
            this.matrix.rotate(angle, x, y, z);
        }
    }

    public void rotate(float angle, Vector3f v) {
        this.matrix.rotate(angle,v.vector);
    }

    public void scale(float xyz) {
        this.matrix.scale(xyz);
    }

    public void scale(float x, float y, float z) {
        this.matrix.scale(x, y, z);
    }

    public void scale(Vector3f v) {
        this.matrix.scale(v.vector);
    }

    public void translate(float x, float y, float z) {
        this.matrix.translate(x, y , z);
    }

    public void translate (Vector3f v) {
        this.matrix.translate(v.vector);
    }

    public void logMatrix() {
        System.out.println(this.matrix);
    }
}
