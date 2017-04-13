package com.helpme.app.utils.mathl;

import org.joml.Quaternionf;

/**
 * Authored by Olle on 2017-04-13.
 */
public class Quaternion {
    private Quaternionf quaternion;

    public Quaternion() {
        quaternion = new Quaternionf();
    }

    public Quaternion(Quaternion copy) {
        quaternion = new Quaternionf(copy.quaternion);
    }

    public Quaternion(float x, float y, float z, float w) {
        quaternion = new Quaternionf(x, y, z, w);
    }

    // GETTERS
    public float x() {
        return quaternion.x();
    }

    public float y() {
        return quaternion.x();
    }

    public float z() {
        return quaternion.x();
    }

    public float w() {
        return quaternion.x();
    }

    //REST

    public float length() {
        return (float) Math.sqrt(quaternion.lengthSquared());
    }

    public Quaternion normalize() {
        this.quaternion.normalize();
        return this;
    }

    public Quaternion conjugate() {
        this.quaternion.conjugate();
        return this;
    }

    public Quaternion multiply(Quaternion q) {
        this.quaternion.mul(q.quaternion);
        return this;
    }

    public Quaternion multiply(Quaternion q, Quaternion dest) {
        this.quaternion.mul(q.quaternion, dest.quaternion);
        return dest;
    }

    public Vector3f transform(Vector3f v) {
        this.quaternion.transform(v.vector);
        return v;
    }
}
