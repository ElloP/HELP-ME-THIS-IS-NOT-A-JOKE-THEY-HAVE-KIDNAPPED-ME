package com.helpme.app.engine.base;

import com.helpme.app.utils.mathl.Matrix4f;
import com.helpme.app.utils.mathl.Vector3f;

/**
 * Authored by Olle on 2017-04-11.
 */

public class Transform {
    // ----------- Transform variables -----------

    private Vector3f position;
    private Vector3f rotation;
    private Vector3f scale;

    // ----------- Transform constructor -----------

    public Transform() {
        position = new Vector3f();
        rotation = new Vector3f();
        scale = new Vector3f(1,1,1);
    }

    // ----------- Transform getters -----------

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

    public Matrix4f getTransformMatrix() { //combine a matrix for the translation, rotation and scale of a transform
        Matrix4f transformMatrix = new Matrix4f()
                .translate(position)
                .rotateXYZ(rotation.toRadians())
                .scale(scale);

        return transformMatrix;
    }

    public static Matrix4f getPerspectiveMatrix(float fov, float width, float height, float zNear, float zFar) {
        return new Matrix4f().perspective(fov, width/height, zNear, zFar);
    }

    //TODO(Olle): if needed add function for orthogonal projection matrix (probably not needed though)

    public void setPosition(Vector3f xyz) {
        position = new Vector3f(xyz);
    }

    public void setPosition(float x, float y, float z) {
        position = new Vector3f(x,y,z);
    }

    public void translate(Vector3f xyz) {
        position.add(xyz);
    }

    public void translate(float x, float y, float z) {
        position.add(x, y, z);
    }

    public void rotate(Vector3f xyz) {
        rotation = new Vector3f(xyz);
    }

    public void rotate(float x, float y, float z) {
        rotation = new Vector3f(x, y, z);
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
