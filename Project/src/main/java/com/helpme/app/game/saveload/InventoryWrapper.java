package com.helpme.app.game.saveload;


import com.helpme.app.game.model.body.inventory.IInventory;
import com.helpme.app.game.model.body.inventory.IReadInventory;
import com.helpme.app.game.model.body.inventory.concrete.InventoryFactory;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IReadItem;
import com.helpme.app.game.saveload.item.ItemWrapper;
import com.helpme.app.game.saveload.item.KeyWrapper;
import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.utils.maybe.Maybe;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Klas on 2017-04-29.
 */
@XmlRootElement(name = "inventory")
public class InventoryWrapper implements ILoadable<IInventory> {

    @XmlElement(name = "default_weapon")
    private ItemWrapper defaultItemWrapper;

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private ItemWrapper[] itemWrappers;

    @XmlElementWrapper(name = "keychain")
    @XmlElement(name = "key")
    private KeyWrapper[] keyWrappers;

    public InventoryWrapper() {
    }

    public InventoryWrapper(IReadInventory inventory) {
        List<Maybe<IReadItem>> inventoryItems = inventory.readItems();
        List<Maybe<IReadItem>> inventoryKeys = inventory.readKeychain();
        this.itemWrappers = new ItemWrapper[inventoryItems.size()];
        this.keyWrappers = new KeyWrapper[inventoryKeys.size()];
        inventory.readDefaultItem().run(item -> this.defaultItemWrapper = new ItemWrapper(Maybe.wrap(item)));

        for (int i = 0; i < inventoryItems.size(); i++) {
            this.itemWrappers[i] = new ItemWrapper(inventoryItems.get(i));
        }

        for (int i = 0; i < inventoryKeys.size(); i++) {
            this.keyWrappers[i] = new KeyWrapper(inventoryKeys.get(i));
        }
    }

    @Override
    public IInventory getObject() {
        List<Maybe<IItem>> items = new ArrayList<>();
        List<Maybe<IItem>> keys = new ArrayList<>();
        Maybe<IItem> defaultWeapon = defaultItemWrapper.getObject();

        for (ItemWrapper itemWrapper : itemWrappers) {
            items.add(itemWrapper.getObject());
        }

        for (KeyWrapper keyWrapper : keyWrappers) {
            keys.add(keyWrapper.getObject());
        }

        return InventoryFactory.createInventory(items, defaultWeapon.isJust() ? defaultWeapon.getValue() : null, keys);
    }
}
