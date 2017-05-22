package com.helpme.app.savetest;

import com.helpme.app.model.body.concrete.BodyFactory;
import com.helpme.app.model.body.IBody;
import com.helpme.app.model.body.inventory.concrete.InventoryFactory;
import com.helpme.app.model.consciousness.*;
import com.helpme.app.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.model.consciousness.behaviour.concrete.BehaviourFactory;
import com.helpme.app.model.consciousness.behaviour.Comparison;
import com.helpme.app.model.consciousness.behaviour.memories.concrete.MemoryFactory;
import com.helpme.app.model.consciousness.concrete.ConsciousnessFactory;
import com.helpme.app.model.consciousness.concrete.Player;
import com.helpme.app.model.item.IItem;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.model.item.concrete.ItemFactory;
import com.helpme.app.model.level.ILevel;
import com.helpme.app.model.level.concrete.LevelFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-04-14.
 */
public class TestWorld {
    ILevel level;
    IConsciousness player;
    IConsciousness enemyConsciousness0;
    IConsciousness enemyConsciousness1;
    IConsciousness enemyConsciousness2;
    IConsciousness enemyConsciousness3;

    public TestWorld() {
        IBody player = BodyFactory.createBody(null, Vector2f.ZERO, Vector2f.NORTH, 100);
        IBody enemy0 = BodyFactory.createBody(InventoryFactory.createInventory(null, ItemFactory.club(), null), new Vector2f(1, 0), Vector2f.WEST, 100);
        IBody enemy1 = BodyFactory.createBody(InventoryFactory.createInventory(null, ItemFactory.club(), null), new Vector2f(0, 3), Vector2f.SOUTH, 100);
        IBody enemy2 = BodyFactory.createBody(InventoryFactory.createInventory(null, ItemFactory.club(), null), new Vector2f(1, 2), Vector2f.EAST, 100);
        IBody enemy3 = BodyFactory.createBody(InventoryFactory.createInventory(null, ItemFactory.club(), null), new Vector2f(3, 3), Vector2f.EAST, 100);

        ILevel level = LevelFactory.createLevel(createEmptyTileSquare(4, 4), null, null, new Vector2f(0, 0));

        /**
         * [e][ ][e][ ]
         * [ ][e][ ][ ]
         * [ ][ ][ ][ ]
         * [p][e][ ][ ]
         */

        this.player = new Player(player, level);
        level.addBody(player);
        level.setPlayer(player);

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

        enemyConsciousness0 = ConsciousnessFactory.createEnemy(enemy0, level, MemoryFactory.createMemory(), behaviours);
        level.addBody(enemy0);
        enemyConsciousness1 = ConsciousnessFactory.createEnemy(enemy1, level, MemoryFactory.createMemory(), behaviours);
        level.addBody(enemy1);
        enemyConsciousness2 = ConsciousnessFactory.createEnemy(enemy2, level, MemoryFactory.createMemory(), behaviours);
        level.addBody(enemy2);
        enemyConsciousness3 = ConsciousnessFactory.createEnemy(enemy3, level, MemoryFactory.createMemory(), behaviours);
        level.addBody(enemy3);

        this.level = level;
    }

    private List<Tuple2<Vector2f, IItem[]>> createEmptyTileSquare(int width, int height) {
        List<Tuple2<Vector2f, IItem[]>> tiles = new ArrayList<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles.add(new Tuple2<>(new Vector2f(i, j), null));
            }
        }
        return tiles;
    }


}
