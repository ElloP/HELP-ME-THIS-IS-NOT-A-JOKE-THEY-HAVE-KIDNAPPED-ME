package com.helpme.app.game.saveload;


import com.helpme.app.game.model.body.inventory.IInventory;
import com.helpme.app.game.model.body.inventory.IReadInventory;
import com.helpme.app.game.model.body.inventory.concrete.InventoryFactory;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IReadItem;
import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.utils.maybe.Maybe;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Klas on 2017-04-29.
 */
@XmlRootElement(name="inventory")
public class InventoryWrapper implements ILoadable<IInventory> {

    ItemWrapper defaultItemWrapper;
    ItemWrapper[] itemWrappers;
    ItemWrapper[] keyWrappers;

    public InventoryWrapper() {}

    public InventoryWrapper(IReadInventory inventory) {
        List<Maybe<IReadItem>> inventoryItems = inventory.readItems();
        List<Maybe<IReadItem>> inventoryKeys = inventory.readKeychain();
        this.itemWrappers = new ItemWrapper[inventoryItems.size()];
        this.keyWrappers = new ItemWrapper[inventoryKeys.size()];
        inventory.readDefaultItem().run(item -> this.defaultItemWrapper = new ItemWrapper(item));

        for(int i = 0; i < inventoryItems.size(); i++){
            int index = i;
            if(inventoryItems.get(i).isJust()){
                this.itemWrappers[index] = new ItemWrapper(inventoryItems.get(i).getValue());
            }
            else{
                this.itemWrappers[index] = new ItemWrapper("empty");
            }

        }

        for(int i = 0; i < inventoryKeys.size(); i++){
            int index = i;
            inventoryKeys.get(i).run(item -> this.keyWrappers[index] = new ItemWrapper(item));
        }
    }

    @XmlElement(name = "default_weapon")
    public ItemWrapper getDefaultItem() { return defaultItemWrapper; }
    public void setDefaultItem(ItemWrapper itemWrapper){
        this.defaultItemWrapper = itemWrapper;
    }
    @XmlElementWrapper(name="keys")
    @XmlElement(name = "key")
    public ItemWrapper[] getKeys() {
        return keyWrappers == null ? null : keyWrappers.clone();
    }
    public void setKeys(ItemWrapper[] keys) {
        this.keyWrappers = new ItemWrapper[keys.length];
        for (int i = 0; i < keys.length; i++) {
            this.keyWrappers[i] = new ItemWrapper(keys[i].getName());
        }
    }

    @XmlElementWrapper(name="items")
    @XmlElement(name = "item")
    public ItemWrapper[] getItems() {
        return itemWrappers == null ? null : itemWrappers.clone();
    }
    public void setItems(ItemWrapper[] items) {
        this.itemWrappers = new ItemWrapper[items.length];
        for (int i = 0; i < items.length; i++) {
            this.itemWrappers[i] = new ItemWrapper(items[i].getName());
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\nInventory: ");

        if(itemWrappers != null){
            for (ItemWrapper item : itemWrappers) {
                if (item != null) result.append("\n\tItem:\t" + (item.getName()));
            }
        }
        if(keyWrappers != null){
            for (ItemWrapper key : keyWrappers) {
                if (key != null) result.append("\n\tKey:\t" + (key.getName()));
            }
        }
        return result.toString();
    }

    @Override
    public IInventory getObject() {
        return InventoryFactory.createInventory(fixItems(itemWrappers),defaultItemWrapper.getObject(),fixItems(keyWrappers));
    }

    private IItem[] fixItems(ItemWrapper[] items){
        if(items == null) return new IItem[]{};
        IItem[] result = new IItem[items.length];
        for(int i = 0; i < result.length; i++){
            result[i] = items[i].getObject();
        }
        return result;
    }
}
