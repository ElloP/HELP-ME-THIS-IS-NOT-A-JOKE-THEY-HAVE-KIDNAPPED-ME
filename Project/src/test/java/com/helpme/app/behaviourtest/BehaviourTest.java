package com.helpme.app.behaviourtest;

import com.helpme.app.model.consciousness.behaviour.concrete.Stay;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.model.consciousness.behaviour.concrete.BehaviourFactory;
import com.helpme.app.model.consciousness.behaviour.Comparison;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kopa on 2017-05-15.
 */

public class BehaviourTest {
    private MockMemory mockMemory;

    @Before
    public void setup() {
        mockMemory = new MockMemory();
    }

    @Test
    public void testMoreThanValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory0", new Tuple2<>(5, Comparison.MORE_THAN));

        IBehaviour behaviour = new Stay(0, preconditions);
        assert (behaviour.valid(mockMemory.readMemory()));
    }

    @Test
    public void testLessThanValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory1", new Tuple2<>(3, Comparison.LESS_THAN));

        IBehaviour behaviour = BehaviourFactory.createStay(0, preconditions);
        assert (behaviour.valid(mockMemory.readMemory()));
    }

    @Test
    public void testEqualValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory2", new Tuple2<>(1, Comparison.EQUAL));

        IBehaviour behaviour = BehaviourFactory.createStay(0, preconditions);
        assert (behaviour.valid(mockMemory.readMemory()));
    }

    @Test
    public void testNotEqualValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory3", new Tuple2<>(6, Comparison.NOT_EQUAL));

        IBehaviour behaviour = BehaviourFactory.createStay(0, preconditions);
        assert (behaviour.valid(mockMemory.readMemory()));
    }

    @Test
    public void testMoreThanNotValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory0", new Tuple2<>(1, Comparison.MORE_THAN));

        IBehaviour behaviour = BehaviourFactory.createStay(0, preconditions);
        assert (!behaviour.valid(mockMemory.readMemory()));
    }

    @Test
    public void testLessThanNotValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory1", new Tuple2<>(5, Comparison.LESS_THAN));

        IBehaviour behaviour = BehaviourFactory.createStay(0, preconditions);
        assert (!behaviour.valid(mockMemory.readMemory()));
    }

    @Test
    public void testEqualNotValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory2", new Tuple2<>(2, Comparison.EQUAL));

        IBehaviour behaviour = BehaviourFactory.createStay(0, preconditions);
        assert (!behaviour.valid(mockMemory.readMemory()));
    }


    @Test
    public void testNotEqualNotValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory3", new Tuple2<>(7, Comparison.NOT_EQUAL));

        IBehaviour behaviour = BehaviourFactory.createStay(0, preconditions);
        assert (!behaviour.valid(new MockMemory().readMemory()));
    }


    @Test
    public void testNoMatchNotValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory4", new Tuple2<>(6, Comparison.NOT_EQUAL));

        IBehaviour behaviour = BehaviourFactory.createStay(0, preconditions);
        assert (!behaviour.valid(new MockMemory().readMemory()));
    }
}
