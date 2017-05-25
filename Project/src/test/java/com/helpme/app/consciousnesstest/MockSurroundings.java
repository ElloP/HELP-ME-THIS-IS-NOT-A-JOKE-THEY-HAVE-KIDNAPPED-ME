package com.helpme.app.consciousnesstest;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.consciousness.ISurroundings;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.effect.ITarget;
import com.helpme.app.game.model.tile.ITile;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-05-14.
 */
public class MockSurroundings implements ISurroundings {
    public IBody mockTarget;
    public int tileItems;
    public boolean movementAllowed;

    public MockSurroundings(IBody mockTarget){
        this.mockTarget = mockTarget;
    }

    @Override
    public boolean addTileItem(Vector2f position, IItem item) {
        tileItems++;
        return true;
    }

    @Override
    public boolean addTileItems(Vector2f position, List<Maybe<IItem>> items) {
        return true;
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
    public void updateTile(Vector2f position) {

    }

    @Override
    public boolean isDirectionBlocked(IReadBody body, Vector2f direction) {
        return false;
    }

    @Override
    public boolean isMovementAllowed(IReadBody body, Vector2f direction) {
        return movementAllowed;
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
    public boolean isWithinRange(Vector2f position, Vector2f destination, int range) {
        return false;
    }

    @Override
    public Tuple3<List<Vector2f>, Vector2f, Integer> getPath(Vector2f from, Vector2f to) {
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
        return new Just(mockTarget);
    }

    @Override
    public List<IReadBody> readBodies() {
        return null;
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
