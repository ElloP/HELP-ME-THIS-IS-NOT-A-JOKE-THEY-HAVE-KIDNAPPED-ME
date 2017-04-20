package com.helpme.app.utils.either;

/**
 * Created by Jesper on 2017-04-20.
 */
public class Left<L,R> extends Either {
    L left;
    public Left(L left){
        if (left == null){
            throw new NullPointerException("Can't instantiate Left with a null value");
        }
        this.left = left;
    }

    public L getValue(){
        return left;
    }
}
