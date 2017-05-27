package com.helpme.app.savetest;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.body.concrete.Body;
import com.helpme.app.game.model.body.concrete.BodyFactory;
import com.helpme.app.game.model.body.inventory.IInventory;
import com.helpme.app.game.model.body.inventory.IReadInventory;
import com.helpme.app.game.model.body.inventory.concrete.Inventory;
import com.helpme.app.game.model.body.inventory.concrete.InventoryFactory;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.behaviour.Comparison;
import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.game.model.consciousness.behaviour.concrete.Attack;
import com.helpme.app.game.model.consciousness.behaviour.concrete.Stay;
import com.helpme.app.game.model.consciousness.behaviour.memory.IMemory;
import com.helpme.app.game.model.consciousness.behaviour.memory.concrete.MemoryFactory;
import com.helpme.app.game.model.consciousness.concrete.Enemy;
import com.helpme.app.game.model.consciousness.concrete.Player;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IReadItem;
import com.helpme.app.game.model.item.concrete.ItemFactory;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.game.model.level.concrete.Level;
import com.helpme.app.game.model.tile.ITile;
import com.helpme.app.game.model.tile.concrete.TileFactory;
import com.helpme.app.game.model.tile.edge.IEdge;
import com.helpme.app.game.model.tile.edge.concrete.Door;
import com.helpme.app.game.model.tile.edge.concrete.Opening;
import com.helpme.app.game.model.tile.edge.concrete.Wall;
import com.helpme.app.game.saveload.*;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple2;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.*;

/**
 * Created by Klas on 2017-04-29.
 */

public class SaveTest {

    private JAXBContext context;
    private IItem[] items;
    private IInventory inventory;
    private Vector2f hitpoints;

    @Before
    public void init() throws JAXBException {
        this.context = JAXBContext.newInstance(SaveRoot.class, BodyWrapper.class);
        // this.context = JAXBContext.newInstance(BodyWrapper.class);
    }

    @Test
    public void testMarshaller() throws JAXBException {
        items = new IItem[]{ItemFactory.club(), ItemFactory.fists(), null, null};
        inventory = InventoryFactory.createInventory(items, ItemFactory.fists(), new IItem[]{ItemFactory.createKey("Red Key")});
        hitpoints = new Vector2f(100, 50);
        IBody Body = BodyFactory.createBody(inventory, Vector2f.EAST, Vector2f.WEST, hitpoints);
        String fileTest = "test.xml";
        SavePlayer save = new SavePlayer();
        save.marshall(Body, fileTest);

    }

    @Test
    public void saveTest2() throws JAXBException {
        TestWorld mock = new TestWorld();
        // mock.player.setPlayerPosition(new Vector2f(1,1));
        IConsciousness[] enemy = mock.enemyConsciousnesses.toArray(new IConsciousness[mock.enemyConsciousnesses.size()]);
        SaveRoot saveroot = new SaveRoot(mock.level, mock.player.getBody(), enemy);
        File file = new File("test.xml");
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(saveroot, file);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        SaveRoot loadroot = (SaveRoot) unmarshaller.unmarshal(file);
        Player player = loadroot.loadPlayer();
        ILevel level = loadroot.loadLevel();
        IConsciousness[] enemy1 = loadroot.loadEnemies();

        Player player1 = new Player(player.getBody(), level);
        assert (Math.round(player1.readBody().readCurrentHitpoints()) == Math.round(mock.player.readBody().readCurrentHitpoints()));
        assert (Math.round(player1.readBody().readMaxHitpoints()) == Math.round(mock.player.readBody().readMaxHitpoints()));
        assert (level.getTiles().keySet().size() == mock.level.getTiles().keySet().size());
        assert (enemy[0].readBody().readPosition().equals(enemy1[0].readBody().readPosition()));

    }

    @Test
    public void testSaveInventory() throws JAXBException {
        List<Maybe<IItem>> items = new ArrayList<>();
        List<Maybe<IItem>> keys = new ArrayList<>();
        File file = new File("test.xml");
        List<Maybe<IReadItem>> loadedKeyChain;
        Marshaller marshaller = this.context.createMarshaller();
        Unmarshaller unmarshaller;
        IInventory inventory;
        InventoryWrapper inventoryWrapper;
        IInventory loadedInventory;

        items.add(new Just<>(ItemFactory.potion()));
        items.add(new Just<>(ItemFactory.fists()));
        items.add(new Just<>(ItemFactory.club()));
        items.add(new Nothing<>());

        keys.add(new Just<>(ItemFactory.createKey("key0")));
        keys.add(new Just<>(ItemFactory.createKey("key1")));
        keys.add(new Just<>(ItemFactory.createKey("key2")));
        keys.add(new Nothing<>());

        inventory = InventoryFactory.createInventory(items, ItemFactory.fists(), keys);

        marshaller.marshal(new InventoryWrapper(inventory), file);

        unmarshaller = this.context.createUnmarshaller();


        inventoryWrapper = (InventoryWrapper) unmarshaller.unmarshal(file);
        loadedInventory = inventoryWrapper.getObject();
        loadedKeyChain = loadedInventory.readKeychain();

        assert (loadedInventory.readItem(0).check(item -> item.equals(ItemFactory.potion())) &&
                loadedInventory.readItem(1).check(item -> item.equals(ItemFactory.fists())) &&
                loadedInventory.readItem(2).check(item -> item.equals(ItemFactory.club())) &&
                loadedInventory.readItem(3).isNothing() &&
                loadedInventory.readDefaultItem().check(item -> item.equals(ItemFactory.fists())) &&
                loadedKeyChain.contains(new Just<>((IReadItem) ItemFactory.createKey("key0"))) &&
                loadedKeyChain.contains(new Just<>((IReadItem) ItemFactory.createKey("key1"))) &&
                loadedKeyChain.contains(new Just<>((IReadItem) ItemFactory.createKey("key0"))));
    }

    @Test
    public void testSaveBody() throws JAXBException {
        IBody body = BodyFactory.createBody(null, Vector2f.ZERO, Vector2f.NORTH, 100);
        File file = new File("test.xml");
        Marshaller marshaller = this.context.createMarshaller();
        Unmarshaller unmarshaller;
        BodyWrapper bodyWrapper;
        IBody loadedBody;

        marshaller.marshal(new BodyWrapper(body), file);

        unmarshaller = this.context.createUnmarshaller();

        bodyWrapper = (BodyWrapper) unmarshaller.unmarshal(file);
        loadedBody = bodyWrapper.getObject();


        assert (loadedBody.readHitpoints().equals(new Vector2f(100, 100)) &&
                loadedBody.readPosition().equals(Vector2f.ZERO) &&
                loadedBody.readDirection().equals(Vector2f.NORTH) &&
                loadedBody.isDead() == false);

    }

    @Test
    public void testSaveTile() throws JAXBException {

        List<Maybe<IItem>> items = new ArrayList<>();
        Map<Vector2f, IEdge> edges = new HashMap<>();
        ITile tile;
        File file;
        Marshaller marshaller;
        Unmarshaller unmarshaller;
        TileWrapper tileWrapper;
        ITile loadedTile;

        items.add(new Just<>(ItemFactory.club()));
        items.add(new Just<>(ItemFactory.potion()));
        items.add(new Just<>(ItemFactory.club()));

        edges.put(Vector2f.NORTH, new Wall());
        edges.put(Vector2f.EAST, new Opening());
        edges.put(Vector2f.SOUTH, new Door(true, ItemFactory.createKey("key")));
        edges.put(Vector2f.WEST, new Wall());

        tile = TileFactory.createTile(items, edges);

        file = new File("test.xml");
        marshaller = this.context.createMarshaller();
        marshaller.marshal(new TileWrapper(tile, Vector2f.ZERO), file);

        unmarshaller = this.context.createUnmarshaller();

        tileWrapper = (TileWrapper) unmarshaller.unmarshal(file);

        System.out.println(tileWrapper);
    }

    @Test
    public void testSaveEnemy() throws JAXBException {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        IBehaviour attack = new Attack(2, preconditions, "test");
        IBehaviour stay = new Stay(1, null);
        List<IBehaviour> behaviours = new ArrayList<>();
        Map<String, Integer> longTerm = new HashMap<>();
        Map<String, Integer> shortTerm = new HashMap<>();
        IBody body;
        ILevel level;
        IConsciousness enemy;

        behaviours.add(attack);
        behaviours.add(stay);

        preconditions.put("name0", new Tuple2<>(1, Comparison.EQUAL));
        preconditions.put("name1", new Tuple2<>(4, Comparison.LESS_THAN));


        longTerm.put("longterm0", 1);
        longTerm.put("longterm1", 5);


        shortTerm.put("shortterm0", 3);
        shortTerm.put("shortterm1", 0);

        IMemory memory = MemoryFactory.createMemory(shortTerm, longTerm);

        body = BodyFactory.createBody(null, Vector2f.ZERO, Vector2f.NORTH, new Vector2f(100, 100), Vector2f.ZERO, null, false);

        level = new Level(null, Vector2f.ZERO, new HashMap<>(), new ArrayList<>());

        enemy = new Enemy(body, level, memory, new SortedList<>(FXCollections.observableList(behaviours), Comparator.comparingInt(IBehaviour::getPriority)));

        File file = new File("test.xml");
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(new SaveRoot(level, body, new IConsciousness[]{enemy}), file);

    }
}

