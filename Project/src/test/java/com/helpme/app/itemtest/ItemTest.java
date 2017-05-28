package com.helpme.app.itemtest;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.concrete.Consumable;
import com.helpme.app.game.model.item.concrete.Item;
import com.helpme.app.game.model.item.concrete.Key;
import com.helpme.app.game.model.item.concrete.Single;
import com.helpme.app.game.model.item.effect.IEffect;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-21.
 */

public class ItemTest {

    @Test
    public void testGetAttackEffect() {
        MockEffect mockAttack = new MockEffect(false);
        Item item = new Single("item", mockAttack, null);
        IEffect attackEffect = item.getAttackEffect();
        assert (attackEffect == mockAttack);
    }

    @Test
    public void testGetSelfieEffect() {
        MockEffect mockSelfie = new MockEffect(false);
        Item item = new Single("item", null, mockSelfie);
        IEffect selfieEffect = item.getSelfieEffect();
        assert (selfieEffect == mockSelfie);
    }


    @Test
    public void testKeyAccept() {
        IItem key = new Key("key");
        assert (key.accept(new MockVisitor()));
    }

    @Test
    public void testSingleAccept() {
        IItem single = new Single("item", t -> {
        }, t -> {
        });
        assert (single.accept(new MockVisitor()));
    }

    @Test
    public void testConsumableAccept() {
        IItem consumable = new Consumable("item", 0, t -> {
        }, t -> {
        });
        assert (consumable.accept(new MockVisitor()));
    }

    /**
     * Testing equality between different items given that it's used in other tests
     * (new MockEffect(true) means that MockEffect's equals method will return true)
     */

    @Test
    public void testSingleEquals() {
        IItem single0 = new Single("test",new MockEffect(true), new MockEffect(true));
        IItem single1 = new Single("test", new MockEffect(true), new MockEffect(true));
        assert (single0.equals(single1));
    }

    @Test
    public void testSingleNotEqualsDifferentName() {
        IItem single0 = new Single("test0",new MockEffect(true), new MockEffect(true));
        IItem single1 = new Single("test1", new MockEffect(true), new MockEffect(true));
        assert (!single0.equals(single1));
    }

    @Test
    public void testSingleNotEqualsDifferentAttack() {
        IItem single0 = new Single("test",new MockEffect(false), new MockEffect(true));
        IItem single1 = new Single("test", new MockEffect(false), new MockEffect(true));
        assert (!single0.equals(single1));
    }

    @Test
    public void testSingleNotEqualsDifferentSelfie() {
        IItem single0 = new Single("test",new MockEffect(true), new MockEffect(false));
        IItem single1 = new Single("test", new MockEffect(true), new MockEffect(false));
        assert (!single0.equals(single1));
    }

    @Test
    public void testKeyEquals() {
        IItem key0 = new Key("test");
        IItem key1 = new Key("test");
        assert (key0.equals(key1));
    }

    @Test
    public void testKeyNotEquals() {
        IItem key0 = new Key("test0");
        IItem key1 = new Key("test1");
        assert (!key0.equals(key1));
    }

    @Test
    public void testConsumableEquals() {
        IItem consumable0 = new Consumable("test", 2, new MockEffect(true), new MockEffect(true));
        IItem consumable1 = new Consumable("test", 2, new MockEffect(true), new MockEffect(true));
        assert (consumable0.equals(consumable1));
    }

    @Test
    public void testConsumableEqualsDifferentStacks() {
        IItem consumable0 = new Consumable("test", 2, new MockEffect(true), new MockEffect(true));
        IItem consumable1 = new Consumable("test", 4, new MockEffect(true), new MockEffect(true));
        assert (consumable0.equals(consumable1));
    }

    @Test
    public void testConsumableNotEqualsDifferentName() {
        IItem consumable0 = new Consumable("test0", 2, new MockEffect(true), new MockEffect(true));
        IItem consumable1 = new Consumable("test1", 2, new MockEffect(true), new MockEffect(true));
        assert (!consumable0.equals(consumable1));
    }

    @Test
    public void testConsumableNotEqualsDifferentAttack(){
        IItem consumable0 = new Consumable("test", 2, new MockEffect(false), new MockEffect(true));
        IItem consumable1 = new Consumable("test", 2, new MockEffect(false), new MockEffect(true));
        assert (!consumable0.equals(consumable1));
    }

    @Test
    public void testConsumableNotEqualsDifferentSelfie(){
        IItem consumable0 = new Consumable("test", 2, new MockEffect(true), new MockEffect(false));
        IItem consumable1 = new Consumable("test", 2, new MockEffect(true), new MockEffect(false));
        assert (!consumable0.equals(consumable1));
    }
}
