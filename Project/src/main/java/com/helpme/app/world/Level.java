package com.helpme.app.world;

import com.helpme.app.character.IMonster;
import com.helpme.app.item.IItem;
import com.helpme.app.tile.ITile;
import com.helpme.app.tile.ITileFactory;
import com.helpme.app.tile.Tile;
import com.helpme.app.tile.edge.Door;
import com.helpme.app.tile.edge.Opening;
import com.helpme.app.tile.edge.Wall;
import com.helpme.app.utils.Tuple.Tuple2;
import com.helpme.app.utils.Tuple.Tuple3;
import com.helpme.app.utils.Vector2f;

import java.util.*;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Level implements ILevel{
    private IMonster player;
    private Map<Vector2f, ITile> tiles;
    private List<IMonster> monsters;

    public Level(List<Tuple2<Vector2f, IItem[]>> tiles, List<Tuple3<Vector2f, Vector2f, Door>> doors, List<IMonster> monsters, IMonster player, Vector2f startingPosition) {
        this.tiles = new HashMap<>();
        this.monsters = monsters;
        this.player = player.clone();
        this.player.setPosition(startingPosition);
        monsters.add(player);
        generateLevel(tiles, doors);
    }

    private void generateLevel(List<Tuple2<Vector2f,IItem[]>> tiles, List<Tuple3<Vector2f, Vector2f, Door>> doors) {
        generateTiles(tiles);
        generateEdges();
        generateDoors(doors);
    }

    private void generateTiles(List<Tuple2<Vector2f, IItem[]>> tiles) {
        for(Tuple2<Vector2f, IItem[]> tuple : tiles){
            Vector2f position = tuple.a;
            List<IItem> items = tuple.b == null ? null : new ArrayList<>(Arrays.asList(tuple.b));
            this.tiles.put(position, ITileFactory.tile(items));
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

            if (oppositeTile == null || defaultTile == null || door == null) {
                continue;
            }

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



    public boolean isEdgeBlocked(IMonster monster, Vector2f position, Vector2f direction) {
        ITile tile = tiles.get(position);
        return !monster.traverse(tile.getEdge(direction));
    }

    public boolean isTileOccupied(Vector2f position) {
        for (IMonster monster : monsters) {
            if (monster.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public IMonster getTileOccupant(Vector2f position){
        for (IMonster monster : monsters) {
            if (monster.getPosition().equals(position)) {
                return monster;
            }
        }
        return null;
    }

    @Override
    public boolean isTileValid(Vector2f position){
        return tiles.get(position) != null;
    }

    @Override
    public List<IItem> popTileItems(Vector2f position) {
        ITile tile = tiles.get(position);
        if(tile == null) {
            return null;
        }
        return tile.popItems();
    }

    @Override
    public void addTileItem(Vector2f position, IItem item){
        ITile tile = tiles.get(position);
        if(tile == null || item == null) {
            return;
        }
        tile.addItem(item);
    }

    @Override
    public void addTileItems(Vector2f position, IItem[] items){
        ITile tile = tiles.get(position);
        if(tile == null || items == null) {
            return;
        }
        tile.addItems(items);
    }

    @Override
    public IMonster getMonster(Vector2f position) {
        for (IMonster monster : monsters) {
            if (monster.getPosition().equals(position)) return monster;
        }
        return null;
    }


}
