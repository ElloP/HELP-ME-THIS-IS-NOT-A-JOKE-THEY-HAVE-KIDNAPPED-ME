package com.helpme.app.levelfactorytest;

import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IReadItem;
import com.helpme.app.model.level.ILevel;
import com.helpme.app.model.level.concrete.LevelFactory;
import com.helpme.app.model.tile.ITile;
import com.helpme.app.model.tile.edge.IDoor;
import com.helpme.app.model.tile.edge.IOpening;
import com.helpme.app.model.tile.edge.IWall;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple3;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-05-23.
 */
public class LevelFactoryTest {

    @Test
    public void testGenerateOpeningBetweenTwoTiles() {
        Map<Vector2f, IItem[]> tileInfo = new HashMap<>();
        ILevel level;
        Map<Vector2f, ITile> tiles;
        ITile tile00;
        ITile tile10;

        tileInfo.put(new Vector2f(0, 0), new IItem[0]);
        tileInfo.put(new Vector2f(1, 0), new IItem[0]);
        level = LevelFactory.createLevel(tileInfo, new ArrayList<>(), null, null);
        tiles = level.getTiles();
        tile00 = tiles.get(new Vector2f(0, 0));
        tile10 = tiles.get(new Vector2f(1, 0));


        assert (tile00.getEdge(Vector2f.NORTH).getValue() instanceof IWall &&
                tile00.getEdge(Vector2f.EAST).getValue() instanceof IOpening &&
                tile00.getEdge(Vector2f.SOUTH).getValue() instanceof IWall &&
                tile00.getEdge(Vector2f.WEST).getValue() instanceof IWall &&
                tile10.getEdge(Vector2f.NORTH).getValue() instanceof IWall &&
                tile10.getEdge(Vector2f.EAST).getValue() instanceof IWall &&
                tile10.getEdge(Vector2f.SOUTH).getValue() instanceof IWall &&
                tile10.getEdge(Vector2f.WEST).getValue() instanceof IOpening);
    }

    @Test
    public void testGenerateTileWithItems() {
        Map<Vector2f, IItem[]> tileInfo = new HashMap<>();
        ILevel level;
        Map<Vector2f, ITile> tiles;
        ITile tile00;
        List<Maybe<IReadItem>> items;
        MockItem mockItem0 = new MockItem();
        MockItem mockItem1 = new MockItem();

        tileInfo.put(new Vector2f(0, 0), new IItem[]{mockItem0, mockItem1});
        level = LevelFactory.createLevel(tileInfo, new ArrayList<>(), null, null);
        tiles = level.getTiles();
        tile00 = tiles.get(new Vector2f(0, 0));
        items = tile00.readItems();


        assert (items.size() == 2 &&
                items.get(0).equals(new Just<>(mockItem0)) &&
                items.get(1).equals(new Just<>(mockItem1)));
    }

    @Test
    public void testGenerateDoorBetweenTwoTiles(){
        Map<Vector2f, IItem[]> tileInfo = new HashMap<>();
        List<Tuple3<Vector2f, Vector2f, IDoor>> doorInfo = new ArrayList<>();
        ILevel level;
        Map<Vector2f, ITile> tiles;
        ITile tile00;
        ITile tile10;
        IDoor door = new MockDoor();

        doorInfo.add(new Tuple3<>(new Vector2f(0,0), Vector2f.EAST, door));
        tileInfo.put(new Vector2f(0, 0), new IItem[0]);
        tileInfo.put(new Vector2f(1, 0), new IItem[0]);

        level = LevelFactory.createLevel(tileInfo, doorInfo, null, null);
        tiles = level.getTiles();
        tile00 = tiles.get(new Vector2f(0, 0));
        tile10 = tiles.get(new Vector2f(1, 0));


        assert (tile00.getEdge(Vector2f.NORTH).getValue() instanceof IWall &&
                tile00.getEdge(Vector2f.EAST).getValue().equals(door) &&
                tile00.getEdge(Vector2f.SOUTH).getValue() instanceof IWall &&
                tile00.getEdge(Vector2f.WEST).getValue() instanceof IWall &&
                tile10.getEdge(Vector2f.NORTH).getValue() instanceof IWall &&
                tile10.getEdge(Vector2f.EAST).getValue() instanceof IWall &&
                tile10.getEdge(Vector2f.SOUTH).getValue() instanceof IWall &&
                tile10.getEdge(Vector2f.WEST).getValue().equals(door));
    }
}
