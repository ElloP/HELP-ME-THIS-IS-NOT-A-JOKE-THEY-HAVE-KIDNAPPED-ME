package com.helpme.app.tile;

import com.helpme.app.tile.edge.Edge;

import static com.helpme.app.utilities.Type.*;
import static com.helpme.app.utilities.Type.Direction.*;


/**
 * Created by kopa on 2017-03-30.
 */
public class Tile {
    private Edge[] edges = new Edge[4];

    public Tile(Edge north, Edge east, Edge south, Edge west) {
        edges[NORTH.ordinal()] = north;
        edges[EAST.ordinal()] = east;
        edges[SOUTH.ordinal()] = south;
        edges[WEST.ordinal()] = west;
    }

    public boolean checkEdge(Direction direction) {
        return edges[direction.ordinal()].traverse();
    }

    public void setEdge(Direction direction, Edge edge){
        edges[direction.ordinal()] = edge;
    }

    public static Tile empty(){
        return new Tile(null,null,null,null);
    }
}
