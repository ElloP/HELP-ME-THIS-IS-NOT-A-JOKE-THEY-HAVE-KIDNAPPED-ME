package com.helpme.app.saveload;


import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.consciousness.IReadSurroundings;
import com.helpme.app.world.level.ILevel;
import com.helpme.app.world.level.concrete.LevelFactory;
import com.helpme.app.world.tile.ITile;

import javax.xml.bind.annotation.XmlElement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Klas on 2017-05-02.
 */
public class LevelWrapper implements ILoadable<ILevel>{
    private TileWrapper[] tiles;
    private Vector2Wrapper startingPosition;

    public LevelWrapper(){}

    public LevelWrapper(IReadSurroundings level){

        startingPosition = new Vector2Wrapper(level.readStartingPoint());

        Map<Vector2f, ITile> levelTiles = level.getTiles();
        this.tiles = new TileWrapper[levelTiles.size()];
        Iterator<Vector2f> keys = levelTiles.keySet().iterator();
        int i = 0;
        while(keys.hasNext()){
            Vector2f tmp = keys.next();
            this.tiles[i] = new TileWrapper(levelTiles.get(tmp),tmp);
            i++;
        }
    }
    @XmlElement(name="Tiles")
    public TileWrapper[] getTiles() {return this.tiles; }
    public void setTiles(TileWrapper[] tiles){ this.tiles = tiles;}
    public String toString(){
        String result = "";
        for(TileWrapper t : tiles){
            result += "\n" + t;
        }
        return result;
    }
    @XmlElement(name="StartingPosition")
    public Vector2Wrapper getStartingPosition(){
        return this.startingPosition;
    }
    public void setStartingPosition(Vector2Wrapper pos){
        this.startingPosition = pos;
    }


    @Override
    public ILevel getObject() {
        Map<Vector2f, ITile> loadTiles = new HashMap<>();
        for(TileWrapper tw : tiles){
            loadTiles.put(tw.getLocation(),tw.getObject());
        }
        return LevelFactory.createLevel(loadTiles,startingPosition.getObject());
    }
}
