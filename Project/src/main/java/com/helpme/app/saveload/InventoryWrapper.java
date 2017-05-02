package com.helpme.app.saveload;


import com.helpme.app.world.character.inventory.IReadInventory;
import com.helpme.app.world.item.IReadItem;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Klas on 2017-04-29.
 */
public class InventoryWrapper {

    ItemWrapper[] items;

    public InventoryWrapper(){};

    public InventoryWrapper(IReadInventory inventory){
        if(inventory == null || inventory.readItems().isNothing()){
            items = new ItemWrapper[]{};
        } else{
            IReadItem[] list = inventory.readItems().getValue();
            items = new ItemWrapper[list.length];
            for(int i = 0; i < list.length; i++){
                items[i] = new ItemWrapper(list[i]);
            }
        }
    }

    public void setItems(ItemWrapper[] items) {
        this.items = new ItemWrapper[items.length];
        for(int i = 0; i < items.length; i++){
            this.items[i] = new ItemWrapper(items[i].getName());
        }
    }

    @XmlElement(name="item")
    public ItemWrapper[] getItems(){
        return items;
    }
    public String toString(){
        String result = "";
        for(ItemWrapper item : items){
            if(item != null) result += "\nItem: " + (item.getName());
        }
        return result;
    }
}
