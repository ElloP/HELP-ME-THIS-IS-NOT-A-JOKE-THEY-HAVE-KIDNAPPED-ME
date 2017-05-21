package com.helpme.app.saveload;

import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.model.body.IBody;
import com.helpme.app.model.consciousness.IConsciousness;
import com.helpme.app.model.level.ILevel;

/**
 * Created by og on 2017-05-19.
 */
public interface SaveLoad {
    void saveGame(ILevel level, IBody player, IConsciousness[] enemies, String filePath);
    Tuple3<ILevel, IBody, IConsciousness[]> loadGame(String filePath);
}
