package com.helpme.app;

import com.helpme.app.Mock.MockWorld;
import com.helpme.app.item.Consumable;
import com.helpme.app.item.IItem;
import com.helpme.app.utils.Vector2f;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-04-11.
 */
public class ItemTest {
    private MockWorld mockWorld;

    @Before
    public void setUp() {
        mockWorld = new MockWorld();
    }

    @Test
    public void testAttackEnemyWithInventoryItem() {
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.playerController.changePlayerActiveItem(0);
        mockWorld.playerController.setPlayerPosition(tileStart);
        mockWorld.playerController.usePlayerAttack();
        assert (mockWorld.level.getMonster(new Vector2f(2, 2)).getHitpoints().y == 90);
    }


    @Test
    public void testAttackEnemyWithDefaultItem() {
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.playerController.changePlayerActiveItem(-1);
        mockWorld.playerController.setPlayerPosition(tileStart);
        mockWorld.playerController.usePlayerAttack();
        assert (mockWorld.level.getMonster(new Vector2f(2, 2)).getHitpoints().y == 98);
    }

    @Test
    public void testSelfieWithDefaultItem() {
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.playerController.changePlayerActiveItem(-1);
        mockWorld.playerController.setPlayerPosition(tileStart);
        mockWorld.playerController.usePlayerSelfie();
        assert ( mockWorld.playerController.getPlayer().getHitpoints().y == 99);
    }

    @Test
    public void testSelfieWithInventoryItem() {
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.playerController.changePlayerActiveItem(0);
        mockWorld.playerController.setPlayerPosition(tileStart);
        mockWorld.playerController.usePlayerSelfie();
        assert ( mockWorld.playerController.getPlayer().getHitpoints().y == 95);
    }

    @Test
    public void testHealWithInventoryConsumable() {
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.playerController.changePlayerActiveItem(0);
        mockWorld.playerController.setPlayerPosition(tileStart);
        mockWorld.playerController.usePlayerSelfie();
        mockWorld.playerController.usePlayerSelfie();
        mockWorld.playerController.changePlayerActiveItem(1);
        mockWorld.playerController.usePlayerSelfie();
        mockWorld.playerController.usePlayerSelfie();
        assert ( mockWorld.playerController.getPlayer().getHitpoints().y == 99);
    }

    @Test
    public void testPickupItems() {
        Vector2f tileStart = new Vector2f(6, 0);
        Vector2f tileTo = new Vector2f(8, 0);
        mockWorld.playerController.setPlayerPosition(tileStart);
        mockWorld.playerController.rotatePlayerRight();
        mockWorld.playerController.movePlayerForward();
        mockWorld.playerController.usePlayerPickupAll();
        mockWorld.playerController.movePlayerForward();
        mockWorld.playerController.movePlayerForward();
        mockWorld.playerController.changePlayerActiveItem(2);
        mockWorld.playerController.usePlayerAttack();
        assert (mockWorld.level.getMonster(new Vector2f(9, 0)).getHitpoints().y == 60
                &&  mockWorld.playerController.getPlayer().getPosition().equals(tileTo));
    }

    @Test
    public void testExcessivePickup() {
        Vector2f tileStart = new Vector2f(1, 5);
        mockWorld.playerController.setPlayerPosition(tileStart);
        mockWorld.playerController.setPlayerItems(new IItem[]{null});
        mockWorld.playerController.usePlayerPickupAll();
        assert (mockWorld.level.removeTileItems(tileStart).length == 2
                &&  mockWorld.playerController.getPlayer().getInventory().getItem(0) != null);
    }

    @Test
    public void testStackablePickup() {
        Vector2f tileStart = new Vector2f(2, 5);
        mockWorld.playerController.setPlayerPosition(tileStart);
        mockWorld.playerController.setPlayerItems(new IItem[]{null});
        mockWorld.playerController.usePlayerPickupAll();
        assert (mockWorld.level.removeTileItems(tileStart).length == 0
                && ((Consumable)  mockWorld.playerController.getPlayer().getInventory().getItem(0)).getStacks() == 6);
    }

    @Test
    public void testSinglePickup() {
        Vector2f tileStart = new Vector2f(3, 5);
        mockWorld.playerController.setPlayerPosition(tileStart);
        mockWorld.playerController.setPlayerItems(new IItem[]{null, null, null});
        mockWorld.playerController.usePlayerPickupSingle(0);
        mockWorld.playerController.usePlayerPickupSingle(2);
        IItem[] items = mockWorld.level.removeTileItems(tileStart);
        assert (items[0] == null && items[2] == null && items[1].toString().equals("Item: Box")
                &&  mockWorld.playerController.getPlayer().getInventory().getItem(0).toString().equals("Consumable: Paper")
                &&  mockWorld.playerController.getPlayer().getInventory().getItem(1).toString().equals("Item: Sword"));
    }

    @Test
    public void testSingleDrop() {
        Vector2f tileStart = new Vector2f(0, 0);
        mockWorld.playerController.dropPlayerItem(0);
        IItem[] items = mockWorld.level.removeTileItems(tileStart);
        assert(items[0].toString().equals("Item: Club") && items.length == 1);
    }
}
