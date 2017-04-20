package com.helpme.app.Mock;

import com.helpme.app.character.IMonster;
import com.helpme.app.character.Monster;
import com.helpme.app.character.behaviour.FollowAndAttack;
import com.helpme.app.character.inventory.Inventory;
import com.helpme.app.item.IItem;
import com.helpme.app.utils.Tuple.Tuple2;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kopa on 2017-04-14.
 */
public class MockWorld1 {
    public ILevel level;
    public PlayerController playerController;
    public MonsterController enemyController0;
    public MonsterController enemyController1;

    public MockWorld1() {
        IMonster player = new Monster(null, Vector2f.zero, Vector2f.up, 100);
        IMonster enemy0 = new Monster(new Inventory(null, MockItem.defaultWeapon, null), new Vector2f(1, 0), Vector2f.left, 100);
        IMonster enemy1 = new Monster(new Inventory(null, MockItem.defaultWeapon, null), new Vector2f(0, 3), Vector2f.down, 100);

        ILevel level = new Level(createEmptyTileSquare(4, 4), null, null, new Vector2f(0, 0));

        /**
         * [e][ ][ ][ ]
         * [ ][ ][ ][ ]
         * [ ][ ][ ][ ]
         * [p][e][ ][ ]
         */

        playerController = new PlayerController(player, level);

        enemyController0 = new EnemyController(enemy0, level, new FollowAndAttack(2));
        enemyController1 = new EnemyController(enemy1, level, new FollowAndAttack(2));

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
