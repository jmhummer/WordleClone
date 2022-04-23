// javac -d bin -cp bin src/WordleGUI.java
// java -cp bin WordleGUI

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.Random;
import java.util.Scanner;
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
    //private String message = ""; Don't need this to be a field. Made it a local variable.

    private WordleGame wg;
    private Player[] player;
    private Board board;

    private String message;

    /** x coordinate of upper lefthand corner of GUI */
    public static final int X = 400;

    /** y coordinate of upper lefthand corner of GUI */
    public static final int Y = 50;

    /** Pixel width of original game window */
    public static final int WINDOW_WIDTH = 396;

    /** Pixel height of original game window */
    public static final int WINDOW_HEIGHT = 595;
    
    /** Font size of text */
    public static final int FONT_SIZE = 15;

    /** Font size for guess letters */
    public static final int GUESS_FONT_SIZE = 22;

    /** Width of text */
    public static final int TEXT_WIDTH = 10;

    public static final int BORDER_WIDTH = 2;

    public static final int NUM_OF_GUESSES = 6;
    
    public static final int NUM_OF_LETTERS = 5;

    public static final int NUM_OF_PLAYERS = 2;

    private JLabel playerLabel[], roundLabel, messageLabel, letterLabel[][], directionsLabel;
    private JPanel mainPanel, panelOne, panelTwo, guessPanel[], bottomPanel;
    private JTextField guessTextField;

    /** Buttons */
    private JButton enterButton, quitButton;

    /**
     * Creates instance of PokerGUI class
     * @param seed if -1, a random game is played, otherwise the same game is played, in that
     * the deck will be shuffled the same way, whenever the seed is the same.
     */
    public WordleGUI(int testFlag) {
        
        wg = new WordleGame(testFlag);
        board = wg.getBoard();

        letterLabel = new JLabel[NUM_OF_GUESSES][NUM_OF_LETTERS];
        playerLabel = new JLabel[NUM_OF_PLAYERS];
        guessPanel = new JPanel[NUM_OF_GUESSES];
        player = new Player[2];

        message = "Input Player 1's name and click ENTER.";
        player = new Player[NUM_OF_PLAYERS];
        playerLabel = new JLabel[NUM_OF_PLAYERS];
        for(int i = 0; i < player.length; i++) {
            player[i] = new Player();
        }
        playerLabel[0] = new JLabel();

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = WINDOW_WIDTH;
		int height = WINDOW_HEIGHT;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLocation(X, Y);
        setTitle("Wordle Clone");
        Container c = getContentPane(); 

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

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(9, 1));
        mainPanel.add(panelOne);
        mainPanel.add(panelTwo);

        Board board = wg.getBoard(); //TODO: add this to WordleGame
        
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

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 2));
        bottomPanel.add(directionsLabel);
        bottomPanel.add(guessTextField);
        bottomPanel.add(quitButton);
        bottomPanel.add(enterButton);

        mainPanel.add(bottomPanel);
        
        c.add(mainPanel);
        setVisible(true);

        guessTextField.addKeyListener(this);
        quitButton.addActionListener(this);
        enterButton.addActionListener(this);     

        this.message = "Press ENTER to continue.";     
    }

    private void updateGUI(Player[] player) {
        
    }

    public void updateGUI() {

        String input = null;
        this.message = null;
        
        input = guessTextField.getText();
        message = messageLabel.getText();
        
        message = wg.next(input, message);
        guessTextField.setText("");
        guessTextField.requestFocusInWindow();
        messageLabel.setText(message);
        
        int round = wg.getCurrentRound();

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
            new WordleGUI(0); //Wordle treats -1 as testing and everything else as normal. 
        } else {
            System.out.println("Usage: java -cp bin WordleGUI <testFlag>");
        }
    }
}

//TODO: make panel array
//assign letters to panel
//make getColorsArray
//figure out colors
//Figure out a way to just update the current guess
//combine player[i] initialization into a for loop