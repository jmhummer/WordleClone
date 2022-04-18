
/**
 * Implements storage for the data needed to display the board/grid of guesses.
 * For each guess of the current round, it contains each letter and the color 
 * associated with each letter (green for correct position, yellow for incorrect position,
 *                              white for not in word)
 *
 * @author Nick Sanford
 */
public class Board {
    
    /** 2D array of letters guessed so far
     *  Each row represents a word, each column represents a letter within that word
     */
    private char[][] letters;
    
    /** 2D array of colors corresponding to letters guessed so far
     *  Each row represents a word, each column represents a color within that word
     */
    private String[][] colors;
    
    /** The number of guesses made so far
     *  Also, the next index to add a new word's letters and colors
     */
    private int currentGuesses;
    
    /**
     * Constructor method for a Board object
     * @param wordLength the number of letters in the guessed words (typically 5)
     * @param numTotalGuesses the number of guesses allowed on each player's turn (typically 6)
     * @throws IllegalArgumentException if the word length or number of guesses is < 1
     */
    public Board(int wordLength, int numTotalGuesses) {
        // Check for invalid arguments
        if (wordLength <= 0) {
            throw new IllegalArgumentException("Word length must be greater than 0");
        }
        if (numTotalGuesses <= 0) {
            throw new IllegalArgumentException("Number of guesses must be greater than 0");
        }
        
        // Initialize 2D arrays to represent the letters and colors of the guesses
        letters = new char[numTotalGuesses][wordLength];
        colors = new String[numTotalGuesses][wordLength];
        
        currentGuesses = 0;
    }
    
    /**
     * Adds an array of a word to the 2D letters array and
     * adds an array of colors to the 2D colors array
     *
     * @param guessLetters the letters to be displayed the word
     * @param guessColors the colors to be displayed for each letter in the word
     * @throws IllegalArgumentException if the parameter arrays are not the same length as the
     *                                  rows in the private field arrays or
     *                                  if there is no more room in the private field arrays
     */
    public void addGuess(char[] guessLetters, String[] guessColors) {
        // Check to make sure that there won't be any array indexing issues
        if (guessLetters.length != letters[0].length || guessColors.length != colors[0].length) {
            throw new IllegalArgumentException("There must be " + letters[0].length + 
                                               " letters in the guessed word");
        }
        if (currentGuesses > letters.length) {
            throw new IllegalArgumentException("The maximum number of guesses has" +
                                               " already been made");
        }
        
        letters[currentGuesses] = guessLetters;
        colors[currentGuesses] = guessColors;
        currentGuesses++;
    }
    
    /**
     * Returns the letters of the word at the specified index
     *
     * @param index the index for which to return the letters
     * @return a char array of the letters in the word to be displayed
     */
    public char[] getGuessLetters(int index) {
        return letters[index];
    }
    
    /**
     * Returns the colors of the word at the specified index
     *
     * @param index the index for which to return the letters
     * @return a char array of the colors in the word to be displayed
     */
    public String[] getGuessColors(int index) {
        return colors[index];
    }
    
    /**
     * Returns a String representation of the Board object
     * For each guess stored in the Board, there are two lines
     * The first contains the letters of that guess and
     * the second contains the color associated with each letter
     *
     * @return a String representation of the Board object
     */
    public String toString() {
        String boardString = "";
        for (int i = 0; i < currentGuesses; i++) {
            for (int j = 0; j < letters[i].length; j++) {
                boardString += letters[i][j];
            }
            boardString += "\n";
            for (int k = 0; k < colors[i].length; k++) {
                boardString += colors[i][k] + " ";
            }
            boardString += "\n";
        }
        return boardString;
    }

}