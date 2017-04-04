package com.helpme.app.world;

import com.helpme.app.character.Monster;
import com.helpme.app.utilities.Coordinate;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * Created by kopa on 2017-04-02.
 */
public class WorldView {

    private WorldView() {
    }

    public static void show(double window, World world) {

        Coordinate offset = new Coordinate(0,0);
        Monster player = world.getPlayer();
        Coordinate position = player.getPosition();

        drawSquare(position.x+offset.x, position.y+offset.y, 10,  10, Color.blue);
    }

    public static void drawSquare(float xPos, float yPos, float width, float height, Color color) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        // set the color of the quad (R,G,B,A)
        GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());

        // draw quad
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(xPos, yPos);
            GL11.glVertex2f(xPos + width, yPos);
            GL11.glVertex2f(xPos + width, yPos + height);
            GL11.glVertex2f(xPos, yPos + height);
        GL11.glEnd();
    }

}
