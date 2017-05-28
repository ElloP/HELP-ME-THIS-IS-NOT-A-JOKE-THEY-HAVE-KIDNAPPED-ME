package com.helpme.app.intelligencetest;

import com.helpme.app.game.model.consciousness.behaviour.concrete.Intelligence;
import com.helpme.app.utils.mathl.Vector2f;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-22.
 */
public class IntelligenceTest {
    private MockBody mockBody;
    private MockSurroundings mockSurroundings;

    @Before
    public void setup(){
        mockBody = new MockBody(Vector2f.ZERO);
        mockSurroundings = new MockSurroundings();
    }

    /**
     * Tests that it returns true given that two bodies next to each other (exactly one tile away)
     * (Tiles diagonal to each other are not neighbours)
     */
    @Test
    public void testIsNextToNorth(){
        MockBody neighbour = new MockBody(Vector2f.NORTH);
        mockSurroundings.mockBody = neighbour;

        assert (Intelligence.isNextTo(mockBody, neighbour, mockSurroundings));
    }

    @Test
    public void testIsNextToEast(){
        MockBody neighbour = new MockBody(Vector2f.EAST);
        mockSurroundings.mockBody = neighbour;

        assert (Intelligence.isNextTo(mockBody, neighbour, mockSurroundings));
    }

    @Test
    public void testIsNextToSouth(){
        MockBody neighbour = new MockBody(Vector2f.SOUTH);
        mockSurroundings.mockBody = neighbour;

        assert (Intelligence.isNextTo(mockBody, neighbour, mockSurroundings));
    }

    @Test
    public void testIsNextToWest(){
        MockBody neighbour = new MockBody(Vector2f.WEST);
        mockSurroundings.mockBody = neighbour;

        assert (Intelligence.isNextTo(mockBody, neighbour, mockSurroundings));
    }

    /**
     * Tests that it returns false given that two bodies are not neighbours (more than one tile away)
     * (Tiles diagonal to each other are not neighbours)
     */
    @Test
    public void testIsNotNextTo(){
        MockBody neighbour = new MockBody(new Vector2f(0,2));
        mockSurroundings.mockBody = neighbour;

        assert (!Intelligence.isNextTo(mockBody, neighbour, mockSurroundings));
    }
}
