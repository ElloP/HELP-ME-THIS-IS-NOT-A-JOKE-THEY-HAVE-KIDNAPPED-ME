package com.helpme.app.tile;

import com.helpme.app.character.Monster;
import com.helpme.app.tile.edge.Edge;

import static com.helpme.app.utilities.Type.*;
import static com.helpme.app.utilities.Type.Direction.*;


/**
 * Created by kopa on 2017-03-30.
 */
public class Tile {
    private Edge[] edges = new Edge[4];

    private Monster occupant;

    public Tile(Edge north, Edge east, Edge south, Edge west) {
        edges[NORTH.ordinal()] = north;
        edges[EAST.ordinal()] = east;
        edges[SOUTH.ordinal()] = south;
        edges[WEST.ordinal()] = west;
    }


    public boolean isOccupied(){
        return occupant != null;
    }

    public boolean putMonster(Monster monster) {
        if(isOccupied()) return false;
        occupant = monster;
        return true;
    }

    public Monster removeMonster() {
        Monster monster = occupant;
        occupant = null;
        return monster;
    }

    public boolean checkEdge(Direction direction) {
        return edges[direction.ordinal()].traverse();
    }

    public void setEdge(Direction direction, Edge edge){
        edges[direction.ordinal()] = edge;
    }

    public Tile copy(){
        Tile tile = Tile.empty();
        tile.edges = edges.clone();
        tile.occupant = occupant;
        return tile;
    }

    public static Tile empty(){
        return new Tile(null,null,null,null);
    }
}
