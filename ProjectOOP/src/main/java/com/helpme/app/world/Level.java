package com.helpme.app.world;

import com.helpme.app.tile.Tile;
import com.helpme.app.utils.Vector2f;

import java.util.Map;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Level {
    Map<Vector2f, Tile> tiles;
    Map<Vector2f, Character> characters;

    public Level(Map<Vector2f, Tile> tiles, Map<Vector2f, Character> characters) {
        this.tiles = tiles;
        this.characters = characters;
    }
}
