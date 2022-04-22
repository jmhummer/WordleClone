
/**
 * Implements storage for the data needed to display the board/grid of guesses.
 * For each guess of the current round, it contains each letter and the color 
 * associated with each letter (orange for correct position, blue for incorrect position,
 *                              black for not in word)
 *
 * @author Nick Sanford
 * @author Nick Schauer
 * @author Joe Hummer
 * @author Ben Morris
 */
public class Board {
    
    /** The number of letters in the alphabet */
    public static final int NUM_LETTERS_IN_ALPHABET = 26;
    
    /** The ASCII value of 'A' */
    public static final int CAPITAL_A_ASCII_VALUE = 65;
    
    /** 2D array of letters guessed so far
     *  Each row represents a word, each column represents a letter within that word
     *  Note: the default initialization value of a char is '\\u0000'
     */
    private char[][] letters;
    
    /** 2D array of colors corresponding to letters guessed so far
     *  Each row represents a word, each column represents a color within that word
     *  Black incorrect, blue wrong pos but is in word, orange correct position
     *  Note: the String will have been initialized to the notInWordColor by the constructor
     */
    private String[][] colors;
    
    /** The number of guesses made so far
     *  Also, the next index to add a new word's letters and colors
     */
    private int currentGuesses;
    
    /** The color of a correctly placed letter: orange */
    private String correctPositionColor;
    
    /** The color of an incorrectly placed letter that exists in the word: blue */
    private String incorrectPositionColor;
    
    /** The color of a letter that is not in the word/default color: black */
    private String notInWordColor;
    
    /** The word that the user is trying to guess (the "wordle") in char array format */
    private char[] targetWordArray;
    
    /**
     * Constructor method for a Board object
     * @param wordLength the number of letters in the guessed words (typically 5)
     * @param numTotalGuesses the number of guesses allowed on each player's turn (typically 6)
     * @param targetWord the word that the user is trying to guess (the "wordle")
     * @throws IllegalArgumentException if the word length or number of guesses is < 1 or
     *                                  if the targetWord has a different length
     */
    public Board(int numTotalGuesses, int wordLength, String targetWord) {
        // Check for invalid arguments
        if (wordLength <= 0) {
            throw new IllegalArgumentException("Word length must be greater than 0");
        }
        if (numTotalGuesses <= 0) {
            throw new IllegalArgumentException("Number of guesses must be greater than 0");
        }
        if (targetWord.length() != wordLength) {
            throw new IllegalArgumentException("Target word must be the same as the word length");
        }
        
        // Initialize 2D arrays to represent the letters and colors of the guesses
        letters = new char[numTotalGuesses][wordLength];
        colors = new String[numTotalGuesses][wordLength];
        
        correctPositionColor = "orange";
        incorrectPositionColor = "blue";
        notInWordColor = "black";
        
        // Initialize each colors element to "black"
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < colors[i].length; j++) {
                colors[i][j] = notInWordColor;
            }
        }
        
        // Convert the target word into a char array for ease of comparison
        targetWordArray = new char[wordLength];
        for (int i = 0; i < wordLength; i++) {
            targetWordArray[i] = Character.toUpperCase(targetWord.charAt(i));
        }
        
        currentGuesses = 0;
    }
    
    /**
     * Adds an array of a word to the 2D letters array and
     * adds an array of colors to the 2D colors array.
     * Sets each letter's color to orange if it is in the correct position
     * Otherwise, sets each letter's color to blue if it is in the word and incorrect position
     * Otherwise, keeps the letter's color to the default initialization of black
     *
     * @param guessWord the word that was guessed by the user
     * @throws IllegalArgumentException if the parameter arrays are not the same length as the
     *                                  rows in the private field arrays or
     *                                  if there is no more room in the private field arrays
     */
    public void addGuess(String guessWord) {
        int lettersPerWord = letters[0].length;
        // Check to make sure that there won't be any array indexing issues
        if (guessWord.length() != lettersPerWord) {
            throw new IllegalArgumentException("There must be " + lettersPerWord + 
                                               " letters in the guessed word");
        }
        if (currentGuesses > letters.length) {
            throw new IllegalArgumentException("The maximum number of guesses has" +
                                               " already been made");
        }
        
        // Add each letter of the guessed word to the letters array
        for (int i = 0; i < lettersPerWord; i++) {
            letters[currentGuesses][i] = Character.toUpperCase(guessWord.charAt(i));
        }
        
        // Holds the number of occurances of each letter in the target word
        // Based on alphabetical indexing: index 0 is A's, index 25 is Z's
        // Decremented whenver the letter is encountered in the guessed word
        int[] numOfEachLetter = new int[NUM_LETTERS_IN_ALPHABET];
        for (int i = 0; i < lettersPerWord; i++) {
            numOfEachLetter[targetWordArray[i] - CAPITAL_A_ASCII_VALUE]++;
        }
        
        // Compare guessed word letters to target word letters
        for (int i = 0; i < lettersPerWord; i++) {
            // Check if the letters match
            if (letters[currentGuesses][i] == targetWordArray[i]) {
                colors[currentGuesses][i] = correctPositionColor;
                // Decrement the number of that letter remaining in the word
                numOfEachLetter[targetWordArray[i] - CAPITAL_A_ASCII_VALUE]--;
            }
        }
        
        // Check if the letter exists in the targetWord elsewhere
        // Check if the letter has already been set to "correct position" color, do not overwrite
        for (int i = 0; i < lettersPerWord; i++) {
            if ((numOfEachLetter[letters[currentGuesses][i] - CAPITAL_A_ASCII_VALUE] > 0) &&
                (colors[currentGuesses][i].equals(notInWordColor))) {
                colors[currentGuesses][i] = incorrectPositionColor;
                numOfEachLetter[letters[currentGuesses][i] - CAPITAL_A_ASCII_VALUE]--;
            }
        }
        
        // Increment currentGuesses to prepare for the next guess to be added
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