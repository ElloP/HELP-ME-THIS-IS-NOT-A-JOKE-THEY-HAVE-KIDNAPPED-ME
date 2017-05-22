package com.helpme.app.model.level.concrete;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.model.body.IBody;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.level.ILevel;
import com.helpme.app.model.tile.ITile;
import com.helpme.app.model.tile.concrete.TileFactory;
import com.helpme.app.model.tile.edge.concrete.Door;
import com.helpme.app.model.tile.edge.concrete.Opening;
import com.helpme.app.model.tile.edge.concrete.Wall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-05-15.
 */
public final class LevelFactory {
    private LevelFactory(){

    }

    public static ILevel createLevel(List<Tuple2<Vector2f, IItem[]>> tileInfo, List<Tuple3<Vector2f, Vector2f, Door>> doorInfo, List<IBody> bodies, Vector2f startingPosition){
        Map<Vector2f, ITile> tiles = generateDoors(doorInfo, generateEdges(generateTiles(tileInfo)));
        return createLevel(null, startingPosition, tiles, bodies);
    }

    public static ILevel createLevel(IBody player, Vector2f startingPosition, Map<Vector2f, ITile> tiles, List<IBody> bodies){
        return new Level(player, startingPosition == null ? Vector2f.ZERO : startingPosition, tiles == null ? new HashMap<>() : tiles, bodies == null ? new ArrayList<>() : bodies);
    }
    public static ILevel createLevel(Map<Vector2f, ITile> tiles, Vector2f startingPosition){
        List<IBody> bodies = new ArrayList<>();
        return createLevel(null,startingPosition,tiles,bodies);
    }

    private static Map<Vector2f, ITile> generateTiles(List<Tuple2<Vector2f, IItem[]>> info) {
        Map<Vector2f, ITile> tiles = new HashMap<>();

        if (info == null) {
            return tiles;
        }

        for (Tuple2<Vector2f, IItem[]> tuple : info) {
            Vector2f position = tuple.a;
            tiles.put(position, TileFactory.createTile(tuple.b));
        }

        return tiles;
    }

    private static Map<Vector2f, ITile> generateDoors(List<Tuple3<Vector2f, Vector2f, Door>> doors, Map<Vector2f, ITile> tiles) {
        if (doors == null) {
            return tiles;
        }

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
        return tiles;
    }

    private static Map<Vector2f, ITile> generateEdges(Map<Vector2f, ITile> tiles) {
        for (Map.Entry<Vector2f, ITile> entry : tiles.entrySet()) {
            ITile tile = entry.getValue();
            Vector2f position = entry.getKey();
            tile.setEdge(tiles.get(Vector2f.add(position, Vector2f.NORTH)) == null ? new Wall() : new Opening(), Vector2f.NORTH);
            tile.setEdge(tiles.get(Vector2f.add(position, Vector2f.EAST)) == null ? new Wall() : new Opening(), Vector2f.EAST);
            tile.setEdge(tiles.get(Vector2f.add(position, Vector2f.SOUTH)) == null ? new Wall() : new Opening(), Vector2f.SOUTH);
            tile.setEdge(tiles.get(Vector2f.add(position, Vector2f.WEST)) == null ? new Wall() : new Opening(), Vector2f.WEST);
        }
        return tiles;
    }
}
