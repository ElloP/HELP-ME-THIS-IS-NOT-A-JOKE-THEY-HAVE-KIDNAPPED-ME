package com.helpme.app.tile;

import com.helpme.app.tile.edge.Edge;

import static com.helpme.app.utilities.Type.*;


/**
 * Created by kopa on 2017-03-30.
 */
public class Tile {
    private Edge[] edges = new Edge[4];

    public boolean checkEdge(Direction direction) {
        return edges[direction.ordinal()].traverse();
    }
}
