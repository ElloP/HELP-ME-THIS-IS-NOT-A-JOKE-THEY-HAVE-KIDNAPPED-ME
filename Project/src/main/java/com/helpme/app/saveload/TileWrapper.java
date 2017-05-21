package com.helpme.app.saveload;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IReadItem;
import com.helpme.app.model.tile.IReadTile;
import com.helpme.app.model.tile.ITile;
import com.helpme.app.model.tile.concrete.TileFactory;
import com.helpme.app.model.tile.edge.IEdge;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Klas on 2017-05-01.
 */

@XmlRootElement(name="tile")
public class TileWrapper implements ILoadable<ITile> {

    private Vector2Wrapper positionWrapper;
    private ItemWrapper[] itemWrappers;
    private Map<Vector2Wrapper, EdgeWrapper> edgeWrappers;


    //TODO (klas) Save edgeWrappers
    public TileWrapper() {
    }

    public TileWrapper(IReadTile tile, Vector2f position) {
        this.positionWrapper = new Vector2Wrapper(position);
        List<Maybe<IReadItem>> tileItems = tile.readItems(); //TODO (klas) Fix maybe nothing case?
        this.itemWrappers = new ItemWrapper[tileItems.size()];
        this.edgeWrappers = new HashMap<>();
        for (int i = 0; i < itemWrappers.length; i++) {
            int index = i;
            tileItems.get(index).run(item -> this.itemWrappers[index] = new ItemWrapper(item));
        }

        for (Map.Entry<Vector2f, IEdge> entry : tile.readEdges().entrySet()) {
            edgeWrappers.put(new Vector2Wrapper(entry.getKey()), new EdgeWrapper(entry.getValue()));
        }
    }

    @XmlElement(name = "position")
    public Vector2Wrapper getPositionWrapper() {
        return this.positionWrapper;
    }

    public void setPositionWrapper(Vector2Wrapper pos) {
        this.positionWrapper = pos;

    }



    @XmlElementWrapper(name="items")
    @XmlElement(name = "item")
    public ItemWrapper[] getItemWrappers() {
        return this.itemWrappers;
    }

    public void setItemWrappers(ItemWrapper[] itemWrappers) {
        this.itemWrappers = new ItemWrapper[itemWrappers.length];
        for (int i = 0; i < itemWrappers.length; i++) {
            this.itemWrappers[i] = new ItemWrapper(itemWrappers[i].getName());
        }
    }

    @XmlElementWrapper(name="edges")
    @XmlElement(name = "edge")
    public Map<Vector2Wrapper, EdgeWrapper> getEdges() {
        return this.edgeWrappers;
    }

    public void setEdges(Map<Vector2Wrapper, EdgeWrapper> edges) {
        this.edgeWrappers = edges;

    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Tile at " + positionWrapper);
        if (itemWrappers != null) {
            for (ItemWrapper item : itemWrappers) {
                if (item != null) result.append("\nItem: " + (item.getName()));
            }
        }

        if(edgeWrappers != null){
            for(Map.Entry<Vector2Wrapper, EdgeWrapper> entry : edgeWrappers.entrySet()){
                if(entry != null) result.append("\nEdge: " + (entry.getValue().toString()) + " facing " + entry.getKey().toString());
            }
        }
        return result.toString();
    }

    @Override
    public ITile getObject() {
        IItem[] tmp = null;
        if (itemWrappers != null) {
            tmp = new IItem[itemWrappers.length];
            for (int i = 0; i < itemWrappers.length; i++) {
                tmp[i] = itemWrappers[i].getObject();
            }
        }

        Map<Vector2f, IEdge> edges = new HashMap<>();
        for (Map.Entry<Vector2Wrapper, EdgeWrapper> entry : edgeWrappers.entrySet()) {
            edges.put(entry.getKey().getObject(), entry.getValue().getObject());
        }

        return TileFactory.createTile(tmp, edges);


    }
    public Vector2f getLocation(){
        return this.positionWrapper.getObject();
    }
}
