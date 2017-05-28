package com.helpme.app.game.saveload;

import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.utils.mathl.Vector2f;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Klas on 2017-05-02.
 */
public class Vector2Wrapper implements ILoadable<Vector2f> {
    @XmlElement(name = "x")
    private float x;

    @XmlElement(name = "y")
    private float y;

    public Vector2Wrapper(){}

    public Vector2Wrapper(Vector2f vec){
        this.x = vec.x;
        this.y = vec.y;
    }

    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    @Override
    public Vector2f getObject() {
        return new Vector2f(x,y);
    }
}
