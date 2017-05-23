package com.helpme.app.mathtest;

import com.helpme.app.utils.Vector2f;
import org.junit.Before;
import org.junit.Test;

/**
 * Authored by Olle on 2017-05-22.
 */
public class TestVector2f {
    Vector2f v1;
    Vector2f v2;

    float[][] m1;
    float[][] m2;

    @Before
    public void setup() {
        v1 = new Vector2f(1, 0);
        v2 = new Vector2f(0,1);
        m1 = new float[][] {
                {1, 0},
                {0, 1}
        };
        m2 = new float[][] {
                {2, 1},
                {0, 2}
        };
    }

    @Test
    public void matrixMultiplicationTest() {
        Vector2f v3 = Vector2f.multiply(m1, v1);
        Vector2f v4 = Vector2f.multiply(m2, v1);
        Vector2f v5 = Vector2f.multiply(m1, v2);
        Vector2f v6 = Vector2f.multiply(m2, v2);

        assert(v3.x == 1f && v3.y == 0f
                && v4.x == 2f && v4.y == 0f
                && v5.x == 0f && v5.y == 1f
                && v6.x == 1f && v6.y == 2f);
    }

    @Test
    public void rotateTest() {
        Vector2f v3 = v1.backward();
        Vector2f v4 = v1.right();
        Vector2f v5 = v1.left();
        assert(v3.x == -1f && v3.y == 0f
                && v4.x == 0f && v4.y == -1f
                && v5.x == 0f && v5.y == 1
        );
    }

    @Test
    public void rotateXTimesTest() {
        Vector2f v3 = v2.rotateRightAngle(0);
        Vector2f v4 = v2.rotateRightAngle(1);
        Vector2f v5 = v2.rotateRightAngle(2);
        Vector2f v6 = v2.rotateRightAngle(3);
        Vector2f v7 = v2.rotateRightAngle(4);

        assert(v3.x == 0f && v3.y == 1f
                && v4.x == 1f && v4.y == 0f
                && v5.x == 0f && v5.y == -1f
                && v6.x == -1f && v6.y == 0f
                && v7.x == 0f && v7.y == 1);
    }

    @Test
    public void additionTest() {
        Vector2f v3 = Vector2f.add(v1,v2);
        Vector2f v4 = Vector2f.add(v2,v1);
        assert(v3.x == 1f && v3.y == 1f
                && v3.equals(v4));
    }

    @Test
    public void scalarAdditionTest() {
        Vector2f v3 = Vector2f.add(v1, 3);
        assert(v3.x == 4f && v3.y == 3f);
    }

    @Test
    public void subtractionTest() {
        Vector2f v3 = Vector2f.subtract(v1,v2);
        Vector2f v4 = Vector2f.subtract(v2,v1);
        assert(v3.x == 1f && v3.y == -1f
                && v4.x == -1f && v4.y == 1f);
    }

    @Test
    public void scalarSubtractionTest() {
        Vector2f v3 = Vector2f.subtract(v1, 3);
        assert(v3.x == -2f && v3.y == -3f);
    }



    @Test
    public void multiplicationTest() {
        Vector2f v3 = Vector2f.multiply(v1,v2);
        Vector2f v4 = Vector2f.multiply(v2,v1);
        assert(v3.x == 0f && v3.y == 0f
                && v3.equals(v4));
    }

    @Test
    public void scalarMultiplicationTest() {
        Vector2f v3 = Vector2f.multiply(v1, 3);
        Vector2f v4 = Vector2f.multiply(v2, 3);
        assert(v3.x == 3f && v3.y == 0f
                && v4.x == 0f && v4.y == 3f);
    }

    @Test
    public void getNeighboursTest() {
        Vector2f[] n1 = Vector2f.getNeighbours(v1);
        Vector2f[] n2 = Vector2f.getNeighbours(v2);

        assert(n1[0].x == 1f && n1[0].y == 1f
                && n1[1].x == 2f && n1[1].y == 0f
                && n1[2].x == 1f && n1[2].y == -1f
                && n1[3].x == 0f && n1[3].y == 0f
                && n2[0].x == 0f && n2[0].y == 2f
                && n2[1].x == 1f && n2[1].y == 1f
                && n2[2].x == 0f && n2[2].y == -0f
                && n2[3].x == -1f && n2[3].y == 1f
        );
    }
}
