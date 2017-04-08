package com.helpme.app;

import com.helpme.app.character.IMonster;
import com.helpme.app.character.Monster;
import com.helpme.app.item.Item;
import com.helpme.app.tile.edge.Door;
import com.helpme.app.utils.Tuple.Tuple3;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.Vector4f;
import com.helpme.app.world.Level;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class AppTest {
    public Level testLevel;

    @Before
    public void setUp() {
        List<Vector2f> tiles = new ArrayList<>();
        List<Tuple3<Vector2f, Vector2f, Door>> doors = new ArrayList<>();
        List<IMonster> monsters = new ArrayList<>();
        IMonster player = new Monster(new Item[]{new Item("key")}, Vector2f.zero, Vector2f.up);
        IMonster enemy = new Monster(null, new Vector2f(2, 2), Vector2f.down);

        tiles.add(new Vector2f(0, 0));
        tiles.add(new Vector2f(1, 0));
        tiles.add(new Vector2f(2, 0));
        tiles.add(new Vector2f(1, 1));
        tiles.add(new Vector2f(2, 1));
        tiles.add(new Vector2f(3, 1));
        tiles.add(new Vector2f(1, 2));
        tiles.add(new Vector2f(2, 2));
        tiles.add(new Vector2f(3, 2));
        tiles.add(new Vector2f(1, 2));
        tiles.add(new Vector2f(2, 3));
        tiles.add(new Vector2f(3, 3));

        tiles.add(new Vector2f(5, 5));

        tiles.add(new Vector2f(6, 2));
        tiles.add(new Vector2f(7, 2));
        tiles.add(new Vector2f(8, 2));
        tiles.add(new Vector2f(9, 2));
        tiles.add(new Vector2f(10, 2));

        doors.add(new Tuple3<>(new Vector2f(6,2), Vector2f.right, new Door(true, null)));
        doors.add(new Tuple3<>(new Vector2f(8,2), Vector2f.left, new Door(false, null)));
        doors.add(new Tuple3<>(new Vector2f(8,2), Vector2f.right, new Door(true, new Item("key"))));

        /**
         *         []
         *
         *   [][][]
         *   [][][]  [|[]/]|][]
         *   [][][]
         * [][][]
         */


        monsters.add(enemy);
        testLevel = new Level(tiles, doors, monsters, player, Vector2f.zero);
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
        testLevel.setPlayerPosition(tileSingle);
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
        testLevel.setPlayerPosition(tileStart);
        testLevel.movePlayerForward();
        assert (testLevel.getPlayer().getPosition().equals(tileStart));
    }

    @Test
    public void testWalkAroundMonster(){
        Vector2f tileStart = new Vector2f(2,1);
        Vector2f tileTo = new Vector2f(2,3);
        testLevel.setPlayerPosition(tileStart);
        testLevel.movePlayerRight();
        testLevel.movePlayerForward();
        testLevel.movePlayerForward();
        testLevel.movePlayerLeft();
        assert (testLevel.getPlayer().getPosition().equals(tileTo));
    }

    @Test
    public void testWalkThroughUnlockedDoor(){
        Vector2f tileStart = new Vector2f(7,2);
        Vector2f tileTo = new Vector2f(8,2);
        testLevel.setPlayerPosition(tileStart);
        testLevel.rotatePlayerRight();
        testLevel.movePlayerForward();
        assert (testLevel.getPlayer().getPosition().equals(tileTo));
    }

    @Test
    public void testBlockedByLockedDoor(){
        Vector2f tileStart = new Vector2f(7,2);
        testLevel.setPlayerPosition(tileStart);
        testLevel.rotatePlayerLeft();
        testLevel.movePlayerForward();
        assert (testLevel.getPlayer().getPosition().equals(tileStart));
    }

    @Test
    public void testUnlockDoorAndWalkThrough(){
        Vector2f tileStart = new Vector2f(8,2);
        Vector2f tileTo = new Vector2f(9,2);
        testLevel.setPlayerPosition(tileStart);
        testLevel.rotatePlayerRight();
        testLevel.movePlayerForward();
        testLevel.movePlayerForward();
        assert (testLevel.getPlayer().getPosition().equals(tileTo));
    }
}
