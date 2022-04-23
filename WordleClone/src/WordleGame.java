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
        wordle.newAnswer();
        player = new Player[NUMBER_OF_PLAYERS];
        for(int i = 0; i < player.length; i++) {
            player[i] = new Player();
        }
        
        board = new Board(NUMBER_OF_GUESSES, NUMBER_OF_LETTERS, wordle.getAnswer());
        currentPlayer = 1;
        currentRound = 0;
        currentGuess = 1;
    }
    
    /**
     * logic to run Wordle gameplay
     * @param input String user input
     * @param oldMessage String previous message/instruction from GUI
     * @return new message/instruction for GUI
     */
    public String next(String input, String oldMessage) {
        String newMessage = null;
        String name = input;
        input = input.toLowerCase();
        oldMessage = oldMessage.toLowerCase();
        
        // Input Player names
        if (oldMessage.indexOf("name") > 0) {
            if (currentPlayer < NUMBER_OF_PLAYERS) {
                player[currentPlayer - 1].addName(name);
                currentPlayer++;
                newMessage = "Input Player " + currentPlayer + "'s name and click ENTER.";
            } else { // last player name
                player[currentPlayer - 1].addName(name);
                currentPlayer = 0; // start game with player 1
                // no score or round update
                newMessage = player[currentPlayer].getName() + ": Guess the Wordle.";
                currentRound++;
                System.out.println("Current Round: " + currentRound);
            }
        // Enter Guess
        } else if (oldMessage.indexOf("word") > 0) {
                // Correct Guess
                if (wordle.isAnswer(input)) {
                    board.addGuess(input);
                    newMessage = player[currentPlayer].getName() + ": Correct! Click Enter to Continue.";
                    player[currentPlayer].addScore(currentGuess);

                // Taken Guess
                } else if (wordle.isWord(input)) {
                    board.addGuess(input);
                    if (currentGuess == NUMBER_OF_GUESSES) { // out of guess
                        newMessage = player[currentPlayer].getName() + ": Out of guesses. Click Enter to Continue.";
                    } else { // next guess
                        newMessage = player[currentPlayer].getName() + ": Guess the Wordle.";
                        currentGuess++;
                    }
                // Not 5 Letter Word
                } else if (input.length() != NUMBER_OF_LETTERS) {
                    newMessage = player[currentPlayer].getName() + ": Must be a 5 letter word, try again.";
                // Not a Word
                } else {
                    newMessage = player[currentPlayer].getName() + ": Not a word, try again.";
                }  
        // Continue Game
        } else if (oldMessage.indexOf("continue") > 0) {
            // Game Over
            if(currentGuess == NUMBER_OF_GUESSES && currentPlayer == (NUMBER_OF_PLAYERS - 1)
                            && currentRound == NUMBER_OF_ROUNDS) {
                if (player[0].getScore() == player[1].getScore()) {
                    newMessage = "Game Over. It is a TIE!";
                } else if (player[0].getScore() > player[1].getScore()) {
                    newMessage = "Game Over. " + player[0].getName() + " WINS!";
                } else {
                    newMessage = "Game Over. " + player[1].getName() + " WINS!";
                }
            // Next Round
            } else if (currentGuess == NUMBER_OF_GUESSES && currentPlayer == (NUMBER_OF_PLAYERS - 1)) {
                currentGuess = 1;
                currentPlayer = 0;
                currentRound++;
                wordle.newAnswer();
                newMessage = player[currentPlayer].getName() + "Guess the Wordle.";
                board = new Board(NUMBER_OF_GUESSES, NUMBER_OF_LETTERS, wordle.getAnswer());
            // Next Player
            } else if (currentGuess == NUMBER_OF_GUESSES) {
                currentGuess = 1;
                currentPlayer++;
                wordle.newAnswer();
                newMessage = player[currentPlayer].getName() + "Guess the Wordle.";
                board = new Board(NUMBER_OF_GUESSES, NUMBER_OF_LETTERS, wordle.getAnswer());
            }
        }
        return newMessage;
    }
   
    /**
     * Return current round
     * @return current round
     */
    public int getCurrentRound() {
        return currentRound;
    }
    
    /**
     * Return player object
     * @return player object
     */
    public Player[] getPlayer() {
        return player;
    }
    
    /**
     * Return board object
     * @return board object
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Return the current guess number
     * @return currentGuess
     */
    public int getCurrentGuess() {
        return currentGuess;
    }
}