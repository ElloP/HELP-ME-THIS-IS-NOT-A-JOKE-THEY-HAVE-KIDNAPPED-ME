package com.helpme.app.saveload;


import com.helpme.app.world.tile.edge.Door;
import com.helpme.app.world.tile.edge.EdgeType;
import com.helpme.app.world.tile.edge.IEdge;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by og on 2017-05-11.
 */
public class EdgeWrapper {
    private ItemWrapper key;
    private boolean locked;
    private EdgeType edgeType;

    public EdgeWrapper(){}

    public EdgeWrapper(IEdge edge){
        this.edgeType = edge.getType();
        if(edge.getType() == EdgeType.DOOR){
            Door door = (Door)edge;
            this.key = new ItemWrapper(door.getKey());
            this.locked = door.isLocked();
        } else{
            key = null;
            locked = false;
        }
    }

    @XmlElement(name="Edge")
    public EdgeType getEdgeType() {
        return edgeType;
    }

    public void setEdgeType(EdgeType edgeType) {
        this.edgeType = edgeType;
    }
    @XmlElement(name="Key")
    public ItemWrapper getKey() {
        return key;
    }
    public void setKey(ItemWrapper key) {
        this.key = key;
    }
    @XmlElement(name="Locked")
    public boolean isLocked() {
        return locked;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    public IEdge getObject(){
        if(edgeType == EdgeType.DOOR) return new Door(locked,key.getObject());
        else return IEdge.createEdge(edgeType).getValue();
    }
}
