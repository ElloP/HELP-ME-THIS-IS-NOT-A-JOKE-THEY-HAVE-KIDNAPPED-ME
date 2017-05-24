package com.helpme.app.intelligencetest;

import com.helpme.app.model.body.IReadBody;
import com.helpme.app.model.body.inventory.IReadInventory;
import com.helpme.app.model.tile.edge.IEdge;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.tuple.Tuple2;

import java.util.Observable;

/**
 * Created by kopa on 2017-05-22.
 */
public class MockBody extends Observable implements IReadBody {
    Vector2f position;

    public MockBody(Vector2f position){
        this.position = position;
    }

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
        return position;
    }

    @Override
    public Vector2f readDirection() {
        return Vector2f.NORTH;
    }

    @Override
    public Vector2f readHitpoints() {
        return null;
    }

    @Override
    public float readMaxHitpoints() {
        return 0;
    }

    @Override
    public float readCurrentHitpoints() {
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
    public boolean traverse(IEdge edge) {
        return false;
    }

    @Override
    public IReadInventory readInventory() {
        return null;
    }
}
