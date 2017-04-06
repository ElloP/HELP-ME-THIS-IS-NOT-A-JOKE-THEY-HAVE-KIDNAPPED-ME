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

    public float length(){
        return (float) Math.sqrt(this.x * this.x, this.y * this.y, this.z * this.z);
    }

    public float dotProduct(Vector3f vec){
        return this.x * vec.x + this.y * vec.y + this.z * vec.z;
    }

    public Vector3f scalarMul(float multiplier){
        this.x *= multiplier;
        this.y *= multiplier;
        this.z *= multiplier;
    }
}
