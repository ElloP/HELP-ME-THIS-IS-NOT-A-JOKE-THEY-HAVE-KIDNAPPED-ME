package com.helpme.app;

import com.helpme.app.savetest.TestWorld;
import com.helpme.app.saveload.BodyWrapper;
import com.helpme.app.saveload.SavePlayer;
import com.helpme.app.saveload.SaveRoot;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.body.concrete.BodyFactory;
import com.helpme.app.world.body.inventory.IInventory;
import com.helpme.app.world.body.inventory.concrete.InventoryFactory;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.consciousness.concrete.Enemy;
import com.helpme.app.world.consciousness.concrete.Player;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.concrete.ItemFactory;
import com.helpme.app.world.level.ILevel;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.security.KeyFactory;

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
        this.context = JAXBContext.newInstance(SaveRoot.class,BodyWrapper.class);
       // this.context = JAXBContext.newInstance(BodyWrapper.class);
    }

    @Test
    public void testMarshaller() throws JAXBException {
        items = new IItem[]{ItemFactory.club(), ItemFactory.fists(), null, null};
        inventory = InventoryFactory.createInventory(items, ItemFactory.fists(), new IItem[]{ItemFactory.createKey("Red Key")});
        hitpoints = new Vector2f(100,50);
        IBody Body = BodyFactory.createBody(inventory,Vector2f.right,Vector2f.left,hitpoints);
        String fileTest = "test.xml";
        SavePlayer save = new SavePlayer();
        save.marshall(Body,fileTest);

    }

    @Test
    public void saveTest2() throws JAXBException {
        TestWorld mock = new TestWorld();
       // mock.player.setPlayerPosition(new Vector2f(1,1));

        Enemy[] enemy = {(Enemy) mock.enemyConsciousness0};
        SaveRoot saveroot = new SaveRoot(mock.level,mock.player.readBody(), enemy);
        File file = new File("test.xml");
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(saveroot, file);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        SaveRoot loadroot = (SaveRoot) unmarshaller.unmarshal(file);
        IBody player = loadroot.loadPlayer();
        ILevel level = loadroot.loadLevel();
        IConsciousness[] enemy1 = loadroot.loadEnemies();

        Player player1 = new Player(player,level);
        assert(player1.readBody().readCurrentHp() == mock.player.readBody().readCurrentHp());
        assert(player1.readBody().readMaxHp() == mock.player.readBody().readMaxHp());
        assert (level.getTiles().keySet().size() == mock.level.getTiles().keySet().size());
        assert(true == enemy[0].readBody().readPosition().equals(enemy1[0].readBody().readPosition()));

    }
}

