package com.helpme.app.utils.tuple;

/**
 * Created by og on 2017-04-06.
 */
public class Tuple2<A,B> implements Cloneable{
    public A a;
    public B b;
    public Tuple2(A a, B b){
        this.a = a;
        this.b = b;
    }
}
