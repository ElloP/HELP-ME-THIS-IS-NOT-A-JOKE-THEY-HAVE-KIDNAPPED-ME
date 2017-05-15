package com.helpme.app;

import com.helpme.app.Mock.MockItem;
import com.helpme.app.Mock.MockWorld0;
import com.helpme.app.Mock.MockWorld1;
import com.helpme.app.saveload.LevelWrapper;
import com.helpme.app.saveload.BodyWrapper;
import com.helpme.app.saveload.SavePlayer;
import com.helpme.app.saveload.SaveRoot;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.Body;
import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.character.inventory.Inventory;
import com.helpme.app.world.consciousness.Enemy;
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
    IBody Body;
    Vector2f hitpoints;

    @Before
    public void init() throws JAXBException {
        this.context = JAXBContext.newInstance(SaveRoot.class);
       // this.context = JAXBContext.newInstance(BodyWrapper.class);
    }

    @Test
    public void testMarshaller() throws JAXBException {
        items = new IItem[]{MockItem.weapon, MockItem.potion, null, null};
        inventory = new Inventory(items, MockItem.defaultWeapon, new IItem[]{MockItem.key});
        hitpoints = new Vector2f(100,50);
        IBody Body = new Body(inventory,Vector2f.right,Vector2f.left,hitpoints);
        String fileTest = "test.xml";
        SavePlayer save = new SavePlayer();
        save.marshall(Body,fileTest);

        System.out.println(save.unmarshall(fileTest).toString());
    }

    @Test
    public void saveTest2() throws JAXBException {
        MockWorld1 mock = new MockWorld1();
        mock.player.setPlayerPosition(new Vector2f(1,1));
        mock.level.readPlayer().getValue();
        Enemy[] enemy = {(Enemy) mock.enemyConsciousness0};
        SaveRoot saveroot = new SaveRoot(mock.level,mock.player.readBody(), enemy);
        File file = new File("test.xml");
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(saveroot, file);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        SaveRoot loadroot = (SaveRoot) unmarshaller.unmarshal(file);

    }
}

