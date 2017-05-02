package com.helpme.app;

import com.helpme.app.Mock.MockItem;
import com.helpme.app.Mock.MockWorld1;
import com.helpme.app.saveload.LevelWrapper;
import com.helpme.app.saveload.PlayerWrapper;
import com.helpme.app.saveload.SavePlayer;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.character.IMonster;
import com.helpme.app.world.character.Monster;
import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.character.inventory.Inventory;
import com.helpme.app.world.item.IItem;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
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
        this.context = JAXBContext.newInstance(LevelWrapper.class);
       // this.context = JAXBContext.newInstance(PlayerWrapper.class);
    }
    @Test
    public void saveTest() throws JAXBException {
        items = new IItem[]{MockItem.weapon, MockItem.potion, null, null};
        inventory = new Inventory(items, MockItem.defaultWeapon, new IItem[]{MockItem.key});
        hitpoints = new Vector2f(100,50);
        IMonster monster = new Monster(inventory,Vector2f.right,Vector2f.left,hitpoints);
        File file = new File("test.xml");
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(new PlayerWrapper(monster), file);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        PlayerWrapper pw = (PlayerWrapper) unmarshaller.unmarshal(file);

        System.out.println(pw);
    }

    @Test
    public void testMarshaller() throws JAXBException {
        items = new IItem[]{MockItem.weapon, MockItem.potion, null, null};
        inventory = new Inventory(items, MockItem.defaultWeapon, new IItem[]{MockItem.key});
        hitpoints = new Vector2f(100,50);
        IMonster monster = new Monster(inventory,Vector2f.right,Vector2f.left,hitpoints);
        String fileTest = "test.xml";
        SavePlayer save = new SavePlayer();
        save.marshall(monster,fileTest);

        System.out.println(save.unmarshall(fileTest).toString());
    }

    @Test
    public void saveTest2() throws JAXBException {
        MockWorld1 mock = new MockWorld1();
        File file = new File("test.xml");
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(new LevelWrapper(mock.level), file);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        LevelWrapper pw = (LevelWrapper) unmarshaller.unmarshal(file);

        System.out.println(pw);
    }
}
