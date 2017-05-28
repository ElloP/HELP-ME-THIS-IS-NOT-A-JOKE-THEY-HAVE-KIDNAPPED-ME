package com.helpme.app.game.saveload.edge;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.tile.edge.IDoor;
import com.helpme.app.game.model.tile.edge.IEdge;
import com.helpme.app.game.model.tile.edge.concrete.EdgeFactory;
import com.helpme.app.game.saveload.item.ItemWrapper;
import com.helpme.app.game.saveload.item.KeyWrapper;
import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.utils.maybe.Maybe;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by kopa on 2017-05-28.
 */
public class DoorWrapper implements ILoadable<IEdge> {;
    @XmlElement(name = "locked")
    private Boolean locked;

    @XmlElement(name = "key")
    private ItemWrapper keyWrapper;

    public DoorWrapper(){

    }

    public DoorWrapper(IDoor door){
        locked = door.isLocked();
        keyWrapper = new ItemWrapper(Maybe.wrap(door.getKey()));
    }

    @Override
    public IEdge getObject() {
        Maybe<IItem> maybeKey = keyWrapper.getObject();
        return EdgeFactory.createDoor(locked, maybeKey.isJust() ? maybeKey.getValue() : null);
    }
}
