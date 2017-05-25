package com.helpme.app.consciousnesstest.enemytest;

import com.helpme.app.game.model.consciousness.behaviour.memory.IMemory;

import java.util.Map;

/**
 * Created by kopa on 2017-05-22.
 */
public class MockMemory implements IMemory {
    @Override
    public Map<String, Integer> readLongTerm() {
        return null;
    }

    @Override
    public void removeLongTermMemory(String name) {

    }

    @Override
    public void updateLongTermMemory(String name, int value) {

    }

    @Override
    public Map<String, Integer> readShortTerm() {
        return null;
    }

    @Override
    public void updateShortTermMemory(String name, int value) {

    }

    @Override
    public void removeShortTermMemory(String name) {

    }

    @Override
    public IMemory copy() {
        return null;
    }

    @Override
    public Map<String, Integer> readMemory() {
        return null;
    }
}
