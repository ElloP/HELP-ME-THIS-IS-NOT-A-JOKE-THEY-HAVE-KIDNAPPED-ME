package com.helpme.app.utils;

/**
 * Created by kopa on 2017-04-05.
 */
public class Matrix2f {
    public float[][] matrix = new float[2][2];

    public Matrix2f(float x1y1, float x2y1, float x1y2, float x2y2){
        matrix[0][0] = x1y1;
        matrix[1][0] = x2y1;
        matrix[0][1] = x1y2;
        matrix[1][1] = x2y2;
    }
}
