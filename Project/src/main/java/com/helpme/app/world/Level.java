package com.helpme.app.world;

import com.helpme.app.character.IMonster;
import com.helpme.app.character.ITarget;
import com.helpme.app.character.Monster;
import com.helpme.app.item.IItem;
import com.helpme.app.tile.ITile;
import com.helpme.app.tile.ITileFactory;
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
public class Level implements ILevel {
    private IMonster player;
    private Map<Vector2f, ITile> tiles;
    private List<IMonster> monsters;
    private Vector2f startingPosition;

    public Level(List<Tuple2<Vector2f, IItem[]>> tiles, List<Tuple3<Vector2f, Vector2f, Door>> doors, List<IMonster> monsters, Vector2f startingPosition) {
        this.tiles = new HashMap<>();
        this.monsters = monsters == null ? new ArrayList<>() : monsters;
        this.startingPosition = startingPosition;
        generateLevel(tiles, doors);
    }

    private void generateLevel(List<Tuple2<Vector2f, IItem[]>> tiles, List<Tuple3<Vector2f, Vector2f, Door>> doors) {
        generateTiles(tiles);
        generateEdges();
        generateDoors(doors);
    }

    private void generateTiles(List<Tuple2<Vector2f, IItem[]>> tiles) {
        if (tiles == null) {
            return;
        }

        for (Tuple2<Vector2f, IItem[]> tuple : tiles) {
            Vector2f position = tuple.a;
            ;
            this.tiles.put(position, ITileFactory.tile(tuple.b));
        }
    }

    private void generateDoors(List<Tuple3<Vector2f, Vector2f, Door>> doors) {
        if (doors == null) {
            return;
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


    public boolean isMonsterBlockedByEdge(IMonster monster, Vector2f direction) {
        ITile tile = tiles.get(monster.getPosition());
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

    public ITarget getTarget(IMonster monster, Vector2f direction) {
        Vector2f position = monster.getPosition();
        Vector2f destination = Vector2f.add(monster.getPosition(), direction);

        if(isMonsterBlockedByEdge(monster, direction)){
            return tiles.get(position).getEdge(direction);
        }

        return getMonster(destination);
    }

    @Override
    public boolean isTileValid(Vector2f position) {
        return tiles.get(position) != null;
    }

    @Override
    public IItem[] removeTileItems(Vector2f position) {
        ITile tile = tiles.get(position);
        return tile == null ? null : tile.removeItems();
    }

    @Override
    public IItem removeTileItem(Vector2f position, int index) {
        ITile tile = tiles.get(position);
        return tile == null ? null : tile.removeItem(index);
    }

    @Override
    public void addMonster(IMonster monster) {
        if (monster == null || monsters.contains(monster)) return;
        monsters.add(monster);
    }

    @Override
    public void addTileItem(Vector2f position, IItem item) {
        ITile tile = tiles.get(position);
        if (tile == null || item == null) {
            return;
        }
        tile.addItem(item);
    }

    @Override
    public void addTileItems(Vector2f position, IItem[] items) {
        ITile tile = tiles.get(position);
        if (tile == null || items == null) {
            return;
        }
        tile.addItems(items);
    }

    @Override
    public void setPlayer(IMonster player) {
        this.player = player;
        player.setPosition(startingPosition);
    }

    @Override
    public IMonster getMonster(Vector2f position) {
        for (IMonster monster : monsters) {
            if (monster.getPosition().equals(position)) {
                return monster;
            }
        }
        return null;
    }

    @Override
    public IMonster getPlayer() {
        return player;
    }

    class CameFrom {
        CameFrom previous;
        Vector2f position;

        public CameFrom(CameFrom previous, Vector2f position){
            this.previous = previous;
            this.position = position;
        }
    }

    //TODO (Jesper): Take edges and doors into consideration
    public Tuple3<List<Vector2f>, Vector2f, Integer> getShortestPath(Vector2f from, Vector2f to){
        Vector2f currentPos;
        CameFrom current;
        ArrayList<Vector2f> result = new ArrayList<>();
        ArrayList<Vector2f> visitedNodes = new ArrayList<>();
        Stack<CameFrom> frontier = new Stack<>();
        int cost = 0;
        frontier.push(new CameFrom(null, from));
        while (!frontier.isEmpty()){
            current = frontier.pop();
            if (current.position.equals(to)){
                result = recreatePath(current);
                break;
            }

            Vector2f[] neighbors = Vector2f.getNeighbors(current.position);
            for (Vector2f neighbor : neighbors){
                if (!visitedNodes.contains(neighbor) && isTileValid(neighbor) && (!isTileOccupied(neighbor) || player.getPosition().equals(neighbor)))
                    frontier.push(new CameFrom(current, neighbor));
            }
            visitedNodes.add(current.position);
        }
        cost = result.size();
        Vector2f nextPos;
        if (cost != 0){
            nextPos = result.get(1);
        }
        else {
            nextPos = from;
        }
        return new Tuple3<>(result, nextPos, cost);
    }

    private ArrayList<Vector2f> recreatePath(CameFrom current){
        ArrayList<Vector2f> result = new ArrayList<>();
        result.add(current.position);
        while (current.previous != null){
            current = current.previous;
            result.add(current.position);
        }
        Collections.reverse(result);
        return result;
    }



    @Override
    public boolean isDistanceFrom(IMonster monster, Vector2f destination, int longestDistance) {
        ArrayList<Vector2f> positions = new ArrayList<>();
        ArrayList<Vector2f> notAdded = new ArrayList<>();
        notAdded.add(monster.getPosition());
        for (int i = 1; i <= longestDistance; i++) {
            ArrayList<Vector2f> temp = new ArrayList<>();
            for (Vector2f pos : notAdded) {
                for (Vector2f neighbour : Vector2f.getNeighbors(pos)){
                    temp.add(neighbour);
                }
                positions.add(pos);
            }
            notAdded.removeAll(notAdded);
            notAdded.addAll(temp);
        }
        positions.addAll(notAdded);
        return positions.contains(destination);
    }

}
