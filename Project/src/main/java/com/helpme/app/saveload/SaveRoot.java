package com.helpme.app.saveload;

import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.consciousness.Enemy;
import com.helpme.app.world.consciousness.IEnemy;
import com.helpme.app.world.level.ILevel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by og on 2017-05-09.
 */
@XmlRootElement(name="Root")
public class SaveRoot {
    private BodyWrapper player;
    private EnemyWrapper[] monsters;
    private LevelWrapper level;
    private ILevel loadLevel;
    private IBody loadPlayer;
    private Enemy[] loadEnemies;

    public SaveRoot(){}

    public SaveRoot(ILevel level, IBody player, Enemy[] enemies){

        this.player = new BodyWrapper(level.readPlayer().getValue()); //TODO (klas) maybe check?

        this.level = new LevelWrapper(level);

        this.monsters = new EnemyWrapper[enemies.length];
        for(int i = 0; i < enemies.length; i++){
            this.monsters[i] = new EnemyWrapper(enemies[i]);
        }
    }

    @XmlElement(name="Player")
    public BodyWrapper getPlayer(){
        return this.player;
    }
    public void setPlayer(BodyWrapper player) {
        this.player = player;
    }
    @XmlElement(name="Monsters")
    public EnemyWrapper[] getMonsters() {
        return this.monsters;
    }
    public void setMonsters(EnemyWrapper[] monsters) {
        this.monsters = monsters;
    }
    @XmlElement(name="Level")
    public LevelWrapper getLevel() {
        return level;
    }
    public void setLevel(LevelWrapper level) {
        this.level = level;
    }


    public void loadGame(){
        this.loadLevel = level.getObject();
        this.loadPlayer = player.getObject();
        this.loadEnemies = new Enemy[monsters.length];
        for(int i = 0; i < monsters.length; i++){
            this.loadEnemies[i] = monsters[i].getObject(this.loadLevel);
            this.loadLevel.addBody(this.loadEnemies[i].readBody());
        }
        this.loadLevel.setPlayer(this.loadPlayer);
    }
    public IBody loadPlayer(){
        if(loadPlayer == null) loadGame();
        return this.loadPlayer;
    }
    public Enemy[] loadEnemies(){
        if(loadEnemies == null) loadGame();
        return this.loadEnemies;
    }
    public ILevel loadLevel(){
        if(loadLevel == null) loadGame();
        return this.loadLevel;
    }
}
