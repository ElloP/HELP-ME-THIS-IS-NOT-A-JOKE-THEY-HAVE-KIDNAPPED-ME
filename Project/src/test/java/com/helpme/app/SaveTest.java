package com.helpme.app;

import com.helpme.app.Mock.MockItem;
import com.helpme.app.saveload.PlayerWrapper;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.character.IMonster;
import com.helpme.app.world.character.Monster;
import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.character.inventory.Inventory;
import com.helpme.app.world.item.IItem;
import com.sun.xml.internal.ws.util.Pool;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Created by Klas on 2017-04-29.
 */
public class SaveTest {

    JAXBContext context;
    IItem[] items;
    IInventory inventory;
    IMonster monster;
    Vector2f hitpoints;

    @Before
    public void init() throws JAXBException {
        this.context = JAXBContext.newInstance(PlayerWrapper.class);
        items = new IItem[]{MockItem.weapon, MockItem.potion, null, null};
        inventory = new Inventory(items, MockItem.defaultWeapon, new IItem[]{MockItem.key});
        hitpoints = new Vector2f(100,50);
        IMonster monster = new Monster(inventory,Vector2f.zero,Vector2f.zero,hitpoints);
    }
    @Test
    public void saveTest() throws JAXBException {
        items = new IItem[]{MockItem.weapon, MockItem.potion, null, null};
        inventory = new Inventory(items, MockItem.defaultWeapon, new IItem[]{MockItem.key});
        hitpoints = new Vector2f(100,50);
        IMonster monster = new Monster(inventory,Vector2f.zero,Vector2f.zero,hitpoints);

        Marshaller marshaller = this.context.createMarshaller();
        System.out.println(monster.toString());
        marshaller.marshal(new PlayerWrapper(monster), new File("test.xml"));
    }
}
