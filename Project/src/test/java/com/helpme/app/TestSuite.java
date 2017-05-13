package com.helpme.app;

import com.helpme.app.bodytest.BodyTest;
import com.helpme.app.inventorytest.InventoryTest;
import com.helpme.app.tiletest.TileTest;
import org.junit.runners.Suite;
import org.junit.runner.RunWith;


/**
 * Created by Jacob on 2017-04-11.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({DialogueTest.class, EdgeTest.class, ItemTest.class, BodyTest.class, BehaviourTest.class, InventoryTest.class, TileTest.class})
public class TestSuite {
    //nothing
}

