package com.helpme.app.leveltest;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.body.IReadBody;
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
 * Created by kopa on 2017-05-23.
 */
public class LevelBodyTest {
    private ILevel level;
    private MockPlayer mockPlayer;
    private MockBody mockBody;
    private MockTile mockTile;

    @Before
    public void setup() {
        Map<Vector2f, ITile> tiles = new HashMap<>();
        List<IBody> bodies = new ArrayList<>();
        mockBody = new MockBody();
        mockPlayer = new MockPlayer();
        mockTile = new MockTile();

        tiles.put(Vector2f.ZERO, new MockTile());
        tiles.put(Vector2f.NORTH, new MockTile());
        tiles.put(Vector2f.EAST, mockTile);

        bodies.add(mockPlayer);
        bodies.add(mockBody);

        level = new Level(mockPlayer, Vector2f.ZERO, tiles, bodies);
    }

    @Test
    public void testAddBody() {
        MockBody newBody = new MockBody();
        newBody.position = Vector2f.EAST;
        assert (level.addBody(newBody) && level.readBodies().contains(newBody));
    }

    @Test
    public void testAddBodyInvalidTile() {
        MockBody newBody = new MockBody();
        newBody.position = new Vector2f(8, 8);
        assert (!level.addBody(newBody) && !level.readBodies().contains(newBody));
    }

    @Test
    public void testAddBodyOccupiedTile() {
        MockBody newBody = new MockBody();
        newBody.position = Vector2f.NORTH;
        assert (!level.addBody(newBody) && !level.readBodies().contains(newBody));
    }

    @Test
    public void testReadBodies() {
        List<IReadBody> bodies = level.readBodies();
        assert (bodies.contains(mockBody) && bodies.contains(mockPlayer));
    }

    @Test
    public void testReadBodySuccess() {
        Maybe<IReadBody> body = level.readBody(Vector2f.NORTH);
        assert (body.getValue() instanceof MockBody);
        body = level.readBody(new Vector2f(0, 0));
        assert (body.getValue() instanceof MockPlayer);
    }

    @Test
    public void testReadBodyFailure() {
        Maybe<IReadBody> body = level.readBody(Vector2f.EAST);
        assert (body instanceof Nothing);
    }

    @Test
    public void testReadPlayerSuccess() {
        Maybe<IReadBody> player = level.readPlayer();
        assert (player.getValue() instanceof MockPlayer);
    }

    @Test
    public void testReadPlayerFailure() {
        level.setPlayer(null);
        Maybe<IReadBody> player = level.readPlayer();
        assert (player instanceof Nothing);
    }

    @Test
    public void testSetPlayer() {
        level.setPlayer(mockBody);
        Maybe<IReadBody> player = level.readPlayer();
        assert (player.getValue() instanceof MockBody);
    }

    @Test
    public void testResetPlayer() {
        mockPlayer.setPosition(new Vector2f(8, 8));
        level.resetPlayer();
        assert (mockPlayer.readPosition().equals(Vector2f.ZERO));
    }
}
