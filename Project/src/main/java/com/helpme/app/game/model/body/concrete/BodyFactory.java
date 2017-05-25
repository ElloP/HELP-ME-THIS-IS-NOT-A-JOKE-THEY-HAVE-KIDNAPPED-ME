package com.helpme.app.game.model.body.concrete;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.body.dialogue.IDialogue;
import com.helpme.app.game.model.body.inventory.IInventory;
import com.helpme.app.game.model.body.inventory.concrete.InventoryFactory;
import com.helpme.app.utils.mathl.Vector2f;

/**
 * Created by kopa on 2017-05-12.
 */
public final class BodyFactory {
    private BodyFactory(){

    }

    public static IBody createBody(Vector2f position, Vector2f direction, IDialogue dialogue) {
        return createBody(null, position, direction, null, null, dialogue);
    }

    public static IBody createBody(IInventory inventory, Vector2f position, Vector2f direction, float hitpoints) {
        return createBody(inventory, position, direction, new Vector2f(hitpoints, hitpoints), null, null);
    }

    public static IBody createBody(IInventory inventory, Vector2f position, Vector2f direction, Vector2f hitpoints) {
        return createBody(inventory, position, direction, hitpoints, null, null);
    }

    public static IBody createBody(IInventory inventory, Vector2f position, Vector2f direction, Vector2f hitpoints, Vector2f startingPosition, IDialogue dialogue) {
        return new Body(inventory == null ? InventoryFactory.createInventory(null, null, null) : inventory,
                position == null ? Vector2f.ZERO : position,
                direction == null ? Vector2f.NORTH : direction,
                hitpoints == null ? new Vector2f(100,100) : hitpoints,
                startingPosition == null ? position : startingPosition,
                dialogue
        );
    }
}
