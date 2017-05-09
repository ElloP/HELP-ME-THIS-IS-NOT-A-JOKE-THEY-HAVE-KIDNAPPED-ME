package com.helpme.app.saveload;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IItemFactory;
import com.helpme.app.world.item.IReadItem;
import com.helpme.app.world.tile.IReadTile;
import com.helpme.app.world.tile.ITile;
import com.helpme.app.world.tile.Tile;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Klas on 2017-05-01.
 */
public class TileWrapper implements ILoadable<ITile> {
    private Vector2Wrapper position;
    private ItemWrapper[] items;
    //TODO (klas) Save edges
    public TileWrapper(){}

    public TileWrapper(IReadTile tile, Vector2f position){
        this.position = new Vector2Wrapper(position);
        IReadItem[] tileItems = tile.readItems().getValue(); //TODO (klas) Fix maybe nothing case?
        this.items = new ItemWrapper[tileItems.length];
        for(int i = 0; i < tileItems.length; i++){
            this.items[i] = new ItemWrapper(tileItems[i]);
        }
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
                //tmp[i] TODO(klas)
            }
        }

        return new Tile(null);

    }
}
