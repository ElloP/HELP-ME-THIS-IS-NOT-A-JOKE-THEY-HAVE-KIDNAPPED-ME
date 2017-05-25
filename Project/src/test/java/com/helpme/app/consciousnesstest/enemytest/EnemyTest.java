package com.helpme.app.consciousnesstest.enemytest;

import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.game.model.consciousness.concrete.Enemy;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kopa on 2017-05-22.
 */
public class EnemyTest {
    IConsciousness enemy;
    MockMemory mockMemory;
    List<MockBehaviour> mockBehaviours;

    @Before
    public void setup(){
        mockBehaviours = new ArrayList<>();
        mockBehaviours.add(new MockBehaviour(1, true));
        mockBehaviours.add(new MockBehaviour(0, true));
        mockBehaviours.add(new MockBehaviour(2, true));
        mockMemory = new MockMemory();
        enemy = new Enemy(null, null, mockMemory, new SortedList<>(FXCollections.observableList(mockBehaviours), Comparator.comparingInt(IBehaviour::getPriority)));
    }

    @Test
    public void testPriority(){
        enemy.update();
        assert (mockBehaviours.get(1).execute == 1 &&
                mockBehaviours.get(1).reset == 1 &&
                mockBehaviours.get(0).execute == 0 &&
                mockBehaviours.get(0).reset == 0 &&
                mockBehaviours.get(2).execute == 0 &&
                mockBehaviours.get(2).reset == 0);
    }

    @Test
    public void testValid(){
        mockBehaviours.get(1).valid = false;
        mockBehaviours.get(0).valid = false;
        enemy.update();
        assert (mockBehaviours.get(1).execute == 0 &&
                mockBehaviours.get(1).reset == 0 &&
                mockBehaviours.get(0).execute == 0 &&
                mockBehaviours.get(0).reset == 0 &&
                mockBehaviours.get(2).execute == 1 &&
                mockBehaviours.get(2).reset == 1);
    }


}
