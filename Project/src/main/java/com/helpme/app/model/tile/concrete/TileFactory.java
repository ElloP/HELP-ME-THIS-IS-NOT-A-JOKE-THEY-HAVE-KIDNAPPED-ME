package com.helpme.app.model.tile.concrete;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.tile.ITile;
import com.helpme.app.model.tile.edge.IEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-04-10.
 */
public final class TileFactory {
    private TileFactory(){

    }

    public static ITile createTile(IItem[] items) {
        return createTile(items, null);
    }

    public static ITile createTile(IItem[] items, IEdge north, IEdge east, IEdge south, IEdge west){
        Map<Vector2f, IEdge> edges = new HashMap<>();
        edges.put(Vector2f.NORTH, north);
        edges.put(Vector2f.EAST, east);
        edges.put(Vector2f.SOUTH, south);
        edges.put(Vector2f.WEST, west);

        return createTile(items, edges);
    }

    public static ITile createTile(IItem[] items, Map<Vector2f, IEdge> edges) {
        List<Maybe<IItem>> itemList = new ArrayList<>();

        if(items != null){
            for(IItem item : items){
                itemList.add(Maybe.wrap(item));
            }
        }

        return new Tile(itemList, edges == null ? new HashMap<>() : edges);
    }
}
