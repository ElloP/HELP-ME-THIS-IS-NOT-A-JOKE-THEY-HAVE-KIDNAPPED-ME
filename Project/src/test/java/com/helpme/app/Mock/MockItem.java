package com.helpme.app.Mock;

import com.helpme.app.item.Consumable;
import com.helpme.app.item.IItem;
import com.helpme.app.item.Item;
import com.helpme.app.item.Key;
import com.helpme.app.item.effect.IEffectFactory;

/**
 * Created by kopa on 2017-04-11.
 */
public interface MockItem {
    IItem weapon = new Item("Club", target -> target.damage(10), target -> target.damage(5));
    IItem potion = new Consumable("Healing Potion", 1, target -> target.heal(9), target -> target.heal(9));
    IItem defaultWeapon = new Item("Fists", target -> target.damage(2), target -> target.damage(1));
    IItem pickupWeapon = new Item("Sword", target -> target.damage(40), target -> target.damage(10));
    IItem excessiveItem = new Item("Box", stats -> {
    }, stats -> {
    });
    IItem stackingConsumables = new Consumable("Paper", 2, stats -> {
    }, stats -> {
    });
    IItem key = new Key("Red Key");
    IItem pickupKey = new Key("Blue Key");
}
