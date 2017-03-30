package com.helpme.app.tile;

import com.helpme.app.tile.edge.Edge;

/**
 * Created by kopa on 2017-03-30.
 */
public class Tile {
    public enum Direction {
        NORTH, EAST, SOUTH, WEST
    }

    private Edge[] edges = new Edge[4];

    boolean exit(Direction direction){
        return edges[direction.ordinal()].traverse();
    }
}
