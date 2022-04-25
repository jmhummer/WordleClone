// javac -d bin -cp bin src/WordleGUI.java
// java -cp bin WordleGUI

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.Random;
import java.util.Scanner;
import java.util.jar.JarEntry;

import javax.swing.border.Border;

/**
 * Wordle Graphical User Interface
 * @author Joe Hummer
 * @author Nick Schauer
 * @author Nick Sanford
 * @author Ben Morris
 */
public class WordleGUI extends JFrame implements ActionListener, KeyListener 
{
    /** WordleGame object */
    private WordleGame wg;

    /** Array of Player objects */
    private Player[] player;

    /** Board object */
    private Board board;

    /** String to update message for the Board */
    private String message;

    /** x coordinate of upper lefthand corner of GUI */
    public static final int X = 400;

    /** y coordinate of upper lefthand corner of GUI */
    public static final int Y = 25;

    /** Pixel width of original game window */
    public static final int WINDOW_WIDTH = 396;

    /** Pixel height of original game window */
    public static final int WINDOW_HEIGHT = 650;
    
    /** Font size of text */
    public static final int FONT_SIZE = 15;

    /** Font size for guess letters */
    public static final int GUESS_FONT_SIZE = 22;

    /** Font size for alphabet */
    public static final int ALPHABET_FONT_SIZE = 15;

    /** Width of text */
    public static final int TEXT_WIDTH = 10;

    /** Alphabet letters per row */
    public static final int ALPHABET_LETTERS_PER_ROW = 9;

    /** Rows of alphabet letters */
    public static final int ROWS_OF_ALPHABET_LETTERS = 3;

    /** Border width for panels */
    public static final int BORDER_WIDTH = 2;

    /** Border width specifically for the alphabet panel */
    public static final int ALPHABET_BORDER_WIDTH = 1;

    /** Number of guesses for each player */
    public static final int NUM_OF_GUESSES = 6;
    
    /** Number of letters in each word */
    public static final int NUM_OF_LETTERS = 5;

    /** Number of players to play */
    public static final int NUM_OF_PLAYERS = 2;

    /** Number of letters in the alphabet */
    public static final int NUM_LETTERS_IN_ALPHABET = 26;

    /** Label for the player and score panel */
    private JLabel playerLabel[];
    
    /** Label for listing the current round */
    private JLabel roundLabel;
    
    /** Label for the message panel */
    private JLabel messageLabel;
    
    /** Displays the letters of the guesses */
    private JLabel letterLabel[][];
    
    /** Directions for the next input */
    private JLabel directionsLabel;
    
    /** Labels for the alphabet panel */
    private JLabel alphabetLabel[][];

    /** Panel within which to combine all other panels */
    private JPanel mainPanel;
    
    /** Panel that includes the two Player labels */
    private JPanel panelOne;

    /** Panel that includes the round and message labels */
    private JPanel panelTwo;
    
    /** Panel that shows all of the guesses */
    private JPanel guessPanel[];
    
    /** Panel that shows directions, blank text box, quit button, and enter button */
    private JPanel bottomPanel;
    
    /** Panel containing one row of the alphabet section */
    private JPanel alphabetRowPanel;
    
    /** Panel combining the rows of the alphabet section */
    private JPanel combinedAlphabetPanel;

    /** Text field for entering player names and guesses */
    private JTextField guessTextField;

    /** Enter button */
    private JButton enterButton;
    
    /** Quit button */
    private JButton quitButton;

    /**
     * Creates instance of WordleGUI class. This class creates and initializes the fields of the GUI.
     * 
     * @param testFlag if -1, a game using the test words is played, otherwise the a game with random
     * words from the main file will be played
     */
    public WordleGUI(int testFlag) {
        
        //Create a new WordleGame
        wg = new WordleGame(testFlag);
        //Creat a Board
        board = wg.getBoard();

        //Initialize 2d Label array storing the guesses by letter
        letterLabel = new JLabel[NUM_OF_GUESSES][NUM_OF_LETTERS];

        //Initialize the label array of player names
        playerLabel = new JLabel[NUM_OF_PLAYERS];

        //Initialize the panel array for the guesses
        guessPanel = new JPanel[NUM_OF_GUESSES];

        //Initialize the array to store player names
        player = new Player[NUM_OF_PLAYERS];

        //Initialize message create Player array
        message = "Input Player 1's name and click ENTER.";
        for(int i = 0; i < player.length; i++) {
            player[i] = new Player();
        }

        // Get values to initialize the window
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        
        // Set window dimensions and other details
        int width = WINDOW_WIDTH;
		int height = WINDOW_HEIGHT;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLocation(X, Y);
        setTitle("Wordle Clone");
        // Create container
        Container c = getContentPane(); 

        // Create Player 1 and initialize name to "Player 1"
        String name = player[0].getName();

        if(name == null)
            name = "Player 1";
        int score = player[0].getScore();
        playerLabel[0] = new JLabel(name + ": " + score + " points");
        playerLabel[0].setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
        playerLabel[0].setHorizontalAlignment(JLabel.CENTER);
        playerLabel[0].setOpaque(true);
        playerLabel[0].setBackground(Color.darkGray);
        playerLabel[0].setForeground(Color.white);
        Border border = BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH);
        playerLabel[0].setBorder(border);

        // Create player Labels and Panel
        name = player[1].getName();
        if(name == null)
            name = "Player 2";
        score = player[1].getScore();
        playerLabel[1] = new JLabel(name + ": " + score + " points");
        playerLabel[1].setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
        playerLabel[1].setHorizontalAlignment(JLabel.CENTER);
        playerLabel[1].setOpaque(true);
        playerLabel[1].setBackground(Color.darkGray);
        playerLabel[1].setForeground(Color.white);
        border = BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH);
        playerLabel[1].setBorder(border); 

        panelOne = new JPanel();
        panelOne.setLayout(new GridLayout(1, 2));
        panelOne.add(playerLabel[0]);
        panelOne.add(playerLabel[1]);

        // Create round and message labels and panels
        int roundNum = wg.getCurrentRound();
        String round = "Round " + roundNum + " of 5";
        roundLabel = new JLabel(round);
        roundLabel.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
        roundLabel.setHorizontalAlignment(JLabel.CENTER);
        roundLabel.setOpaque(true);
        roundLabel.setBackground(Color.darkGray);
        roundLabel.setForeground(Color.white);
        border = BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH);
        roundLabel.setBorder(border);

        messageLabel = new JLabel(this.message);
        messageLabel.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setOpaque(true);
        messageLabel.setBackground(Color.darkGray);
        messageLabel.setForeground(Color.white);
        border = BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH);
        messageLabel.setBorder(border);

        panelTwo = new JPanel();
        panelTwo.setLayout(new GridLayout(2, 1));
        panelTwo.add(roundLabel);
        panelTwo.add(messageLabel);
        panelTwo.setBorder(BorderFactory.createLineBorder(Color.black));

        // Add subpanels to mainPanel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(10, 1));
        mainPanel.add(panelOne);
        mainPanel.add(panelTwo);

        // Get board initialization
        Board board = wg.getBoard();
        
        // Create guess labels and panel
        for(int guess = 0; guess < NUM_OF_GUESSES; guess++) {           
            
            guessPanel[guess] = new JPanel();
            guessPanel[guess].setLayout(new GridLayout(1, 5));
            
            for(int letter = 0; letter < NUM_OF_LETTERS; letter++) {                         
                letterLabel[guess][letter] = new JLabel();
                letterLabel[guess][letter].setFont(new Font("SansSerif",Font.BOLD,GUESS_FONT_SIZE));
                letterLabel[guess][letter].setHorizontalAlignment(JLabel.CENTER);
                letterLabel[guess][letter].setOpaque(true);
                letterLabel[guess][letter].setText("");
                
                String colorOfLetters[] = board.getGuessColors(guess);
                String color = colorOfLetters[letter];
                //Add a switch statement for the color if using 'color' doesn't work.
                letterLabel[guess][letter].setBackground(Color.BLACK);
                letterLabel[guess][letter].setForeground(Color.WHITE);
                border = BorderFactory.createLineBorder(Color.DARK_GRAY, BORDER_WIDTH);
                letterLabel[guess][letter].setBorder(border); 
                guessPanel[guess].add(letterLabel[guess][letter]);
                guessPanel[guess].setBorder(BorderFactory.createLineBorder(Color.black));
            }
            mainPanel.add(guessPanel[guess]);
        }
        
        // Create directions label, guessTextField, QUIT button, ENTER button and their panel
        directionsLabel = new JLabel("Enter your name:");
        directionsLabel.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
        directionsLabel.setHorizontalAlignment(JLabel.CENTER);
        directionsLabel.setOpaque(true);
        directionsLabel.setBackground(Color.darkGray);
        directionsLabel.setForeground(Color.white);
        border = BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH);
        directionsLabel.setBorder(border);

        guessTextField = new JTextField(5);
        guessTextField.setText("");
        guessTextField.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
        guessTextField.setHorizontalAlignment(JTextField.CENTER);
        guessTextField.setBackground(Color.WHITE);
        guessTextField.setForeground(Color.BLACK);
        guessTextField.setOpaque(true);
        border = BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH);
        guessTextField.setBorder(border);

        quitButton = new JButton("QUIT");
        quitButton.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
        quitButton.setOpaque(true);
        quitButton.setBackground(Color.darkGray);
        quitButton.setForeground(Color.white);
        border = BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH);
        quitButton.setBorder(border);

        enterButton = new JButton("ENTER");
        enterButton.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
        enterButton.setOpaque(true);
        enterButton.setBackground(Color.darkGray);
        enterButton.setForeground(Color.white);
        border = BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH);
        enterButton.setBorder(border);

        // Add above to panel bottomPanel
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 2));
        bottomPanel.add(directionsLabel);
        bottomPanel.add(guessTextField);
        bottomPanel.add(quitButton);
        bottomPanel.add(enterButton);

        // Add bottomPanel to mainPanel
        mainPanel.add(bottomPanel);

        // Create the alphabet panel
        combinedAlphabetPanel = new JPanel();
        combinedAlphabetPanel.setLayout(new GridLayout(3,1));
        alphabetLabel = new JLabel[ROWS_OF_ALPHABET_LETTERS][ALPHABET_LETTERS_PER_ROW];
    
        // Initialize the alphabet panel
        char alphabetLetter = 'A';
        for(int row = 0; row < ROWS_OF_ALPHABET_LETTERS; row++) {
            
            alphabetRowPanel = new JPanel();
            alphabetRowPanel.setLayout(new GridLayout(1, ALPHABET_LETTERS_PER_ROW));

            for(int column = 0; column < ALPHABET_LETTERS_PER_ROW; column++) {
                alphabetLabel[row][column] = new JLabel();
                alphabetLabel[row][column].setFont(new Font("SansSerif",Font.BOLD,ALPHABET_FONT_SIZE));
                alphabetLabel[row][column].setHorizontalAlignment(JLabel.CENTER);
                alphabetLabel[row][column].setOpaque(true);
                if(row == 0 && column == 0) {
                    alphabetLabel[row][column].setText("");
                    alphabetLabel[row][column].setBackground(Color.BLACK);
                }
                else {
                    alphabetLabel[row][column].setText("" + alphabetLetter);
                    alphabetLabel[row][column].setBackground(Color.DARK_GRAY);
                    alphabetLetter++;
                }
                alphabetLabel[row][column].setForeground(Color.WHITE);
                border = BorderFactory.createLineBorder(Color.BLACK, ALPHABET_BORDER_WIDTH);
                alphabetLabel[row][column].setBorder(border); 
                alphabetRowPanel.add(alphabetLabel[row][column]);
                alphabetRowPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            }
            combinedAlphabetPanel.add(alphabetRowPanel);
        }

        // Add the alphabet panel to the main panel
        mainPanel.add(combinedAlphabetPanel);
        
        // Add the main panel to the container and make it visible
        c.add(mainPanel);
        setVisible(true);

        // Create listeners for the text field and two buttons
        guessTextField.addKeyListener(this);
        quitButton.addActionListener(this);
        enterButton.addActionListener(this);     

        // Beginning message
        this.message = "Press ENTER to continue.";     
    }

    /** Updates the fields of the GUI */
    public void updateGUI() {

        String input = null;
        this.message = null;
        
        input = guessTextField.getText();
        message = messageLabel.getText();
        
        // Update message
        message = wg.next(input, message);
        guessTextField.setText("");
        guessTextField.requestFocusInWindow();
        messageLabel.setText(message);
        
        // Get the current round #
        int round = wg.getCurrentRound();

        // Create arrays for player names and scores
        String[] name = new String[NUM_OF_PLAYERS];
        int[] score = new int[NUM_OF_PLAYERS];

        for(int i = 0; i < NUM_OF_PLAYERS; i++) {
            player = wg.getPlayer();
            name[i] = player[i].getName();
            if(name[i] == null)
                name[i] = "Player " + (i + 1);
            
            score[i] = player[i].getScore();
            playerLabel[i].setText(name[i] + ": " + score[i] + " points");
        }

        int guessNumber = wg.getCurrentGuess();
        int guessIndex = -1;
        
        //adjusting the guessIndex versus the current wg.currentGuess
        if(guessNumber == 1 && round == 1)
            directionsLabel.setText("Enter your guess:");

        if(guessNumber > 1)
            guessIndex = guessNumber - 2;

        roundLabel.setText("Round " + round + " of 5");                

        board = wg.getBoard();
        char[][] guessLettersArray = board.getGuessLettersArray();
        String[][] guessColorsArray = board.getGuessColorsArray();
        for(int guess = 0; guess < NUM_OF_GUESSES; guess++) {
            for(int letter = 0; letter < NUM_OF_LETTERS; letter++) {
                letterLabel[guess][letter].setText("" + guessLettersArray[guess][letter]);
                switch (guessColorsArray[guess][letter]) {
                    case "black":
                        letterLabel[guess][letter].setBackground(Color.BLACK);
                        break;
                
                    case "blue":
                        letterLabel[guess][letter].setBackground(Color.BLUE);
                        break;

                    case "orange":
                        letterLabel[guess][letter].setBackground(Color.ORANGE);
                        break;

                    default:
                        break;
                }
            }
        }

        String alphabetColors[] = new String[NUM_LETTERS_IN_ALPHABET];
        alphabetColors = board.getAlphabetColorsArray();
        int colorIndex = 0;
        for(int row = 0; row < ROWS_OF_ALPHABET_LETTERS; row++) {
            for(int column = 0; column < ALPHABET_LETTERS_PER_ROW; column++) {
                if(!((row == 0) && (column == 0))) {
                    switch (alphabetColors[colorIndex]) {
                        case "black":
                            alphabetLabel[row][column].setBackground(Color.BLACK);
                            break;
                    
                        case "blue":
                            alphabetLabel[row][column].setBackground(Color.BLUE);
                            break;

                        case "orange":
                            alphabetLabel[row][column].setBackground(Color.ORANGE);
                            break;

                        default:
                            alphabetLabel[row][column].setBackground(Color.DARK_GRAY);
                            break;
                    }
                    colorIndex++;
                }
            }
        }
    }
    
    /**
     * Executes action based on event
     * @param e event (button press, etc.)
     */
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == enterButton) {
            updateGUI();     
        } else if (e.getSource() == quitButton) { // exit game
            System.exit(1);
        }
    }
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
          updateGUI();
        }
    }

    @Override
    public void keyReleased(KeyEvent arg) {}
    @Override
    public void keyTyped(KeyEvent arg) {}

    /**
     * Starts up Wordle game
     * @param args args[0] -1 optional testFlag used for testing
     */
    public static void main(String[] args) {

        if (args.length == 1) {
            try {
                new WordleGUI(Integer.parseInt(args[0]));
            } catch (NumberFormatException e) {
                System.out.println("Usage: java -cp bin WordleGUI <testFlag>");
            }
        } else if (args.length == 0) {
            new WordleGUI(0); //Wordle treats -1 as testing and everything else as normal. 
        } else {
            System.out.println("Usage: java -cp bin WordleGUI <testFlag>");
        }
    }
}