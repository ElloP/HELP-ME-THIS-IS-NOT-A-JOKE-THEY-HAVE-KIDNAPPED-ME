package com.helpme.app.saveload;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.world.level.IReadLevel;

/**
 * Created by Klas on 2017-05-02.
 */
public class LevelWrapper {
    private PlayerWrapper player;
    private MonsterWrapper[] monsters;

    public LevelWrapper(IReadLevel level){
        this.player = new PlayerWrapper(level.readPlayer().getValue()); //TODO (klas) maybe check?
        Maybe<IReadMonster[]> maybeMonsters = level.readMonsters();
        if(maybeMonsters.isJust()){
            IReadMonster[] levelMonster = maybeMonsters.getValue();
            monsters = new MonsterWrapper[levelMonster.length];
            for(int i = 0; i < levelMonster.length; i++){
                monsters[i] = new MonsterWrapper(levelMonster[i]);
            }
        }
    }
}
