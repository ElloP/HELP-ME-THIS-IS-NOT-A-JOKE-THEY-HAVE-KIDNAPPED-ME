package com.helpme.app.model.body.concrete.visitor;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.model.body.inventory.IKeyChain;
import com.helpme.app.model.tile.edge.*;

/**
 * Created by Jacob on 2017-04-08.
 */
public final class Traverse implements IEdgeVisitor<Boolean> {
    private final Maybe<IKeyChain> keychain;

    public Traverse(IKeyChain keychain){
        this.keychain = Maybe.wrap(keychain);
    }

    @Override
    public Boolean visit(IDoor door) {
        if(door.isLocked()){
            if(keychain.check(k -> k.hasKey(door.getKey()))){
                door.unlock();
            }
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(IWall wall) {
        return false;
    }

    @Override
    public Boolean visit(IOpening opening) {
        return true;
    }
}
