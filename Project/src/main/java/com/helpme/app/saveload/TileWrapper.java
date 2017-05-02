package com.helpme.app.saveload;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.item.IReadItem;
import com.helpme.app.world.tile.IReadTile;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by Klas on 2017-05-01.
 */
public class TileWrapper {
    //edges
    private ItemWrapper[] items;

    public TileWrapper(){}

    public TileWrapper(IReadTile tile){
        IReadItem[] tileItems = tile.readItems().getValue(); //TODO (klas) Fix maybe nothing case?
        this.items = new ItemWrapper[tileItems.length];
        for(int i = 0; i < tileItems.length; i++){
            this.items[i] = new ItemWrapper(tileItems[i]);
        }
    }
    @XmlElement(name="items")
    public ItemWrapper[] getItems(){
        return items;
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
