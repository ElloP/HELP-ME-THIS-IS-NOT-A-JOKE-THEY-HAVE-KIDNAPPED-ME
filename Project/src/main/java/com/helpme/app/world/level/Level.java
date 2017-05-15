package com.helpme.app.world.level;

import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.character.target.ITarget;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.tile.ITile;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.utils.Vector2f;

import java.util.*;

/**
 * Created by Jacob on 2017-03-30.
 */

public class Level implements ILevel {
    private IBody player;
    private Vector2f startingPosition;
    private Map<Vector2f, ITile> tiles;
    private List<IBody> bodies;


    public Level(IBody player, Vector2f startingPosition, Map<Vector2f, ITile> tiles, List<IBody> bodies) {
        this.player = player;
        this.startingPosition = startingPosition;
        this.tiles = tiles;
        this.bodies = bodies;

        resetPlayer();
    }


    public boolean isDirectionBlocked(IReadBody body, Vector2f direction) {
        ITile tile = tiles.get(body.readPosition());
        return tile.getEdge(direction).check(e -> !body.isTraversable(e));
    }

    public boolean isTileOccupied(Vector2f position) {
        for (IBody body : bodies) {
            if (body.readPosition().equals(position) && !body.isDead()) {
                return true;
            }
        }
        return false;
    }

    public Maybe<ITarget> getTarget(IBody body, Vector2f direction) {
        Vector2f position = body.readPosition();
        Vector2f destination = Vector2f.add(body.readPosition(), direction);

        if (isDirectionBlocked(body, direction)) {
            return Maybe.wrap(tiles.get(position).getEdge(direction));
        }

        return Maybe.wrap(accessBody(destination));
    }

    @Override
    public Map<Vector2f, ITile> getTiles() {
        return this.tiles;
    }

    @Override
    public boolean isTileValid(Vector2f position) {
        return tiles.get(position) != null;
    }

    @Override
    public Maybe<List<Maybe<IItem>>> removeTileItems(Vector2f position) {
        Maybe<ITile> maybeTile = Maybe.wrap(tiles.get(position));
        return maybeTile.chain(ITile::removeItems);
    }

    @Override
    public Maybe<IItem> removeTileItem(Vector2f position, int index) {
        Maybe<ITile> maybeTile = Maybe.wrap(tiles.get(position));
        return maybeTile.chain(t -> {
            Maybe<IItem> maybeItem = t.removeItem(index);
            return maybeItem.isJust() ? maybeItem.getValue() : null;
        });
    }

    @Override
    public void addBody(IBody body) {
        if (body == null || bodies.contains(body)) return;
        bodies.add(body);
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
    public void addTileItems(Vector2f position, List<Maybe<IItem>> items) {
        ITile tile = tiles.get(position);
        if (tile == null || items == null) {
            return;
        }
        tile.addItems(items);
    }

    @Override
    public void setPlayer(IBody player) {
        this.player = player;
    }

    @Override
    public void resetPlayer() {
        player.setPosition(startingPosition);
    }

    @Override
    public Maybe<IReadBody> readBody(Vector2f position) {
        return Maybe.wrap(accessBody(position));
    }


    private Maybe<IBody> accessBody(Vector2f position) {
        for (IBody body : bodies) {
            if (body.readPosition().equals(position)) {
                return new Just(body);
            }
        }
        return new Nothing();
    }

    @Override
    public Maybe<IReadBody> readPlayer() {
        return Maybe.wrap(player);
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
                if (!visitedNodes.contains(neighbor) && isTileValid(neighbor) && (!isTileOccupied(neighbor) || player.readPosition().equals(neighbor)))
                    frontier.push(new CameFrom(current, neighbor));
            }
            visitedNodes.add(current.position);
        }
        cost = result.size();
        cost--;
        Vector2f nextPos;
        if (cost > 0){
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

    public boolean isMovementAllowed(IReadBody body, Vector2f direction) {
        Vector2f position = body.readPosition();
        Vector2f destination = Vector2f.add(position, direction);

        if (isDirectionBlocked(body, direction)) {
            return false;
        }

        if (isTileOccupied(destination)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isDistanceFrom(IReadBody body, Vector2f destination, int longestDistance) {
        ArrayList<Vector2f> positions = new ArrayList<>();
        ArrayList<Vector2f> notAdded = new ArrayList<>();
        notAdded.add(body.readPosition());
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

    @Override
    public void updateDeadBody(Vector2f position){
        for(IBody body : bodies){
            if(body.readPosition().equals(position)){
                addTileItems(position,body.getInventory().dropItems());
                body.dropAllItems();
                return;
            }
        }
    }

    @Override
    public Maybe<IReadBody> readFacing(IReadBody body){
        Vector2f position = body.readPosition();
        Vector2f direction = body.readDirection();
        Vector2f destination = Vector2f.add(position, direction);

        if (isDirectionBlocked(body, direction)) {
            return new Nothing();
        }

        return readBody(destination);

    }

    @Override
    public IReadBody[] readBodies() {
        IReadBody[] result = new IReadBody[bodies.size()];
        for(int i = 0; i < bodies.size(); i++){
            result[i] = bodies.get(i);
        }
        return result;
    }

    @Override
    public Vector2f readStartingPoint() {
        return startingPosition;
    }

}
