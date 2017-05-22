package com.helpme.app.model.level;

import com.helpme.app.model.body.IBody;
import com.helpme.app.model.consciousness.ISurroundings;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.model.tile.ITile;

import java.util.Map;

/**
 * Created by Jacob on 2017-04-08.
 */

public interface ILevel extends ISurroundings {
    boolean addBody(IBody body);
    void setPlayer(IBody player);
    void resetPlayer();
    Map<Vector2f, ITile> getTiles();
}
