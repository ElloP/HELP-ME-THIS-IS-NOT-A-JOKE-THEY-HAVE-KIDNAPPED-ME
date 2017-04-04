package com.helpme.app.world;

import com.helpme.app.character.Monster;
import com.helpme.app.tile.Tile;
import com.helpme.app.tile.edge.Opening;
import com.helpme.app.tile.edge.Wall;
import com.helpme.app.utilities.Coordinate;

import java.util.HashMap;
import java.util.Map;

import static com.helpme.app.utilities.Type.*;
import static com.helpme.app.utilities.Type.Direction.*;

/**
 * Created by kopa on 2017-03-30.
 */
public class Level {

    private Monster player;
    private Map<Coordinate, Tile> tiles;
    private Coordinate startingPosition;


    public Level(Map<Coordinate, Tile> tiles, Coordinate startingPosition) {
        this.tiles = tiles;
        this.startingPosition = startingPosition;
    }

    public void init(Monster player) {
        this.player = player;
        player.setPosition(startingPosition);
        player.setDirection(NORTH);
        generateWalls();
    }

    public void generateWalls() {
        for (Coordinate coordinate : tiles.keySet()) {
            Tile tile = tiles.get(coordinate);

            tile.setEdge(NORTH, tiles.containsKey(coordinate.moveUp()) ? new Opening() : new Wall());
            tile.setEdge(EAST, tiles.containsKey(coordinate.moveRight()) ? new Opening() : new Wall());
            tile.setEdge(WEST, tiles.containsKey(coordinate.moveLeft()) ? new Opening() : new Wall());
            tile.setEdge(SOUTH, tiles.containsKey(coordinate.moveDown()) ? new Opening() : new Wall());
        }
    }

    public void setPlayer(Monster player) {
        this.player = player.copy();
    }

    public void removePlayer() {
        this.player = null;
    }

    private void movePlayer(int clockwise) {
        if (player == null) return;

        movePlayer(rotateDirection(player.getDirection(), clockwise));
    }

    private void movePlayer(Direction direction) {
        if (player == null) return;

        Tile playerTile = tiles.get(player.getPosition());

        if (playerTile == null) return;

        if (playerTile.checkEdge(direction)) {
            player.move(direction);
        }
    }

    public void movePlayerForward() {
        movePlayer(0);
    }

    public void movePlayerRight() {
        movePlayer(1);
    }

    public void movePlayerBackward() {
        movePlayer(2);
    }

    public void movePlayerLeft() {
        movePlayer(3);
    }

    public void rotatePlayerRight() {
        if (player == null) return;

        player.rotateRight();
    }

    public void rotatePlayerLeft() {
        if (player == null) return;

        player.rotateLeft();
    }

    public Coordinate getStartingPosition() {
        return startingPosition;
    }
}


