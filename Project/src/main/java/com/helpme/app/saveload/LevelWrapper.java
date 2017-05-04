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
    private Vector2Wrapper startingPoint;

    public LevelWrapper(){}

    public LevelWrapper(IReadLevel level){
        this.player = new PlayerWrapper(level.readPlayer().getValue()); //TODO (klas) maybe check?
        IReadMonster[] levelMonsters = level.readMonsters();
        monsters = new MonsterWrapper[levelMonsters.length];
        for(int i = 0; i < levelMonsters.length; i++){
            monsters[i] = new MonsterWrapper(levelMonsters[i]);
        }
        startingPoint = new Vector2Wrapper(level.readStartingPoint());
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
    @XmlElement(name="StartingPoint")
    public Vector2Wrapper getStartingPoint(){return startingPoint;}
    public void setStartingPoint(Vector2Wrapper v){this.startingPoint = v;}
    public String toString(){
        String result = "";
        result += "Starting Point: " + startingPoint;
        result += "\nPlayer: " + player.toString();
        for(MonsterWrapper m : monsters){
            result += "\n Monster: "+ m.toString();
        }
        return result;
    }
}
