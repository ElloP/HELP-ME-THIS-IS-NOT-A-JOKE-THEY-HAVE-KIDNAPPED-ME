package com.helpme.app.utils.mathl;

/**
 * Created by og on 2017-04-06.
 */
public class Matrix2f {
    public Vector2f x;
    public Vector2f y;
    public Matrix2f xy;

    public Matrix2f(Vector2f x, Vector2f y){
        this.x = x;
        this.y = y;
        xy = this;
    }
    public Matrix2f(){
        this.x = new Vector2f(0,0);
        this.y = new Vector2f(0,0);
    }

    public Matrix2f transpose(){
        Vector2f x1 = new Vector2f(x.x,y.x);
        Vector2f y1 = new Vector2f(x.y,y.y);
        Matrix2f result = new Matrix2f(x1,y1);
        return result;
    }
    public Matrix2f plus(Matrix2f b){
        Vector2f nx = new Vector2f(x.x + b.x.y, x.y + b.x.y);
        Vector2f ny = new Vector2f(y.x + b.y.x, y.y + b.y.y);
        return new Matrix2f(nx,ny);
    }
    public Matrix2f minus(Matrix2f b){
        Vector2f nx = new Vector2f(x.x - b.x.y, x.y - b.x.y);
        Vector2f ny = new Vector2f(y.x - b.y.x, y.y - b.y.y);
        return new Matrix2f(nx,ny);
    }
    public void print(){
        System.out.printf("%30s %30s", "X", "Y");
        //TODO(klas) print out formatted matrix
    }
    public Vector2f multiply(Vector2f vec2){
        return new Vector2f(x.x*vec2.x + y.x*vec2.y, x.y*vec2.x + y.y*vec2.y);
    }
    public Matrix2f multiply(Matrix2f mat2){
        Vector2f nx = new Vector2f(x.x * mat2.x.x + y.x * mat2.x.y,x.y * mat2.x.x + y.y * mat2.x.y);
        Vector2f ny = new Vector2f(x.x * mat2.y.x + x.y * mat2.y.y, x.x * mat2.y.x + x.y * mat2.y.y);
        return new Matrix2f(nx,ny);
    }
    public Matrix2f scalarMul(float f){
        Vector2f nx = new Vector2f(x.x*f,x.y*f);
        Vector2f ny = new Vector2f(y.x*f,y.y*f);
        return new Matrix2f(nx,ny);
    }


}
