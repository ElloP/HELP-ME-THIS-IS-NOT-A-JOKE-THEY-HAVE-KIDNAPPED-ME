package com.helpme.app.engine.base;

import com.helpme.app.utils.mathl.Vector3f;

/**
 * Authored by Olle on 2017-04-12.
 */

public class Camera {
    private Vector3f position;
    private Vector3f direction;
    private Vector3f right; //Note(Olle): right directional vector relative to the camera object
    private Vector3f up; //Note(Olle): up directional vector relative to the camera object

    Camera() {
        position = new Vector3f(0.0f, 0.0f, 3.0f);
        direction = new Vector3f();

    }

    public void lookAt(Vector3f target) {
        position.subtract(target, direction).normalize();
    }

}
