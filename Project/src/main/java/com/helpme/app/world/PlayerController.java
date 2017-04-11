package com.helpme.app.world;

import com.helpme.app.character.IMonster;
import com.helpme.app.character.ITarget;
import com.helpme.app.item.IItem;
import com.helpme.app.utils.Vector2f;

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
        Vector2f position  = player.getPosition();
        Vector2f direction = player.getDirection();
        if(level.isEdgeBlocked(player,position,direction)){
            player.attack(level.getTarget(position,direction));
            return;
        }
        ITarget target = level.getMonster(player.targetTile());
        if (target == null) return; // Attack opening instead?
        player.attack(target);
    }

    public void usePlayerPickupAll() {
        Vector2f position = player.getPosition();
        IItem[] items = level.removeTileItems(position);

        if (items == null) {
            return;
        }

        for (IItem item : items) {
            if (item == null) {
                continue;
            }

            if (!player.pickupItem(item)) {
                level.addTileItem(position, item);
            }
        }
    }

    public void usePlayerPickupSingle(int index){
        Vector2f position = player.getPosition();
        IItem item = level.removeTileItem(position, index);
        if (item == null) {
            return;
        }
        player.pickupItem(item);
    }

    public void setPlayerItems(IItem[] items) {
        player.setItems(items);
    }

    public void dropPlayerItem(int index) {
        if (level.isTileValid(player.getPosition())) {
            IItem item = player.dropItem(index);
            if (item == null) {
                return;
            }
            level.addTileItem(player.getPosition(), item);
        }

    }

    public void usePlayerSelfie() {
        player.selfie();
    }

    public void changePlayerActiveItem(int index) {
        player.changeActiveItem(index);
    }

    public String usePlayerTalk() { //TODO (jacob) change name to something better
        Vector2f position = player.getPosition();
        Vector2f direction = player.getDirection();
        Vector2f destination = Vector2f.add(position, direction);

        if (level.isEdgeBlocked(player, position, direction)) {
            return null;
        }
        IMonster npc = level.getMonster(destination);
        if (npc != null) {
            //TODO (klas) output
            return npc.getResponse();
        }
        return null;
    }
}
