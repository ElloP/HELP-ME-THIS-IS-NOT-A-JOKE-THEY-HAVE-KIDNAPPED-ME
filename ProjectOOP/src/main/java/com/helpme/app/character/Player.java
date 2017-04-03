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

    public void move(Direction){
        switch (direction){
            case North:
                //this.setTile(this.getTile().);

        }
        this.getTile();
    }
}
