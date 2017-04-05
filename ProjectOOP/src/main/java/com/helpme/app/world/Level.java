package com.helpme.app.world;

import com.helpme.app.character.Monster;
import com.helpme.app.tile.Tile;
import com.helpme.app.tile.edge.Opening;
import com.helpme.app.tile.edge.Wall;
import com.helpme.app.utils.Vector2f;

import java.util.List;
import java.util.Map;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Level {
    Monster player;
    Map<Vector2f, Tile> tiles;
    List<Monster> monsters;

    public Level(Map<Vector2f, Tile> tiles, List<Monster> monsters, Monster player, Vector2f startingPosition) {
        this.tiles = tiles;
        this.monsters = monsters;
        this.player = player.clone();
        this.player.setPosition(startingPosition);
        monsters.add(player);
        generateLevel();
    }

    private void generateLevel(){
        for(Vector2f position : tiles.keySet()){
            Tile tile = tiles.get(position);
            tile.setEdge(tiles.get(Vector2f.add(position, Vector2f.up)) == null ? new Wall() : new Opening(), Vector2f.up);
            tile.setEdge(tiles.get(Vector2f.add(position, Vector2f.right)) == null ? new Wall() : new Opening(), Vector2f.right);
            tile.setEdge(tiles.get(Vector2f.add(position, Vector2f.down)) == null ? new Wall() : new Opening(), Vector2f.down);
            tile.setEdge(tiles.get(Vector2f.add(position, Vector2f.left)) == null ? new Wall() : new Opening(), Vector2f.left);
        }
    }

    public Monster getPlayer(){
        return player.clone();
    }

    private boolean isMovementAllowed(Vector2f position, Vector2f direction) {
        Tile tile = tiles.get(position);
        Vector2f destination = Vector2f.add(position, direction);
        if(isExitBlocked(tile, direction)) return false;
        if(isOccupied(destination)) return false;
        return true;
    }

    private boolean isExitBlocked(Tile tile, Vector2f direction){
        return tile.tryExit(direction);
    }

    private boolean isOccupied(Vector2f position){
        for(Monster monster : monsters){
            if(monster.getPosition() == position) return true;
        }
        return false;
    }

    public void movePlayerForward() {
        if(isMovementAllowed(player.getPosition(), player.getDirection())) return;
        player.moveForward();
    }

    public void movePlayerRight() {
        if(isMovementAllowed(player.getPosition(), player.getDirection().right())) return;
        player.moveRight();
    }

    public void movePlayerBackward() {
        if(isMovementAllowed(player.getPosition(), player.getDirection().backward())) return;
        player.moveBackward();
    }

    public void movePlayerLeft() {
        if(isMovementAllowed(player.getPosition(), player.getDirection().left())) return;
        player.moveLeft();
    }

    public void rotatePlayerRight() {
        player.rotateRight();
    }

    public void rotatePlayerLeft() {
        player.rotateLeft();
    }


}
