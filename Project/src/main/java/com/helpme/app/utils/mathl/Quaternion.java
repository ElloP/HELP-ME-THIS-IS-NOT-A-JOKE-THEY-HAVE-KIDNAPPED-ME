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
}
