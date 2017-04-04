package com.helpme.app.character;

/**
 * Created by kopa on 2017-03-30.
 */
public class Player extends Character {
    private Direction direction;

    public int attack(NPC npc) {
        return 0;
    }

    public void talk() {

    }

    public void move(){
        switch (direction){
            case North:
                if (this.tile.walkPast(Direction.North) != null)
                    this.tile = this.tile.walkPast(Direction.North);
                break;
            case South:
                if (this.tile.walkPast(Direction.South) != null)
                    this.tile = this.tile.walkPast(Direction.South);
                break;
            case West:
                if (this.tile.walkPast(Direction.West) != null)
                    this.tile = this.tile.walkPast(Direction.West);
                break;
            case East:
                if (this.tile.walkPast(Direction.East) != null)
                    this.tile = this.tile.walkPast(Direction.East);
                break;
        }
        this.getTile();
    }
    public void rotateLeft(){
        switch (direction){
            case North:
                direction = Direction.West;
                break;
            case West:
                direction = Direction.South;
                break;
            case South:
                direction = Direction.East;
                break;
            case East:
                direction = Direction.North;
                break;
        }
    }

    public void rotateRight(){
        switch (direction){
            case North:
                direction = Direction.East;
                break;
            case West:
                direction = Direction.North;
                break;
            case South:
                direction = Direction.West;
                break;
            case East:
                direction = Direction.South;
                break;
        }
    }
}
