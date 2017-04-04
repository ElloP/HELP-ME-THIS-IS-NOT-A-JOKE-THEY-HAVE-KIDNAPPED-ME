package com.helpme.app.tile;

import com.helpme.app.character.Character;
import com.helpme.app.character.Direction;
import com.helpme.app.tile.edge.Edge;

/**
 * Created by kopa on 2017-03-30.
 */
public class Tile {
    private Edge north;
    private Edge south;
    private Edge west;
    private Edge east;

    public Tile walkPast(Direction direction){
        switch (direction){
            case North:
                if (north.walkOver()){
                    return north.returnNeighbour(this);
                }
                break;
            case South:
                if (south.walkOver()){
                    return south.returnNeighbour(this);
                }
                break;
            case West:
                if (west.walkOver()){
                    return west.returnNeighbour(this);
                }
                break;
            case East:
                if (east.walkOver()){
                    return east.returnNeighbour(this);
                }
                break;
        }
        return null;
    }
}
