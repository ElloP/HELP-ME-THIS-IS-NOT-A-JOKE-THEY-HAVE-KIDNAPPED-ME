package com.helpme.app;

import com.helpme.app.character.IInventory;
import com.helpme.app.character.IMonster;
import com.helpme.app.character.Inventory;
import com.helpme.app.character.Monster;
import com.helpme.app.item.*;
import com.helpme.app.item.effect.IEffectFactory;
import com.helpme.app.tile.edge.Door;
import com.helpme.app.utils.Tuple.Tuple2;
import com.helpme.app.utils.Tuple.Tuple3;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.ILevel;
import com.helpme.app.world.Level;
import com.helpme.app.world.PlayerController;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class AppTest {
    public PlayerController testPlayerController;
    public ILevel testLevel;

    @Before
    public void setUp() {
        List<Tuple2<Vector2f, IItem[]>> tiles = new ArrayList<>();
        List<Tuple3<Vector2f, Vector2f, Door>> doors = new ArrayList<>();
        List<IMonster> monsters = new ArrayList<>();

        IItem mockWeapon = new Item("Club", IEffectFactory.damage(10), IEffectFactory.damage(5));
        IItem mockPotion = new Consumable("Healing Potion", 1, IEffectFactory.heal(9), IEffectFactory.heal(9));
        IItem defaultMockWeapon = new Item("Fists", IEffectFactory.damage(2), IEffectFactory.damage(1));
        IItem mockPickupWeapon = new Item("Sword", IEffectFactory.damage(40), IEffectFactory.damage(10));
        IItem excessiveItem = new Item("Box", stats -> {
        }, stats -> {
        });
        IItem stackingConsumables = new Consumable("Paper", 2, stats -> {
        }, stats -> {
        });

        IItem mockKey = new Key("Red Key");
        IItem mockPickupKey = new Key("Blue Key");

        IInventory inventory = new Inventory(new IItem[]{mockWeapon, mockPotion, null, null}, defaultMockWeapon, new IItem[]{mockKey});

        IMonster player = new Monster(inventory, Vector2f.zero, Vector2f.up, 100);
        IMonster enemy0 = new Monster(null, new Vector2f(2, 2), Vector2f.down, 100);
        IMonster enemy1 = new Monster(null, new Vector2f(9, 0), Vector2f.down, 100);

        monsters.add(enemy0);
        monsters.add(enemy1);

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

        tiles.add(new Tuple2<>(new Vector2f(6, 0), null));
        tiles.add(new Tuple2<>(new Vector2f(7, 0), new IItem[]{mockPickupKey, mockPickupWeapon}));
        tiles.add(new Tuple2<>(new Vector2f(8, 0), null));
        tiles.add(new Tuple2<>(new Vector2f(9, 0), null));

        tiles.add(new Tuple2<>(new Vector2f(9, 0), null));

        tiles.add(new Tuple2<>(new Vector2f(1, 5), new IItem[]{excessiveItem.clone(), excessiveItem.clone(), excessiveItem.clone()}));
        tiles.add(new Tuple2<>(new Vector2f(2, 5), new IItem[]{stackingConsumables.clone(), stackingConsumables.clone(), stackingConsumables.clone()}));

        doors.add(new Tuple3<>(new Vector2f(6, 2), Vector2f.right, new Door(true, null)));
        doors.add(new Tuple3<>(new Vector2f(8, 2), Vector2f.left, new Door(false, null)));
        doors.add(new Tuple3<>(new Vector2f(8, 2), Vector2f.right, new Door(true, mockKey)));

        doors.add(new Tuple3<>(new Vector2f(7, 0), Vector2f.right, new Door(true, mockPickupKey)));

        /**
         *    [x][x]      [ ]
         *
         *    [ ][ ][ ]
         *    [ ][e][ ]      [ |[ ]/ ]| ][ ]
         *    [ ][ ][ ]
         * [ ][ ][ ]         [ ][x]| ][e]
         */


        ILevel level = new Level(tiles, doors, monsters, player, Vector2f.zero);
        testPlayerController = new PlayerController(player, level);
        testLevel = level;
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

    @Test
    public void testAttackEnemyWithInventoryItem() {
        Vector2f tileStart = new Vector2f(2, 1);
        testPlayerController.changePlayerActiveItem(0);
        testPlayerController.setPlayerPosition(tileStart);
        testPlayerController.usePlayerAttack();
        assert (testLevel.getMonster(new Vector2f(2, 2)).getHitpoints().y == 90);
    }


    @Test
    public void testAttackEnemyWithDefaultItem() {
        Vector2f tileStart = new Vector2f(2, 1);
        testPlayerController.changePlayerActiveItem(-1);
        testPlayerController.setPlayerPosition(tileStart);
        testPlayerController.usePlayerAttack();
        assert (testLevel.getMonster(new Vector2f(2, 2)).getHitpoints().y == 98);
    }

    @Test
    public void testSelfieWithDefaultItem() {
        Vector2f tileStart = new Vector2f(2, 1);
        testPlayerController.changePlayerActiveItem(-1);
        testPlayerController.setPlayerPosition(tileStart);
        testPlayerController.usePlayerSelfie();
        assert (testPlayerController.getPlayer().getHitpoints().y == 99);
    }

    @Test
    public void testSelfieWithInventoryItem() {
        Vector2f tileStart = new Vector2f(2, 1);
        testPlayerController.changePlayerActiveItem(0);
        testPlayerController.setPlayerPosition(tileStart);
        testPlayerController.usePlayerSelfie();
        assert (testPlayerController.getPlayer().getHitpoints().y == 95);
    }

    @Test
    public void testHealWithInventoryConsumable() {
        Vector2f tileStart = new Vector2f(2, 1);
        testPlayerController.changePlayerActiveItem(0);
        testPlayerController.setPlayerPosition(tileStart);
        testPlayerController.usePlayerSelfie();
        testPlayerController.usePlayerSelfie();
        testPlayerController.changePlayerActiveItem(1);
        testPlayerController.usePlayerSelfie();
        testPlayerController.usePlayerSelfie();
        assert (testPlayerController.getPlayer().getHitpoints().y == 99);
    }

    @Test
    public void testPickupItems() {
        Vector2f tileStart = new Vector2f(6, 0);
        Vector2f tileTo = new Vector2f(8, 0);
        testPlayerController.setPlayerPosition(tileStart);
        testPlayerController.rotatePlayerRight();
        testPlayerController.movePlayerForward();
        testPlayerController.usePlayerPickup();
        testPlayerController.movePlayerForward();
        testPlayerController.movePlayerForward();
        testPlayerController.changePlayerActiveItem(2);
        testPlayerController.usePlayerAttack();
        assert (testLevel.getMonster(new Vector2f(9, 0)).getHitpoints().y == 60
                && testPlayerController.getPlayer().getPosition().equals(tileTo));
    }

    @Test
    public void testExcessivePickup() {
        Vector2f tileStart = new Vector2f(1, 5);
        testPlayerController.setPlayerPosition(tileStart);
        testPlayerController.setPlayerItems(new IItem[]{null});
        testPlayerController.usePlayerPickup();
        assert (testLevel.popTileItems(tileStart).size() == 2
                && testPlayerController.getPlayer().getInventory().getItem(0) != null);
    }

    @Test
    public void testStackablePickup() {
        Vector2f tileStart = new Vector2f(2, 5);
        testPlayerController.setPlayerPosition(tileStart);
        testPlayerController.setPlayerItems(new IItem[]{null});
        testPlayerController.usePlayerPickup();
        assert (testLevel.popTileItems(tileStart).size() == 0
                && ((Consumable)testPlayerController.getPlayer().getInventory().getItem(0)).getStacks() == 6);
    }
}
