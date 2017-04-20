package com.helpme.app.utils.mathl;

import org.joml.Quaternionf;

/**
 * Authored by Olle on 2017-04-13.
 */
public class Quaternion {
    protected Quaternionf quaternion;

    // ----------- Constructors -----------

    public Quaternion() {
        quaternion = new Quaternionf();
    }

    public Quaternion(Quaternion copy) {
        quaternion = new Quaternionf(copy.quaternion);
    }

    public Quaternion(float x, float y, float z, float w) {
        quaternion = new Quaternionf(x, y, z, w);
    }

    // ----------- Getters -----------

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

    // ----------- Operations/functions -----------

    public Quaternion conjugate() {
        this.quaternion.conjugate();
        return this;
    }

    public Vector3f toEulerAngles(Vector3f dest) {
        this.quaternion.getEulerAnglesXYZ(dest.vector);
        return dest.toDegrees();
    }

    public float length() {
        return (float) Math.sqrt(quaternion.lengthSquared());
    }

    public Quaternion logQuaternion() {
        System.out.println(quaternion);
        return this;
    }

    public Quaternion normalize() {
        this.quaternion.normalize();
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

    public Quaternion rotate(Vector3f eulerAngles) {
        this.quaternion.rotationZYX(eulerAngles.z(), eulerAngles.y(), eulerAngles.x());
        return this;
    }

    public Quaternion rotate(float x, float y, float z) {
        this.quaternion.rotationZYX(z, y, x);
        return this;
    }
}