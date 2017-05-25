package com.helpme.app.game.model.level;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.consciousness.ISurroundings;
import com.helpme.app.game.model.tile.ITile;
import com.helpme.app.utils.mathl.Vector2f;

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
