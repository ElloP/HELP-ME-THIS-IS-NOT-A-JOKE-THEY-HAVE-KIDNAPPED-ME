package com.helpme.app.saveload;

import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.consciousness.concrete.Enemy;
import com.helpme.app.world.level.ILevel;

/**
 * Created by og on 2017-05-19.
 */
public interface SaveLoad {
    void saveGame(ILevel level, IBody player, IConsciousness[] enemies, String filePath);
    Tuple3<ILevel, IBody, IConsciousness[]> loadGame(String filePath);
}
