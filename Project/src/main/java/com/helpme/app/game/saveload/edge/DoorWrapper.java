package com.helpme.app.game.saveload.edge;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.tile.edge.IDoor;
import com.helpme.app.game.model.tile.edge.IEdge;
import com.helpme.app.game.model.tile.edge.concrete.EdgeFactory;
import com.helpme.app.game.saveload.item.KeyWrapper;
import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.utils.maybe.Maybe;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by kopa on 2017-05-28.
 */
public class DoorWrapper implements ILoadable<IEdge> {;
    private Boolean locked;
    private KeyWrapper keyWrapper;

    public DoorWrapper(){

    }

    public DoorWrapper(IDoor door){
        locked = door.isLocked();
        keyWrapper = new KeyWrapper(Maybe.wrap(door.getKey()));
    }

    @XmlElement(name = "locked")
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @XmlElement(name = "key")
    public KeyWrapper getKey() {
        return keyWrapper;
    }

    public void setKey(KeyWrapper key) {
        this.keyWrapper = key;
    }

    @Override
    public IEdge getObject() {
        Maybe<IItem> maybeKey = keyWrapper.getObject();
        return EdgeFactory.createDoor(locked, maybeKey.isJust() ? maybeKey.getValue() : null);
    }
}
