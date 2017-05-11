package com.helpme.app.saveload;

import com.helpme.app.world.character.IBody;

/**
 * Created by Klas on 2017-05-01.
 */
public interface IBodyWrapper extends ILoadable<IBody> {
    Vector2Wrapper getHitpoints();
    void setHitpoints(Vector2Wrapper vec);
    String toString();
    InventoryWrapper getInventory();
    void setInventory(InventoryWrapper inventory);
    void setDead(boolean dead);
    boolean getDead();
}
