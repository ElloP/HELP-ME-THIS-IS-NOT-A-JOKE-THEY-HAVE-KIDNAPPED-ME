package com.helpme.app.saveload;


import com.helpme.app.saveload.visitor.GetEdgeInfo;
import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.model.item.IReadItem;
import com.helpme.app.model.tile.edge.*;
import com.helpme.app.model.tile.edge.concrete.EdgeFactory;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by kopa on 2017-05-15.
 */
public class EdgeWrapper implements ILoadable<IEdge> {
    protected String type;
    private Boolean locked;
    private ItemWrapper key;


    public EdgeWrapper() {

    }

    public EdgeWrapper(IEdge edge) {
        Tuple3<String, Boolean, IReadItem> edgeInfo = edge.accept(new GetEdgeInfo());
        type = edgeInfo.a;
        locked = edgeInfo.b;
        key = new ItemWrapper(edgeInfo.c);
    }

    @XmlElement(name = "type")
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String result = "";
        result += type;
        if (type.equals("door")) {
            result += " is " + (locked ? "locked" : "unlocked") + " with " + key.toString();
        }
        return result;
    }

    @XmlElement(name = "locked")
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @XmlElement(name = "key")
    public ItemWrapper getKey() {
        return key;
    }

    public void setKey(ItemWrapper key) {
        this.key = key;
    }

    @Override
    public IEdge getObject() {
        switch (type) {
            case "door":
                return EdgeFactory.createDoor(locked, key.getObject());
            case "opening":
                return EdgeFactory.createOpening();
            case "wall":
                return EdgeFactory.createWall();
        }
        return null;

    }
}
