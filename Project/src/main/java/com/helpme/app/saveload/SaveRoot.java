package com.helpme.app.saveload;

import com.helpme.app.model.body.IBody;
import com.helpme.app.model.consciousness.IConsciousness;
import com.helpme.app.model.consciousness.concrete.Enemy;
import com.helpme.app.model.level.ILevel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by og on 2017-05-09.
 */
@XmlRootElement(name="Root")
public class SaveRoot {
    private BodyWrapper player;
    private EnemyWrapper[] enemies;
    private LevelWrapper level;
    private ILevel loadLevel;
    private IBody loadPlayer;
    private IConsciousness[] loadEnemies;

    public SaveRoot(){}

    public SaveRoot(ILevel level, IBody player, IConsciousness[] enemies){

//        level.readPlayer().run(p -> this.player = new BodyWrapper(p)); //TODO (klas) maybe check?

        this.player = new BodyWrapper(player);

        this.level = new LevelWrapper(level);

        this.enemies = new EnemyWrapper[enemies.length];
        for(int i = 0; i < enemies.length; i++){
            this.enemies[i] = new EnemyWrapper((Enemy)enemies[i]);
        }
    }

    @XmlElement(name="Player")
    public BodyWrapper getPlayer(){
        return this.player;
    }
    public void setPlayer(BodyWrapper player) {
        this.player = player;
    }


    @XmlElement(name="Enemies")
    public EnemyWrapper[] getEnemies() {
        return enemies == null ? null : enemies.clone();
    }
    public void setEnemies(EnemyWrapper[] enemies) {
        this.enemies = enemies == null ? null : enemies.clone();
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
        this.loadEnemies = new IConsciousness[enemies.length];
        for(int i = 0; i < enemies.length; i++){
            this.loadEnemies[i] = enemies[i].getObject(this.loadLevel);
            this.loadLevel.addBody(this.loadEnemies[i].readBody());
        }
        this.loadLevel.setPlayer(this.loadPlayer);
    }
    public IBody loadPlayer(){
        if(loadPlayer == null) loadGame();
        return this.loadPlayer;
    }
    public IConsciousness[] loadEnemies(){
        if(loadEnemies == null) loadGame();
        return loadEnemies == null ? null : loadEnemies.clone();
    }
    public ILevel loadLevel(){
        if(loadLevel == null) loadGame();
        return this.loadLevel;
    }

}
