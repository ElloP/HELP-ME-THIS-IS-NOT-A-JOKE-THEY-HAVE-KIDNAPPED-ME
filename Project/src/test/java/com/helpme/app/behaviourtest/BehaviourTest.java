package com.helpme.app.behaviourtest;

import com.helpme.app.game.model.consciousness.behaviour.Comparison;
import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.game.model.consciousness.behaviour.concrete.Stay;
import com.helpme.app.utils.tuple.Tuple2;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kopa on 2017-05-15.
 */

public class BehaviourTest {
    private MockMemory mockMemory;
    private IBehaviour behaviour;

    @Before
    public void setup() {
        mockMemory = new MockMemory();
        behaviour = new Stay(10, null);
    }

    @Test
    public void testGetPriority(){
        assert (behaviour.getPriority() == 10);
    }

    @Test
    public void testSetPriority(){
        behaviour.setPriority(5);
        assert (behaviour.getPriority() == 5);
    }

    @Test
    public void testGetPreconditions() {
        Map<String, Tuple2<Integer, Comparison>> preconditions1;
        Map<String, Tuple2<Integer, Comparison>> preconditions0 = new HashMap<>();
        preconditions0.put("memory0", new Tuple2<>(5, Comparison.MORE_THAN));
        preconditions0.put("memory1", new Tuple2<>(4, Comparison.LESS_THAN));

        behaviour = new Stay(0, preconditions0);
        preconditions1 = behaviour.getPreconditions();

        assert (preconditions1.size() == 2 &&
                preconditions1.get("memory0").equals(new Tuple2<>(5, Comparison.MORE_THAN)) &&
                preconditions1.get("memory1").equals(new Tuple2<>(4, Comparison.LESS_THAN)));
    }

    @Test
    public void testSetPreconditions() {
        Map<String, Tuple2<Integer, Comparison>> preconditions1;
        Map<String, Tuple2<Integer, Comparison>> preconditions0 = new HashMap<>();
        preconditions0.put("memory0", new Tuple2<>(5, Comparison.MORE_THAN));
        preconditions0.put("memory1", new Tuple2<>(4, Comparison.LESS_THAN));

        behaviour = new Stay(0, new HashMap<>());
        behaviour.setPreconditions(preconditions0);
        preconditions1 = behaviour.getPreconditions();

        assert (preconditions1.size() == 2 &&
                preconditions1.get("memory0").equals(new Tuple2<>(5, Comparison.MORE_THAN)) &&
                preconditions1.get("memory1").equals(new Tuple2<>(4, Comparison.LESS_THAN)));
    }

    /**
     * Tests if the comparison "MORE THAN" is valid for preconditions
     */
    @Test
    public void testMoreThanValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory0", new Tuple2<>(5, Comparison.MORE_THAN));

        behaviour = new Stay(0, preconditions);
        assert (behaviour.valid(mockMemory.readMemory()));
    }

    @Test
    public void testMoreThanNotValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory0", new Tuple2<>(1, Comparison.MORE_THAN));

        behaviour = new Stay(0, preconditions);
        assert (!behaviour.valid(mockMemory.readMemory()));
    }

    /**
     * Tests if the comparison "LESS THAN" is valid for preconditions
     */
    @Test
    public void testLessThanValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory1", new Tuple2<>(3, Comparison.LESS_THAN));

        behaviour =new Stay(0, preconditions);
        assert (behaviour.valid(mockMemory.readMemory()));
    }

    @Test
    public void testLessThanNotValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory1", new Tuple2<>(5, Comparison.LESS_THAN));

        behaviour = new Stay(0, preconditions);
        assert (!behaviour.valid(mockMemory.readMemory()));
    }

    /**
     * Tests if the comparison "EQUAL" is valid for preconditions
     */
    @Test
    public void testEqualValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory2", new Tuple2<>(1, Comparison.EQUAL));

        behaviour = new Stay(0, preconditions);
        assert (behaviour.valid(mockMemory.readMemory()));
    }

    @Test
    public void testEqualNotValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory2", new Tuple2<>(2, Comparison.EQUAL));

        behaviour = new Stay(0, preconditions);
        assert (!behaviour.valid(mockMemory.readMemory()));
    }

    /**
     * Tests if the comparison "NOT EQUAL" is valid for preconditions
     */
    @Test
    public void testNotEqualValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory3", new Tuple2<>(6, Comparison.NOT_EQUAL));

        behaviour = new Stay(0, preconditions);
        assert (behaviour.valid(mockMemory.readMemory()));
    }

    @Test
    public void testNotEqualNotValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory3", new Tuple2<>(7, Comparison.NOT_EQUAL));

        behaviour = new Stay(0, preconditions);
        assert (!behaviour.valid(new MockMemory().readMemory()));
    }

    /**
     * Tests so that the precondition fails if there's no match
     */
    @Test
    public void testNoMatchNotValid() {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        preconditions.put("memory4", new Tuple2<>(6, Comparison.NOT_EQUAL));

        behaviour = new Stay(0, preconditions);
        assert (!behaviour.valid(new MockMemory().readMemory()));
    }
}
