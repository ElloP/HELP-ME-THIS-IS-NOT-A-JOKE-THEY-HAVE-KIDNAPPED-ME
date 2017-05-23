package com.helpme.app.utils.tuple;

import com.helpme.app.utils.interfaces.ICopyable;

/**
 * Created by og on 2017-04-06.
 */
public class Tuple2<A,B> implements ICopyable{
    public A a;
    public B b;
    public Tuple2(A a, B b){
        this.a = a;
        this.b = b;
    }

    @Override
    public Tuple2<A, B> copy(){
        return new Tuple2<>(a, b);
    }
}
