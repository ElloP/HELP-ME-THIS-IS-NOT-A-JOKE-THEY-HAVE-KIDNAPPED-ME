package com.helpme.app.Mock;

import com.helpme.app.world.character.Body;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.behaviour.FollowAndAttack;
import com.helpme.app.world.character.behaviour.GoBack;
import com.helpme.app.world.character.inventory.Inventory;
import com.helpme.app.world.consciousness.Consciousness;
import com.helpme.app.world.item.IItem;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.consciousness.Enemy;
import com.helpme.app.world.consciousness.Player;
import com.helpme.app.world.level.ILevel;
import com.helpme.app.world.level.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kopa on 2017-04-14.
 */
public class MockWorld1 {
    public ILevel level;
    public Player player;
    public Consciousness enemyConsciousness0;
    public Consciousness enemyConsciousness1;
    public Consciousness enemyConsciousness2;
    public Consciousness enemyConsciousness3;
    public MockWorld1() {
        IBody player = new Body(null, Vector2f.zero, Vector2f.up, 100);
        IBody enemy0 = new Body(new Inventory(null, MockItem.defaultWeapon, null), new Vector2f(1, 0), Vector2f.left, 100);
        IBody enemy1 = new Body(new Inventory(null, MockItem.defaultWeapon, null), new Vector2f(0, 3), Vector2f.down, 100);
        IBody enemy2 = new Body(new Inventory(null, MockItem.defaultWeapon, null), new Vector2f(1, 2), Vector2f.right, 100);
        IBody enemy3 = new Body(new Inventory(null, MockItem.defaultWeapon, null), new Vector2f(3, 3), Vector2f.right, 100);

        ILevel level = new Level(createEmptyTileSquare(4, 4), null, null, new Vector2f(0, 0));

        /**
         * [e][ ][e][ ]
         * [ ][e][ ][ ]
         * [ ][ ][ ][ ]
         * [p][e][ ][ ]
         */

        this.player = new Player(player, level);
        level.addBody(player);
        level.setPlayer(player);

        enemyConsciousness0 = new Enemy(enemy0, level, new FollowAndAttack(2), new FollowAndAttack(2));
        level.addBody(enemy0);
        enemyConsciousness1 = new Enemy(enemy1, level, new FollowAndAttack(), new FollowAndAttack());
        level.addBody(enemy1);
        enemyConsciousness2 = new Enemy(enemy2, level, new FollowAndAttack(1), new FollowAndAttack(1));
        level.addBody(enemy2);
        enemyConsciousness3 = new Enemy(enemy3, level, new GoBack(), new FollowAndAttack(1));
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
