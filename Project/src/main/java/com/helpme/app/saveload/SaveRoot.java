package com.helpme.app.saveload;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.concrete.Enemy;
import com.helpme.app.game.model.consciousness.concrete.Player;
import com.helpme.app.game.model.level.ILevel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Klas on 2017-05-09.
 *
 * The "root" of the save graph.
 */
@XmlRootElement(name="Root")
public class SaveRoot {
    private BodyWrapper player;             //Wrapper for saving a players body
    private EnemyWrapper[] enemies;         //Array to save all enemies
    private LevelWrapper level;             //Wrapper to save level
    private ILevel loadLevel;               //The complete level after loading the game
    private IBody loadPlayer;               //The complete body of the player after loading the game
    private IConsciousness[] loadEnemies;   //The complete list of enemies after loading the game

    public SaveRoot(){}

    public SaveRoot(ILevel level, IBody player, IConsciousness[] enemies){

//        level.readPlayer().run(p -> this.player = new BodyWrapper(p)); //TODO (klas) maybe check?
        // Wrap body of player
        this.player = new BodyWrapper(player);
        // Wrap the level
        this.level = new LevelWrapper(level);
        // Wrap enemies
        this.enemies = new EnemyWrapper[enemies.length];
        for(int i = 0; i < enemies.length; i++){
            this.enemies[i] = new EnemyWrapper((Enemy)enemies[i]);
        }
    }
    // Create a tag for player in the xml-document
    @XmlElement(name="Player")
    public BodyWrapper getPlayer(){
        return this.player;
    }
    public void setPlayer(BodyWrapper player) {
        this.player = player;
    }

    // Create a tag for each enemy in the game
    @XmlElement(name="Enemy")
    public EnemyWrapper[] getEnemies() {
        return enemies == null ? null : enemies.clone();
    }
    public void setEnemies(EnemyWrapper[] enemies) {
        this.enemies = enemies == null ? null : enemies.clone();
    }

    // Create tag for the level
    @XmlElement(name="Level")
    public LevelWrapper getLevel() {
        return level;
    }
    public void setLevel(LevelWrapper level) {
        this.level = level;
    }

    // Loads game from xml
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
    public Player loadPlayer(){
        if(loadPlayer == null) loadGame();
        Player player = new Player(loadPlayer, loadLevel);
        return player;
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
