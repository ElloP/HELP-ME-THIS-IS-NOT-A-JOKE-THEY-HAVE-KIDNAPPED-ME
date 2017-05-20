package com.helpme.app.savetest;
import com.helpme.app.saveload.*;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.body.concrete.BodyFactory;
import com.helpme.app.world.body.inventory.IInventory;
import com.helpme.app.world.body.inventory.IReadInventory;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.consciousness.ISurroundings;
import com.helpme.app.world.consciousness.behaviour.IBehaviour;
import com.helpme.app.world.consciousness.behaviour.concrete.BehaviourFactory;
import com.helpme.app.world.consciousness.behaviour.concrete.Comparison;
import com.helpme.app.world.consciousness.behaviour.memories.IMemory;
import com.helpme.app.world.consciousness.behaviour.memories.concrete.MemoryFactory;
import com.helpme.app.world.consciousness.concrete.ConsciousnessFactory;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.level.ILevel;
import com.helpme.app.world.level.concrete.Level;
import com.helpme.app.world.level.concrete.LevelFactory;
import com.helpme.app.world.tile.edge.concrete.Door;
import com.helpme.app.world.tile.edge.IEdge;
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
    JAXBContext context;

    @Before
    public void setup() throws JAXBException {
        this.context = JAXBContext.newInstance(LevelWrapper.class, BodyWrapper.class, SaveRoot.class);
    }


    @Test
    public void testSaveBody() throws JAXBException {
        List<Maybe<IItem>> mockItems = new ArrayList<Maybe<IItem>>(){
            {
                add(new Just(new MockItem("item0")));
                add(new Just(new MockItem("item1")));
                add(new Just(new MockItem("item2")));
                add(new Nothing());
            }
        };

        List<Maybe<IItem>> mockKeys = new ArrayList<Maybe<IItem>>(){
            {
                add(new Just(new MockItem("key0")));
                add(new Just(new MockItem("key1")));
                add(new Just(new MockItem("key2")));
                add(new Nothing());
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
                add(new Just(new MockItem("item0")));
                add(new Just(new MockItem("item1")));
                add(new Just(new MockItem("item2")));
            }
        };

        Map<Vector2f, IEdge> mockEdges = new HashMap<Vector2f, IEdge>(){
            {
                put(Vector2f.up, new Wall());
                put(Vector2f.right, new Opening());
                put(Vector2f.down, new Door(true, new MockItem("key")));
                put(Vector2f.left, new Wall());
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

        IBody body = BodyFactory.createBody(null, Vector2f.zero, Vector2f.up,100);

        ILevel level = LevelFactory.createLevel(null, Vector2f.zero, null, null);

        IConsciousness enemy = ConsciousnessFactory.createEnemy(body, level, memory, behaviours);

        File file = new File("test.xml");
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(new SaveRoot(level, body, new IConsciousness[]{enemy}), file);


    }
}

