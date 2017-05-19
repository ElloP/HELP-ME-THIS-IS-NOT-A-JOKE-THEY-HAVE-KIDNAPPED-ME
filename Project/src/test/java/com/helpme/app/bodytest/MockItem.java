package com.helpme.app.bodytest;

import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.concrete.Item;
import com.helpme.app.world.item.concrete.Single;

/**
 * Created by kopa on 2017-05-11.
 */
public final class MockItem {
    private MockItem(){

    }

    static IItem activeWeapon() {
        return new Single("Club", target -> target.damage(1), target -> target.damage(1));
    }

    static IItem defaultWeapon(){
        return new Single("Heal", target -> target.heal(0), target -> target.heal(1));
    }

    static IItem strongPotion(){
        return new Single("Potion", target -> target.heal(101), target -> target.heal(101));
    }

    static IItem strongWeapon(){
        return new Single("Katana", target -> target.damage(101), target -> target.damage(101));
    }
}
