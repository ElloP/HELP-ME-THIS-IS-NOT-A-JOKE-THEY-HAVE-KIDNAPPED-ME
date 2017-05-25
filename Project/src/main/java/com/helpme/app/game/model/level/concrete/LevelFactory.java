package com.helpme.app.game.model.level.concrete;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.game.model.tile.ITile;
import com.helpme.app.game.model.tile.concrete.TileFactory;
import com.helpme.app.game.model.tile.edge.IDoor;
import com.helpme.app.game.model.tile.edge.concrete.Opening;
import com.helpme.app.game.model.tile.edge.concrete.Wall;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.tuple.Tuple3;

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

    public static ILevel createLevel(Map<Vector2f, IItem[]> tileInfo, List<Tuple3<Vector2f, Vector2f, IDoor>> doorInfo, List<IBody> bodies, Vector2f startingPosition){
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

    private static Map<Vector2f, ITile> generateTiles(Map<Vector2f, IItem[]> info) {
        Map<Vector2f, ITile> tiles = new HashMap<>();

        if (info == null) {
            return tiles;
        }

        for (Map.Entry<Vector2f, IItem[]> entry : info.entrySet()) {
            Vector2f position = entry.getKey();
            tiles.put(position, TileFactory.createTile(entry.getValue()));
        }

        return tiles;
    }

    private static Map<Vector2f, ITile> generateDoors(List<Tuple3<Vector2f, Vector2f, IDoor>> doors, Map<Vector2f, ITile> tiles) {
        if (doors == null) {
            return tiles;
        }

        for (Tuple3<Vector2f, Vector2f, IDoor> tuple : doors) {
            Vector2f defaultPosition = tuple.a;
            Vector2f defaultDirection = tuple.b;
            Vector2f oppositeDirection = defaultDirection.rotateRightAngle(2);
            Vector2f oppositePosition = Vector2f.add(defaultPosition, defaultDirection);
            ITile defaultTile = tiles.get(defaultPosition);
            ITile oppositeTile = tiles.get(oppositePosition);
            IDoor door = tuple.c;

            if (oppositeTile == null || defaultTile == null || door == null) {
                continue;
            }

            defaultTile.setEdge(defaultDirection, door);
            oppositeTile.setEdge(oppositeDirection, door);
        }
        return tiles;
    }

    private static Map<Vector2f, ITile> generateEdges(Map<Vector2f, ITile> tiles) {
        for (Map.Entry<Vector2f, ITile> entry : tiles.entrySet()) {
            ITile tile = entry.getValue();
            Vector2f position = entry.getKey();
            tile.setEdge(Vector2f.NORTH, tiles.get(Vector2f.add(position, Vector2f.NORTH)) == null ? new Wall() : new Opening());
            tile.setEdge(Vector2f.EAST, tiles.get(Vector2f.add(position, Vector2f.EAST)) == null ? new Wall() : new Opening());
            tile.setEdge(Vector2f.SOUTH, tiles.get(Vector2f.add(position, Vector2f.SOUTH)) == null ? new Wall() : new Opening());
            tile.setEdge(Vector2f.WEST, tiles.get(Vector2f.add(position, Vector2f.WEST)) == null ? new Wall() : new Opening());
        }
        return tiles;
    }
}
