package com.helpme.app.utils.either;

/**
 * Created by Jesper on 2017-04-20.
 */
public class Right<L,R> extends Either {
    R right;
    public Right(R right){
        if (right == null){
            throw new NullPointerException("Can't instantiate Right with a null value");
        }
        this.right = right;
    }

    public R getValue(){
        return right;
    }
}
