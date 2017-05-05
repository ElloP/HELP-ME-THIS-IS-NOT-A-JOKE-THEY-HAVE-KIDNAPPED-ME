package com.helpme.app.world.level;

import com.helpme.app.world.character.IBody;
import com.helpme.app.world.consciousness.ISurroundings;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.tile.ITile;

import java.util.Map;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface ILevel extends ISurroundings {
    void addBody(IBody body);
    void setPlayer(IBody player);
    Map<Vector2f, ITile> getTiles();
}
