package com.helpme.app.world;

import com.helpme.app.character.IMonster;
import com.helpme.app.tile.ITile;
import com.helpme.app.tile.Tile;
import com.helpme.app.tile.edge.Door;
import com.helpme.app.tile.edge.Opening;
import com.helpme.app.tile.edge.Wall;
import com.helpme.app.utils.Tuple.Tuple3;
import com.helpme.app.utils.Vector2f;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Level implements ILevel{
    private IMonster player;
    private Map<Vector2f, ITile> tiles;
    private List<IMonster> monsters;

    public Level(List<Vector2f> tiles, List<Tuple3<Vector2f, Vector2f, Door>> doors, List<IMonster> monsters, IMonster player, Vector2f startingPosition) {
        this.tiles = new HashMap<>();
        this.monsters = monsters;
        this.player = player.clone();
        this.player.setPosition(startingPosition);
        monsters.add(player);
        generateLevel(tiles, doors);
    }

    private void generateLevel(List<Vector2f> tiles, List<Tuple3<Vector2f, Vector2f, Door>> doors) {
        generateTiles(tiles);
        generateEdges();
        generateDoors(doors);
    }

    private void generateTiles(List<Vector2f> tiles) {
        for(Vector2f position : tiles){
            this.tiles.put(position, Tile.empty());
        }
    }

    private void generateDoors(List<Tuple3<Vector2f, Vector2f, Door>> doors) {
        for (Tuple3<Vector2f, Vector2f, Door> tuple : doors) {
            Vector2f defaultPosition = tuple.a;
            Vector2f defaultDirection = tuple.b;
            Vector2f oppositeDirection = defaultDirection.rotateRightAngle(2);
            Vector2f oppositePosition = Vector2f.add(defaultPosition, defaultDirection);
            ITile defaultTile = tiles.get(defaultPosition);
            ITile oppositeTile = tiles.get(oppositePosition);
            Door door = tuple.c;

            if (oppositeTile == null || defaultTile == null || door == null) continue;

            defaultTile.setEdge(door, defaultDirection);
            oppositeTile.setEdge(door, oppositeDirection);
        }
    }

    private void generateEdges() {
        for (Vector2f position : tiles.keySet()) {
            ITile tile = tiles.get(position);
            tile.setEdge(tiles.get(Vector2f.add(position, Vector2f.up)) == null ? new Wall() : new Opening(), Vector2f.up);
            tile.setEdge(tiles.get(Vector2f.add(position, Vector2f.right)) == null ? new Wall() : new Opening(), Vector2f.right);
            tile.setEdge(tiles.get(Vector2f.add(position, Vector2f.down)) == null ? new Wall() : new Opening(), Vector2f.down);
            tile.setEdge(tiles.get(Vector2f.add(position, Vector2f.left)) == null ? new Wall() : new Opening(), Vector2f.left);
        }
    }

    @Override
    public boolean isEdgeBlocked(IMonster monster, Vector2f position, Vector2f direction) {
        ITile tile = tiles.get(position);
        return !monster.traverse(tile.getEdge(direction));
    }

    @Override
    public boolean isTileOccupied(Vector2f position) {
        for (IMonster monster : monsters) {
            if (monster.getPosition().equals(position)) return true;
        }
        return false;
    }

    @Override
    public boolean isTileValid(Vector2f position){
        return tiles.get(position) != null;
    }

    @Override
    public IMonster getMonster(Vector2f position) {
        for (IMonster monster : monsters) {
            if (monster.getPosition().equals(position)) return monster;
        }
        return null;
    }


}
