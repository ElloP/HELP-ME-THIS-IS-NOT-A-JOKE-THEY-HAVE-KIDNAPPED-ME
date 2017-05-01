package com.helpme.app.saveload;

import java.util.List;

/**
 * Created by Klas on 2017-05-01.
 */
public interface ILevelWrapper {
    TileWrapper[] getTiles();
    void setTiles(TileWrapper[] tiles);
    MonsterWrapper[] getMonsters();
    void setMonsters(MonsterWrapper[] monsters);
}
