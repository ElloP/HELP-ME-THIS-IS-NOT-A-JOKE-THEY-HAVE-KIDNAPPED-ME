package com.helpme.app;

import com.helpme.app.behaviourtest.*;
import com.helpme.app.bodytest.BodyTest;
import com.helpme.app.consciousnesstest.ConsciousnessTest;
import com.helpme.app.consciousnesstest.enemytest.EnemyTest;
import com.helpme.app.edgetest.EdgeTest;
import com.helpme.app.edgetest.traversetest.TraverseTest;
import com.helpme.app.intelligencetest.IntelligenceTest;
import com.helpme.app.inventorytest.InventoryTest;
import com.helpme.app.itemtest.ItemTest;
import com.helpme.app.itemtest.attacktest.AttackTest;
import com.helpme.app.itemtest.pickuptest.PickupTest;
import com.helpme.app.itemtest.selfietest.SelfieTest;
import com.helpme.app.leveltest.LevelTest;
import com.helpme.app.tiletest.TileTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * Created by Jacob on 2017-04-11.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({BodyTest.class,
        InventoryTest.class,
        TileTest.class,
        IntelligenceTest.class,
        ItemTest.class,
        EdgeTest.class,
        EnemyTest.class,
        ConsciousnessTest.class,
        LevelTest.class,
        BehaviourTest.class,
        AttackBehaviourTest.class,
        FollowBehaviourTest.class,
        StayBehaviourTest.class,
        ReturnBehaviourTest.class,
        TraverseTest.class,
        AttackTest.class,
        PickupTest.class,
        TileTest.class,
        SelfieTest.class})
public class TestSuite {
    // Nothing
}

