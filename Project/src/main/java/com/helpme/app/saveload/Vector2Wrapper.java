package com.helpme.app.saveload;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.interfaces.ILoadable;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Klas on 2017-05-02.
 */
public class Vector2Wrapper implements ILoadable<Vector2f> {
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
    public void setX(float x){
        this.x = x;
    }

    @XmlElement(name="y")
    public float getY(){
        return y;
    }
    public void setY(float y){
        this.y = y;
    }

    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    @Override
    public Vector2f getObject() {
        return new Vector2f(x,y);
    }
}
