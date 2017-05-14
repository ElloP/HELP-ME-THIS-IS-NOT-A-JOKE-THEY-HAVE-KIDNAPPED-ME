package com.helpme.app.consciousnesstest;

import com.helpme.app.inventorytest.MockItem;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.character.inventory.InventoryFactory;
import com.helpme.app.world.character.target.ITarget;
import com.helpme.app.world.consciousness.ConsciousnessFactory;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.item.IItem;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-11.
 */
public class ConsciousnessTest {
    IConsciousness enemy;
    IConsciousness player;

    @Before
    public void setup() {
        enemy = ConsciousnessFactory.createEnemy(new MockBody(), new MockSurroundings(), new MockBehaviour(), new MockBehaviour());
        player = ConsciousnessFactory.createPlayer(new MockBody(), new MockSurroundings());
    }

    @Test
    public void testAttack(){

    }


}
