import java.util.*;
import java.io.*;

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
    
    /** Index of wordleList array from which to pull the target word */
    private int index;
    
    /** Target word for user to guess */
    private String wordle;
    
    /** Stores all of the possible Wordles (target words to be guessed), read from a text file */
    private String[] wordleList;
    
    /** Stores all of the valid words to guess, read from a text file */
    private String[] guessList;
    
    /**
     * Reads the .txt files to create arrays of words
     * @param test Indicates if program is in test mode, -1 if the program is in test mode
     */
    public Wordle(int test) {
        this.test = test;
        String wordleFile;
        String guessFile = "words/GuessList.txt";
        
        if (test == -1) {
            wordleFile = "words/TestList.txt";
        }
        else {
            wordleFile = "words/WordleList.txt";
        }
        
        createWordleListArray(wordleFile);
        createGuessListArray(guessFile);
        
    }
    
    /**
     * Randomly assigns a target word for a Wordle game.
     * If game is in test mode, pull sequentially from the test words array.
     */
    public void newAnswer() {
        int oldAnswerIndex;
        int newAnswerIndex;
        String oldWordle = wordle;
        //If test mode is active, find the previous word in the test array 
        //and pull the next word in line
        if (test == -1 && wordleList.length == 10) {
            if (wordle == null) {
                wordle = wordleList[0];
            }
            else {
                for(int i = 0; i < wordleList.length; i++) {
                    if (wordleList[i].equals(oldWordle)) {
                        oldAnswerIndex = i;
                        wordle = wordleList[oldAnswerIndex + 1];
                    }
                }
            }
        }
        //If not in test mode, randomly pull from the array of guesses, assign new answer,
        //and replace with null value. If null value is pulled, pull the next word in line.
        else {
            Random r = new Random();
            newAnswerIndex = r.nextInt(wordleList.length);
            if (wordleList[newAnswerIndex] == null) {
                while (wordleList[newAnswerIndex] == null) {
                    newAnswerIndex++;
                }
            }
            wordle = wordleList[newAnswerIndex];
            wordleList[newAnswerIndex] = null;
        }
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
     * @param guess The input value to compare to the list of possible words
     * @return true if input is a word, false if it is not
     */
    public boolean isWord(String guess) {
        boolean isWord = false;
        for (int i = 0; i < guessList.length; i++) {
            if (guessList[i].equalsIgnoreCase(guess)) {
                isWord = true;
            }
        }
        return isWord;
    }
    
    /**
     * Determines if the input word is the target word
     * @param guess The input value to compare to the answer
     * @return true if input word is the target word, false otherwise
     */
    public boolean isAnswer(String guess) {
        if (guess.equalsIgnoreCase(wordle)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Initializes the wordleList array with Strings read in from either the
     * WordleList.txt file or the TestList.txt file if testing mode is on
     * Note: the words will be in all uppercase
     *
     * @param wordleFile the filename of the file containing the valid Wordles (target words)
     */
    public void createWordleListArray(String wordleFile) {
        Scanner input = null;
        try {
            input = new Scanner(new FileInputStream(wordleFile));
        } catch (FileNotFoundException e) {
            System.out.println("Unable to access Wordle file: " + wordleFile);
            System.exit(1);
        }
        // Iterate through the txt file once to obtain the number of words
        // Since each word is on its own line, use nextLine to get text
        int numWords = 0;
        while (input.hasNextLine()) {
            input.nextLine();
            numWords++;
        }
        input.close();
        
        // Reopen FileInputStream to actually read in the words this time
        wordleList = new String[numWords];
        try {
            input = new Scanner(new FileInputStream(wordleFile));
        } catch (FileNotFoundException e) {
            System.out.println("Unable to access Wordle file: " + wordleFile);
            System.exit(1);
        }
        
        for (int i = 0; i < numWords; i++) {
            wordleList[i] = input.nextLine().toUpperCase();
        }
        input.close();
    }
    
    /**
     * Initializes the guessList array with Strings read in from the GuessList.txt file
     * Note: the words will be in all uppercase
     *
     * @param guessFile the filename of the file containing the valid words that can be guessed
     */
    public void createGuessListArray(String guessFile) {
        Scanner input = null;
        try {
            input = new Scanner(new FileInputStream(guessFile));
        } catch (FileNotFoundException e) {
            System.out.println("Unable to access guess list file: " + guessFile);
            System.exit(1);
        }
        // Iterate through the txt file once to obtain the number of words
        // Since each word is on its own line, use nextLine to get text
        int numWords = 0;
        while (input.hasNextLine()) {
            input.nextLine();
            numWords++;   
        }
        input.close();
        
        // Reopen FileInputStream to actually read in the words this time
        guessList = new String[numWords];
        try {
            input = new Scanner(new FileInputStream(guessFile));
        } catch (FileNotFoundException e) {
            System.out.println("Unable to access guess list file: " + guessFile);
            System.exit(1);
        }
        
        for (int i = 0; i < numWords; i++) {
            guessList[i] = input.nextLine().toUpperCase();
        }
        input.close();
    }
 
    
}