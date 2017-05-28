package com.helpme.app.savetest;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.body.concrete.BodyFactory;
import com.helpme.app.game.model.body.inventory.IInventory;
import com.helpme.app.game.model.body.inventory.concrete.InventoryFactory;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.behaviour.Comparison;
import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.game.model.consciousness.behaviour.concrete.Attack;
import com.helpme.app.game.model.consciousness.behaviour.concrete.BehaviourFactory;
import com.helpme.app.game.model.consciousness.behaviour.concrete.Stay;
import com.helpme.app.game.model.consciousness.memory.IMemory;
import com.helpme.app.game.model.consciousness.memory.concrete.MemoryFactory;
import com.helpme.app.game.model.consciousness.concrete.ConsciousnessFactory;
import com.helpme.app.game.model.consciousness.concrete.Enemy;
import com.helpme.app.game.model.consciousness.concrete.Player;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IReadItem;
import com.helpme.app.game.model.item.concrete.ItemFactory;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.game.model.level.concrete.LevelFactory;
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
        this.context = JAXBContext.newInstance(SaveRoot.class, BodyWrapper.class, EnemyWrapper.class);
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
        File file = new File("test.xml");
        List<Maybe<IItem>> items = new ArrayList<>();
        List<Maybe<IItem>> keys = new ArrayList<>();
        IItem defaultItem = ItemFactory.fists();
        Marshaller marshaller = this.context.createMarshaller();
        Unmarshaller unmarshaller;
        IInventory inventory;
        InventoryWrapper inventoryWrapper;
        IInventory loadedInventory;
        List<Maybe<IReadItem>> loadedKeyChain;

        items.add(new Just<>(ItemFactory.potion()));
        items.add(new Just<>(ItemFactory.fists()));
        items.add(new Just<>(ItemFactory.club()));
        items.add(new Nothing<>());

        keys.add(new Just<>(ItemFactory.createKey("key0")));
        keys.add(new Just<>(ItemFactory.createKey("key1")));
        keys.add(new Just<>(ItemFactory.createKey("key2")));
        keys.add(new Nothing<>());

        inventory = InventoryFactory.createInventory(items, defaultItem, keys);

        marshaller.marshal(new InventoryWrapper(inventory), file);

        unmarshaller = this.context.createUnmarshaller();


        inventoryWrapper = (InventoryWrapper) unmarshaller.unmarshal(file);
        loadedInventory = inventoryWrapper.getObject();
        loadedKeyChain = loadedInventory.readKeychain();

        assert (loadedInventory.readSize() == 4 &&
                loadedInventory.readItem(0).equals(items.get(0)) &&
                loadedInventory.readItem(1).equals(items.get(1)) &&
                loadedInventory.readItem(2).equals(items.get(2)) &&
                loadedInventory.readItem(3).equals(items.get(3)) &&
                loadedInventory.readDefaultItem().check(item -> item.equals(defaultItem)) &&
                loadedKeyChain.size() == 4 &&
                loadedKeyChain.get(0).equals(keys.get(0)) &&
                loadedKeyChain.get(1).equals(keys.get(1)) &&
                loadedKeyChain.get(2).equals(keys.get(2))) &&
                loadedKeyChain.get(3).equals(keys.get(3));
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


        assert (loadedBody.readHitpoints().equals(body.readHitpoints()) &&
                loadedBody.readPosition().equals(body.readPosition()) &&
                loadedBody.readDirection().equals(body.readDirection()) &&
                loadedBody.isDead() == body.isDead());

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
        File file = new File("test.xml");
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        IBehaviour attack = BehaviourFactory.createAttack(
                2,
                preconditions,
                "test");
        IBehaviour stay = BehaviourFactory.createStay(
                1,
                null);
        IBehaviour follow = BehaviourFactory.createFollow(
                3,
                null,
                2,
                "found",
                "following",
                "lost");

        List<IBehaviour> behaviours = new ArrayList<>();

        SortedList<IBehaviour> sortedBehaviours;
        Map<String, Integer> longTerm = new HashMap<>();
        Map<String, Integer> shortTerm = new HashMap<>();
        IMemory memory;
        Marshaller marshaller;
        Unmarshaller unmarshaller;
        EnemyWrapper enemyWrapper;
        IBody body;
        ILevel level;
        Enemy enemy;
        Enemy loadedEnemy;
        IMemory loadedMemory;
        List<IBehaviour> loadedBehaviours;

        behaviours.add(attack);
        behaviours.add(stay);
        behaviours.add(follow);

        sortedBehaviours = new SortedList<>(
                FXCollections.observableList(behaviours),
                Comparator.comparingInt(IBehaviour::getPriority));

        preconditions.put("name0", new Tuple2<>(1, Comparison.EQUAL));
        preconditions.put("name1", new Tuple2<>(4, Comparison.LESS_THAN));


        longTerm.put("longterm0", 1);
        longTerm.put("longterm1", 5);


        shortTerm.put("shortterm0", 3);
        shortTerm.put("shortterm1", 0);

        memory = MemoryFactory.createMemory(shortTerm, longTerm);

        body = BodyFactory.createBody(null, Vector2f.ZERO, Vector2f.NORTH, new Vector2f(100, 100), Vector2f.ZERO, null, false);

        level = LevelFactory.createLevel(null, Vector2f.ZERO, new HashMap<>(), new ArrayList<>());

        enemy = (Enemy) ConsciousnessFactory.createEnemy(body, level, memory, sortedBehaviours);

        marshaller = this.context.createMarshaller();
        marshaller.marshal(new EnemyWrapper(enemy), file);

        unmarshaller = this.context.createUnmarshaller();

        enemyWrapper = (EnemyWrapper) unmarshaller.unmarshal(file);
        loadedEnemy = (Enemy) enemyWrapper.getObject(level);
        loadedMemory = loadedEnemy.readMemory();
        loadedBehaviours = loadedEnemy.getBehaviours();

        assert (loadedBehaviours.size() == behaviours.size() &&
                loadedBehaviours.get(0).equals(sortedBehaviours.get(0)) &&
                loadedBehaviours.get(1).equals(sortedBehaviours.get(1)) &&
                loadedBehaviours.get(2).equals(sortedBehaviours.get(2)) &&
                loadedMemory.equals(memory));
    }
}

