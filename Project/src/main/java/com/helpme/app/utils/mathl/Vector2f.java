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

    public float length(){
        return (float) Math.hypot(this.x, this.y);
    }

    public float dotProduct(Vector2f vec){
        return this.x * vec.x + this.y + vec.y;
    }

    public Vector2f scalarMul(float multiplier){
        return new Vector2f(this.x * multiplier, this.y * multiplier);
    }

}
