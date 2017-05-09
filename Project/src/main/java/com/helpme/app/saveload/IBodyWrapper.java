package com.helpme.app.saveload;

/**
 * Created by Klas on 2017-05-01.
 */
public interface IBodyWrapper {
    float getX();
    float getY();
    void setX(float x);
    void setY(float y);
    float getMaxHitpoints();
    float getCurrentHitpoints();
    void setMaxHitpoints(float maxHitpoints);
    void setCurrentHitpoints(float currentHitpoints);
    String toString();
    InventoryWrapper getInventory();
    void setInventory(InventoryWrapper inventory);

}
