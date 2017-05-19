package com.helpme.app.engine.game;

import com.helpme.app.engine.renderer.base.Texture;
import com.helpme.app.engine.renderer.base.Vertex;
import com.helpme.app.engine.renderer.base.Vertex2D;
import com.helpme.app.engine.utils.TextureLoader;
import com.helpme.app.utils.maybe.Maybe;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by kopa on 2017-05-10.
 */
public final class Resources {
    private Resources() {
    }

    private static final Dictionary<String, Texture> textures = new Hashtable<String, Texture>() {
        {
            put("floor", TextureLoader.loadTexture("floor.jpg"));
            put("wall", TextureLoader.loadTexture("brick.jpg"));
            put("door", TextureLoader.loadTexture("door.jpg"));
            put("health", TextureLoader.loadTexture("health.png"));
            put("default", TextureLoader.loadTexture("default.png"));
        }
    };

    public static Maybe<Texture> getTexture(String name) {
        Texture texture = textures.get(name);
        return Maybe.wrap(texture == null ? textures.get("default") : texture);
    }


    public final static int[] floor() {
        return new int[]{
                0, 3, 2,
                3, 0, 1
        };
    }

    public final static int[] wall() {
        return new int[]{
                0, 1, 2,
                2, 3, 0
        };
    }

    public static Vertex2D[] uiVert() {
        return new Vertex2D[] {
                new Vertex2D(0.5f, 0.5f, 1, 1),
                new Vertex2D(0.5f, -0.5f, 1, 0),
                new Vertex2D(-0.5f, 0.5f, 0, 1),

                new Vertex2D(0.5f, -0.5f, 1, 0),
                new Vertex2D(-0.5f, -0.5f, 0, 0),
                new Vertex2D(-0.5f, 0.5f, 0, 1),
        };

    }


    public static Vertex[] floorVert() {
        return new Vertex[]{
                new Vertex(-3, -1.0f, 3, 0.0f, 0.0f),
                new Vertex(-3, -1.0f, -3, 0.0f, 1.0f),
                new Vertex(3, -1.0f, 3, 1.0f, 0.0f),
                new Vertex(3, -1.0f, -3, 1.0f, 1.0f)
        };
    }

    public static Vertex[] wallVert() {
        return new Vertex[]{
                new Vertex(-3.0f, -1.0f, 3.0f, 0.0f, 0.0f),
                new Vertex(3.0f, -1.0f, 3.0f, 1.0f, 0.0f),
                new Vertex(3.0f, 3.0f, 3.0f, 1.0f, 1.0f),
                new Vertex(-3.0f, 3.0f, 3.0f, 0.0f, 1.0f)
        };
    }
}
