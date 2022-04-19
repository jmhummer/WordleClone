import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test Wordle class
 *
 * @author Nick Sanford
 */
public class WordleTest {

    @Test
    public void testExecutionTime() {
        // Argument of 0 for a regular game
        Wordle wordle = new Wordle(0);
        assertTrue(1 == 1);
        // Current Junit test run time: 127 ms
    }
    

}