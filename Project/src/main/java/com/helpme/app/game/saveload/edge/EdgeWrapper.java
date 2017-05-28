package com.helpme.app.game.saveload.edge;


import com.helpme.app.game.model.tile.edge.IEdge;
import com.helpme.app.game.model.tile.edge.concrete.Door;
import com.helpme.app.game.model.tile.edge.concrete.EdgeFactory;
import com.helpme.app.game.model.tile.edge.concrete.Opening;
import com.helpme.app.game.model.tile.edge.concrete.Wall;
import com.helpme.app.utils.interfaces.ILoadable;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by kopa on 2017-05-15.
 */
public class EdgeWrapper implements ILoadable<IEdge> {
    @XmlElement(name = "door")
    private DoorWrapper doorWrapper;

    @XmlElement(name = "opening")
    private OpeningWrapper openingWrapper;

    @XmlElement(name = "wall")
    private WallWrapper wallWrapper;


    public EdgeWrapper() {

    }

    public EdgeWrapper(IEdge edge) {
        if (edge instanceof Door) {
            doorWrapper = new DoorWrapper((Door) edge);
        } else if (edge instanceof Opening) {
            openingWrapper = new OpeningWrapper();
        } else if (edge instanceof Wall) {
            wallWrapper = new WallWrapper();
        }
    }

    @Override
    public IEdge getObject() {
        if (doorWrapper != null) {
            return doorWrapper.getObject();
        } else if (openingWrapper != null) {
            return openingWrapper.getObject();
        } else if (wallWrapper != null) {
            return wallWrapper.getObject();
        }
        return null;

    }
}
