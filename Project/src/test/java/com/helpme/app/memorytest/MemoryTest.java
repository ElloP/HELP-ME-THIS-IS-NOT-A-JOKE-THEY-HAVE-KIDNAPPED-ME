package com.helpme.app.memorytest;

import com.helpme.app.model.consciousness.behaviour.memory.IMemory;
import com.helpme.app.model.consciousness.behaviour.memory.concrete.Memory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kopa on 2017-05-22.
 */
public class MemoryTest {
    IMemory memory;

    @Before
    public void setup() {
        Map<String, Integer> longTerm = new HashMap<>();
        Map<String, Integer> shortTerm = new HashMap<>();

        longTerm.put("long_term", 2);
        shortTerm.put("short_term", 3);
        memory = new Memory(shortTerm, longTerm);
    }

    @Test
    public void testReadMemory() {
        Map<String, Integer> combined = memory.readMemory();
        assert (combined.size() == 2 &&
                combined.get("long_term") == 2 &&
                combined.get("short_term") == 3);
    }

    @Test
    public void testReadMemoryPure() {
        Map<String, Integer> copy0 = memory.readMemory();
        Map<String, Integer> copy1 = memory.readMemory();
        copy1.put("short_term", 8);
        copy1.put("long_term", 7);

        assert (copy0.size() == 2 &&
                copy0.get("long_term") == 2 &&
                copy0.get("short_term") == 3 &&
                copy1.size() == 2 &&
                copy1.get("long_term") == 7 &&
                copy1.get("short_term") == 8);
    }

    @Test
    public void testReadShortTerm() {
        Map<String, Integer> shortTerm = memory.readShortTerm();
        assert (shortTerm.size() == 1 &&
                shortTerm.get("short_term") == 3);
    }

    @Test
    public void testReadShortTermPure() {
        Map<String, Integer> copy0 = memory.readShortTerm();
        Map<String, Integer> copy1 = memory.readShortTerm();

        copy1.put("short_term", 8);

        assert (copy0.size() == 1 &&
                copy0.get("short_term") == 3 &&
                copy1.size() == 1 &&
                copy1.get("short_term") == 8);
    }

    @Test
    public void testReadLongTerm() {
        Map<String, Integer> shortTerm = memory.readLongTerm();
        assert (shortTerm.size() == 1 &&
                shortTerm.get("long_term") == 2);
    }

    @Test
    public void testReadLongTermPure() {
        Map<String, Integer> copy0 = memory.readLongTerm();
        Map<String, Integer> copy1 = memory.readLongTerm();

        copy1.put("long_term", 8);

        assert (copy0.size() == 1 &&
                copy0.get("long_term") == 2 &&
                copy1.size() == 1 &&
                copy1.get("long_term") == 8);
    }

    @Test
    public void testRemoveLongTerm() {
        memory.removeLongTermMemory("long_term");
        assert (memory.readLongTerm().size() == 0);
    }

    @Test
    public void testRemoveShortTerm() {
        memory.removeShortTermMemory("short_term");
        assert (memory.readShortTerm().size() == 0);
    }


    @Test
    public void testUpdateLongTerm() {
        memory.updateLongTermMemory("new", 6);
        assert (memory.readLongTerm().get("new") == 6);
    }

    @Test
    public void testUpdateShortTerm() {
        memory.updateShortTermMemory("new", 6);
        assert (memory.readShortTerm().get("new") == 6);
    }
}
