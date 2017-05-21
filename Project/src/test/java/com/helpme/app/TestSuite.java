package com.helpme.app;

import com.helpme.app.attacktest.AttackTest;
import com.helpme.app.behaviourtest.*;
import com.helpme.app.bodytest.BodyTest;
import com.helpme.app.consciousnesstest.ConsciousnessTest;
import com.helpme.app.inventorytest.InventoryTest;
import com.helpme.app.leveltest.LevelTest;
import com.helpme.app.selfietest.SelfieTest;
import com.helpme.app.tiletest.TileTest;
import com.helpme.app.traversetest.TraverseTest;
import org.junit.runners.Suite;
import org.junit.runner.RunWith;


/**
 * Created by Jacob on 2017-04-11.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({BodyTest.class,
        InventoryTest.class,
        TileTest.class,
        ConsciousnessTest.class,
        LevelTest.class,
        BehaviourTest.class,
        AttackBehaviourTest.class,
        FollowBehaviourTest.class,
        StayBehaviourTest.class,
        ReturnBehaviourTest.class,
        TraverseTest.class,
        AttackTest.class,
        TileTest.class,
        SelfieTest.class})
public class TestSuite {
    // Nothing
}

