package com.helpme.app.world.tile.concrete;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.tile.ITile;
import com.helpme.app.world.tile.edge.IEdge;

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
        Map<Vector2f, IEdge> edges = new HashMap<Vector2f, IEdge>(){
            {
                put(Vector2f.up, north);
                put(Vector2f.right, east);
                put(Vector2f.down, south);
                put(Vector2f.left, west);
            }
        };

        return createTile(items, edges);
    }

    public static ITile createTile(IItem[] items, Map<Vector2f, IEdge> edges) {
        List<Maybe<IItem>> list = new ArrayList<Maybe<IItem>>(){
            {
                if(items != null){
                    for(IItem item : items){
                        add(Maybe.wrap(item));
                    }
                }
            }
        };

        return new Tile(list, edges == null ? new HashMap<>() : edges);
    }
}
