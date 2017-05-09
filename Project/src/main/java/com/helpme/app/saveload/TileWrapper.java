package com.helpme.app.saveload;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.item.IReadItem;
import com.helpme.app.world.tile.IReadTile;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Klas on 2017-05-01.
 */
public class TileWrapper {
    private Vector2Wrapper position;
    private ItemWrapper[] items;

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
        for(ItemWrapper item : items){
            if(item != null) result += "\nItem: " + (item.getName());
        }
        return result;
    }
}
