package com.helpme.app.Mock;

import com.helpme.app.character.IMonster;
import com.helpme.app.character.Monster;
import com.helpme.app.character.behaviour.AttackEveryoneClose;
import com.helpme.app.character.inventory.Inventory;
import com.helpme.app.item.IItem;
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
    public PlayerHandler playerController;
    public MonsterHandler enemyController0;
    public MonsterHandler enemyController1;

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

        playerController = new PlayerHandler(player, level);

        enemyController0 = new EnemyHandler(enemy0, level, new AttackEveryoneClose(2));
        enemyController1 = new EnemyHandler(enemy1, level, new AttackEveryoneClose(2));

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
