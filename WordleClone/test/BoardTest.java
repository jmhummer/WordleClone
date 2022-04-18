import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test Board class
 *
 * @author Nick Sanford
 */
public class BoardTest {

    private char[] lettersArray1 = {'h', 'e', 'l', 'l', 'o'};
    private char[] lettersArray2 = {'s', 't', 'a', 'm', 'p'};
    private char[] lettersArray3 = {'c', 'r', 'a', 'n', 'e'};
    private char[] lettersArray4 = {'m', 'u', 'l', 'c', 'h'};
    private char[] lettersArray5 = {'n', 'i', 'n', 'j', 'a'};
    private char[] lettersArray6 = {'w', 'a', 't', 'e', 'r'};
    
    private String[] colorsArray1 = {"green", "green", "green", "green", "green"};
    private String[] colorsArray2 = {"green", "green", "gray", "yellow", "gray"};
    private String[] colorsArray3 = {"gray", "green", "green", "yellow", "gray"};
    private String[] colorsArray4 = {"green", "green", "gray", "gray", "gray"};
    private String[] colorsArray5 = {"gray", "gray", "gray", "gray", "gray"};
    private String[] colorsArray6 = {"gray", "green", "gray", "green", "green"};
    
    @Test
    public void testToStringFullBoard() {
        // Create Board with 5 letters per word and 6 max guesses
        Board gameBoard = new Board(5, 6);
        gameBoard.addGuess(lettersArray1, colorsArray1);
        gameBoard.addGuess(lettersArray2, colorsArray2);
        gameBoard.addGuess(lettersArray3, colorsArray3);
        gameBoard.addGuess(lettersArray4, colorsArray4);
        gameBoard.addGuess(lettersArray5, colorsArray5);
        gameBoard.addGuess(lettersArray6, colorsArray6);
        
        String testBoardString = gameBoard.toString();
        String expectedString = "hello\ngreen green green green green \n" +
                                "stamp\ngreen green gray yellow gray \n" +
                                "crane\ngray green green yellow gray \n" +
                                "mulch\ngreen green gray gray gray \n" +
                                "ninja\ngray gray gray gray gray \n" +
                                "water\ngray green gray green green \n";
        
        assertEquals(expectedString, gameBoard.toString(), "Full Board to String");
    }
}