package com.helpme.app.saveload;

import com.helpme.app.model.consciousness.concrete.Player;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.model.consciousness.IConsciousness;
import com.helpme.app.model.level.ILevel;

/**
 * Created by Klas on 2017-05-19.
 *
 * Interface for saving and loading the state of a game
 */
public interface SaveLoad {
    void saveGame(ILevel level, Player player, IConsciousness[] enemies, String filePath);
    Tuple3<ILevel, Player, IConsciousness[]> loadGame(String filePath);
}
