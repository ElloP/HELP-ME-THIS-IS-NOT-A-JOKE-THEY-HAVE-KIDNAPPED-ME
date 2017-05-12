package com.helpme.app.world.character;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.character.dialogue.IDialogue;
import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.character.inventory.Inventory;
import com.helpme.app.world.character.inventory.InventoryFactory;

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
                direction == null ? Vector2f.up : direction,
                hitpoints == null ? new Vector2f(100,100) : hitpoints,
                dialogue
        );
    }
}
