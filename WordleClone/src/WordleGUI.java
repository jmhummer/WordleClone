package Backup;
// javac -d bin -cp bin src/WordleGUI.java
// java -cp bin WordleGUI

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.Random;
import java.util.Scanner;
import javax.swing.border.Border;

import Board;
import Player;
import WordleGame;

/**
 * Wordle Graphical User Interface
 * @author Joe Hummer
 * @author Nick Schauer
 * @author Nick Sanford
 * @author Ben Morris
 */
public class WordleGUI extends JFrame implements ActionListener 
{
    //private String message = ""; Don't need this to be a field. Made it a local variable.

    private WordleGame wg;
    private Player[] player;

    String message;

    /** x coordinate of upper lefthand corner of GUI */
    public static final int X = 50;

    /** y coordinate of upper lefthand corner of GUI */
    public static final int Y = 50;
    
    /** Font size of text */
    public static final int FONT_SIZE = 15;

    /** Width of text */
    public static final int TEXT_WIDTH = 10;

    public static final int BORDER_WIDTH = 2;

    public static final int NUM_OF_GUESSES = 6;
    
    public static final int NUM_OF_LETTERS = 5;

    private JLabel playerOneLabel, playerTwoLabel, roundLabel, messageLabel, letterLabel, blankLabel;
    private JPanel mainPanel, panelOne, panelTwo, individualGuessPanel, bottomPanel;
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
        
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 400;
		int height = 600;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLocation(X, Y);
        setTitle("Wordle Clone");

        player = new Player[2];
        player[0] = new Player();
        player[1] = new Player();
        //player = wg.getPlayer();        

        wg.newGame();
        player[1].addName("Julia");
        //System.out.println(wg.getName(1));

        this.message = "Press ENTER to continue.";
        updateGUI(player);
    }

    private void updateGUI(Player[] player) {
        
        Container c = getContentPane();  
        Player[] player2 = new Player[2];
        player2 = wg.getPlayer();
        String name = player[0].getName();
        //String name = wg.getName(0);
        //String name = "Ben";
        if(name == null)
            name = "Player 1";
        //int score = player[0].getScore();
        int score = -1;
        playerOneLabel = new JLabel(name + ": " + score + " points");
        playerOneLabel.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
        playerOneLabel.setHorizontalAlignment(JLabel.CENTER);
        playerOneLabel.setOpaque(true);
        playerOneLabel.setBackground(Color.darkGray);
        playerOneLabel.setForeground(Color.white);
        Border border = BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH);
        playerOneLabel.setBorder(border);

        name = player[1].getName();
        if(name == null)
            name = "Player 2";
        //score = player[1].getScore();
        playerTwoLabel = new JLabel(name + ": " + score + " points");
        playerTwoLabel.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
        playerTwoLabel.setHorizontalAlignment(JLabel.CENTER);
        playerTwoLabel.setOpaque(true);
        playerTwoLabel.setBackground(Color.darkGray);
        playerTwoLabel.setForeground(Color.white);
        border = BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH);
        playerTwoLabel.setBorder(border); 

        panelOne = new JPanel();
        panelOne.setLayout(new GridLayout(1, 2));
        panelOne.add(playerOneLabel);
        panelOne.add(playerTwoLabel);

        int roundNum = wg.getCurrentRound();
        String round = "round " + roundNum + " of 5";
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

        Board board = wg.getCurrentBoard(); //TODO: add this to WordleGame
        
        for(int guess = 0; guess < NUM_OF_GUESSES; guess++) {           
            
            individualGuessPanel = new JPanel();
            individualGuessPanel.setLayout(new GridLayout(1, 5));
            
            for(int letter = 0; letter < NUM_OF_LETTERS; letter++) {                
                
                char guessArray[] = board.getGuessLetters(guess);
                String guessLetter = "b" + guessArray[letter];
                //String guessLetter = "A";            
                letterLabel = new JLabel(guessLetter);
                letterLabel.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
                letterLabel.setHorizontalAlignment(JLabel.CENTER);
                letterLabel.setOpaque(true);
                
                String colorOfLetters[] = board.getGuessColors(guess);
                String color = colorOfLetters[letter];
                //Add a switch statement for the color if using 'color' doesn't work.
                letterLabel.setBackground(Color.BLACK);
                letterLabel.setForeground(Color.WHITE);
                border = BorderFactory.createLineBorder(Color.DARK_GRAY, BORDER_WIDTH);
                letterLabel.setBorder(border); 
                individualGuessPanel.add(letterLabel);
                individualGuessPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            }
            mainPanel.add(individualGuessPanel);
        }
        
        String labelText;
        if(wg.getCurrentRound() == 0)
            labelText = "Enter your name:  ";
        else
            labelText = "Enter your guess:  ";
        
        blankLabel = new JLabel(labelText);
        blankLabel.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
        blankLabel.setHorizontalAlignment(JLabel.RIGHT);
        blankLabel.setOpaque(true);
        blankLabel.setBackground(Color.darkGray);
        blankLabel.setForeground(Color.white);
        border = BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH);
        blankLabel.setBorder(border);

        guessTextField = new JTextField(5);
        if(wg.getCurrentRound() == 0)
            guessTextField.setText("Place Input Here");
        else
            guessTextField.setText("");
        
        guessTextField.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
        guessTextField.setHorizontalAlignment(JTextField.CENTER);
        guessTextField.setBackground(Color.WHITE);
        guessTextField.setForeground(Color.BLACK);
        guessTextField.setOpaque(true);
        border = BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH);
        guessTextField.setBorder(border);

        quitButton = new JButton("QUIT");
        quitButton.setOpaque(true);
        quitButton.setBackground(Color.darkGray);
        quitButton.setForeground(Color.white);
        border = BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH);
        quitButton.setBorder(border);

        enterButton = new JButton("ENTER");
        enterButton.setOpaque(true);
        enterButton.setBackground(Color.darkGray);
        enterButton.setForeground(Color.white);
        border = BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH);
        enterButton.setBorder(border);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 2));
        bottomPanel.add(blankLabel);
        bottomPanel.add(guessTextField);
        bottomPanel.add(quitButton);
        bottomPanel.add(enterButton);

        mainPanel.add(bottomPanel);
        
        c.add(mainPanel);
        setVisible(true);

        quitButton.addActionListener(this);
        enterButton.addActionListener(this);

        Random rnd = new Random();
        System.out.println(rnd.nextInt(10));
        System.out.println("here again");
    }

    
    /**
     * Executes action based on event
     * @param e event (button press, etc.)
     */
    public void actionPerformed(ActionEvent e) {
        String input = null;
        this.message = null;
        
        input = guessTextField.getText();
        this.message = messageLabel.getText();
        
        /*I think this logic should be in WordleGame.
        if(e.getSource() == btnEnter && !(input.equals("") || input.equals("[Enter Input Here]"))) {

            this.PlayerlblPlayer1.setText(Player[1].getName + ": " + Player[1].getScore); //TODO initialize name as "[Player i Name]" and score  as 0
            lblPlayer2.setText(Player[2].getName + ": " + Player[2].getScore);
            lblRound.setText("Round " + wg.getCurrentRound() + " of " & wg.ROUNDS);
            lblMessage.setText(message); // update message
        */
        
        if(e.getSource() == enterButton) {
            this.message = wg.next(input, message);
            
            Player[] player = new Player[2];
            player[0] = new Player();
            player[1] = new Player();
            //player = wg.getPlayer();
            player[0].addName("Tyler");
            player[0].getName();
            updateGUI(player);
        } else if (e.getSource() == quitButton) { // exit game
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
            new WordleGUI(0); //Wordle treats -1 as testing and everything else as normal. 
        } else {
            System.out.println("Usage: java -cp bin WordleGUI <testFlag>");
        }
    }
}
