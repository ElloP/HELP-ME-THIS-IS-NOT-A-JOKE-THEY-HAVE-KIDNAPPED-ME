package com.helpme.app.engine.base;


import com.helpme.app.utils.mathl.Vector3f;

/**
 * Authored by Olle on 2017-04-12.
 */

public class Camera {
    private Vector3f position;
    private Vector3f forward;
    private Vector3f up;

    public Camera() {
        this(new Vector3f(0.0f,0.0f, 0.3f), new Vector3f(0.0f,0.0f,1.0f), new Vector3f(0.0f,1.0f,0.0f));
    }

    public Camera(Vector3f position, Vector3f forward, Vector3f up) {
        this.position = position;
        this.forward = forward;
        this.up = up;

        up.normalize();
        forward.normalize();
    }

    public void move(Vector3f dir, float distance) {
        position.add(dir.multiply(distance));
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getForward() {
        return forward;
    }

    public void setForward(Vector3f forward) {
        this.forward = forward;
    }

    public Vector3f getUp() {
        return up;
    }

    public void setUp(Vector3f up) {
        this.up = up;
    }
}
