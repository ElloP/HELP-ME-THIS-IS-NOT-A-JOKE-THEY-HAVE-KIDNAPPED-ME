package com.helpme.app.savetest;

import com.helpme.app.saveload.TileWrapper;
import com.helpme.app.saveload.BodyWrapper;
import com.helpme.app.saveload.SavePlayer;
import com.helpme.app.saveload.SaveRoot;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.body.concrete.BodyFactory;
import com.helpme.app.world.body.inventory.IInventory;
import com.helpme.app.world.body.inventory.IReadInventory;
import com.helpme.app.world.body.inventory.concrete.InventoryFactory;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.consciousness.behaviour.Comparison;
import com.helpme.app.world.consciousness.behaviour.IBehaviour;
import com.helpme.app.world.consciousness.behaviour.concrete.BehaviourFactory;
import com.helpme.app.world.consciousness.behaviour.memories.IMemory;
import com.helpme.app.world.consciousness.behaviour.memories.concrete.MemoryFactory;
import com.helpme.app.world.consciousness.concrete.ConsciousnessFactory;
import com.helpme.app.world.consciousness.concrete.Enemy;
import com.helpme.app.world.consciousness.concrete.Player;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.concrete.ItemFactory;
import com.helpme.app.world.level.ILevel;
import com.helpme.app.world.level.concrete.LevelFactory;
import com.helpme.app.world.tile.edge.IEdge;
import com.helpme.app.world.tile.edge.concrete.Door;
import com.helpme.app.world.tile.edge.concrete.Opening;
import com.helpme.app.world.tile.edge.concrete.Wall;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Klas on 2017-04-29.
 */

public class SaveTest {

    private JAXBContext context;
    private IItem[] items;
    private IInventory inventory;
    private IBody Body;
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
        IBody Body = BodyFactory.createBody(inventory,Vector2f.east,Vector2f.west,hitpoints);
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
        List<Maybe<IItem>> mockItems = new ArrayList<Maybe<IItem>>(){
            {
                add(new Just<>(new MockItem("item0")));
                add(new Just<>(new MockItem("item1")));
                add(new Just<>(new MockItem("item2")));
                add(new Nothing<>());
            }
        };

        List<Maybe<IItem>> mockKeys = new ArrayList<Maybe<IItem>>(){
            {
                add(new Just<>(new MockItem("key0")));
                add(new Just<>(new MockItem("key1")));
                add(new Just<>(new MockItem("key2")));
                add(new Nothing<>());
            }
        };

        IInventory mockInventory = new MockInventory(mockItems, mockKeys);
        IBody mockBody = new MockBody(mockInventory);

        File file = new File("test.xml");
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(new BodyWrapper(mockBody), file);

        Unmarshaller unmarshaller = this.context.createUnmarshaller();

        BodyWrapper bodyWrapper = (BodyWrapper) unmarshaller.unmarshal(file);
        IBody loadedBody = bodyWrapper.getObject();
        assert(loadedBody.readHitpoints().equals(new Vector2f(100,100)));
        IReadInventory loadedInventory = loadedBody.readInventory();
        System.out.println(loadedInventory.readItems());
        assert(loadedInventory.readItem(0).getValue().readName().equals("item0"));
    }

    @Test
    public void testSaveTile() throws JAXBException {

        List<Maybe<IItem>> mockItems = new ArrayList<Maybe<IItem>>(){
            {
                add(new Just<>(new MockItem("item0")));
                add(new Just<>(new MockItem("item1")));
                add(new Just<>(new MockItem("item2")));
            }
        };

        Map<Vector2f, IEdge> mockEdges = new HashMap<Vector2f, IEdge>(){
            {
                put(Vector2f.north, new Wall());
                put(Vector2f.east, new Opening());
                put(Vector2f.south, new Door(true, new MockItem("key")));
                put(Vector2f.west, new Wall());
            }
        };

        MockTile mockTile = new MockTile(mockItems, mockEdges);

        File file = new File("test.xml");
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(new TileWrapper(mockTile, Vector2f.zero), file);

        Unmarshaller unmarshaller = this.context.createUnmarshaller();

        TileWrapper tileWrapper = (TileWrapper) unmarshaller.unmarshal(file);

        System.out.println(tileWrapper);
    }

    @Test
    public void testSaveEnemy() throws JAXBException {
        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<String, Tuple2<Integer, Comparison>>() {
            {
                put("name0", new Tuple2<>(1, Comparison.EQUAL));
                put("name1", new Tuple2<>(4, Comparison.LESS_THAN));
            }
        };
        IBehaviour attack = BehaviourFactory.createAttack(2, preconditions, "test");
        IBehaviour stay = BehaviourFactory.createStay(1, null);

        List<IBehaviour> behaviours = new ArrayList<IBehaviour>(){
            {
                add(attack);
                add(stay);
            }
        };

        Map<String, Integer> longTerm = new HashMap<String, Integer>(){
            {
                put("longterm0", 1);
                put("longterm1", 5);
            }
        };

        Map<String, Integer> shortTerm = new HashMap<String, Integer>(){
            {
                put("shortterm0", 3);
                put("shortterm1", 0);
            }
        };

        IMemory memory = MemoryFactory.createMemory(shortTerm, longTerm);

        IBody body = BodyFactory.createBody(null, Vector2f.zero, Vector2f.north,100);

        ILevel level = LevelFactory.createLevel(null, Vector2f.zero, null, null);

        IConsciousness enemy = ConsciousnessFactory.createEnemy(body, level, memory, behaviours);

        File file = new File("test.xml");
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(new SaveRoot(level, body, new IConsciousness[]{enemy}), file);

    }
}

