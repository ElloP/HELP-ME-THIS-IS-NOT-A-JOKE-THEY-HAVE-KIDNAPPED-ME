package com.helpme.app.saveload;


import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.character.inventory.IReadInventory;
import com.helpme.app.world.character.inventory.Inventory;
import com.helpme.app.world.character.inventory.InventoryFactory;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IReadItem;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Klas on 2017-04-29.
 */
@XmlRootElement(name="inventory")
public class InventoryWrapper implements ILoadable<IInventory> {

    ItemWrapper[] items;
    ItemWrapper[] keys;

    public InventoryWrapper() {}

    public InventoryWrapper(IReadInventory inventory) {
        List<Maybe<IReadItem>> inventoryItems = inventory.readItems();
        List<Maybe<IReadItem>> inventoryKeys = inventory.readKeychain();
        this.items = new ItemWrapper[inventoryItems.size()];
        this.keys = new ItemWrapper[inventoryKeys.size()];

        for(int i = 0; i < inventoryItems.size(); i++){
            int index = i;
            if(inventoryItems.get(i).isJust()){
                this.items[index] = new ItemWrapper(inventoryItems.get(i).getValue());
            }
            else{
                this.items[index] = new ItemWrapper("empty");
            }

        }

        for(int i = 0; i < inventoryKeys.size(); i++){
            int index = i;
            inventoryKeys.get(i).run(item -> this.keys[index] = new ItemWrapper(item));
        }
    }


    @XmlElementWrapper(name="keys")
    @XmlElement(name = "key")
    public ItemWrapper[] getKeys() {
        return this.keys;
    }
    public void setKeys(ItemWrapper[] keys) {
        this.keys = new ItemWrapper[keys.length];
        for (int i = 0; i < keys.length; i++) {
            this.keys[i] = new ItemWrapper(keys[i].getName());
        }
    }

    @XmlElementWrapper(name="items")
    @XmlElement(name = "item")
    public ItemWrapper[] getItems() {
        return this.items;
    }
    public void setItems(ItemWrapper[] items) {
        this.items = new ItemWrapper[items.length];
        for (int i = 0; i < items.length; i++) {
            this.items[i] = new ItemWrapper(items[i].getName());
        }
    }

    public String toString() {
        String result = "\nInventory: ";
        if(items != null){
            for (ItemWrapper item : items) {
                if (item != null) result += "\n\tItem:\t" + (item.getName());
            }
        }
        if(keys != null){
            for (ItemWrapper key : keys) {
                if (key != null) result += "\n\tKey:\t" + (key.getName());
            }
        }
        return result;
    }

    @Override
    public IInventory getObject() {
        return InventoryFactory.createInventory(fixItems(items),null,fixItems(keys));
    }

    private IItem[] fixItems(ItemWrapper[] items){
        IItem[] result = new IItem[items.length];
        for(int i = 0; i < result.length; i++){
            result[i] = items[i].getObject();
        }
        return result;
    }
}
