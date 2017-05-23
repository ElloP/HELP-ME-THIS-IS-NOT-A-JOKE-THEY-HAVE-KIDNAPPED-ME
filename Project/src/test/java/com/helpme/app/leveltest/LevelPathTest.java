package com.helpme.app.leveltest;

import com.helpme.app.model.body.IBody;
import com.helpme.app.model.level.ILevel;
import com.helpme.app.model.level.concrete.Level;
import com.helpme.app.model.tile.ITile;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.tuple.Tuple3;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-05-23.
 */
public class LevelPathTest {
    private ILevel level;
    private MockPlayer mockPlayer;
    private MockBody mockBody;

    @Before
    public void setup() {
        Map<Vector2f, ITile> tiles = new HashMap<>();
        List<IBody> bodies = new ArrayList<>();
        mockBody = new MockBody();
        mockPlayer = new MockPlayer();

        tiles.put(new Vector2f(-2, 1), new MockTile());
        tiles.put(new Vector2f(-2, 0), new MockTile());
        tiles.put(new Vector2f(-1, 0), new MockTile());
        tiles.put(new Vector2f(0, 0), new MockTile());
        tiles.put(new Vector2f(1, 0), new MockTile());
        tiles.put(new Vector2f(2, 0), new MockTile());
        tiles.put(new Vector2f(3, 0), new MockTile());
        tiles.put(new Vector2f(4, 0), new MockTile());
        tiles.put(new Vector2f(3, 1), new MockTile());
        tiles.put(new Vector2f(4, 1), new MockTile());
        tiles.put(new Vector2f(5, 1), new MockTile());
        tiles.put(new Vector2f(5, 0), new MockTile());
        tiles.put(new Vector2f(6, 0), new MockTile());
        tiles.put(new Vector2f(6, -1), new MockTile());
        tiles.put(new Vector2f(6, -2), new MockTile());
        tiles.put(new Vector2f(6, -3), new MockTile());
        tiles.put(new Vector2f(6, -4), new MockTile());
        tiles.put(new Vector2f(6, -5), new MockTile());
        tiles.put(new Vector2f(7, -3), new MockTile());
        tiles.put(new Vector2f(8, -3), new MockTile());
        tiles.put(new Vector2f(8, -4), new MockTile());
        tiles.put(new Vector2f(8, -5), new MockTile());
        tiles.put(new Vector2f(7, -5), new MockTile());

        bodies.add(mockBody);
        mockBody.position = new Vector2f(4, 0);

        level = new Level(mockPlayer, Vector2f.ZERO, tiles, bodies);
    }

    @Test
    public void testGetPathSuccess() {
        Tuple3<List<Vector2f>, Vector2f, Integer> path = level.getPath(Vector2f.ZERO, new Vector2f(6, -5));
        List<Vector2f> correctPath = new ArrayList<>();
        correctPath.add(new Vector2f(0,0));
        correctPath.add(new Vector2f(1,0));
        correctPath.add(new Vector2f(2,0));
        correctPath.add(new Vector2f(3,0));
        correctPath.add(new Vector2f(3,1));
        correctPath.add(new Vector2f(4,1));
        correctPath.add(new Vector2f(5,1));
        correctPath.add(new Vector2f(5,0));
        correctPath.add(new Vector2f(6,0));
        correctPath.add(new Vector2f(6,-1));
        correctPath.add(new Vector2f(6,-2));
        correctPath.add(new Vector2f(6,-3));
        correctPath.add(new Vector2f(6,-4));
        correctPath.add(new Vector2f(6,-5));

        assert (path.a.equals(correctPath) && path.b.equals(correctPath.get(1)) && path.c == 13);
    }

    @Test
    public void testGetPathFail(){
        mockBody.position = new Vector2f(3,0);
        Tuple3<List<Vector2f>, Vector2f, Integer> path = level.getPath(Vector2f.ZERO, new Vector2f(6, -5));
        System.out.println(path.a.isEmpty() && path.b.equals(Vector2f.ZERO) && path.c == 0);
    }
}
