package com.helpme.app.itemtest;

import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.concrete.Consumable;
import com.helpme.app.model.item.concrete.ItemFactory;
import com.helpme.app.model.item.concrete.Key;
import com.helpme.app.model.item.concrete.Single;
import com.helpme.app.model.item.effect.IEffect;
import com.helpme.app.utils.functions.IAction;
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

    @Test
    public void testEqualsSingle() {
        IItem single0 = new Single("test", IEffect.empty(), IEffect.empty());
        IItem single1 = new Single("test", t -> t.damage(2), t -> t.heal(4));
        assert (single0.equals(single1));
    }

    @Test
    public void testEqualsKey() {
        IItem key0 = new Key("test");
        IItem key1 = new Key("test");
        assert (key0.equals(key1));
    }

    @Test
    public void testEqualsConsumable() {
        IItem consumable0 = new Consumable("test", 4, IEffect.empty(), IEffect.empty());
        IItem consumable1 = new Consumable("test", 2, t -> t.damage(2), t -> t.heal(4));
        assert (consumable0.equals(consumable1));
    }
}
