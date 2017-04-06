package com.helpme.app;

import com.helpme.app.tile.Tile;
import com.helpme.app.utils.Tuple.Tuple2;
import com.helpme.app.utils.Tuple.Tuple3;

/**
 * Created by og on 2017-04-06.
 */
public class TestTuple {
    public static void main(String[] args){
        Tuple2<Integer,Integer> tup2 = new Tuple2<>(2,3);
        System.out.println("A: "+ tup2.a + " B: " + tup2.b);

        Tuple3<Integer,Integer,Tile> tup3 = new Tuple3<>(2,2,new Tile());

    }
}
