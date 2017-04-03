package com.helpme.app.world;

import com.helpme.app.character.Player;
import com.helpme.app.tile.Tile;
import com.helpme.app.utilities.Coordinate;

import java.util.HashMap;
import java.util.Map;
import static com.helpme.app.utilities.Type.*;
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

    private void movePlayer(Direction direction){
        Tile playerTile = tiles.get(player.getPosition());
        if (playerTile.checkEdge(direction)) {
            player.move(direction);
        }
    }

    public void movePlayerForward() {
        movePlayer(player.getDirection());

    }

    public void movePlayerRight() {
        movePlayer(rotateDirection(player.getDirection(),1));
    }

    public void movePlayerBackward() {
        movePlayer(rotateDirection(player.getDirection(),2));
    }

    public void movePlayerLeft() {
        movePlayer(rotateDirection(player.getDirection(),3));
    }

    public void rotatePlayerRight() {
        player.rotateRight();
    }

    public void rotatePlayerLeft() {
        player.rotateLeft();
    }
}


