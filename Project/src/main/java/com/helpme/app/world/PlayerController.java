package com.helpme.app.world;

import com.helpme.app.character.IMonster;
import com.helpme.app.item.IItem;
import com.helpme.app.utils.Vector2f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 2017-04-08.
 */
public class PlayerController implements IController {
    private IMonster player;
    private ILevel level;

    public PlayerController(IMonster player, ILevel level) {
        this.player = player;
        this.level = level;
    }

    public void update() {

    }

    public IMonster getPlayer() {
        return player.clone();
    }

    private boolean isMovementAllowed(IMonster monster, Vector2f direction) {
        Vector2f position = monster.getPosition();
        Vector2f destination = Vector2f.add(position, direction);

        if (level.isEdgeBlocked(monster, position, direction)) {
            return false;
        }
        if (level.isTileOccupied(destination)) {
            return false;
        }

        return true;
    }

    public void movePlayerForward() {
        if (!isMovementAllowed(player, player.getDirection())) return;
        player.moveForward();
    }

    public void movePlayerRight() {
        if (!isMovementAllowed(player, player.getDirection().right())) return;
        player.moveRight();
    }

    public void movePlayerBackward() {
        if (!isMovementAllowed(player, player.getDirection().backward())) return;
        player.moveBackward();
    }

    public void movePlayerLeft() {
        if (!isMovementAllowed(player, player.getDirection().left())) return;
        player.moveLeft();
    }

    public void rotatePlayerRight() {
        player.rotateRight();
    }

    public void rotatePlayerLeft() {
        player.rotateLeft();
    }

    public void setPlayerPosition(Vector2f position) {
        if (!level.isTileValid(position)) return;
        player.setPosition(position);
    }

    public void usePlayerAttack() {
        IMonster target = level.getMonster(player.targetTile());
        if (target == null) return;
        player.attack(target);
    }

    public void usePlayerPickup() {
        Vector2f position = player.getPosition();
        List<IItem> items = level.popTileItems(position);

        if (items == null) {
            return;
        }

        for (IItem item : items) {
            if(item == null) {
                continue;
            }

            if (!player.pickupItem(item)) {
                level.addTileItem(position, item);
            }
        }

    }

    public void usePlayerSelfie() {
        player.selfie();
    }

    public void changePlayerActiveItem(int index) {
        player.changeActiveItem(index);
    }
}
