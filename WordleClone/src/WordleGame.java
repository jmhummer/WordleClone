// TODO - double check what imports are needed
// javac -d bin -cp bin src/WordleGame.java
// java -cp bin WordleGame
import java.util.*;

/**
 * This program handles the logic behind a game of Wordle
 * @author Joe Hummer
 * @author Nick Schauer
 * @author Nick Sanford
 * @author Ben Morris
 */
public class WordleGame {
    
    /** Indicates that a random game should be played */
    public static final int RANDOM_GAME = 0;
    
    /** Number of players in game */
    public static final int NUMBER_OF_PLAYERS = 2;
    
    /** Number of rounds in game */
    public static final int NUMBER_OF_ROUNDS = 5;
    
    /** Number of guesses per player in a round */
    public static final int NUMBER_OF_GUESSES = 6;
    
    /** Number of letters in a wordle */
    public static final int NUMBER_OF_LETTERS = 5;
    
    /** Number of points for correct guess on 1st attempt */
    public static final int POINTS_1 = 50;
    
    /** Number of points for correct guess on 2nd attempt */
    public static final int POINTS_2 = 40;
    
    /** Number of points for correct guess on 3rd attempt */
    public static final int POINTS_3 = 30;
    
    /** Number of points for correct guess on 4th attempt */
    public static final int POINTS_4 = 20;
    
    /** Number of points for correct guess on 5th attempt */
    public static final int POINTS_5 = 10;
    
    /** Current player */
    private int currentPlayer;
    
    /** Current round */
    private int currentRound;
    
    /** Current guess */
    private int currentGuess;
    
    /** Array of Players object, each element is a player */
    private Player[] player;
    
    /** Wordle object */
    private Wordle wordle;
    
    /** Board object */
    private Board board;
    
    /**
     * Constructor of the WordleGame class.
     * @param testFlag int random seed for testing
     */
    public WordleGame(int testFlag) {
        wordle = new Wordle(testFlag);
    }
    
    /**
     * Start new Wordle game
     */
    public void newGame() {
        player = new Player(NUMBER_OF_PLAYERS);
        board = new Board(NUMBER_OF_GUESSES);
        currentPlayer = 1;
        currentRound = 1;
        currentGuess = 1;
    }
    
    /**
     * logic to run Wordle gameplay
     * @param input String user input
     * @param oldMessage String previous message/instruction from GUI
     * @return new message/instruction for GUI
     */
    public void next(String input, String oldMessage) {
        String newMessage = null;
        input = input.toLowerCase();
        oldMessage = oldMessage.toLowerCase();
        
        // Input Player Names
        if (oldMessage.indexOf("name") > 0) {
            if (currentPlayer < NUMBER_OF_PLAYERS) {
                Player[currentPlayer].setName(input);
                currentPlayer++;
                newMessage = "Input Player " + currentPlayer + "'s Name and Click Enter.";
            } else { // last player name
                Player[currentPlayer].setName(input);
                currentPlayer = 1; // start game with player 1
                // no score or round update
                newMessage = player[currentPlayer].getName + ": Guess the Wordle.";
            }
        // Enter Guess
        } else if (oldMessage.indexOf("word") > 0) {
                // Correct Guess
                if (wordle.isAnswer(input)) {
                    //TODO updateboard() with guess
                    newMessage = player[currentPlayer].getName + ": Correct! Click Enter to Continue.";
                    int score = scoreGuess(currentGuess);
                    Player[currentPlayer].addScore(score);
                // Taken Guess
                } else if (wordle.isWord(input)) {
                    //TODO updateboard() with guess
                    if (currentGuess == NUMBER_OF_GUESSES) { // out of guess
                        newMessage = player[currentPlayer].getName + ": Out of guesses. Click Enter to Continue.";
                    } else { // next guess
                        newMessage = player[currentPlayer].getName + ": Guess the Wordle.";
                        currentGuess++;
                    }
                // Not 5 Letter Word
                } else if (input.length() != NUMBER_OF_LETTERS) {
                    newMessage = player[currentPlayer].getName + ": Must be a 5 letter word, try again.";
                // Not a Word
                } else {
                    newMessage = player[currentPlayer].getName + ": Not a word, try again.";
                }  
        // Continue Game
        } else if (oldMessage.indexOf("continue") > 0) {
            // Game Over
            if(currentGuess == NUMBER_OF_GUESSES && currentPlayer == NUMBER_OF_PLAYERS
                            && currentRound == NUMBER_OF_ROUNDS) {
                if (player[1].getScore() == player[2].getScore()) {
                    newMessage = "Game Over. It is a TIE!";
                } else if (player[1].getScore() > player[2].getScore()) {
                    newMessage = "Game Over. " + player[1].getName() + " WINS!";
                } else {
                    newMessage = "Game Over. " + player[2].getName() + " WINS!";
                }
            // Next Round
            } else if (currentGuess == NUMBER_OF_GUESSES && currentPlayer == NUMBER_OF_PLAYERS) {
                currentGuess = 1;
                currentPlayer = 1;
                currentRound++;
                wordle.newAnswer();
                newMessage = player[currentPlayer].getName + "Guess the Wordle.";
                //TODO updateboard() to blank or new board
            // Next Player
            } else if (currentGuess == NUMBER_OF_GUESSES) {
                currentGuess = 1;
                currentPlayer++;
                wordle.newAnswer();
                newMessage = player[currentPlayer].getName + "Guess the Wordle.";
                //TODO updateboard() to blank or new board
            }
        }
        return newMessage;
    }

    /**
     * Returns the score based on currentGuess attempt
     * @param currentGuess int of current guess attempt
     * @return score amount for current guess attempt
     */
    public int scoreGuess(int currentGuess) {
        int points = 0;
        switch (currentGuess) {
            case 1:
                points = POINTS_1;
                break;
            case 2:
                points = POINTS_2;
                break;
            case 3:
                points = POINTS_3;
                break;
            case 4:
                points = POINTS_4;
                break;
            case 5:
                points = POINTS_5;
                break;
            default: 
                // nothing
        }
        return points;
    }
    
    /**
     * Return current round
     * @return current round
     */
    public int getCurrentRound() {
        return currentRound;
    }
}