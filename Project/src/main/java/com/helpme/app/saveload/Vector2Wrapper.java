package com.helpme.app.saveload;

import com.helpme.app.utils.Vector2f;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Klas on 2017-05-02.
 */
public class Vector2Wrapper {
    private float x;
    private float y;
    public Vector2Wrapper(){}
    public Vector2Wrapper(Vector2f vec){
        this.x = vec.x;
        this.y = vec.y;
    }

    @XmlElement(name="x")
    public float getX(){
        return x;
    }
    @XmlElement(name="y")
    public float getY(){
        return y;
    }
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}
