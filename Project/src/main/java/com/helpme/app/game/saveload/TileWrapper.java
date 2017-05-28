package com.helpme.app.game.saveload;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.tile.ITile;
import com.helpme.app.game.model.tile.concrete.Tile;
import com.helpme.app.game.model.tile.concrete.TileFactory;
import com.helpme.app.game.model.tile.edge.IEdge;
import com.helpme.app.game.saveload.edge.EdgeWrapper;
import com.helpme.app.game.saveload.item.ItemWrapper;
import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Maybe;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Klas on 2017-05-01.
 */

@XmlRootElement(name = "tile")
public class TileWrapper implements ILoadable<ITile> {

    @XmlElement(name = "position")
    private Vector2Wrapper positionWrapper;

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private ItemWrapper[] itemWrappers;

    @XmlElementWrapper(name = "edges")
    @XmlElement(name = "edge")
    private Map<Vector2Wrapper, EdgeWrapper> edgeWrappers;


    public TileWrapper() {
    }

    public TileWrapper(Vector2f position, ITile tileInterface) {
        if (!(tileInterface instanceof Tile)) {
            return;
        }

        Tile tile = (Tile) tileInterface;
        this.positionWrapper = new Vector2Wrapper(position);
        List<Maybe<IItem>> tileItems = tile.getItems();
        this.itemWrappers = new ItemWrapper[tileItems.size()];
        this.edgeWrappers = new HashMap<>();
        for (int i = 0; i < itemWrappers.length; i++) {
            this.itemWrappers[i] = new ItemWrapper(tileItems.get(i));
        }

        for (Map.Entry<Vector2f, IEdge> entry : tile.readEdges().entrySet()) {
            edgeWrappers.put(new Vector2Wrapper(entry.getKey()), new EdgeWrapper(entry.getValue()));
        }
    }

    @Override
    public ITile getObject() {
        List<Maybe<IItem>> items = new ArrayList<>();
        Map<Vector2f, IEdge> edges = new HashMap<>();

        for (ItemWrapper itemWrapper : itemWrappers) {
            items.add(itemWrapper.getObject());
        }

        for (Map.Entry<Vector2Wrapper, EdgeWrapper> entry : edgeWrappers.entrySet()) {
            edges.put(entry.getKey().getObject(), entry.getValue().getObject());
        }

        return TileFactory.createTile(items, edges);


    }

    public Vector2f getLocation() {
        return this.positionWrapper.getObject();
    }
}
