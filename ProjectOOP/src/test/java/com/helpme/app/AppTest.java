package com.helpme.app;

import com.helpme.app.character.Monster;
import com.helpme.app.tile.Tile;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.Level;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;

/**
 * Unit test for simple App.
 */

public class AppTest
        extends TestCase {
    public Level testLevel;

    @BeforeEach
    public void init(){
        Map<Vector2f, Tile> tiles = new HashMap<>();
        tiles.put(new Vector2f(0,0), Tile.empty());
        tiles.put(new Vector2f(1,0), Tile.empty());
        tiles.put(new Vector2f(2,0), Tile.empty());
        tiles.put(new Vector2f(2,1), Tile.empty());
        tiles.put(new Vector2f(2,2), Tile.empty());
        tiles.put(new Vector2f(2,3), Tile.empty());
        Monster player = new Monster(null, Vector2f.zero, Vector2f.up);
        Monster enemy = new Monster(null, new Vector2f(2,2), Vector2f.down);
        List<Monster> monsters = new ArrayList<>();
        monsters.add(enemy);
        testLevel = new Level(tiles, monsters, player, Vector2f.zero);
    }

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     * <p>
     * public static Test suite()
     * {
     * return new TestSuite( AppTest.class );
     * }
     * <p>
     * /**
     * Rigourous Test :-)
     */
    public void testRotateRight() {
        testLevel.movePlayerRight();
        assert(testLevel.getPlayer().getDirection().equals(Vector2f.right));
    }
}
