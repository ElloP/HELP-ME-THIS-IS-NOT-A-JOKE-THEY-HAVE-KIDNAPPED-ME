package com.helpme.app;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.body.concrete.Body;
import com.helpme.app.world.consciousness.concrete.Player;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.level.ILevel;
import com.helpme.app.world.level.concrete.LevelFactory;
import com.helpme.app.world.tile.edge.concrete.Door;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 2017-05-20.
 * Copy of MockWorld0
 */
public class Setup {
    private ILevel level;
    private Player player;
    private IBody playerBody;

    public ILevel setup(){
        List<Tuple2<Vector2f, IItem[]>> tiles = new ArrayList<>();
        List<Tuple3<Vector2f, Vector2f, Door>> doors = new ArrayList<>();
        List<IBody> bodies = new ArrayList<>();

        //IInventory inventory = InventoryFactory.createInventory(new IItem[]{MockItem.weapon, MockItem.potion, null, null}, MockItem.defaultWeapon, new IItem[]{MockItem.key});

        //MockDialogue dialogue = new MockDialogue();

        playerBody = new Body(null, new Vector2f(0, 0), Vector2f.south, 100);
        IBody enemy0 = new Body(null, new Vector2f(2, 2), Vector2f.south, 100);
        IBody enemy1 = new Body(null, new Vector2f(9, 0), Vector2f.south, 100);
        //IBody enemy2 = new Body(new Vector2f(7, 5), Vector2f.right, dialogue.dialogue0);

        bodies.add(enemy0);
        bodies.add(enemy1);
        //bodies.add(enemy2);

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
        //tiles.add(new Tuple2<>(new Vector2f(7, 0), new IItem[]{MockItem.pickupKey, MockItem.pickupWeapon}));
        tiles.add(new Tuple2<>(new Vector2f(8, 0), null));
        tiles.add(new Tuple2<>(new Vector2f(9, 0), null));

        tiles.add(new Tuple2<>(new Vector2f(9, 0), null));


        tiles.add(new Tuple2<>(new Vector2f(7,5),null));
        tiles.add(new Tuple2<>(new Vector2f(8,5),null));

        //tiles.add(new Tuple2<>(new Vector2f(1, 5), new IItem[]{MockItem.excessiveItem, MockItem.excessiveItem, MockItem.excessiveItem}));
        //tiles.add(new Tuple2<>(new Vector2f(2, 5), new IItem[]{MockItem.stackingConsumables, MockItem.stackingConsumables, MockItem.stackingConsumables}));
        //tiles.add(new Tuple2<>(new Vector2f(3, 5), new IItem[]{MockItem.stackingConsumables, MockItem.excessiveItem, MockItem.pickupWeapon}));

        doors.add(new Tuple3<>(new Vector2f(6, 2), Vector2f.east, new Door(true, null)));
        doors.add(new Tuple3<>(new Vector2f(8, 2), Vector2f
                .west, new Door(false, null)));
        //doors.add(new Tuple3<>(new Vector2f(8, 2), Vector2f.right, new Door(true, MockItem.key)));

        //doors.add(new Tuple3<>(new Vector2f(7, 0), Vector2f.right, new Door(true, MockItem.pickupKey)));

        /**
         *    [x][x][x]   [ ]   [e][ ]
         *
         *    [ ][ ][ ]
         *    [ ][e][ ]      [ |[ ]/ ]| ][ ]
         *    [ ][ ][ ]
         * [p][ ][ ]         [ ][x]| ][e]
         * Note to self (Jesper):
         * looking right -> is -1 in x
         * looking up ^ is 1 in z
         */


        ILevel level = LevelFactory.createLevel(tiles, doors, bodies, Vector2f.zero);
        this.player = new Player(playerBody, level);
        this.level = level;
        this.level.setPlayer(playerBody);
        return level;
    }

    public ILevel getLevel() {
        return level;
    }

    public Player getPlayer() {
        return player;
    }

    public IBody getPlayerBody() {
        return playerBody;
    }
}
