package com.helpme.app.tile.edge;

import com.helpme.app.tile.Tile;

import java.util.HashMap;

/**
 * Created by kopa on 2017-03-30.
 */
public abstract class Edge {

    public abstract boolean walkOver();

    private HashMap<Tile, Tile> neighbours;

    public Tile returnNeighbour(Tile key){
        return neighbours.get(key);
    }
}
