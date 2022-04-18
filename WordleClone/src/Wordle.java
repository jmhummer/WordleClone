import java.util.*;
import java.io.*;
//TODO Remove this note: 
//To compile: javac -d bin -cp bin src/Wordle.java
//To run: java -cp bin Wordle

/**
 * Runs the main Wordle program, determines if a guess is the target word
 * @author Joe Hummer
 * @author Nick Schauer
 * @author Nick Sanford
 * @author Ben Morris
 */
public class Wordle {
    
    /** Indicates if program is in test mode or standard mode */
    private int test;
    
    /** Index of GuessList array from which to pull the target word */
    private int index;
    
    /** Target word for user to guess */
    private String wordle;
    
    
    /**
     * Reads the .txt files to create arrays of words
     * @param test Indicates if program is in test mode
     * @param numPlayers The current player number
     */
    public Wordle(int test, int numPlayers) {
        //TODO create file reader for GuessList.txt, WordlesList.txt, and TestList.txt
        //Note: Look at Github .txt files when making file scanner, they are separated by line, not comma
        this.test = test;
    }
    
    /**
     * Randomly assigns a target word for a Wordle game
     */
    public void newAnswer() {
        //TODO randomly pull from GuessList or NOT randomly pull from TestList and assign as wordle
    }
    
    /**
     * Returns target word
     * @return wordle Target word for user to guess
     */
    public String getAnswer() {
        return wordle;
    }
    
    /**
     * Searches array of possible words and indicates if a guess is a word
     * @return true if input is a word, false if it is not
     */
    public boolean isWord() {
        //TODO determine if input is a word by searching GuessList
        return true;
    }
    
    /**
     * Determines if the input word is the target word
     * @return true if input word is the target word, false otherwise
     */
    public boolean isAnswer() {
        //TODO determine if input is the target word
        return true;
    }
}