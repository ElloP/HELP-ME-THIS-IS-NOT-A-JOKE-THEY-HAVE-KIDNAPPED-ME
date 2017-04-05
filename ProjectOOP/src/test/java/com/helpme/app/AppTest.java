package com.helpme.app;

import com.helpme.app.world.Level;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.BeforeEach;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;

/**
 * Unit test for simple App.
 */

public class AppTest
        extends TestCase {
    public Level testLevel;

    @BeforeEach
    public void init(){
        
    }

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     * <p>
     * public static Test suite()
     * {
     * return new TestSuite( AppTest.class );
     * }
     * <p>
     * /**
     * Rigourous Test :-)
     */
    public void testRotateRight() {
        assertTrue(true);
    }
}
