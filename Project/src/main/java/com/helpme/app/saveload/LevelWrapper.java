package com.helpme.app.saveload;


import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.consciousness.IReadSurroundings;
import com.helpme.app.world.tile.ITile;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Klas on 2017-05-02.
 */
@XmlRootElement(name="Level")
public class LevelWrapper {
    private BodyWrapper player;
    private BodyWrapper[] monsters;
    private Vector2Wrapper startingPoint;
    private TileWrapper[] tiles;

    public LevelWrapper(){}

    public LevelWrapper(IReadSurroundings level){
        this.player = new BodyWrapper(level.readPlayer().getValue()); //TODO (klas) maybe check?
        IReadBody[] levelMonsters = level.readMonsters();
        monsters = new BodyWrapper[levelMonsters.length];
        for(int i = 0; i < levelMonsters.length; i++){
            monsters[i] = new BodyWrapper(levelMonsters[i]);
        }
        Map<Vector2f, ITile> levelTiles = level.getTiles();
        this.tiles = new TileWrapper[levelTiles.size()];
        Iterator<Vector2f> keys = levelTiles.keySet().iterator();
        int i = 0;
        while(keys.hasNext()){
            Vector2f tmp = keys.next();
            this.tiles[i] = new TileWrapper(levelTiles.get(tmp),tmp);
            i++;
        }
        startingPoint = new Vector2Wrapper(level.readStartingPoint());
    }

    @XmlElement(name="Player")
    public BodyWrapper getPlayer(){
        return this.player;
    }
    public void setPlayer(BodyWrapper player) {
        this.player = player;
    }
    @XmlElement(name="Monsters")
    public BodyWrapper[] getMonsters() {
        return this.monsters;
    }
    public void setMonsters(BodyWrapper[] monsters) {
        this.monsters = monsters;
    }
    @XmlElement(name="StartingPoint")
    public Vector2Wrapper getStartingPoint(){return this.startingPoint;}
    @XmlElement(name="Tiles")
    public TileWrapper[] getTiles() {return this.tiles; }
    public void setTiles(TileWrapper[] tiles){ this.tiles = tiles;}
    public void setStartingPoint(Vector2Wrapper v){this.startingPoint = v;}
    public String toString(){
        String result = "";
        result += "Starting Point: " + startingPoint;
        result += "\nPlayer: " + player.toString();
        for(BodyWrapper m : monsters){
            result += "\nMonster: "+ m.toString();
        }
        for(TileWrapper t : tiles){
            result += "\n" + t;
        }
        return result;
    }
}
