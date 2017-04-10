package com.helpme.app;

import com.helpme.app.character.IInventory;
import com.helpme.app.character.IMonster;
import com.helpme.app.character.Inventory;
import com.helpme.app.character.Monster;
import com.helpme.app.item.*;
import com.helpme.app.tile.edge.Door;
import com.helpme.app.utils.Tuple.Tuple2;
import com.helpme.app.utils.Tuple.Tuple3;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.Vector4f;
import com.helpme.app.world.ILevel;
import com.helpme.app.world.Level;
import com.helpme.app.world.PlayerController;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class AppTest {
    public PlayerController testPlayerController;

    @Before
    public void setUp() {
        List<Tuple2<Vector2f, List<IItem>>> tiles = new ArrayList<>();
        List<Tuple3<Vector2f, Vector2f, Door>> doors = new ArrayList<>();
        List<IMonster> monsters = new ArrayList<>();

        IInventory inventory = new Inventory(new IItem[]{IItemFactory.club()}, IItemFactory.fists(), new ArrayList<>());

        IMonster player = new Monster(inventory, Vector2f.zero, Vector2f.up);
        IMonster enemy = new Monster(null, new Vector2f(2, 2), Vector2f.down);

        inventory.addKey(IKeyFactory.redKey());

        tiles.add(new Tuple2<>(new Vector2f(0, 0), null));
        tiles.add(new Tuple2<>(new Vector2f(1, 0), null));
        tiles.add(new Tuple2<>(new Vector2f(2, 0), null));
        tiles.add(new Tuple2<>(new Vector2f(1, 1), null));
        tiles.add(new Tuple2<>(new Vector2f(2, 1), null));
        tiles.add(new Tuple2<>(new Vector2f(3, 1), null));
        tiles.add(new Tuple2<>(new Vector2f(1, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(2, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(3, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(1, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(2, 3), null));
        tiles.add(new Tuple2<>(new Vector2f(3, 3), null));

        tiles.add(new Tuple2<>(new Vector2f(5, 5), null));

        tiles.add(new Tuple2<>(new Vector2f(6, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(7, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(8, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(9, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(10, 2), null));

        doors.add(new Tuple3<>(new Vector2f(6, 2), Vector2f.right, new Door(true, null)));
        doors.add(new Tuple3<>(new Vector2f(8, 2), Vector2f.left, new Door(false, null)));
        doors.add(new Tuple3<>(new Vector2f(8, 2), Vector2f.right, new Door(true, IKeyFactory.redKey())));

        /**
         *         []
         *
         *   [][][]
         *   [][][]  [|[]/]|][]
         *   [][][]
         * [][][]
         */


        monsters.add(enemy);
        ILevel level = new Level(tiles, doors, monsters, player, Vector2f.zero);
        testPlayerController = new PlayerController(player, level);
    }

    @Test
    public void testRotateRight() {
        testPlayerController.rotatePlayerRight();
        assert (testPlayerController.getPlayer().getDirection().equals(Vector2f.right));
        testPlayerController.rotatePlayerRight();
        assert (testPlayerController.getPlayer().getDirection().equals(Vector2f.down));
        testPlayerController.rotatePlayerRight();
        assert (testPlayerController.getPlayer().getDirection().equals(Vector2f.left));
        testPlayerController.rotatePlayerRight();
        assert (testPlayerController.getPlayer().getDirection().equals(Vector2f.up));
    }

    @Test
    public void testRotateLeft() {
        testPlayerController.rotatePlayerLeft();
        assert (testPlayerController.getPlayer().getDirection().equals(Vector2f.left));
        testPlayerController.rotatePlayerLeft();
        assert (testPlayerController.getPlayer().getDirection().equals(Vector2f.down));
        testPlayerController.rotatePlayerLeft();
        assert (testPlayerController.getPlayer().getDirection().equals(Vector2f.right));
        testPlayerController.rotatePlayerLeft();
        assert (testPlayerController.getPlayer().getDirection().equals(Vector2f.up));
    }

    @Test
    public void testBlockedByWall() {
        Vector2f tileSingle = new Vector2f(5, 5);
        testPlayerController.setPlayerPosition(tileSingle);
        testPlayerController.movePlayerForward();
        assert (testPlayerController.getPlayer().getPosition().equals(tileSingle));
        testPlayerController.movePlayerRight();
        assert (testPlayerController.getPlayer().getPosition().equals(tileSingle));
        testPlayerController.movePlayerBackward();
        assert (testPlayerController.getPlayer().getPosition().equals(tileSingle));
        testPlayerController.movePlayerLeft();
        assert (testPlayerController.getPlayer().getPosition().equals(tileSingle));
    }

    @Test
    public void testMoveThroughOpening() {
        Vector2f tileTo = new Vector2f(2, 1);

        testPlayerController.rotatePlayerRight();
        testPlayerController.movePlayerForward();
        testPlayerController.movePlayerForward();
        testPlayerController.rotatePlayerLeft();
        testPlayerController.movePlayerForward();
        assert (testPlayerController.getPlayer().getPosition().equals(tileTo));
    }

    @Test
    public void testBlockedByMonster() {
        Vector2f tileStart = new Vector2f(2, 1);
        testPlayerController.setPlayerPosition(tileStart);
        testPlayerController.movePlayerForward();
        assert (testPlayerController.getPlayer().getPosition().equals(tileStart));
    }

    @Test
    public void testWalkAroundMonster() {
        Vector2f tileStart = new Vector2f(2, 1);
        Vector2f tileTo = new Vector2f(2, 3);
        testPlayerController.setPlayerPosition(tileStart);
        testPlayerController.movePlayerRight();
        testPlayerController.movePlayerForward();
        testPlayerController.movePlayerForward();
        testPlayerController.movePlayerLeft();
        assert (testPlayerController.getPlayer().getPosition().equals(tileTo));
    }

    @Test
    public void testWalkThroughUnlockedDoor() {
        Vector2f tileStart = new Vector2f(7, 2);
        Vector2f tileTo = new Vector2f(8, 2);
        testPlayerController.setPlayerPosition(tileStart);
        testPlayerController.rotatePlayerRight();
        testPlayerController.movePlayerForward();
        assert (testPlayerController.getPlayer().getPosition().equals(tileTo));
    }

    @Test
    public void testBlockedByLockedDoor() {
        Vector2f tileStart = new Vector2f(7, 2);
        testPlayerController.setPlayerPosition(tileStart);
        testPlayerController.rotatePlayerLeft();
        testPlayerController.movePlayerForward();
        assert (testPlayerController.getPlayer().getPosition().equals(tileStart));
    }

    @Test
    public void testUnlockDoorAndWalkThrough() {
        Vector2f tileStart = new Vector2f(8, 2);
        Vector2f tileTo = new Vector2f(9, 2);
        testPlayerController.setPlayerPosition(tileStart);
        testPlayerController.rotatePlayerRight();
        testPlayerController.movePlayerForward();
        testPlayerController.movePlayerForward();
        assert (testPlayerController.getPlayer().getPosition().equals(tileTo));
    }
}
