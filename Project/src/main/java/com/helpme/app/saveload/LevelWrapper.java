package com.helpme.app.saveload;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.world.level.IReadLevel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Klas on 2017-05-02.
 */
@XmlRootElement(name="Level")
public class LevelWrapper {
    private PlayerWrapper player;
    private MonsterWrapper[] monsters;

    public LevelWrapper(){}

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

    @XmlElement(name="Player")
    public PlayerWrapper getPlayer(){

        return this.player;
    }
    public void setPlayer(PlayerWrapper player) {
        this.player = player;
    }
    @XmlElement(name="Monsters")
    public MonsterWrapper[] getMonsters() {
        return monsters;
    }
    public void setMonsters(MonsterWrapper[] monsters) {
        this.monsters = monsters;
    }
    public String toString(){
        return "hejhå!";
    }
}
