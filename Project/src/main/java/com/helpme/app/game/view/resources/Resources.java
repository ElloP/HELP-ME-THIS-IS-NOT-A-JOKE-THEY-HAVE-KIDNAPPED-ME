package com.helpme.app.game.view.resources;

import com.helpme.app.engine.audio.AudioHandler;
import com.helpme.app.engine.renderer.base.Texture;
import com.helpme.app.engine.renderer.base.TextureLoader;
import com.helpme.app.engine.renderer.base.Vertex2D;
import com.helpme.app.engine.renderer.base.Vertex3D;
import com.helpme.app.utils.maybe.Maybe;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by kopa on 2017-05-10.
 */
public final class Resources {
    private static final String RESOURCES_PATH = "/src/main/java/com/helpme/app/game/view/resources/";

    private Resources() {
    }

    private static Dictionary<String, Texture> textures = new Hashtable<>();

    private static Dictionary<String, Integer> sounds = new Hashtable<>();

    public static void init() {
        textures = new Hashtable<String, Texture>() {
            {
                put("floor", TextureLoader.loadTexture(RESOURCES_PATH + "textures/floor.jpg"));
                put("wall", TextureLoader.loadTexture(RESOURCES_PATH + "textures/brick.jpg"));
                put("door", TextureLoader.loadTexture(RESOURCES_PATH + "textures/door.jpg"));
                put("healthframe", TextureLoader.loadTexture(RESOURCES_PATH + "textures/healthframe.png"));
                put("healthbar", TextureLoader.loadTexture(RESOURCES_PATH + "textures/healthbar.png"));
                put("default", TextureLoader.loadTexture(RESOURCES_PATH + "textures/default.png"));
                put("menuload", TextureLoader.loadTexture(RESOURCES_PATH + "textures/menuload.png"));
                put("menunew", TextureLoader.loadTexture(RESOURCES_PATH + "textures/menunew.png"));
                put("klas", TextureLoader.loadTexture(RESOURCES_PATH + "textures/klas.png"));

            }
        };

        sounds = new Hashtable<String, Integer>() {
            {
                put("footstep", AudioHandler.loadSound(RESOURCES_PATH + "sounds/Cowboy.wav"));
                put("groan", AudioHandler.loadSound(RESOURCES_PATH + "sounds/Groan.wav"));
                put("wallMove", AudioHandler.loadSound(RESOURCES_PATH + "sounds/WallMove.wav"));
                put("unlock", AudioHandler.loadSound(RESOURCES_PATH + "sounds/Unlock.wav"));
                put("default", AudioHandler.loadSound(RESOURCES_PATH + "sounds/Woah.wav"));
            }
        };
    }

    public static Maybe<Texture> getTexture(String name) {
        Texture texture = textures.get(name);
        return Maybe.wrap(texture == null ? textures.get("default") : texture);
    }

    public static Maybe<Integer> getSound(String name) {
        Integer sound = sounds.get(name);
        return Maybe.wrap(sound);
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

    public static Vertex2D[] uiVert(float width, float height) {
        return new Vertex2D[]{
                new Vertex2D(0.2f * width, 0.2f * height, 1, 1),
                new Vertex2D(0.2f * width, -0.2f * height, 1, 0),
                new Vertex2D(-0.2f * width, 0.2f * height, 0, 1),

                new Vertex2D(0.2f * width, -0.2f * height, 1, 0),
                new Vertex2D(-0.2f * width, -0.2f * height, 0, 0),
                new Vertex2D(-0.2f * width, 0.2f * height, 0, 1),
        };

    }


    public static Vertex3D[] floorVert() {
        return new Vertex3D[]{
                new Vertex3D(-3, -1.0f, 3, 0.0f, 0.0f),
                new Vertex3D(-3, -1.0f, -3, 0.0f, 1.0f),
                new Vertex3D(3, -1.0f, 3, 1.0f, 0.0f),
                new Vertex3D(3, -1.0f, -3, 1.0f, 1.0f)
        };
    }

    public static Vertex3D[] wallVert() {
        return new Vertex3D[]{
                new Vertex3D(-3.0f, -1.0f, 3.0f, 0.0f, 0.0f),
                new Vertex3D(3.0f, -1.0f, 3.0f, 1.0f, 0.0f),
                new Vertex3D(3.0f, 3.0f, 3.0f, 1.0f, 1.0f),
                new Vertex3D(-3.0f, 3.0f, 3.0f, 0.0f, 1.0f)
        };
    }
}
