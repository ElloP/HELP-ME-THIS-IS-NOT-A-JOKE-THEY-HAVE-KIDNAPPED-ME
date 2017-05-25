package com.helpme.app.leveltest;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.item.effect.ITarget;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.game.model.level.concrete.Level;
import com.helpme.app.game.model.tile.ITile;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
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
    private ILevel level;
    private MockPlayer mockPlayer;
    private MockBody mockBody;

    @Before
    public void setup() {
        Map<Vector2f, ITile> tiles = new HashMap<>();
        List<IBody> bodies = new ArrayList<>();
        mockBody = new MockBody();
        mockPlayer = new MockPlayer();

        tiles.put(Vector2f.ZERO, new MockTile());
        tiles.put(Vector2f.NORTH, new MockTile());
        tiles.put(Vector2f.EAST, new MockTile());

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
        Maybe<ITarget> target = level.getTarget(mockPlayer, Vector2f.NORTH);
        assert (target.getValue() instanceof MockBody);
    }

    @Test
    public void testGetTargetNothing() {
        mockPlayer.traversable = true;
        Maybe<ITarget> target = level.getTarget(mockPlayer, Vector2f.EAST);
        assert (target instanceof Nothing);
    }

    @Test
    public void testReadFacing() {
        mockBody.traversable = true;
        Maybe<IReadBody> facing = level.readFacing(mockBody);
        assert (facing.getValue() instanceof MockPlayer);
    }

    @Test
    public void testReadFacingNoBody() {
        mockPlayer.traversable = true;
        Maybe<IReadBody> facing = level.readFacing(mockPlayer);
        assert (facing instanceof Nothing);
    }

    @Test
    public void testReadFacingDirectionBlocked() {
        mockPlayer.traversable = false;
        Maybe<IReadBody> facing = level.readFacing(mockBody);
        assert (facing instanceof Nothing);
    }

    @Test
    public void testIsMovementAllowedAllowed() {
        mockPlayer.traversable = true;
        assert (level.isMovementAllowed(mockPlayer, Vector2f.EAST));
    }

    @Test
    public void testIsMovementAllowedDirectionBlocked() {
        mockPlayer.traversable = false;
        assert (!level.isMovementAllowed(mockPlayer, Vector2f.EAST));
    }

    @Test
    public void testIsMovementAllowedTileOccupied() {
        mockPlayer.traversable = true;
        assert (!level.isMovementAllowed(mockPlayer, Vector2f.NORTH));
    }


    @Test
    public void testIsWithinRange() {
        assert (level.isWithinRange(Vector2f.ZERO, new Vector2f(1, 0), 2) &&
                level.isWithinRange(Vector2f.ZERO, new Vector2f(-1, 0), 2) &&
                level.isWithinRange(Vector2f.ZERO, new Vector2f(0, 1), 2) &&
                level.isWithinRange(Vector2f.ZERO, new Vector2f(0, -1), 2) &&
                level.isWithinRange(Vector2f.ZERO, new Vector2f(1, 1), 2) &&
                level.isWithinRange(Vector2f.ZERO, new Vector2f(-1, 1), 2) &&
                level.isWithinRange(Vector2f.ZERO, new Vector2f(1, -1), 2) &&
                level.isWithinRange(Vector2f.ZERO, new Vector2f(-1, -1), 2) &&
                level.isWithinRange(Vector2f.ZERO, new Vector2f(2, 0), 2) &&
                level.isWithinRange(Vector2f.ZERO, new Vector2f(-2, 0), 2) &&
                level.isWithinRange(Vector2f.ZERO, new Vector2f(0, 2), 2) &&
                level.isWithinRange(Vector2f.ZERO, new Vector2f(0, -2), 2));
    }

    @Test
    public void testIsNotWithinRange() {
        assert (!level.isWithinRange(Vector2f.ZERO, new Vector2f(1, 1), 1) &&
                !level.isWithinRange(Vector2f.ZERO, new Vector2f(-1, 1), 1) &&
                !level.isWithinRange(Vector2f.ZERO, new Vector2f(1, -1), 1) &&
                !level.isWithinRange(Vector2f.ZERO, new Vector2f(-1, -1), 1));
    }

    @Test
    public void testReadStartingPosition() {
        assert (level.readStartingPoint().equals(Vector2f.ZERO));
    }
}
