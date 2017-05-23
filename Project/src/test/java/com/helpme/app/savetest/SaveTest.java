package com.helpme.app.savetest;

import com.helpme.app.model.body.concrete.Body;
import com.helpme.app.model.consciousness.behaviour.concrete.Attack;
import com.helpme.app.model.consciousness.behaviour.concrete.Stay;
import com.helpme.app.model.consciousness.concrete.Enemy;
import com.helpme.app.model.level.concrete.Level;
import com.helpme.app.saveload.TileWrapper;
import com.helpme.app.saveload.BodyWrapper;
import com.helpme.app.saveload.SavePlayer;
import com.helpme.app.saveload.SaveRoot;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.model.body.IBody;
import com.helpme.app.model.body.concrete.BodyFactory;
import com.helpme.app.model.body.inventory.IInventory;
import com.helpme.app.model.body.inventory.IReadInventory;
import com.helpme.app.model.body.inventory.concrete.InventoryFactory;
import com.helpme.app.model.consciousness.IConsciousness;
import com.helpme.app.model.consciousness.behaviour.Comparison;
import com.helpme.app.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.model.consciousness.behaviour.concrete.BehaviourFactory;
import com.helpme.app.model.consciousness.behaviour.memories.IMemory;
import com.helpme.app.model.consciousness.behaviour.memories.concrete.MemoryFactory;
import com.helpme.app.model.consciousness.concrete.ConsciousnessFactory;
import com.helpme.app.model.consciousness.concrete.Player;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.concrete.ItemFactory;
import com.helpme.app.model.level.ILevel;
import com.helpme.app.model.level.concrete.LevelFactory;
import com.helpme.app.model.tile.edge.IEdge;
import com.helpme.app.model.tile.edge.concrete.Door;
import com.helpme.app.model.tile.edge.concrete.Opening;
import com.helpme.app.model.tile.edge.concrete.Wall;
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
        this.context = JAXBContext.newInstance(SaveRoot.class,BodyWrapper.class);
       // this.context = JAXBContext.newInstance(BodyWrapper.class);
    }

    @Test
    public void testMarshaller() throws JAXBException {
        items = new IItem[]{ItemFactory.club(), ItemFactory.fists(), null, null};
        inventory = InventoryFactory.createInventory(items, ItemFactory.fists(), new IItem[]{ItemFactory.createKey("Red Key")});
        hitpoints = new Vector2f(100,50);
        IBody Body = BodyFactory.createBody(inventory,Vector2f.EAST,Vector2f.WEST,hitpoints);
        String fileTest = "test.xml";
        SavePlayer save = new SavePlayer();
        save.marshall(Body,fileTest);

    }

    @Test
    public void saveTest2() throws JAXBException {
        TestWorld mock = new TestWorld();
       // mock.player.setPlayerPosition(new Vector2f(1,1));

        IConsciousness[] enemy = {mock.enemyConsciousness0,mock.enemyConsciousness1 };
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

    @Test
    public void testSaveBody() throws JAXBException {
        List<Maybe<IItem>> mockItems = new ArrayList<>();
        List<Maybe<IItem>> mockKeys = new ArrayList<>();
        IInventory mockInventory = new MockInventory(mockItems, mockKeys);
        IBody mockBody = new MockBody(mockInventory);
        File file = new File("test.xml");
        Marshaller marshaller = this.context.createMarshaller();
        Unmarshaller unmarshaller;
        BodyWrapper bodyWrapper;
        IBody loadedBody;
        IReadInventory loadedInventory;

        mockItems.add(new Just<>(new MockItem("item0")));
        mockItems.add(new Just<>(new MockItem("item1")));
        mockItems.add(new Just<>(new MockItem("item2")));
        mockItems.add(new Nothing<>());

        mockKeys.add(new Just<>(new MockItem("key0")));
        mockKeys.add(new Just<>(new MockItem("key1")));
        mockKeys.add(new Just<>(new MockItem("key2")));
        mockKeys.add(new Nothing<>());

        marshaller.marshal(new BodyWrapper(mockBody), file);

        unmarshaller = this.context.createUnmarshaller();

        bodyWrapper = (BodyWrapper) unmarshaller.unmarshal(file);
        loadedBody = bodyWrapper.getObject();
        assert(loadedBody.readHitpoints().equals(new Vector2f(100,100)));

        loadedInventory = loadedBody.readInventory();
        System.out.println(loadedInventory.readItems());
        assert(loadedInventory.readItem(0).getValue().readName().equals("item0"));
    }

    @Test
    public void testSaveTile() throws JAXBException {

        List<Maybe<IItem>> mockItems = new ArrayList<>();
        Map<Vector2f, IEdge> mockEdges = new HashMap<>();
        MockTile mockTile;
        File file;
        Marshaller marshaller;
        Unmarshaller unmarshaller;
        TileWrapper tileWrapper;

        mockItems.add(new Just<>(new MockItem("item0")));
        mockItems.add(new Just<>(new MockItem("item1")));
        mockItems.add(new Just<>(new MockItem("item2")));

        mockEdges.put(Vector2f.NORTH, new Wall());
        mockEdges.put(Vector2f.EAST, new Opening());
        mockEdges.put(Vector2f.SOUTH, new Door(true, new MockItem("key")));
        mockEdges.put(Vector2f.WEST, new Wall());

        mockTile = new MockTile(mockItems, mockEdges);

        file = new File("test.xml");
        marshaller = this.context.createMarshaller();
        marshaller.marshal(new TileWrapper(mockTile, Vector2f.ZERO), file);

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

        body = new Body(null, Vector2f.ZERO, Vector2f.NORTH, new Vector2f(100,100), Vector2f.ZERO, null);

        level = new Level(null, Vector2f.ZERO, new HashMap<>(), new ArrayList<>());

        enemy = new Enemy(body, level, memory, new SortedList<>(FXCollections.observableList(behaviours), Comparator.comparingInt(IBehaviour::getPriority)));

        File file = new File("test.xml");
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(new SaveRoot(level, body, new IConsciousness[]{enemy}), file);

    }
}

