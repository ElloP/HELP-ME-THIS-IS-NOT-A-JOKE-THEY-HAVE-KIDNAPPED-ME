package com.helpme.app.tile;

import com.helpme.app.item.Item;
import com.helpme.app.tile.edge.IEdge;
import com.helpme.app.utils.Vector2f;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Tile {
    IEdge[] edges = new IEdge[4];

    public IEdge getEdge(Vector2f direction) {
        try {
            return edges[Vector2fToIndex(direction)];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
            return null;
        }
    }


    public void setEdge(IEdge edge, Vector2f direction) {
        try {
            edges[Vector2fToIndex(direction)] = edge;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    private int Vector2fToIndex(Vector2f vec0) {
        if (vec0.equals(Vector2f.up)) {
            return 0;
        }
        if (vec0.equals(Vector2f.right)) {
            return 1;
        }
        if (vec0.equals(Vector2f.down)) {
            return 2;
        }
        if (vec0.equals(Vector2f.left)) {
            return 3;
        }

        return -1;
    }

    public static Tile empty() {
        return new Tile();
    }
}
