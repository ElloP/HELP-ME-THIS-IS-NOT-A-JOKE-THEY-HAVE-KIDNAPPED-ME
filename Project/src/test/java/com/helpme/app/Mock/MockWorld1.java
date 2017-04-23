package com.helpme.app.Mock;


import com.helpme.app.world.character.IMonster;
import com.helpme.app.world.character.Monster;
import com.helpme.app.world.character.behaviour.FollowAndAttack;
import com.helpme.app.world.character.inventory.Inventory;
import com.helpme.app.world.item.IItem;
import com.helpme.app.utils.Tuple.Tuple2;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.handler.EnemyHandler;
import com.helpme.app.world.handler.MonsterHandler;
import com.helpme.app.world.handler.PlayerHandler;
import com.helpme.app.world.level.ILevel;
import com.helpme.app.world.level.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kopa on 2017-04-14.
 */
public class MockWorld1 {
    public ILevel level;
    public PlayerHandler playerHandler;
    public MonsterHandler enemyHandler0;
    public MonsterHandler enemyHandler1;
    public  MonsterHandler enemyHandler2;
    public MockWorld1() {
        IMonster player = new Monster(null, Vector2f.zero, Vector2f.up, 100);
        IMonster enemy0 = new Monster(new Inventory(null, MockItem.defaultWeapon, null), new Vector2f(1, 0), Vector2f.left, 100);
        IMonster enemy1 = new Monster(new Inventory(null, MockItem.defaultWeapon, null), new Vector2f(0, 3), Vector2f.down, 100);
        IMonster enemy2 = new Monster(new Inventory(null, MockItem.defaultWeapon, null), new Vector2f(1, 2), Vector2f.right, 100);

        ILevel level = new Level(createEmptyTileSquare(4, 4), null, null, new Vector2f(0, 0));

        /**
         * [e][ ][ ][ ]
         * [ ][e][ ][ ]
         * [ ][ ][ ][ ]
         * [p][e][ ][ ]
         */

        playerHandler = new PlayerHandler(player, level);


        enemyHandler0 = new EnemyHandler(enemy0, level, new FollowAndAttack(2));
        enemyHandler1 = new EnemyHandler(enemy1, level, new FollowAndAttack(8));
        enemyHandler2 = new EnemyHandler(enemy2, level, new FollowAndAttack(1));


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
