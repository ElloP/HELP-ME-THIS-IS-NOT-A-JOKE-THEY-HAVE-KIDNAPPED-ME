package com.helpme.app;

import com.helpme.app.character.Monster;
import com.helpme.app.tile.Tile;
import com.helpme.app.utilities.Coordinate;
import com.helpme.app.utilities.InputHandler;
import com.helpme.app.world.Level;
import com.helpme.app.world.World;
import com.helpme.app.world.WorldView;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Hello world!
 */

public class App {
    // The window handle
    private InputHandler inputHandler = new InputHandler();
    private long window;

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        window = glfwCreateWindow(300, 300, "Hello World!", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (l, i, i1, i2, i3) -> inputHandler.setKeyboardKey(l, i, i1, i2, i3));

        // Setup a cursor position callback. It will be called every time the mouse cursor position changes
        glfwSetCursorPosCallback(window, (l, v, v1) -> inputHandler.setCursorPosition(l, v, v1));

        // Setup a cursor enter callback. It will be called every time the mouse cursor enters or leaves the window
        glfwSetCursorEnterCallback(window, (l, b) -> inputHandler.setCursorEntered(l,b));

        // Setup a mouse button callback. It will be called every time a mouse button is clicked
        glfwSetMouseButtonCallback(window, (l, i, i1, i2) -> inputHandler.setMouseButton(l, i, i1, i2));

        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
    }

    private void loop() {

        Map<Coordinate, Tile> tiles = new HashMap<>();
        tiles.put(new Coordinate(0,0), Tile.empty());
        tiles.put(new Coordinate(1,0), Tile.empty());
        tiles.put(new Coordinate(2,0), Tile.empty());
        tiles.put(new Coordinate(2,1), Tile.empty());
        Level testLevel = new Level(tiles, new Coordinate(0,0));
        World game = new World(new Level[] {testLevel}, new Monster());
        game.startLevel();

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 0, 600, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);


        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents(); // poll for events first

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            glfwSwapBuffers(window); // swap the color buffers


            if (InputHandler.isKeyboardKeyPress(GLFW_KEY_ESCAPE)) {
                System.out.println("GDSGDS");
            }

            game.update();
            WorldView.show(window, game);

            inputHandler.update(); // update "press" and "release" states to their respective up or down
        }
    }

    public static void main(String[] args) {
        new App().run();
    }
}
