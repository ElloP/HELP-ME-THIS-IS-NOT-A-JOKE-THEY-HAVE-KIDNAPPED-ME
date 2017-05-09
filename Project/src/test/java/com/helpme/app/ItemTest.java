package com.helpme.app;

import com.helpme.app.Mock.MockWorld0;
import com.helpme.app.world.item.Consumable;
import com.helpme.app.world.item.IItem;
import com.helpme.app.utils.Vector2f;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jacob on 2017-04-11.
 */
public class ItemTest {
    private MockWorld0 mockWorld;

    @Before
    public void setUp() {
        mockWorld = new MockWorld0();
    }

    @Test
    public void testAttackEnemyWithInventoryItem() {
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.player.changePlayerActiveItem(0);
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.usePlayerAttack();
        assert (mockWorld.level.readBody(new Vector2f(2, 2)).getValue().readHitpoints().y == 90);
    }


    @Test
    public void testAttackEnemyWithDefaultItem() {
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.player.changePlayerActiveItem(-1);
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.usePlayerAttack();
        assert (mockWorld.level.readBody(new Vector2f(2, 2)).getValue().readHitpoints().y == 98);
    }

    @Test
    public void testSelfieWithDefaultItem() {
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.player.changePlayerActiveItem(-1);
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.usePlayerSelfie();
        assert ( mockWorld.player.getPlayer().readHitpoints().y == 99);
    }

    @Test
    public void testSelfieWithInventoryItem() {
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.player.changePlayerActiveItem(0);
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.usePlayerSelfie();
        assert ( mockWorld.player.getPlayer().readHitpoints().y == 95);
    }

    @Test
    public void testHealWithInventoryConsumable() {
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.player.changePlayerActiveItem(0);
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.usePlayerSelfie();
        mockWorld.player.usePlayerSelfie();
        mockWorld.player.changePlayerActiveItem(1);
        mockWorld.player.usePlayerSelfie();
        mockWorld.player.usePlayerSelfie();
        assert ( mockWorld.player.getPlayer().readHitpoints().y == 99);
    }

    @Test
    public void testPickupItems() {
        Vector2f tileStart = new Vector2f(6, 0);
        Vector2f tileTo = new Vector2f(8, 0);
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.rotatePlayerRight();
        mockWorld.player.movePlayerForward();
        mockWorld.player.usePlayerPickupAll();
        mockWorld.player.movePlayerForward();
        mockWorld.player.movePlayerForward();
        mockWorld.player.changePlayerActiveItem(2);
        mockWorld.player.usePlayerAttack();
        assert (mockWorld.level.readBody(new Vector2f(9, 0)).getValue().readHitpoints().y == 60
                &&  mockWorld.player.getPlayer().readPosition().equals(tileTo));
    }

    @Test
    public void testExcessivePickup() {
        Vector2f tileStart = new Vector2f(1, 5);
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.setPlayerItems(new IItem[]{null});
        mockWorld.player.usePlayerPickupAll();
        assert (mockWorld.level.removeTileItems(tileStart).length == 2
                &&  mockWorld.player.getPlayer().readInventory().readItem(0) != null);
    }

    @Test
    public void testStackablePickup() {
        Vector2f tileStart = new Vector2f(2, 5);
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.setPlayerItems(new IItem[]{null});
        mockWorld.player.usePlayerPickupAll();
        assert (mockWorld.level.removeTileItems(tileStart).length == 0
                && mockWorld.player.getPlayer().readInventory().readItem(0).check(i -> ((Consumable)i).getStacks() == 6));
    }

    @Test
    public void testSinglePickup() {
        Vector2f tileStart = new Vector2f(3, 5);
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.setPlayerItems(new IItem[]{null, null, null});
        mockWorld.player.usePlayerPickupSingle(0);
        mockWorld.player.usePlayerPickupSingle(2);
        IItem[] items = mockWorld.level.removeTileItems(tileStart);
        assert (items[0] == null && items[2] == null && items[1].toString().equals("Item: Box")
                &&  mockWorld.player.getPlayer().readInventory().readItem(0).check(i -> i.toString().equals("Consumable: Paper"))
                &&  mockWorld.player.getPlayer().readInventory().readItem(1).check(i -> i.toString().equals("Item: Sword")));
    }

    @Test
    public void testSingleDrop() {
        Vector2f tileStart = new Vector2f(0, 0);
        mockWorld.player.dropPlayerItem(0);
        IItem[] items = mockWorld.level.removeTileItems(tileStart);
        assert(items[0].toString().equals("Item: Club") && items.length == 1);
    }
}
