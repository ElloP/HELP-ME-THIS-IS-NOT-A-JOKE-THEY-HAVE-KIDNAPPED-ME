package com.helpme.app.engine.base;


import com.helpme.app.engine.ICamera;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.mathl.Matrix4f;
import com.helpme.app.utils.mathl.Quaternion;
import com.helpme.app.utils.mathl.Vector3f;

/**
 * Authored by Olle on 2017-04-12.
 */

public class Camera implements ICamera {
    // ----------- Variables -----------
    //TODO(Olle): Add some sort of parent child structure with transforms
    private Vector3f position;
    private Vector3f up;
    private Vector3f forward;
    private Vector3f right;

    // ----------- Constructors -----------

    public Camera() {
        this.position = new Vector3f(0,1,0);
        this.forward = new Vector3f(0,0.0f,-1.0f); //Note(Olle): -z is forward in OpenGL
        this.up = new Vector3f(Vector3f.UP);
        setRight();
        normalizeVectors();
    }

    public Camera(Vector3f position, Vector3f forward) {
        this.position = position;
        this.forward = forward;
        up = new Vector3f(Vector3f.UP);
        setRight();
        normalizeVectors();
    }

    public Camera(Vector2f position) {
        this.position = new Vector3f(-position.x * 6, 1, position.y * 6);
        this.forward = new Vector3f(0, 0, -1);
        this.up = new Vector3f(Vector3f.UP);
        setRight();
        normalizeVectors();
    }

    public Camera(Vector3f position, Vector3f forward, Vector3f up) {
        this.position = position;
        this.forward = forward;
        this.up = up;
        setRight();
        normalizeVectors();
    }

    // ----------- Getters -----------
    public Vector3f getPosition() {
        return new Vector3f(position);
    }

    public void setPosition(Vector3f position) {
        this.position = new Vector3f(position);
    }

    public Vector3f getRight() {
        return new Vector3f(right);
    }

    public Vector3f getLeft() {
        Vector3f left = new Vector3f();
        return right.negate(left);
    }

    public Vector3f getForward() {
        return new Vector3f(forward);
    }

    public Vector3f getBack() {
        Vector3f back = new Vector3f();
        return forward.negate(back);
    }

    public Vector3f getUp() {
        return new Vector3f(up);
    }

    public Vector3f getDown() {
        Vector3f down = new Vector3f();
        return up.negate(down);
    }

    // ----------- Operations -----------
    public Matrix4f getViewMatrix() {
        Vector3f target = new Vector3f();
        //Note(Olle): look at the point that is one forward vector infront of the camera position
        position.add(forward, target);
        return new Matrix4f().lookAt(position, target, up);
    }

   public void rotate(Quaternion q) {
        up.rotate(q);
        forward.rotate(q);

        setRight();
        normalizeVectors();
   }

   public void rotate(Vector3f xyz) {
        Quaternion q = new Quaternion().rotate(xyz);
        rotate(q);
   }

    public void rotate(float x, float y, float z) {
        Quaternion q = new Quaternion().rotate(x, y, z);
        rotate(q);
    }

    public void move(Vector3f dir, float amt) {
        Vector3f deltaPos = new Vector3f().add(dir.multiply(amt));
        position.add(deltaPos);
    }

    public void lookAt(Vector3f target) {
        target.subtract(position, forward);
        setRight();
        setUp();
        normalizeVectors();
    }

    public void moveRight(float amt) {
        move(getRight(), amt);
    } //Note(Olle): use the getters for immutability

    public void moveLeft(float amt) {
        move(getLeft(), amt);
    }

    public void moveForward(float amt) {
        move(getForward(), amt);
    }

    public void moveBackward(float amt) {
        move(getBack(), amt);
    }

    public void moveUp(float amt) {
        move(getUp(), amt);
    }

    public void moveDown(float amt) {
        move(getDown(), amt);
    }

    private void normalizeVectors() {
        up.normalize();
        right.normalize();
        forward.normalize();
    }

    private void setRight() {
        //Note(Olle): Sets the right direction vector to the cross product between forward and up
        if(right == null) {
            right = new Vector3f(); //Note(Olle): prevents null pointer errors
        }
        forward.cross(up, right);
    }

    private void setUp() {
        if(up == null) {
            up = new Vector3f();
        }
        forward.cross(getLeft(), up);
    }

}
