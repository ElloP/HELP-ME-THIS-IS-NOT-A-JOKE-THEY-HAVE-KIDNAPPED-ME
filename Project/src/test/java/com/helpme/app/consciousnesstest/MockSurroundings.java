package com.helpme.app.consciousnesstest;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.character.target.ITarget;
import com.helpme.app.world.consciousness.ISurroundings;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.tile.ITile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-05-14.
 */
public class MockSurroundings implements ISurroundings {
    public IBody mockTarget;
    public int tileItems;

    public MockSurroundings(IBody mockTarget){

    }

    @Override
    public void addTileItem(Vector2f position, IItem item) {
        tileItems++;
    }

    @Override
    public void addTileItems(Vector2f position, List<Maybe<IItem>> items) {

    }

    @Override
    public Maybe<List<Maybe<IItem>>> removeTileItems(Vector2f position) {
        Maybe<List<Maybe<IItem>>> maybeItemList = Maybe.wrap(new ArrayList<Maybe<IItem>>(){
            {
                for(int i = 0; i < tileItems; i++){
                    add(Maybe.wrap(new MockItem("")));
                }
            }
        });
        tileItems = 0;
        return maybeItemList;
    }

    @Override
    public Maybe<IItem> removeTileItem(Vector2f position, int index) {
        tileItems--;
        return new Just(new MockItem(""));
    }

    @Override
    public void updateDeadBody(Vector2f position) {

    }

    @Override
    public boolean isBlockedByEdge(IReadBody body, Vector2f direction) {
        return false;
    }

    @Override
    public boolean isMovementAllowed(IReadBody body, Vector2f direction) {
        return false;
    }

    @Override
    public boolean isTileOccupied(Vector2f position) {
        return false;
    }

    @Override
    public boolean isTileValid(Vector2f position) {
        return false;
    }

    @Override
    public boolean isDistanceFrom(IReadBody body, Vector2f destination, int longestDistance) {
        return false;
    }

    @Override
    public Tuple3<List<Vector2f>, Vector2f, Integer> getShortestPath(Vector2f from, Vector2f to) {
        return null;
    }

    @Override
    public Maybe<IReadBody> readPlayer() {
        return null;
    }

    @Override
    public Maybe<IReadBody> readBody(Vector2f position) {
        return null;
    }

    @Override
    public Maybe<ITarget> getTarget(IBody body, Vector2f direction) {
        return new Just(mockTarget);
    }

    @Override
    public Maybe<IReadBody> readFacing(IReadBody body) {
        return null;
    }

    @Override
    public IReadBody[] readMonsters() {
        return new IReadBody[0];
    }

    @Override
    public Vector2f readStartingPoint() {
        return null;
    }

    @Override
    public Map<Vector2f, ITile> getTiles() {
        return null;
    }
}
