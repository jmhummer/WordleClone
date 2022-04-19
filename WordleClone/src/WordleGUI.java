<<<<<<< HEAD
//TODO Resize: http://flyingjxswithjava.blogspot.com/2017/10/java-resize-font-size-automatically.html
// GridBagLayout 
 
import java.awt.*;
import javax.swing.*;
import java.awt.GridBagLayout;
import javax.swing.border.Border;

public class WordleGUI extends JFrame
{
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static int COLUMN_SCREEN_FRACTION = 6;
    final static int ROW_SCREEN_FRACTION = 22;
     
    public static void addComponentsToPane(Container pane) {
                			
        pane.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        if (shouldWeightX) {
            c.weightx = 0.5;
        }

        Border border = BorderFactory.createLineBorder(Color.black, 1);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int width = dimension.width;
		int height = dimension.height - 250;

        JLabel playerOne = new JLabel("Player 1: xxx pts", SwingConstants.CENTER); //TODO add Player 1 name and score
        playerOne.setOpaque(true);
        playerOne.setBackground(Color.GRAY);
        playerOne.setForeground(Color.white);
        playerOne.setBorder(border);
        playerOne.setPreferredSize(new Dimension (width / COLUMN_SCREEN_FRACTION, height / ROW_SCREEN_FRACTION));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 2;
        pane.add(playerOne, c);

        JLabel playerTwo = new JLabel("Player 2: xxx pts", SwingConstants.CENTER); //TODO add Player 2 name and score
        playerTwo.setOpaque(true);
        playerTwo.setBackground(Color.GRAY);
        playerTwo.setForeground(Color.white);
        playerTwo.setBorder(border);
        playerTwo.setPreferredSize(new Dimension (width / COLUMN_SCREEN_FRACTION, height / ROW_SCREEN_FRACTION));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0;
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 1;
        pane.add(playerTwo, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel roundNumber = new JLabel("Round y of 5", SwingConstants.CENTER); //TODO add round number
        roundNumber.setOpaque(true);
        roundNumber.setBackground(Color.GRAY);
        roundNumber.setForeground(Color.white);
        roundNumber.setBorder(border);
        roundNumber.setPreferredSize(new Dimension (width / COLUMN_SCREEN_FRACTION / 2, height / ROW_SCREEN_FRACTION));
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.0;
        c.weighty = 1;
        c.gridwidth = 6;
        c.gridheight = 1;
        pane.add(roundNumber, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel messageArea = new JLabel("Message Area", SwingConstants.CENTER); //TODO add message
        messageArea.setOpaque(true);
        messageArea.setBackground(Color.GRAY);
        messageArea.setForeground(Color.white);
        messageArea.setBorder(border);
        messageArea.setPreferredSize(new Dimension (width / COLUMN_SCREEN_FRACTION / 2, height / ROW_SCREEN_FRACTION));
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.0;
        c.weighty = 1;
        c.gridwidth = 6;
        c.gridheight = 1;
        pane.add(messageArea, c); 

        JLabel[] letter = new JLabel[6];
        char temp = 'A';
        border = BorderFactory.createLineBorder(Color.gray, 1);
        //Colors backgroundColor = "BLACK";
        for(int guess = 0; guess < 6; guess++)
            for(int i = 0; i < 6; i++)
            {
                //System.out.println(backgroundColor);

                c.fill = GridBagConstraints.HORIZONTAL;
                letter[i] = new JLabel(Character.toString(temp), SwingConstants.CENTER); //TODO add guessed word letters
                letter[i].setOpaque(true);
                letter[i].setBackground(Color.black); //TODO add letter backgrounds
                letter[i].setForeground(Color.white);
                letter[i].setBorder(border);
                letter[i].setPreferredSize(new Dimension (width / COLUMN_SCREEN_FRACTION / 6, height / ROW_SCREEN_FRACTION));
                c.gridx = i;
                c.gridy = 3 + guess * 2;
                c.weightx = 1;
                c.gridwidth = 1;
                c.gridheight = 2;
                pane.add(letter[i], c); 

                temp++;
                //backgroundColor++;
            }

        border = BorderFactory.createLineBorder(Color.black, 1);    
        
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel guess = new JLabel("GUESS", SwingConstants.CENTER); //TODO add text box for guess
        guess.setOpaque(true);
        guess.setBackground(Color.GRAY);
        guess.setForeground(Color.white);
        guess.setBorder(border);
        guess.setPreferredSize(new Dimension (width / COLUMN_SCREEN_FRACTION / 2, height / ROW_SCREEN_FRACTION));
        c.gridx = 0;
        c.gridy = 9 * 2;
        c.weightx = 0.0;
        c.weighty = 1;
        c.gridwidth = 3;
        c.gridheight = 1;
        pane.add(guess, c); 

        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel enter = new JLabel("ENTER", SwingConstants.CENTER); //TODO add button for enter
        enter.setOpaque(true);
        enter.setBackground(Color.GRAY);
        enter.setForeground(Color.white);
        enter.setBorder(border);
        enter.setPreferredSize(new Dimension (width / COLUMN_SCREEN_FRACTION / 2, height / ROW_SCREEN_FRACTION));
        c.gridx = 3;
        c.gridy = 9 * 2;
        c.weightx = 0.0;
        c.weighty = 1;
        c.gridwidth = 3;
        c.gridheight = 1;
        pane.add(enter, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel blank = new JLabel("BLANK", SwingConstants.CENTER); //TODO add something here
        blank.setOpaque(true);
        blank.setBackground(Color.GRAY);
        blank.setForeground(Color.white);
        blank.setBorder(border);
        blank.setPreferredSize(new Dimension (width / COLUMN_SCREEN_FRACTION / 2, height / ROW_SCREEN_FRACTION));
        c.gridx = 0;
        c.gridy = 10 * 2;
        c.weightx = 0.0;
        c.weighty = 1;
        c.gridwidth = 3;
        c.gridheight = 1;
        pane.add(blank, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel quit = new JLabel("QUIT", SwingConstants.CENTER); //TODO add button for quit
        quit.setOpaque(true);
        quit.setBackground(Color.GRAY);
        quit.setForeground(Color.white);
        quit.setBorder(border);
        quit.setPreferredSize(new Dimension (width / COLUMN_SCREEN_FRACTION / 2, height / ROW_SCREEN_FRACTION));
        c.gridx = 3;
        c.gridy = 10 * 2;
        c.weightx = 0.0;
        c.weighty = 1;
        c.gridwidth = 3;
        c.gridheight = 1;
        pane.add(quit, c); 
	}
 
    public static void main(String[] args) {
        JFrame frame = new JFrame("Wordle Clone");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
=======
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
>>>>>>> 1155e595566f0e7313aa10c3576a101c94d1b401
    }
}