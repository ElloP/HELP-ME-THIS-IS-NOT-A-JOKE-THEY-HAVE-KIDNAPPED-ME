package com.helpme.app;

import com.helpme.app.character.Monster;
import com.helpme.app.tile.Tile;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.Level;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppTest {
    public Level testLevel;

    @Before
    public void setUp() {
        Map<Vector2f, Tile> tiles = new HashMap<>();
        tiles.put(new Vector2f(0, 0), Tile.empty());
        tiles.put(new Vector2f(1, 0), Tile.empty());
        tiles.put(new Vector2f(2, 0), Tile.empty());
        tiles.put(new Vector2f(1, 1), Tile.empty());
        tiles.put(new Vector2f(2, 1), Tile.empty());
        tiles.put(new Vector2f(3, 1), Tile.empty());
        tiles.put(new Vector2f(1, 2), Tile.empty());
        tiles.put(new Vector2f(2, 2), Tile.empty());
        tiles.put(new Vector2f(3, 2), Tile.empty());
        tiles.put(new Vector2f(1, 2), Tile.empty());
        tiles.put(new Vector2f(2, 3), Tile.empty());
        tiles.put(new Vector2f(3, 3), Tile.empty());
        tiles.put(new Vector2f(5, 5), Tile.empty());

        /**
         *         []
         *
         *   [][][]
         *   [][][]
         *   [][][]
         * [][][]
         */

        Monster player = new Monster(null, Vector2f.zero, Vector2f.up);
        Monster enemy = new Monster(null, new Vector2f(2, 2), Vector2f.down);
        List<Monster> monsters = new ArrayList<>();
        monsters.add(enemy);
        testLevel = new Level(tiles, monsters, player, Vector2f.zero);
    }

    @Test
    public void testRotateRight() {
        testLevel.rotatePlayerRight();
        assert (testLevel.getPlayer().getDirection().equals(Vector2f.right));
        testLevel.rotatePlayerRight();
        assert (testLevel.getPlayer().getDirection().equals(Vector2f.down));
        testLevel.rotatePlayerRight();
        assert (testLevel.getPlayer().getDirection().equals(Vector2f.left));
        testLevel.rotatePlayerRight();
        assert (testLevel.getPlayer().getDirection().equals(Vector2f.up));
    }

    @Test
    public void testRotateLeft() {
        testLevel.rotatePlayerLeft();
        assert (testLevel.getPlayer().getDirection().equals(Vector2f.left));
        testLevel.rotatePlayerLeft();
        assert (testLevel.getPlayer().getDirection().equals(Vector2f.down));
        testLevel.rotatePlayerLeft();
        assert (testLevel.getPlayer().getDirection().equals(Vector2f.right));
        testLevel.rotatePlayerLeft();
        assert (testLevel.getPlayer().getDirection().equals(Vector2f.up));
    }

    @Test
    public void testBlockedByWall() {
        Vector2f tileSingle = new Vector2f(5, 5);
        testLevel.teleportPlayer(tileSingle);
        testLevel.movePlayerForward();
        assert (testLevel.getPlayer().getPosition().equals(tileSingle));
        testLevel.movePlayerRight();
        assert (testLevel.getPlayer().getPosition().equals(tileSingle));
        testLevel.movePlayerBackward();
        assert (testLevel.getPlayer().getPosition().equals(tileSingle));
        testLevel.movePlayerLeft();
        assert (testLevel.getPlayer().getPosition().equals(tileSingle));
    }

    @Test
    public void testMoveThroughOpening() {
        Vector2f tileTo = new Vector2f(2, 1);

        testLevel.rotatePlayerRight();
        testLevel.movePlayerForward();
        testLevel.movePlayerForward();
        testLevel.rotatePlayerLeft();
        testLevel.movePlayerForward();
        assert (testLevel.getPlayer().getPosition().equals(tileTo));
    }

    @Test
    public void testBlockedByMonster(){
        Vector2f tileStart = new Vector2f(2,1);
        testLevel.teleportPlayer(tileStart);
        testLevel.movePlayerForward();
        assert (testLevel.getPlayer().getPosition().equals(tileStart));
    }

    @Test
    public void testWalkAroundMonster(){
        Vector2f tileStart = new Vector2f(2,1);
        Vector2f tileTo = new Vector2f(2,3);
        testLevel.teleportPlayer(tileStart);
        testLevel.movePlayerRight();
        testLevel.movePlayerForward();
        testLevel.movePlayerForward();
        testLevel.movePlayerLeft();
        assert (testLevel.getPlayer().getPosition().equals(tileTo));
    }
}
