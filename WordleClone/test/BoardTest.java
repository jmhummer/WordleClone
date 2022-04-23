import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test Board class
 *
 * @author Nick Sanford
 */
public class BoardTest {

    private String guessedWord1 = "HELLO";
    private String guessedWord2 = "STAMP";
    private String guessedWord3 = "CRANE";
    private String guessedWord4 = "MULCH";
    private String guessedWord5 = "NINJA";
    private String guessedWord6 = "WATER";
    private String guessedWord7 = "HAPPY";
    private String guessedWord8 = "POPPY";
    
    
    @Test
    public void testToStringFullBoardHappy() {
        // Create Board with 5 letters per word and 6 max guesses, target word "happy"
        Board gameBoard = new Board(6, 5, "happy");
        gameBoard.addGuess(guessedWord1);
        gameBoard.addGuess(guessedWord2);
        gameBoard.addGuess(guessedWord3);
        gameBoard.addGuess(guessedWord4);
        gameBoard.addGuess(guessedWord8);
        gameBoard.addGuess(guessedWord7);
        
        String testBoardString = gameBoard.toString();
        String expectedString = "HELLO\norange black black black black \n" +
                                "STAMP\nblack black blue black blue \n" +
                                "CRANE\nblack black blue black black \n" +
                                "MULCH\nblack black black black blue \n" +
                                "POPPY\nblack black orange orange orange \n" +
                                "HAPPY\norange orange orange orange orange \n";
        
        assertEquals(expectedString, testBoardString, "Happy Full Board to String");
    }
    
    @Test
    public void testToStringFullBoardPoppy() {
        Board gameBoard = new Board(6, 5, "poppy");
        gameBoard.addGuess(guessedWord1);
        gameBoard.addGuess(guessedWord2);
        gameBoard.addGuess(guessedWord3);
        gameBoard.addGuess(guessedWord4);
        gameBoard.addGuess(guessedWord7);
        gameBoard.addGuess(guessedWord8);
        
        String testBoardString = gameBoard.toString();
        String expectedString = "HELLO\nblack black black black blue \n" +
                                "STAMP\nblack black black black blue \n" +
                                "CRANE\nblack black black black black \n" +
                                "MULCH\nblack black black black black \n" +
                                "HAPPY\nblack black orange orange orange \n" +
                                "POPPY\norange orange orange orange orange \n";
        
        assertEquals(expectedString, testBoardString, "Poppy Full Board to String");
    }
    
    @Test
    public void testToStringOneGuessApple() {
        Board gameBoard = new Board(6, 5, "apple");
        gameBoard.addGuess(guessedWord7);
        
        String testBoardString = gameBoard.toString();
        String expectedString = "HAPPY\nblack blue orange blue black \n";
        assertEquals(expectedString, testBoardString, "Apple One Guess to String");
                                
    }
}