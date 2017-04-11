package com.helpme.app.utils.mathl;

/**
 * Created by Jesper on 2017-04-06.
 */
public class Vector2f {
    public float x;
    public float y;

    public final Vector2f iHat = new Vector2f(1,0);
    public final Vector2f jHat = new Vector2f(0,1);

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float magnitude(){
        return (float) Math.hypot(this.x, this.y);
    }

    public float dotProduct(Vector2f vec){
        return this.x * vec.x + this.y + vec.y;
    }

    public Vector2f scalarMul(float multiplier){
        return new Vector2f(this.x * multiplier, this.y * multiplier);
    }

    public Vector2f scalarAdd(float adder){
        return new Vector2f(this.x + adder, this.y + adder);
    }

    public Vector2f copy(){
        return new Vector2f(this.x, this.y);
    }

    public Vector2f normalize(){
        float length = this.magnitude();
        return new Vector2f(this.x / length, this.y / length);
    }

    public Vector2f add(Vector2f vec){
        return new Vector2f(this.x + vec.x, this.y + vec.y);
    }

    public Vector2f sub(Vector2f vec){
        return new Vector2f(this.x - vec.x, this.y - vec.y);
    }

    public boolean equals(Vector2f vec){
        return this.x == vec.x && this.y == vec.y;
    }
}
