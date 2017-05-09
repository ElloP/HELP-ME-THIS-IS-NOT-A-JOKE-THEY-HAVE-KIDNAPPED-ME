package com.helpme.app.world.tile.edge.visitor;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.inventory.IKeyChain;
import com.helpme.app.world.tile.edge.Door;
import com.helpme.app.world.tile.edge.Opening;
import com.helpme.app.world.tile.edge.Wall;

/**
 * Created by Jacob on 2017-04-08.
 */
public class Traverse implements IEdgeVisitor {
    private final Maybe<IKeyChain> keychain;

    public Traverse(IKeyChain keychain){
        this.keychain = Maybe.wrap(keychain);
    }

    @Override
    public boolean visit(Door door) {
        if(door.isLocked()){
            if(keychain.check(k -> k.hasKey(door.getKey()))){
                door.unlock();
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean visit(Wall wall) {
        return false;
    }

    @Override
    public boolean visit(Opening opening) {
        return true;
    }
}
