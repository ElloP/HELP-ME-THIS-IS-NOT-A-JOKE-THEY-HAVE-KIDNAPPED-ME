package com.helpme.app.itemtest;

import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.concrete.ItemFactory;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-21.
 */
public class ItemTest {

    @Test
    public void testKey(){
        IItem key = ItemFactory.createKey("key");
        assert (key.accept(new MockVisitor()));
    }

    @Test
    public void testSingle(){
        IItem single = ItemFactory.club();
        assert (single.accept(new MockVisitor()));
    }

    @Test
    public void testConsumable(){
        IItem consumable = ItemFactory.potion();
        assert (consumable.accept(new MockVisitor()));
    }


}
