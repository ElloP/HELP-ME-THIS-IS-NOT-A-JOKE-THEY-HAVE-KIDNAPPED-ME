package com.helpme.app.saveload;


import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.character.inventory.IReadInventory;
import com.helpme.app.world.character.inventory.Inventory;
import com.helpme.app.world.character.inventory.InventoryFactory;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IReadItem;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by Klas on 2017-04-29.
 */
public class InventoryWrapper implements ILoadable<IInventory> {

    ItemWrapper[] items;
    ItemWrapper[] keys;

    public InventoryWrapper() {}

    public InventoryWrapper(IReadInventory inventory) {
        List<Maybe<IReadItem>> inventoryItems = inventory.readItems();
        List<Maybe<IReadItem>> inventoryKeys = inventory.readKeychain();
        items = new ItemWrapper[inventoryItems.size()];
        keys = new ItemWrapper[inventoryKeys.size()];

        for(int i = 0; i < inventoryItems.size(); i++){
            int index = i;
            inventoryItems.get(i).run(item -> items[index] = new ItemWrapper(item));
        }

        for(int i = 0; i < inventoryKeys.size(); i++){
            int index = i;
            inventoryKeys.get(i).run(item -> keys[index] = new ItemWrapper(item));
        }
    }

    public void setItems(ItemWrapper[] items) {
        this.items = new ItemWrapper[items.length];
        for (int i = 0; i < items.length; i++) {
            this.items[i] = new ItemWrapper(items[i].getName());
        }
    }

    @XmlElement(name = "keys")
    public ItemWrapper[] getKeys() {
        return this.keys;
    }

    @XmlElement(name = "items")
    public ItemWrapper[] getItems() {
        return this.items;
    }

    public String toString() {
        String result = "";
        if(items != null){
            for (ItemWrapper item : items) {
                if (item != null) result += "\nItem: " + (item.getName());
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
