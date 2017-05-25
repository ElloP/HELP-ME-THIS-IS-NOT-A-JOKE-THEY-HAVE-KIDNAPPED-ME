package com.helpme.app.game.model.body.concrete.visitor;

import com.helpme.app.game.model.body.inventory.IKeyChain;
import com.helpme.app.game.model.tile.edge.IDoor;
import com.helpme.app.game.model.tile.edge.IEdgeVisitor;
import com.helpme.app.game.model.tile.edge.IOpening;
import com.helpme.app.game.model.tile.edge.IWall;
import com.helpme.app.utils.maybe.Maybe;

/**
 * Created by kopa on 2017-05-25.
 */
public final class Unlock implements IEdgeVisitor<Boolean> {
    private final Maybe<IKeyChain> keychain;

    public Unlock(IKeyChain keychain){
        this.keychain = Maybe.wrap(keychain);
    }

    @Override
    public Boolean visit(IDoor door) {
        if(keychain.check(k -> k.hasKey(door.getKey()))){
            door.unlock();
            return true;
        }
        return false;
    }

    @Override
    public Boolean visit(IWall wall) {
        return false;
    }

    @Override
    public Boolean visit(IOpening opening) {
        return false;
    }
}
