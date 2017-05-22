package com.helpme.app.itemtest;

import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.concrete.Consumable;
import com.helpme.app.model.item.concrete.ItemFactory;
import com.helpme.app.model.item.concrete.Key;
import com.helpme.app.model.item.concrete.Single;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-21.
 */

public class ItemTest {

    @Test
    public void testKey() {
        IItem key = new Key("key");
        assert (key.accept(new MockVisitor()));
    }

    @Test
    public void testSingle() {
        IItem single = new Single("item", t -> {
        }, t -> {
        });
        assert (single.accept(new MockVisitor()));
    }

    @Test
    public void testConsumable() {
        IItem consumable = new Consumable("item", 0, t -> {
        }, t -> {
        });
        assert (consumable.accept(new MockVisitor()));
    }


}
