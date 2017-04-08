package com.helpme.app.world;

import com.helpme.app.character.IMonster;
import com.helpme.app.item.Item;
import com.helpme.app.tile.Tile;
import com.helpme.app.tile.edge.Door;
import com.helpme.app.tile.edge.Opening;
import com.helpme.app.tile.edge.Wall;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.Vector4f;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Level {
    IMonster player;
    Map<Vector2f, Tile> tiles;
    List<IMonster> monsters;

    public Level(List<Vector2f> tiles, Map<Vector4f, Door> doors, List<IMonster> monsters, IMonster player, Vector2f startingPosition) {
        this.tiles = new HashMap<>();
        this.monsters = monsters;
        this.player = player.clone();
        this.player.setPosition(startingPosition);
        monsters.add(player);
        generateLevel(tiles, doors);
    }

    private void generateLevel(List<Vector2f> tiles, Map<Vector4f, Door> doors) {
        generateTiles(tiles);
        generateEdges();
        generateDoors(doors);
    }

    private void generateTiles(List<Vector2f> tiles) {
        for(Vector2f position : tiles){
            this.tiles.put(position, Tile.empty());
        }
    }

    private void generateDoors(Map<Vector4f, Door> doors) {
        for (Vector4f edge : doors.keySet()) {
            Vector2f defaultPosition = new Vector2f(edge.x, edge.y);
            Vector2f defaultDirection = new Vector2f(edge.z, edge.w);
            Vector2f oppositeDirection = new Vector2f(edge.z, edge.w).rotateRightAngle(2);
            Vector2f oppositePosition = Vector2f.add(defaultPosition, defaultDirection);
            Tile defaultTile = tiles.get(defaultPosition);
            Tile oppositeTile = tiles.get(oppositePosition);
            Door door = doors.get(edge);

            if (oppositeTile == null || defaultTile == null || door == null) continue;

            defaultTile.setEdge(door, defaultDirection);
            oppositeTile.setEdge(door, oppositeDirection);
        }
    }

    private void generateEdges() {
        for (Vector2f position : tiles.keySet()) {
            Tile tile = tiles.get(position);
            tile.setEdge(tiles.get(Vector2f.add(position, Vector2f.up)) == null ? new Wall() : new Opening(), Vector2f.up);
            tile.setEdge(tiles.get(Vector2f.add(position, Vector2f.right)) == null ? new Wall() : new Opening(), Vector2f.right);
            tile.setEdge(tiles.get(Vector2f.add(position, Vector2f.down)) == null ? new Wall() : new Opening(), Vector2f.down);
            tile.setEdge(tiles.get(Vector2f.add(position, Vector2f.left)) == null ? new Wall() : new Opening(), Vector2f.left);
        }
    }

    public IMonster getPlayer() {
        return player.clone();
    }

    private boolean isMovementAllowed(IMonster monster, Vector2f direction) {
        Vector2f position = monster.getPosition();
        Vector2f destination = Vector2f.add(position, direction);
        Tile tile = tiles.get(position);

        if (!isTraversable(monster, tile, direction)) {
            return false;
        }
        if (!isVacant(destination)) {
            return false;
        }

        return true;
    }

    private boolean isTraversable(IMonster monster, Tile tile, Vector2f direction) {
        return monster.traverse(tile.getEdge(direction));
    }

    private boolean isVacant(Vector2f position) {
        for (IMonster monster : monsters) {
            if (monster.getPosition().equals(position)) return false;
        }
        return true;
    }

    public void movePlayerForward() {
        if (!isMovementAllowed(player, player.getDirection())) return;
        player.moveForward();
    }

    public void movePlayerRight() {
        if (!isMovementAllowed(player, player.getDirection().right())) return;
        player.moveRight();
    }

    public void movePlayerBackward() {
        if (!isMovementAllowed(player, player.getDirection().backward())) return;
        player.moveBackward();
    }

    public void movePlayerLeft() {
        if (!isMovementAllowed(player, player.getDirection().left())) return;
        player.moveLeft();
    }

    public void rotatePlayerRight() {
        player.rotateRight();
    }

    public void rotatePlayerLeft() {
        player.rotateLeft();
    }

    public void setPlayerPosition(Vector2f position) {
        if (tiles.get(position) == null) return;
        player.setPosition(position);
    }

}
