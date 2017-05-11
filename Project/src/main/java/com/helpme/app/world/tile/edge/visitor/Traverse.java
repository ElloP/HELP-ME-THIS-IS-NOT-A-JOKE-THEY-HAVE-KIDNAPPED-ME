package com.helpme.app.world.tile.edge.visitor;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.inventory.IKeyChain;
import com.helpme.app.world.tile.edge.Door;
import com.helpme.app.world.tile.edge.Opening;
import com.helpme.app.world.tile.edge.Wall;

/**
 * Created by Jacob on 2017-04-08.
 */
public final class Traverse implements IEdgeVisitor<Boolean> {
    private final Maybe<IKeyChain> keychain;

    public Traverse(IKeyChain keychain){
        this.keychain = Maybe.wrap(keychain);
    }

    @Override
    public Boolean visit(Door door) {
        if(door.isLocked()){
            if(keychain.check(k -> k.hasKey(door.getKey()))){
                door.unlock();
            }
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(Wall wall) {
        return false;
    }

    @Override
    public Boolean visit(Opening opening) {
        return true;
    }
}
