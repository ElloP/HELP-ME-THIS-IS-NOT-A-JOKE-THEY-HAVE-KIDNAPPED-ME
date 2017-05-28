package com.helpme.app.game.saveload;


import com.helpme.app.game.model.consciousness.IReadSurroundings;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.game.model.level.concrete.LevelFactory;
import com.helpme.app.game.model.tile.ITile;
import com.helpme.app.game.model.tile.concrete.Tile;
import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.utils.mathl.Vector2f;

import javax.xml.bind.annotation.XmlElement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Klas on 2017-05-02.
 */
public class LevelWrapper implements ILoadable<ILevel> {
    private TileWrapper[] tiles;
    private Vector2Wrapper startingPosition;

    public LevelWrapper(){}

    public LevelWrapper(IReadSurroundings level){

        startingPosition = new Vector2Wrapper(level.readStartingPoint());

        Map<Vector2f, ITile> levelTiles = level.getTiles();
        this.tiles = new TileWrapper[levelTiles.size()];
        int i = 0;
        for(Map.Entry<Vector2f, ITile> entry: levelTiles.entrySet()){
            this.tiles[i] = new TileWrapper(entry.getKey(), entry.getValue());
            i++;
        }
    }
    @XmlElement(name="Tiles")
    public TileWrapper[] getTiles() {return tiles == null ? null : tiles.clone(); }
    public void setTiles(TileWrapper[] tiles){ this.tiles = tiles == null ? null : tiles.clone();}

    @XmlElement(name="StartingPosition")
    public Vector2Wrapper getStartingPosition(){
        return this.startingPosition;
    }
    public void setStartingPosition(Vector2Wrapper pos){
        this.startingPosition = pos;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        for(TileWrapper t : tiles){
            result.append("\n").append(t);
        }
        return result.toString();
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
