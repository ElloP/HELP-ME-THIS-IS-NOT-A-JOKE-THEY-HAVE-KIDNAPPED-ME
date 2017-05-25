package com.helpme.app;

import com.helpme.app.behaviourtest.*;
import com.helpme.app.bodytest.BodyTest;
import com.helpme.app.consciousnesstest.ConsciousnessTest;
import com.helpme.app.consciousnesstest.enemytest.EnemyTest;
import com.helpme.app.edgetest.EdgeTest;
import com.helpme.app.edgetest.traversetest.TraverseTest;
import com.helpme.app.intelligencetest.IntelligenceTest;
import com.helpme.app.inventorytest.InventoryTest;
import com.helpme.app.itemtest.ConsumableTest;
import com.helpme.app.itemtest.ItemTest;
import com.helpme.app.itemtest.attacktest.AttackTest;
import com.helpme.app.itemtest.pickuptest.PickupTest;
import com.helpme.app.itemtest.selfietest.SelfieTest;
import com.helpme.app.itemtest.stacktest.StackTest;
import com.helpme.app.levelfactorytest.LevelFactoryTest;
import com.helpme.app.leveltest.LevelBodyTest;
import com.helpme.app.leveltest.LevelPathTest;
import com.helpme.app.leveltest.LevelTest;
import com.helpme.app.leveltest.LevelTileTest;
import com.helpme.app.mathtest.Vector2fTest;
import com.helpme.app.maybetest.MaybeTest;
import com.helpme.app.memorytest.MemoryTest;
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
        MaybeTest.class,
        ConsciousnessTest.class,
        LevelTest.class,
        LevelBodyTest.class,
        LevelTileTest.class,
        LevelPathTest.class,
        BehaviourTest.class,
        MemoryTest.class,
        ConsumableTest.class,
        LevelFactoryTest.class,
        AttackBehaviourTest.class,
        FollowBehaviourTest.class,
        StayBehaviourTest.class,
        ReturnBehaviourTest.class,
        TraverseTest.class,
        AttackTest.class,
        PickupTest.class,
        StackTest.class,
        TileTest.class,
        SelfieTest.class,
        MaybeTest.class,
        Vector2fTest.class})
public class TestSuite {
    // Nothing
}

