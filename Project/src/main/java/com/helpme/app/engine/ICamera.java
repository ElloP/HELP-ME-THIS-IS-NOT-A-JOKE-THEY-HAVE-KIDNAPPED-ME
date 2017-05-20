package com.helpme.app.engine;

import com.helpme.app.utils.mathl.Matrix4f;
import com.helpme.app.utils.mathl.Quaternion;
import com.helpme.app.utils.mathl.Vector3f;

/**
 * Authored by Olle on 2017-05-20.
 */
public interface ICamera {
    Vector3f getPosition();

    void setPosition(Vector3f position);

    Vector3f getRight();

    Vector3f getLeft();

    Vector3f getForward();

    Vector3f getBack();

    Vector3f getUp();

    Vector3f getDown();

    Matrix4f getViewMatrix();

    void rotate(Quaternion q);

    void rotate(Vector3f xyz);

    void rotate(float x, float y, float z);

    void move(Vector3f dir, float amt);

    void lookAt(Vector3f target);

    void moveRight(float amt);

    void moveLeft(float amt);

    void moveForward(float amt);

    void moveBackward(float amt);

    void moveUp(float amt);

    void moveDown(float amt);
}
