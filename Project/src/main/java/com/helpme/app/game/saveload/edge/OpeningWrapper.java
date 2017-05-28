package com.helpme.app.game.saveload.edge;

import com.helpme.app.game.model.tile.edge.IEdge;
import com.helpme.app.game.model.tile.edge.concrete.EdgeFactory;
import com.helpme.app.utils.interfaces.ILoadable;

/**
 * Created by kopa on 2017-05-28.
 */
public class OpeningWrapper implements ILoadable<IEdge> {
    @Override
    public IEdge getObject() {
        return EdgeFactory.createOpening();
    }
}
