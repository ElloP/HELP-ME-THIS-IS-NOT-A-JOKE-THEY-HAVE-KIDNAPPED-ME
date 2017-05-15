package com.helpme.app.Mock;


import com.helpme.app.world.character.*;
import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.character.inventory.Inventory;
import com.helpme.app.world.character.inventory.InventoryFactory;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.level.LevelFactory;
import com.helpme.app.world.tile.edge.Door;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.consciousness.Player;
import com.helpme.app.world.level.ILevel;
import com.helpme.app.world.level.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 2017-04-11.
 */
public class MockWorld0 {
    public ILevel level;
    public Player player;

    public MockWorld0(){
        List<Tuple2<Vector2f, IItem[]>> tiles = new ArrayList<>();
        List<Tuple3<Vector2f, Vector2f, Door>> doors = new ArrayList<>();
        List<IBody> bodies = new ArrayList<>();

        IInventory inventory = InventoryFactory.createInventory(new IItem[]{MockItem.weapon, MockItem.potion, null, null}, MockItem.defaultWeapon, new IItem[]{MockItem.key});

        MockDialogue dialogue = new MockDialogue();

        IBody player = new Body(inventory, Vector2f.zero, Vector2f.up, 100);
        IBody enemy0 = new Body(null, new Vector2f(2, 2), Vector2f.down, 100);
        IBody enemy1 = new Body(null, new Vector2f(9, 0), Vector2f.down, 100);
        IBody enemy2 = new Body(new Vector2f(7, 5), Vector2f.right, dialogue.dialogue0);

        bodies.add(enemy0);
        bodies.add(enemy1);
        bodies.add(enemy2);

        tiles.add(new Tuple2<>(new Vector2f(0, 0), null));
        tiles.add(new Tuple2<>(new Vector2f(1, 0), null));
        tiles.add(new Tuple2<>(new Vector2f(2, 0), null));
        tiles.add(new Tuple2<>(new Vector2f(1, 1), null));
        tiles.add(new Tuple2<>(new Vector2f(2, 1), null));
        tiles.add(new Tuple2<>(new Vector2f(3, 1), null));
        tiles.add(new Tuple2<>(new Vector2f(1, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(2, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(3, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(1, 3), null));
        tiles.add(new Tuple2<>(new Vector2f(2, 3), null));
        tiles.add(new Tuple2<>(new Vector2f(3, 3), null));

        tiles.add(new Tuple2<>(new Vector2f(5, 5), null));

        tiles.add(new Tuple2<>(new Vector2f(6, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(7, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(8, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(9, 2), null));
        tiles.add(new Tuple2<>(new Vector2f(10, 2), null));

        tiles.add(new Tuple2<>(new Vector2f(6, 0), null));
        tiles.add(new Tuple2<>(new Vector2f(7, 0), new IItem[]{MockItem.pickupKey, MockItem.pickupWeapon}));
        tiles.add(new Tuple2<>(new Vector2f(8, 0), null));
        tiles.add(new Tuple2<>(new Vector2f(9, 0), null));

        tiles.add(new Tuple2<>(new Vector2f(9, 0), null));


        tiles.add(new Tuple2<>(new Vector2f(7,5),null));
        tiles.add(new Tuple2<>(new Vector2f(8,5),null));

        tiles.add(new Tuple2<>(new Vector2f(1, 5), new IItem[]{MockItem.excessiveItem, MockItem.excessiveItem, MockItem.excessiveItem}));
        tiles.add(new Tuple2<>(new Vector2f(2, 5), new IItem[]{MockItem.stackingConsumables, MockItem.stackingConsumables, MockItem.stackingConsumables}));
        tiles.add(new Tuple2<>(new Vector2f(3, 5), new IItem[]{MockItem.stackingConsumables, MockItem.excessiveItem, MockItem.pickupWeapon}));

        doors.add(new Tuple3<>(new Vector2f(6, 2), Vector2f.right, new Door(true, null)));
        doors.add(new Tuple3<>(new Vector2f(8, 2), Vector2f.left, new Door(false, null)));
        doors.add(new Tuple3<>(new Vector2f(8, 2), Vector2f.right, new Door(true, MockItem.key)));

        doors.add(new Tuple3<>(new Vector2f(7, 0), Vector2f.right, new Door(true, MockItem.pickupKey)));

        /**
         *    [x][x][x]   [ ]   [e][ ]
         *
         *    [ ][ ][ ]
         *    [ ][e][ ]      [ |[ ]/ ]| ][ ]
         *    [ ][ ][ ]
         * [p][ ][ ]         [ ][x]| ][e]
         */


        ILevel level = LevelFactory.createLevel(tiles, doors, bodies, Vector2f.zero);
        this.player = new Player(player, level);
        this.level = level;
    }
}
