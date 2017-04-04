package com.helpme.app.tile.edge;

/**
 * Created by kopa on 2017-03-30.
 */
public class Wall implements Edge{
    public boolean traverse() {
        System.out.println("Blocked by wall");
        return false;
    }
}
