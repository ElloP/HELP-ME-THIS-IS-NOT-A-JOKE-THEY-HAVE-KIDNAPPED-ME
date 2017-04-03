package com.helpme.app.world;

import com.helpme.app.character.Player;
import com.helpme.app.tile.Tile;
import com.helpme.app.utilities.Coordinate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kopa on 2017-03-30.
 */
public class Level {

    Player player;
    Map<Coordinate, Tile> tiles = new HashMap<>();

    public Level(Map<Coordinate, Tile> tiles, Player player) {
        this.tiles = tiles;
        this.player = player;
    }

    public void movePlayerForward() {
        Tile tile = tiles.get(player.getPosition());
        if (tile.exit(player.getDirection())) {

        }
    }

    public void movePlayerBackward() {

    }

    public void rotatePlayerRight() {

    }

    public void rotatePlayerLeft() {

    }

    public void movePlayerLeft() {

    }

    public void movePlayerRight() {

    }

}


