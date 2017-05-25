package com.helpme.app;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.body.concrete.BodyFactory;
import com.helpme.app.game.model.body.inventory.concrete.InventoryFactory;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.behaviour.Comparison;
import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.game.model.consciousness.behaviour.concrete.BehaviourFactory;
import com.helpme.app.game.model.consciousness.behaviour.memory.concrete.MemoryFactory;
import com.helpme.app.game.model.consciousness.concrete.ConsciousnessFactory;
import com.helpme.app.game.model.consciousness.concrete.Player;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.concrete.ItemFactory;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.game.model.level.concrete.LevelFactory;
import com.helpme.app.game.model.tile.edge.IDoor;
import com.helpme.app.game.model.tile.edge.concrete.Door;
import com.helpme.app.saveload.GameLoader;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.utils.tuple.Tuple3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Klas on 2017-05-24.
 */
public class MakeLevel {
    /**
     *                [e]
     *                [ ]
     * [ |[ ][ ][ ][ ][ ]
     *       [ ]
     *       [ ]
     *    [ ][e][ ]
     *    [ ][ ][ ]
     *    [ ][ ][ ]
     *       [ ]
     *    [ ][ ]
     *    [ ]
     *    [ ][ ]
     *    [ ][ ]
     *       [ ]
     * [p][ ][ ]
     */
    public static void main(String args[]){
        GameLoader gameLoader = new GameLoader();
        IBody playerBody = BodyFactory.createBody(null, Vector2f.ZERO, Vector2f.WEST, 100);
        ILevel level = makeLevel();
        Player player = new Player(playerBody, level);
        IConsciousness[] enemies = addEnemies(level);
        level.addBody(playerBody);
        gameLoader.saveGame(level,player,enemies,"level1.xml");

    }
    private static IConsciousness[] addEnemies(ILevel level){
        ArrayList<IConsciousness> enemyConsciousnesses = new ArrayList<>();
        IBody enemy0 = BodyFactory.createBody(InventoryFactory.createInventory(null, ItemFactory.club(), null), new Vector2f(2, 9), Vector2f.WEST, 100);
        IBody enemy1 = BodyFactory.createBody(InventoryFactory.createInventory(null, ItemFactory.club(), null), new Vector2f(5, 14), Vector2f.SOUTH, 100);
    //    IBody enemy2 = BodyFactory.createBody(InventoryFactory.createInventory(null, ItemFactory.club(), null), new Vector2f(1, 2), Vector2f.EAST, 100);
    //    IBody enemy3 = BodyFactory.createBody(InventoryFactory.createInventory(null, ItemFactory.club(), null), new Vector2f(3, 3), Vector2f.EAST, 100);

        List<IBehaviour> behaviours = new ArrayList<>();

        Map<String, Tuple2<Integer, Comparison>> followPrecondition = new HashMap<>();
        followPrecondition.put("returning", new Tuple2<>(0, Comparison.EQUAL));


        Map<String, Tuple2<Integer, Comparison>> attackPrecondition = new HashMap<>();
        attackPrecondition.put("attack", new Tuple2<>(1, Comparison.EQUAL));

        Map<String, Tuple2<Integer, Comparison>> returnPrecondition = new HashMap<>();

        IBehaviour attackBehaviour = BehaviourFactory.createAttack(0, attackPrecondition, "attack");
        IBehaviour followBehaviour = BehaviourFactory.createFollow(1, followPrecondition, 2, "attack", "following", "returning");
        IBehaviour returnBehaviour = BehaviourFactory.createReturn(2, returnPrecondition, "returning", "returned");

        behaviours.add(attackBehaviour);
        behaviours.add(followBehaviour);
        behaviours.add(returnBehaviour);



        enemyConsciousnesses.add(ConsciousnessFactory.createEnemy(enemy0, level, MemoryFactory.createMemory(), behaviours));
        level.addBody(enemy0);
        enemyConsciousnesses.add(ConsciousnessFactory.createEnemy(enemy1, level, MemoryFactory.createMemory(), behaviours));
        level.addBody(enemy1);
 /*       enemyConsciousnesses.add(ConsciousnessFactory.createEnemy(enemy2, level, MemoryFactory.createMemory(), behaviours));
        level.addBody(enemy2);
        enemyConsciousnesses.add(ConsciousnessFactory.createEnemy(enemy3, level, MemoryFactory.createMemory(), behaviours));
        level.addBody(enemy3);*/
        IConsciousness[] result = new IConsciousness[2];
        result[0] = enemyConsciousnesses.get(0);
        result[1] = enemyConsciousnesses.get(1);

        return result;

    }
    private static ILevel makeLevel(){
        Map<Vector2f, IItem[]> tiles = new HashMap<>();
        List<Tuple3<Vector2f, Vector2f, IDoor>> doors = new ArrayList<>();
        List<IBody> bodies = new ArrayList<>();

        tiles.put(new Vector2f(0, 0), null);
        tiles.put(new Vector2f(1, 0), null);
        tiles.put(new Vector2f(2, 0), null);
        tiles.put(new Vector2f(2, 1), null);
        tiles.put(new Vector2f(1, 2), null);
        tiles.put(new Vector2f(2, 2), null);
        tiles.put(new Vector2f(1, 3), null);
        tiles.put(new Vector2f(2, 3), null);
        tiles.put(new Vector2f(1, 4), null);
        tiles.put(new Vector2f(1, 5), null);
        tiles.put(new Vector2f(2, 5), null);
        tiles.put(new Vector2f(2, 6), null);
        tiles.put(new Vector2f(1, 7), null);
        tiles.put(new Vector2f(1, 8), null);
        tiles.put(new Vector2f(1, 9), null);
        tiles.put(new Vector2f(2, 7), null);
        tiles.put(new Vector2f(2, 8), null);
        tiles.put(new Vector2f(2, 9), null);
        tiles.put(new Vector2f(3, 7), null);
        tiles.put(new Vector2f(3, 8), null);
        tiles.put(new Vector2f(3, 9), null);
        tiles.put(new Vector2f(2, 10), null);
        tiles.put(new Vector2f(2, 11), null);
        tiles.put(new Vector2f(2, 12), null);
        tiles.put(new Vector2f(3, 12), null);
        tiles.put(new Vector2f(4, 12), null);
        tiles.put(new Vector2f(5, 12), null);
        tiles.put(new Vector2f(5, 13), null);
        tiles.put(new Vector2f(5, 14), null);
        tiles.put(new Vector2f(1, 12), null);
        tiles.put(new Vector2f(0, 12), null);





        doors.add(new Tuple3<>(new Vector2f(0, 12), Vector2f.EAST, new Door(true, ItemFactory.createKey("key0"))));





        ILevel level = LevelFactory.createLevel(tiles, doors, bodies, Vector2f.ZERO);
        return level;
    }
}
