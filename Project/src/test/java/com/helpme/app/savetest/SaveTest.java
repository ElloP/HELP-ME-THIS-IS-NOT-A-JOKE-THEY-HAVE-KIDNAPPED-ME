package com.helpme.app.savetest;
import com.helpme.app.saveload.BodyWrapper;
import com.helpme.app.saveload.LevelWrapper;
import com.helpme.app.saveload.TileWrapper;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.tile.edge.Door;
import com.helpme.app.world.tile.edge.IEdge;
import com.helpme.app.world.tile.edge.Opening;
import com.helpme.app.world.tile.edge.Wall;
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
        this.context = JAXBContext.newInstance(LevelWrapper.class);


        // this.context = JAXBContext.newInstance(BodyWrapper.class);
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
        Vector2f mockHitpoints = new Vector2f(100,100);

        File file = new File("test.xml");
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(new BodyWrapper(mockBody), file);

        Unmarshaller unmarshaller = this.context.createUnmarshaller();

        BodyWrapper bodyWrapper = (BodyWrapper) unmarshaller.unmarshal(file);

        System.out.println(bodyWrapper);
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

    /*
    @Test
    public void saveTest() throws JAXBException {
        mockItems = new IItem[]{MockItem.weapon, MockItem.potion, null, null};
        mockInventory = InventoryFactory.createInventory(mockItems, MockItem.defaultWeapon, new IItem[]{MockItem.key});
        mockHitpoints = new Vector2f(100,50);
        IBody Body = new Body(mockInventory,Vector2f.right,Vector2f.left,mockHitpoints);
        File file = new File("test.xml");
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(new BodyWrapper(Body), file);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        BodyWrapper pw = (BodyWrapper) unmarshaller.unmarshal(file);

        System.out.println(pw);
    }

    @Test
    public void testMarshaller() throws JAXBException {
        mockItems = new IItem[]{MockItem.weapon, MockItem.potion, null, null};
        mockInventory = InventoryFactory.createInventory(mockItems, MockItem.defaultWeapon, new IItem[]{MockItem.key});
        mockHitpoints = new Vector2f(100,50);
        IBody Body = new Body(mockInventory,Vector2f.right,Vector2f.left,mockHitpoints);
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
        File file = new File("test.xml");
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(new LevelWrapper(mock.level), file);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        LevelWrapper pw = (LevelWrapper) unmarshaller.unmarshal(file);
        assert (pw.getPlayer().getPositionWrapper().getX() == mock.player.getPlayer().readPosition().x);
        System.out.println(pw);
    }*/
}

