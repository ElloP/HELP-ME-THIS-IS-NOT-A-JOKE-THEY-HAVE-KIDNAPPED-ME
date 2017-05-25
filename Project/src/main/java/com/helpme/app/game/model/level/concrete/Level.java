package com.helpme.app.game.model.level.concrete;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.effect.ITarget;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.game.model.tile.ITile;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple3;

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
    }


    public boolean isDirectionBlocked(IReadBody body, Vector2f direction) {
        ITile tile = tiles.get(body.readPosition());
        return tile.getEdge(direction).check(e -> !body.traverse(e));
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
    public boolean addBody(IBody body) {
        if (body == null ||
                bodies.contains(body) ||
                !isTileValid(body.readPosition()) ||
                isTileOccupied(body.readPosition()))
        {
            return false;
        }
        bodies.add(body);
        return true;
    }

    @Override
    public boolean addTileItem(Vector2f position, IItem item) {
        ITile tile = tiles.get(position);
        if (tile == null || item == null) {
            return false;
        }
        tile.addItem(item);
        return true;
    }

    @Override
    public boolean addTileItems(Vector2f position, List<Maybe<IItem>> items) {
        ITile tile = tiles.get(position);
        if (tile == null || items == null) {
            return false;
        }
        tile.addItems(items);
        return true;
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


    @Override
    public boolean isWithinRange(Vector2f position, Vector2f destination, int range) {
        ArrayList<Vector2f> positions = new ArrayList<>();
        ArrayList<Vector2f> notAdded = new ArrayList<>();
        notAdded.add(position);
        for (int i = 1; i <= range; i++) {
            ArrayList<Vector2f> temp = new ArrayList<>();
            for (Vector2f pos : notAdded) {
                temp.addAll(Arrays.asList(Vector2f.getNeighbours(pos)));
                positions.add(pos);
            }
            notAdded.clear();
            notAdded.addAll(temp);
        }
        positions.addAll(notAdded);
        return positions.contains(destination);
    }

    private static class Link {
        Link previous;
        Vector2f position;

        public Link(Link previous, Vector2f position) {
            this.previous = previous;
            this.position = position;
        }
    }

    //TODO (Jesper): Take edges and doors into consideration
    @Override
    public Tuple3<List<Vector2f>, Vector2f, Integer> getPath(Vector2f from, Vector2f to) {
        Link current;
        ArrayList<Vector2f> result = new ArrayList<>();
        ArrayList<Vector2f> visitedNodes = new ArrayList<>();
        Stack<Link> frontier = new Stack<>();
        int cost;
        frontier.push(new Link(null, from));
        while (!frontier.isEmpty()) {
            current = frontier.pop();
            if (current.position.equals(to)) {
                result = recreatePath(current);
                break;
            }

            Vector2f[] neighbours = Vector2f.getNeighbours(current.position);
            for (Vector2f neighbour : neighbours) {
                if (!visitedNodes.contains(neighbour) && isTileValid(neighbour) && (!isTileOccupied(neighbour) || player.readPosition().equals(neighbour)))
                    frontier.push(new Link(current, neighbour));
            }
            visitedNodes.add(current.position);
        }
        cost = result.size();
        cost--;
        Vector2f nextPos;
        if (cost > 0) {
            nextPos = result.get(1);
        } else {
            nextPos = from;
        }
        return new Tuple3<>(result, nextPos, cost);
    }


    private ArrayList<Vector2f> recreatePath(Link current) {
        ArrayList<Vector2f> result = new ArrayList<>();
        result.add(current.position);
        while (current.previous != null) {
            current = current.previous;
            result.add(current.position);
        }
        Collections.reverse(result);
        return result;
    }

    public boolean isMovementAllowed(IReadBody body, Vector2f direction) {
        Vector2f position = body.readPosition();
        Vector2f destination = Vector2f.add(position, direction);

        return !isDirectionBlocked(body, direction) && !isTileOccupied(destination);
    }



    @Override
    public void updateTile(Vector2f position) {
        List<IBody> remove = new ArrayList<>();
        for (IBody body : bodies) {
            if (body.readPosition().equals(position) && body.isDead()) {
                addTileItems(position, body.getInventory().dropItems());
                remove.add(body);
            }
        }
        bodies.removeAll(remove);
    }

    @Override
    public Maybe<IReadBody> readFacing(IReadBody body) {
        Vector2f position = body.readPosition();
        Vector2f direction = body.readDirection();
        Vector2f destination = Vector2f.add(position, direction);

        if (isDirectionBlocked(body, direction)) {
            return new Nothing<>();
        }

        return readBody(destination);

    }

    @Override
    public List<IReadBody> readBodies() {
        return new ArrayList<>(bodies);
    }

    @Override
    public Vector2f readStartingPoint() {
        return startingPosition;
    }

}
