package com.helpme.app.tile;

import com.helpme.app.character.Character;
import com.helpme.app.tile.edge.Edge;

/**
 * Created by kopa on 2017-03-30.
 */
public class Tile {
    private Edge north;
    private Edge south;
    private Edge west;
    private Edge east;

    //Can be null
    private Character character;
}
