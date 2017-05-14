package com.helpme.app.world.character.inventory;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IReadItem;
import com.helpme.app.world.item.visitor.Stack;
import com.helpme.app.utils.Clone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 2017-04-09.
 */
public class Inventory implements IInventory {
    private IItem defaultItem;
    private List<Maybe<IItem>> keychain;
    private List<Maybe<IItem>> items;
    private int activeItemIndex = -1;

    public Inventory(List<Maybe<IItem>> items, IItem defaultItem, List<Maybe<IItem>> keychain) {
        this.defaultItem = defaultItem;
        this.items = items;
        this.keychain = keychain;
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
        return activeItemIndex < 0 || activeItemIndex >= items.size() ? new Nothing() : Maybe.wrap(items.get(activeItemIndex));
    }

    @Override
    public Maybe<IItem> getDefaultItem() {
        return Maybe.wrap(defaultItem);
    }

    @Override
    public void addKey(IItem key) {
        keychain.add(Maybe.wrap(key));
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
    }

    @Override
    public boolean addStack(IItem item, int amount) {
        for (Maybe<IItem> maybeStack : items) {
            if (maybeStack.check(stack -> stack.equals(item))) {
                maybeStack.run(stack -> stack.accept(new Stack(amount)));
                return true;
            }
        }
        return false;
    }


    @Override
    public void changeActiveItem(int itemIndex) {
        activeItemIndex = Math.floorMod(itemIndex, items.size());
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public List<Maybe<IItem>> dropItems() {
        List<Maybe<IItem>> droppedItems = items;
        items = new ArrayList<>(items.size());
        return droppedItems;
    }

    @Override
    public List<Maybe<IReadItem>> readItems() {
        return Maybe.wrap(items);
    }

    @Override
    public List<Maybe<IReadItem>> readKeychain() {
        return Maybe.wrap(keychain);
    }

    @Override
    public Maybe<IReadItem> readItem(int index) {
        return Maybe.wrap(getItem(index));
    }

    @Override
    public boolean hasKey(IItem key) {
        for(Maybe<IItem> maybeKey : keychain){
            if(maybeKey.equals(Maybe.wrap(key))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public IInventory clone() {
        return new Inventory(Clone.maybeList(items), defaultItem.clone(), Clone.maybeList(keychain));
    }
}
