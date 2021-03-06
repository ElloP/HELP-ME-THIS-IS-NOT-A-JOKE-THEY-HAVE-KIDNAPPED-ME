package com.helpme.app.game.model.body.inventory.concrete;

import com.helpme.app.game.model.body.concrete.visitor.Stack;
import com.helpme.app.game.model.body.inventory.IInventory;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IReadItem;
import com.helpme.app.utils.Copy;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 2017-04-09.
 */
public class Inventory implements IInventory {
    private IItem defaultItem;              //The item that is active when you have no other item equipped, like hands
    private List<Maybe<IItem>> keyChain;    //All keys in inventory
    private List<Maybe<IItem>> items;       //All items in inventory, excluding keys
    private int activeItemIndex = 0;        //Index of current active item

    public Inventory(List<Maybe<IItem>> items, IItem defaultItem, List<Maybe<IItem>> keyChain) {
        this.defaultItem = defaultItem;
        this.items = items;
        this.keyChain = keyChain;
    }

    public List<Maybe<IItem>> getItems(){
        return items;
    }

    public List<Maybe<IItem>> getKeyChain(){
        return keyChain;
    }

    @Override
    public boolean hasItem(IItem item) {
        for (Maybe<IItem> maybeItem : items) {
            if (maybeItem.check(i -> i.equals(item))) return true;
        }
        return false;
    }

    @Override
    public Maybe<IItem> getItem(int index) {
        return index < 0 || index >= items.size() ? new Nothing<>() : Maybe.wrap(items.get(index));
    }


    @Override
    public Maybe<IItem> getActiveItem() {
        return items.size() == 0 ? new Nothing() : Maybe.wrap(items.get(activeItemIndex));
    }

    @Override
    public Maybe<IItem> getDefaultItem() {
        return Maybe.wrap(defaultItem);
    }

    @Override
    public void addKey(IItem key) {
        keyChain.add(Maybe.wrap(key));
    }

    @Override
    public boolean addItem(IItem item) {
        return swapItem(new Nothing(), Maybe.wrap(item));
    }

    @Override
    public boolean deleteItem(IItem item) {
        return swapItem(Maybe.wrap(item), new Nothing());
    }

    @Override
    public Maybe<IItem> dropItem(int index) {
        Maybe<IItem> maybeItem = Maybe.wrap(items.get(index));
        maybeItem.run(i -> {
            items.set(index, new Nothing<>());
        });
        return maybeItem;
    }

    private boolean swapItem(Maybe<IItem> from, Maybe<IItem> to) {
        int index = items.indexOf(from);
        if (index == -1) return false;
        items.set(index, Maybe.wrap(to));
        return true;
    }

    @Override
    public void setItems(List<Maybe<IItem>> items) {
        if (items == null) return;
        this.items = items;
        setActiveItem(0);
    }

    //Add item to stack. Item have to be of same type and stackable
    @Override
    public boolean addStack(IItem item, int amount) {
        for (Maybe<IItem> maybeStack : items) {
            if (maybeStack.check(stack -> stack.equals(item))) {
                return maybeStack.check(stack -> stack.accept(new Stack(amount)));
            }
        }
        return false;
    }


    @Override
    public void setActiveItem(int itemIndex) {
        activeItemIndex = items.size() == 0 ? 0 : Math.floorMod(itemIndex, items.size());
    }

    @Override
    public int readSize() {
        return items.size();
    }

    @Override
    public int readActiveItemIndex() {
        return activeItemIndex;
    }

    @Override
    public List<Maybe<IItem>> dropItems() {
        List<Maybe<IItem>> droppedItems = items;
        items = new ArrayList<>(items.size());
        return droppedItems;
    }

    @Override
    public List<Maybe<IReadItem>> readItems() {
        return Maybe.cast(items);
    }

    @Override
    public List<Maybe<IReadItem>> readKeychain() {
        return Maybe.cast(keyChain);
    }

    @Override
    public Maybe<IReadItem> readItem(int index) {
        return Maybe.wrap(getItem(index));
    }

    @Override
    public Maybe<IReadItem> readDefaultItem() {
        return Maybe.wrap(defaultItem);
    }

    @Override
    public boolean hasKey(IItem key) {
        for(Maybe<IItem> maybeKey : keyChain){
            if(maybeKey.equals(Maybe.wrap(key))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public IInventory copy() {
        return InventoryFactory.createInventory(Copy.maybeList(items), defaultItem.copy(), Copy.maybeList(keyChain));
    }
}
