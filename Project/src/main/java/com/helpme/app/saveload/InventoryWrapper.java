package com.helpme.app.saveload;


import com.helpme.app.world.character.inventory.IReadInventory;
import com.helpme.app.world.item.IReadItem;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Klas on 2017-04-29.
 */
public class InventoryWrapper {

    ItemWrapper[] items;
    ItemWrapper[] keys;

    public InventoryWrapper(){};

    public InventoryWrapper(IReadInventory inventory){
        if(inventory == null){
            if(inventory.readItems().isNothing()){
                items = new ItemWrapper[]{};
            } else{
                IReadItem[] list = inventory.readItems().getValue();
                items = new ItemWrapper[list.length];
                for(int i = 0; i < list.length; i++){
                    items[i] = new ItemWrapper(list[i]);
                }
            }

            if(inventory.readKeychain().isNothing()){
                keys = new ItemWrapper[]{};
            } else {
                IReadItem[] list = inventory.readKeychain().getValue();
                keys = new ItemWrapper[list.length];
                for(int i = 0; i < list.length; i++){
                    keys[i] = new ItemWrapper(list[i]);
                }
            }
        }
    }

    public void setItems(ItemWrapper[] items) {
        this.items = new ItemWrapper[items.length];
        for(int i = 0; i < items.length; i++){
            this.items[i] = new ItemWrapper(items[i].getName());
        }
    }
    @XmlElement(name="keys")
    public ItemWrapper[] getKeys(){ return this.keys;}
    @XmlElement(name="items")
    public ItemWrapper[] getItems(){
        return this.items;
    }
    public String toString(){
        String result = "";
        //for(ItemWrapper item : items){
        //    if(item != null) result += "\nItem: " + (item.getName());
        //}
        return result;
    }
}
