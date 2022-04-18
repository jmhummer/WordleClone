// TODO - double check what imports are needed
// javac -d bin -cp bin src/WordleGUI.java
// java -cp bin WordleGUI
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Wordle Graphical User Interface
 * @author Joe Hummer
 * @author Nick Schauer
 * @author Nick Sanford
 * @author Ben Morris
 */
public class WordleGUI extends JFrame implements ActionListener {
    
    // TODO public static final constants
    
    // TODO private fields
    
    /** Displays player 1's name and points */
    private JLabel lblPlayer1;
    
    /** Displays player 2's name and points */
    private JLabel lblPlayer2;
    
    /** Displays what round the game is on */
    private JLabel lblRound;
    
    /** Displays player message/instructions */
    private JLabel lblMessage;
    
    /** User input field */
    private JTextField txtInput;
    
    /** Enter button */
    private JButton btnEnter;
    
    /** Quit button */
    private JButton btnQuit;
    
    /** World Game instance */
    private WordleGame wg;
    
    /**
     * Creates instance of WordleGUI class
     * @param testFlag if -1, enables test mode for testing purposes.
     * Test mode loads a predetermined TestWords.txt file with a known
     * list of wordles for each player and each round.
     */    
    public WordleGUI(int testFlag) {
        wg = new WordleGame(testFlag);

        //TODO build GUI
        
        // Labels
        lblPlayer1 = new JLabel("[Player 1 Name]: 0");
        lblPlayer2 = new JLabel("[Player 2 Name]: 0");
        lblRound = new JLabel("Round 1 of " & wg.ROUNDS);
        lblMessage = new JLabel("Input Player 1's Name and Click Enter.");
        // Input
        txtInput = new JTextField("[Enter Input Here]", 10);
        // Buttons
        btnEnter = new JButton("Enter");
        btnQuit = new JButton("Quit");
    }

    /**
     * Executes action based on event
     * @param e event (button press, etc.)
     */
    public void actionPerformed(ActionEvent e) {
        String input = null;
        String message = null;
        
        input = txtInput.getText(); //.setText("")
        message = lblMessage.getText();
        
        if(e.getSource() == btnEnter && !(input.equals("") || input.equals("[Enter Input Here]"))) {
            message = wg.next(input, message);
            lblPlayer1.setText(Player[1].getName + ": " + Player[1].getScore); //TODO initialize name as "[Player i Name]" and score  as 0
            lblPlayer2.setText(Player[2].getName + ": " + Player[2].getScore);
            lblRound.setText("Round " + wg.getCurrentRound() + " of " & wg.ROUNDS);
            lblMessage.setText(message); // update message
            txtInput.setText(""); // clear input
            
            // TODO Update board (nested loop through NUMBER_OF_LETTERS and NUMBER_OF_GUESSES)
            // update to show each letter and color as needed
            
        } else if (e.getSource() == btnQuit) { // exit game
            System.exit(1);
        }
    }
    
    /**
     * Starts up Wordle game
     * @param args args[0] optional testFlag used for testing
     */
    public static void main(String[] args) {

        if (args.length == 1) {
            try {
                new WordleGUI(Integer.parseInt(args[0]));
            } catch (NumberFormatException e) {
                System.out.println("Usage: java -cp bin WordleGUI <testFlag>");
            }
        } else if (args.length == 0) {
            new WordleGUI(wg.RANDOM_GAME);
        } else {
            System.out.println("Usage: java -cp bin WordleGUI <testFlag>");
        }
    }
}