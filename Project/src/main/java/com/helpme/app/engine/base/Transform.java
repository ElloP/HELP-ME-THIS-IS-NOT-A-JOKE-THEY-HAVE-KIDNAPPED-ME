package com.helpme.app.engine.base;

import com.helpme.app.utils.mathl.Matrix4f;
import com.helpme.app.utils.mathl.Quaternion;
import com.helpme.app.utils.mathl.Vector3f;

/**
 * Authored by Olle on 2017-04-11.
 */

public class Transform {
    // ----------- Transform variables -----------
    private Transform parent;
    private Matrix4f parentMatrix;

    private Vector3f position;
    private Vector3f scale;
    private Quaternion rotation;


    // ----------- Transform constructors -----------

    public Transform() {
        position = new Vector3f();
        rotation = new Quaternion();
        scale = new Vector3f(1,1,1);

        parentMatrix = new Matrix4f();
    }

    public Transform(Vector3f position, Quaternion rotation, Vector3f scale) {
        this.position = new Vector3f(position);
        this.rotation = new Quaternion(rotation);
        this.scale = new Vector3f(scale);

        parent = new Transform();
    }

    public Transform(Vector3f position, Quaternion rotation) {
        this.position = new Vector3f(position);
        this.rotation = new Quaternion(rotation);
        this.scale = new Vector3f(1,1,1);

        parent = new Transform();
    }

    // ----------- Transform setters and getters -----------
    public Transform getParent() {
        return parent;
    }

    public void setParent(Transform parent) {
        this.parent = parent;
    }

    public Matrix4f getParentMatrix() {
        if(parent != null) {
            return parent.getModelMatrix();
        }
        return parentMatrix;
    }

    public void setPosition(Vector3f xyz) {
        position = new Vector3f(xyz);
    }

    public void setPosition(float x, float y, float z) {
        position = new Vector3f(x,y,z);
    }

    public Vector3f getPosition() { return position; }

    public Quaternion getRotation() {
        return new Quaternion(rotation);
    }

    public float getEulerAnglesX() {
        return rotation.x();
    }

    public float getEulerAnglesY() {
        return rotation.y();
    }

    public float getEulerAnglesZ() {
        return rotation.z();
    }

    // ----------- Transform operations (mostly setters) -----------

    public Matrix4f getModelMatrix() { //Note(Olle): combine a matrix for the translation, rotation and scale of a transform
        Matrix4f transformMatrix = new Matrix4f()
                .translate(position)
                .rotate(rotation)
                .scale(scale);

        getParentMatrix().multiply(transformMatrix, transformMatrix);

        return transformMatrix;
    }

    public static Matrix4f getPerspectiveMatrix(float fov, float width, float height, float zNear, float zFar) {
        return new Matrix4f().perspective(fov, width/height, zNear, zFar);
    }

    //TODO(Olle): if needed add function for orthogonal projection matrix

    public static Matrix4f getOrthoMatrix(float left, float right, float bottom, float top, float zNear, float zFar) {
        return new Matrix4f().ortho(left, right, bottom, top, zNear, zFar);
    }

    public void translate(Vector3f xyz) {
        position.add(xyz);
    }

    public void translate(float x, float y, float z) {
        position.add(x, y, z);
    }

    public void rotate(Vector3f xyz) {
        rotation.rotate(xyz);
    }

    public void rotate(Quaternion q) {
        rotation = new Quaternion(q);
    }

    public void rotate(float x, float y, float z) {
        rotation.rotate(x, y, z);
    }

    public void scale(Vector3f xyz) {
        scale = new Vector3f(xyz);
    }

    public void scale(float x, float y, float z) {
        scale = new Vector3f(x, y, z);
    }

    public void scale(float xyz) {
        scale = new Vector3f(xyz);
    }
}
