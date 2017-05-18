package com.helpme.app.behaviourtest;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.character.inventory.IReadInventory;
import com.helpme.app.world.tile.edge.IEdge;

/**
 * Created by kopa on 2017-05-16.
 */
public class MockBody implements IReadBody {
    @Override
    public void damage(float amount) {

    }

    @Override
    public void heal(float amount) {

    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public Vector2f readPosition() {
        return null;
    }

    @Override
    public Vector2f readDirection() {
        return null;
    }

    @Override
    public Vector2f readHitpoints() {
        return null;
    }

    @Override
    public float readMaxHp() {
        return 0;
    }

    @Override
    public float readCurrentHp() {
        return 0;
    }

    @Override
    public Vector2f readStartingPosition() {
        return null;
    }

    @Override
    public Tuple2<String, String[]> getResponse(int i) {
        return null;
    }

    @Override
    public Tuple2<String, String[]> getDialogue() {
        return null;
    }

    @Override
    public boolean isTraversable(IEdge edge) {
        return false;
    }

    @Override
    public IReadInventory readInventory() {
        return null;
    }
}
