package com.helpme.app.tile.edge;

/**
 * Created by kopa on 2017-03-30.
 */
public class Door extends Edge {
    private boolean locked;

    public boolean isLocked(){
        return locked;
    }

    public boolean unlock(boolean key){
        if (key)
            locked = false;
        else
            locked = true;

        return locked;
    }

    public boolean walkOver() {
        return !locked;
    }
}
