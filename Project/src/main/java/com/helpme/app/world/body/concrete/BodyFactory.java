package com.helpme.app.world.body.concrete;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.body.dialogue.IDialogue;
import com.helpme.app.world.body.inventory.IInventory;
import com.helpme.app.world.body.inventory.concrete.InventoryFactory;

/**
 * Created by kopa on 2017-05-12.
 */
public final class BodyFactory {
    private BodyFactory(){

    }

    public static IBody createBody(Vector2f position, Vector2f direction, IDialogue dialogue) {
        return createBody(null, position, direction, null, dialogue);
    }

    public static IBody createBody(IInventory inventory, Vector2f position, Vector2f direction, float hitpoints) {
        return createBody(inventory, position, direction, new Vector2f(hitpoints, hitpoints), null);
    }

    public static IBody createBody(IInventory inventory, Vector2f position, Vector2f direction, Vector2f hitpoints) {
        return createBody(inventory, position, direction, hitpoints, null);
    }

    public static IBody createBody(IInventory inventory, Vector2f position, Vector2f direction, Vector2f hitpoints, IDialogue dialogue) {
        return new Body(inventory == null ? InventoryFactory.createInventory(null, null, null) : inventory,
                position == null ? Vector2f.zero : position,
                direction == null ? Vector2f.north : direction,
                hitpoints == null ? new Vector2f(100,100) : hitpoints,
                dialogue
        );
    }
}