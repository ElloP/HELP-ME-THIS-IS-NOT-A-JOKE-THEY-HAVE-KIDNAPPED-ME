package com.helpme.app.saveload;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IItemFactory;
import com.helpme.app.world.item.IReadItem;
import com.helpme.app.world.tile.IReadTile;
import com.helpme.app.world.tile.ITile;
import com.helpme.app.world.tile.Tile;
import com.helpme.app.world.tile.edge.EdgeType;
import com.helpme.app.world.tile.edge.IEdge;

import javax.xml.bind.annotation.XmlElement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Klas on 2017-05-01.
 */
public class TileWrapper implements ILoadable<ITile> {
    private Vector2Wrapper position;
    private ItemWrapper[] items;
    private EdgeWrapper north,east,south,west;

    //TODO (klas) Save edges
    public TileWrapper(){}

    public TileWrapper(IReadTile tile, Vector2f position){
        this.position = new Vector2Wrapper(position);
        IReadItem[] tileItems = tile.readItems().getValue(); //TODO (klas) Fix maybe nothing case?
        this.items = new ItemWrapper[tileItems.length];
        for(int i = 0; i < tileItems.length; i++){
            this.items[i] = new ItemWrapper(tileItems[i]);
        }
        this.north = new EdgeWrapper(tile.readEdge(Vector2f.up));
        this.east = new EdgeWrapper(tile.readEdge(Vector2f.right));
        this.south = new EdgeWrapper(tile.readEdge(Vector2f.down));
        this.west = new EdgeWrapper(tile.readEdge(Vector2f.left));

    }
    @XmlElement(name="position")
    public Vector2Wrapper getPosition(){ return this.position; }
    public void setPosition(Vector2Wrapper pos) { this.position = pos;}
    @XmlElement(name="items")
    public ItemWrapper[] getItems(){
        return this.items;
    }
    public void setItems(ItemWrapper[] items) {
        this.items = new ItemWrapper[items.length];
        for(int i = 0; i < items.length; i++){
            this.items[i] = new ItemWrapper(items[i].getName());
        }
    }
    @XmlElement(name="north")
    public EdgeWrapper getNorth(){
        return this.north;
    }

    public void setNorth(EdgeWrapper north) {
        this.north = north;
    }

    public void setEast(EdgeWrapper east) {
        this.east = east;
    }

    public void setSouth(EdgeWrapper south) {
        this.south = south;
    }

    public void setWest(EdgeWrapper west) {
        this.west = west;
    }

    @XmlElement(name="east")

    public EdgeWrapper getEast(){
        return this.east;
    }
    @XmlElement(name="south")
    public EdgeWrapper getSouth(){
        return this.south;
    }
    @XmlElement(name="west")
    public EdgeWrapper getWest(){
        return this.west;
    }
    public String toString(){
        String result = "";
        result += "Tile at " + position;
        if(items != null){
            for(ItemWrapper item : items){
                if(item != null) result += "\nItem: " + (item.getName());
            }
        }
        return result;
    }

    @Override
    public ITile getObject() {
        IItem[] tmp = null;
        if(items != null){
            tmp = new IItem[items.length];
            for(int i = 0; i < items.length; i++){
                tmp[i] = items[i].getObject();
            }
        }
        Map<Vector2f, IEdge> edges = new HashMap<Vector2f, IEdge>();
        edges.put(Vector2f.up, north.getObject());
        edges.put(Vector2f.right, east.getObject());
        edges.put(Vector2f.down, south.getObject());
        edges.put(Vector2f.left, west.getObject());

        return new Tile(tmp, edges);

    }
    public Vector2f getLocation(){
        return this.position.getObject();
    }
}
