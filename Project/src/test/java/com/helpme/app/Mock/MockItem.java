package com.helpme.app.Mock;

import com.helpme.app.world.item.concrete.Consumable;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.concrete.Item;
import com.helpme.app.world.item.concrete.Key;
import com.helpme.app.world.item.concrete.Single;

/**
 * Created by Jacob on 2017-04-11.
 */
public interface MockItem {
    IItem weapon = new Single("Club", target -> target.damage(10), target -> target.damage(5));
    IItem potion = new Consumable("Healing Potion", 1, target -> target.heal(9), target -> target.heal(9));
    IItem defaultWeapon = new Single("Fists", target -> target.damage(2), target -> target.damage(1));
    IItem pickupWeapon = new Single("Sword", target -> target.damage(40), target -> target.damage(10));
    IItem excessiveItem = new Single("Box", stats -> {
    }, stats -> {
    });
    IItem stackingConsumables = new Consumable("Paper", 2, stats -> {
    }, stats -> {
    });
    IItem key = new Key("Red Key");
    IItem pickupKey = new Key("Blue Key");
}
