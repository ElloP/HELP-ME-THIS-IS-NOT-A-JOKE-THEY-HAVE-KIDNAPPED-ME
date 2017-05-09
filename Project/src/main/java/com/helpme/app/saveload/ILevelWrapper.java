package com.helpme.app.saveload;

/**
 * Created by Klas on 2017-05-01.
 */
public interface ILevelWrapper {
    TileWrapper[] getTiles();
    void setTiles(TileWrapper[] tiles);
    EnemyWrapper[] getMonsters();
    void setMonsters(EnemyWrapper[] monsters);
}
