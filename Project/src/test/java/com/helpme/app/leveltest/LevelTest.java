package com.helpme.app.leveltest;

import com.helpme.app.model.item.IItem;
import com.helpme.app.model.level.concrete.Level;
import com.helpme.app.model.tile.IReadTile;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.model.body.IBody;
import com.helpme.app.model.body.IReadBody;
import com.helpme.app.model.item.effect.ITarget;
import com.helpme.app.model.level.ILevel;
import com.helpme.app.model.tile.ITile;
import com.sun.corba.se.spi.ior.iiop.IIOPProfileTemplate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jesper on 2017-04-20.
 */
public class LevelTest {
    private ILevel level;
    private MockPlayer mockPlayer;
    private MockBody mockBody;
    private MockTile mockTile;

    @Before
    public void setup() {
        Map<Vector2f, ITile> tiles = new HashMap<>();
        List<IBody> bodies = new ArrayList<>();
        mockBody = new MockBody();
        mockPlayer = new MockPlayer();
        mockTile = new MockTile();

        tiles.put(Vector2f.ZERO, new MockTile());
        tiles.put(Vector2f.NORTH, new MockTile());
        tiles.put(Vector2f.EAST, mockTile);

        bodies.add(mockPlayer);
        bodies.add(mockBody);

        level = new Level(mockPlayer, Vector2f.ZERO, tiles, bodies);
    }

    @Test
    public void testDirectionBlockedFalse() {
        mockBody.traversable = true;
        assert (!level.isDirectionBlocked(mockBody, null));
    }


    @Test
    public void testDirectionBlockedTrue() {
        mockBody.traversable = false;
        assert (level.isDirectionBlocked(mockBody, null));
    }

    @Test
    public void testGetTargetEdge() {
        mockPlayer.traversable = false;
        Maybe<ITarget> target = level.getTarget(mockPlayer, Vector2f.ZERO);
        assert (target.getValue() instanceof MockEdge);
    }

    @Test
    public void testGetTargetBody() {
        mockPlayer.traversable = true;
        Maybe<ITarget> target = level.getTarget(mockPlayer, Vector2f.NORTH);
        assert (target.getValue() instanceof MockBody);
    }

    @Test
    public void testGetTargetNothing() {
        mockPlayer.traversable = true;
        Maybe<ITarget> target = level.getTarget(mockPlayer, Vector2f.EAST);
        assert (target instanceof Nothing);
    }

    @Test
    public void testReadBodySuccess() {
        Maybe<IReadBody> body = level.readBody(Vector2f.NORTH);
        assert (body.getValue() instanceof MockBody);
        body = level.readBody(new Vector2f(0, 0));
        assert (body.getValue() instanceof MockPlayer);
    }

    @Test
    public void testReadBodyFailure() {
        Maybe<IReadBody> body = level.readBody(Vector2f.EAST);
        assert (body instanceof Nothing);
    }

    @Test
    public void testReadPlayerSuccess() {
        Maybe<IReadBody> player = level.readPlayer();
        assert (player.getValue() instanceof MockPlayer);
    }

    @Test
    public void testSetPlayer() {
        level.setPlayer(mockBody);
        Maybe<IReadBody> player = level.readPlayer();
        assert (player.getValue() instanceof MockBody);
    }

    @Test
    public void testReadPlayerFailure() {
        level.setPlayer(null);
        Maybe<IReadBody> player = level.readPlayer();
        assert (player instanceof Nothing);
    }

    @Test
    public void testReadFacingSuccess() {
        mockBody.traversable = true;
        Maybe<IReadBody> facing = level.readFacing(mockBody);
        assert (facing.getValue() instanceof MockPlayer);
    }

    @Test
    public void testReadFacingFailure() {
        mockPlayer.traversable = true;
        Maybe<IReadBody> facing = level.readFacing(mockPlayer);
        assert (facing instanceof Nothing);
    }

    @Test
    public void testTileValid() {
        assert (level.isTileValid(Vector2f.NORTH));
    }

    @Test
    public void testTileInvalid() {
        assert (!level.isTileValid(Vector2f.WEST));
    }

    @Test
    public void testTileOccupied() {
        assert (level.isTileOccupied(Vector2f.NORTH));
    }

    @Test
    public void testTileVacant() {
        assert (!level.isTileOccupied(new Vector2f(1, 0)));
        mockBody.dead = true;
        assert (!level.isTileOccupied(new Vector2f(0, 1)));
    }

    @Test
    public void testAddTileItems() {
        List<Maybe<IItem>> mockItems = new ArrayList<>();
        MockItem mockItem0 = new MockItem("mockItem0");
        MockItem mockItem1 = new MockItem("mockItem1");
        mockItems.add(new Just<>(mockItem0));
        mockItems.add(new Just<>(mockItem1));

        level.addTileItems(Vector2f.ZERO, mockItems);
        List<Maybe<IItem>> tileItems = level.getTiles().get(Vector2f.ZERO).removeItems();

        assert (tileItems.size() == 2 &&
                tileItems.contains(new Just<>(mockItem0)) &&
                tileItems.contains(new Just<>(mockItem1)));
    }

    @Test
    public void testAddTileItem() {
        MockItem mockItem = new MockItem("mockItem0");

        level.addTileItem(Vector2f.ZERO, mockItem);
        List<Maybe<IItem>> tileItems = level.getTiles().get(Vector2f.ZERO).removeItems();

        assert (tileItems.size() == 1 &&
                tileItems.contains(new Just<>(mockItem)));
    }

    @Test
    public void testRemoveTileItems() {
        List<Maybe<IItem>> mockItems = new ArrayList<>();
        IItem mockItem0 = new MockItem("mockItem0");
        IItem mockItem1 = new MockItem("mockItem1");
        mockItems.add(new Just<>(mockItem0));
        mockItems.add(new Just<>(mockItem1));
        mockTile.items = mockItems;

        Maybe<List<Maybe<IItem>>> tileItems = level.removeTileItems(Vector2f.EAST);
        assert (tileItems.check(items -> items.contains(new Just<>(mockItem0)) && items.contains(new Just<>(mockItem1))));
    }

    @Test
    public void testRemoveTileItem() {
        IItem mockItem = new MockItem("mockItem");
        mockTile.item = mockItem;

        Maybe<IItem> tileItem = level.removeTileItem(Vector2f.EAST, 0);
        assert (tileItem.check(item -> item == mockItem));
    }


    @Test
    public void testUpdateTile() {
        List<Maybe<IItem>> mockItems = new ArrayList<>();
        MockItem mockItem0 = new MockItem("mockItem0");
        MockItem mockItem1 = new MockItem("mockItem1");
        MockInventory mockInventory;

        mockItems.add(new Just<>(mockItem0));
        mockItems.add(new Just<>(mockItem1));

        mockInventory = new MockInventory(mockItems);

        mockBody.dead = true;
        mockBody.mockInventory = mockInventory;

        level.updateTile(mockBody.readPosition());
        ITile tile = level.getTiles().get(mockBody.readPosition());
        List<Maybe<IItem>> items = tile.removeItems();
        List<IReadBody> bodies = level.readBodies();
        assert (items.size() == 2 &&
                items.contains(new Just<>(mockItem0)) &&
                items.contains(new Just<>(mockItem1)) &&
                bodies.size() == 1 &&
                !bodies.contains(mockBody));
    }


    @Test
    public void testReadBodies() {
        List<IReadBody> bodies = level.readBodies();
        assert (bodies.contains(mockBody) && bodies.contains(mockPlayer));
    }

    @Test
    public void testIsMovementAllowedAllowed() {
        mockPlayer.traversable = true;
        assert (level.isMovementAllowed(mockPlayer, Vector2f.EAST));
    }

    @Test
    public void testIsMovementAllowedDirectionBlocked() {
        mockPlayer.traversable = false;
        assert (!level.isMovementAllowed(mockPlayer, Vector2f.EAST));
    }

    @Test
    public void testIsMovementAllowedTileOccupied() {
        mockPlayer.traversable = true;
        assert (!level.isMovementAllowed(mockPlayer, Vector2f.NORTH));
    }


    @Test
    public void testIsWithinRange() {
        assert (level.isWithinRange(Vector2f.ZERO, new Vector2f(1,0), 2));
        assert (level.isWithinRange(Vector2f.ZERO, new Vector2f(-1,0), 2));
        assert (level.isWithinRange(Vector2f.ZERO, new Vector2f(0,1), 2));
        assert (level.isWithinRange(Vector2f.ZERO, new Vector2f(0,-1), 2));
        assert (level.isWithinRange(Vector2f.ZERO, new Vector2f(1,1), 2));
        assert (level.isWithinRange(Vector2f.ZERO, new Vector2f(-1,1), 2));
        assert (level.isWithinRange(Vector2f.ZERO, new Vector2f(1,-1), 2));
        assert (level.isWithinRange(Vector2f.ZERO, new Vector2f(-1,-1), 2));
        assert (level.isWithinRange(Vector2f.ZERO, new Vector2f(2,0), 2));
        assert (level.isWithinRange(Vector2f.ZERO, new Vector2f(-2,0), 2));
        assert (level.isWithinRange(Vector2f.ZERO, new Vector2f(0,2), 2));
        assert (level.isWithinRange(Vector2f.ZERO, new Vector2f(0,-2), 2));
    }

    @Test
    public void testIsNotWithinRange() {
        assert (!level.isWithinRange(Vector2f.ZERO, new Vector2f(1,1), 1));
        assert (!level.isWithinRange(Vector2f.ZERO, new Vector2f(-1,1), 1));
        assert (!level.isWithinRange(Vector2f.ZERO, new Vector2f(1,-1), 1));
        assert (!level.isWithinRange(Vector2f.ZERO, new Vector2f(-1,-1), 1));
    }


    @Test
    public void testAddBody() {
        MockBody newBody = new MockBody();
        newBody.position = Vector2f.EAST;
        assert (level.addBody(newBody) && level.readBodies().contains(newBody));
    }

    @Test
    public void testAddBodyInvalidTile() {
        MockBody newBody = new MockBody();
        newBody.position = new Vector2f(8,8);
        assert (!level.addBody(newBody) && !level.readBodies().contains(newBody));
    }

    @Test
    public void testAddBodyOccupiedTile() {
        MockBody newBody = new MockBody();
        newBody.position = Vector2f.NORTH;
        assert (!level.addBody(newBody) && !level.readBodies().contains(newBody));
    }


}
