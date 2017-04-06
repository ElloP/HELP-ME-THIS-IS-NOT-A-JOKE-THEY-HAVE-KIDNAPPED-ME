package com.helpme.app.utils.mathl;

/**
 * Created by Jesper on 2017-04-06.
 */
public class Vector3f {
    public float x;
    public float y;
    public float z;

    public final Vector3f iHat = new Vector3f(1,0,0);
    public final Vector3f jHat = new Vector3f(0,1,0);
    public final Vector3f kHat = new Vector3f(0,0,1);

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float magnitude(){
        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public float dotProduct(Vector3f vec){
        return this.x * vec.x + this.y * vec.y + this.z * vec.z;
    }

    public Vector3f scalarMul(float multiplier){
        return new Vector3f(this.x * multiplier, this.y * multiplier, this.z * multiplier);
    }

    public Vector3f scalarAdd(float adder){
        return new Vector3f(this.x + adder, this.y + adder, this.z + adder);
    }

    public Vector3f copy(){
        return new Vector3f(this.x, this.y, this.z);
    }

    public Vector3f normalize(){
        float length = this.magnitude();
        return new Vector3f(this.x / length, this.y / length, this.z / length);
    }

    public Vector3f crossProduct(Vector3f u, Vector3f v){
        return new Vector3f(u.y * v.z - u.z * v.y, u.z * v.x - u.x * v.z, u.x * v.y - u.y * v.x);
    }

    public Vector3f add(Vector3f vec){
        return new Vector3f(this.x + vec.x, this.y + vec.y, this.z + vec.z);
    }

    public Vector3f sub(Vector3f vec){
        return new Vector3f(this.x - vec.x, this.y - vec.y, this.z - vec.z);
    }

    public boolean equals(Vector3f vec){
        return this.x == vec.x && this.y == vec.y && this.z == vec.z;
    }

}
