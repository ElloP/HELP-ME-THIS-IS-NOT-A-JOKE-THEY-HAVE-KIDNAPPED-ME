package com.helpme.app.leveltest;

import com.helpme.app.model.level.concrete.Level;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.model.body.IBody;
import com.helpme.app.model.body.IReadBody;
import com.helpme.app.model.item.effect.ITarget;
import com.helpme.app.model.level.ILevel;
import com.helpme.app.model.tile.ITile;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jesper on 2017-04-20.
 */
public class LevelTest {
    ILevel level;
    MockPlayer mockPlayer;
    MockBody mockBody;

    @Before
    public void setup() {
        Map<Vector2f, ITile> tiles = new HashMap<>();
        List<IBody> bodies = new ArrayList<>();
        mockBody = new MockBody();
        mockPlayer = new MockPlayer();

        tiles.put(Vector2f.ZERO, new MockTile());
        tiles.put(new Vector2f(0, 1), new MockTile());

        bodies.add(mockPlayer);
        bodies.add(mockBody);

        level = new Level(mockPlayer, Vector2f.ZERO, tiles, bodies);
    }

    @Test
    public void testDirectionBlockedFalse() {
        mockBody.traversable = true;
        assert (!level.isDirectionBlocked(mockBody, null));
    }


    @Test
    public void testDirectionBlockedTrue() {
        mockBody.traversable = false;
        assert (level.isDirectionBlocked(mockBody, null));
    }

    @Test
    public void testGetTargetEdge() {
        mockPlayer.traversable = false;
        Maybe<ITarget> target = level.getTarget(mockPlayer, Vector2f.ZERO);
        assert (target.getValue() instanceof MockEdge);
    }

    @Test
    public void testGetTargetBody() {
        mockPlayer.traversable = true;
        Maybe<ITarget> target = level.getTarget(mockPlayer, new Vector2f(0, 1));
        assert (target.getValue() instanceof MockBody);
    }

    @Test
    public void testGetTargetNothing() {
        mockPlayer.traversable = true;
        Maybe<ITarget> target = level.getTarget(mockPlayer, new Vector2f(1, 0));
        assert (target instanceof Nothing);
    }

    @Test
    public void testReadBodySuccess() {
        Maybe<IReadBody> body = level.readBody(new Vector2f(0, 1));
        assert (body.getValue() instanceof MockBody);
        body = level.readBody(new Vector2f(0, 0));
        assert (body.getValue() instanceof MockPlayer);
    }

    @Test
    public void testReadBodyFailure() {
        Maybe<IReadBody> body = level.readBody(new Vector2f(1, 0));
        assert (body instanceof Nothing);
    }

    @Test
    public void testReadPlayerSuccess() {
        Maybe<IReadBody> player = level.readPlayer();
        assert (player.getValue() instanceof MockPlayer);
    }

    @Test
    public void testSetPlayer() {
        level.setPlayer(mockBody);
        Maybe<IReadBody> player = level.readPlayer();
        assert (player.getValue() instanceof MockBody);
    }

    @Test
    public void testReadPlayerFailure() {
        level.setPlayer(null);
        Maybe<IReadBody> player = level.readPlayer();
        assert (player instanceof Nothing);
    }

    @Test
    public void testReadFacingSuccess() {
        mockBody.traversable = true;
        Maybe<IReadBody> facing = level.readFacing(mockBody);
        assert (facing.getValue() instanceof MockPlayer);
    }

    @Test
    public void testReadFacingFailure() {
        mockPlayer.traversable = true;
        Maybe<IReadBody> facing = level.readFacing(mockPlayer);
        assert (facing instanceof Nothing);
    }

    @Test
    public void testTileValid() {
        assert (level.isTileValid(new Vector2f(0, 1)));
    }

    @Test
    public void testTileInvalid() {
        assert (!level.isTileValid(new Vector2f(1, 0)));
    }

    @Test
    public void testTileOccupied() {
        assert (level.isTileOccupied(new Vector2f(0, 1)));
    }

    @Test
    public void testTileVacant() {
        assert (!level.isTileOccupied(new Vector2f(1, 0)));
        mockBody.dead = true;
        assert (!level.isTileOccupied(new Vector2f(0, 1)));
    }
}
