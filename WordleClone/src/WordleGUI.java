import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.border.Border;

/**
 * @author Ben Morris
 *
 */
public class WordleGUI extends JFrame// implements ActionListener 
{
    private String message = "";
    
    private Board thisBoard;

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

    /** Button for Add */
    private JButton enterButton, quitButton;

    /**
     * Creates instance of PokerGUI class
     * @param seed if -1, a random game is played, otherwise the same game is played, in that
     * the deck will be shuffled the same way, whenever the seed is the same.
     */
    public WordleGUI() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 400;
		int height = 600;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLocation(X, Y);
        setTitle("Wordle Clone");

        Player[] player = new Player[2];
        player[0] = new Player();
        player[1] = new Player();
                
        
        this.message = "Press Enter to Continue";
        updateGUI(player);
        Scanner scnr = new Scanner(System.in);       
        scnr.nextLine();
        player[0].addScore(1);
        player[1].addScore(3);
        updateGUI(player);
    }

    private void updateGUI(Player[] player) {
        Container c = getContentPane();

        String name = player[0].getName();
        int score = player[0].getScore();
        playerOneLabel = new JLabel(name + ": " + score + " points"); //+ pm.getPoints());
        playerOneLabel.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
        playerOneLabel.setHorizontalAlignment(JLabel.CENTER);
        playerOneLabel.setOpaque(true);
        playerOneLabel.setBackground(Color.darkGray);
        playerOneLabel.setForeground(Color.white);
        Border border = BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH);
        playerOneLabel.setBorder(border);

        name = player[1].getName();
        score = player[1].getScore();
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

        //declare WordleGame (to get rounds)
        String round = "round # of 5";
        roundLabel = new JLabel(round);
        roundLabel.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
        roundLabel.setHorizontalAlignment(JLabel.CENTER);
        roundLabel.setOpaque(true);
        roundLabel.setBackground(Color.darkGray);
        roundLabel.setForeground(Color.white);
        border = BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH);
        roundLabel.setBorder(border);

        //message is returned from .next()
        messageLabel = new JLabel(message);
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
        
        for(int guess = 0; guess < NUM_OF_GUESSES; guess++) {
            individualGuessPanel = new JPanel();
            individualGuessPanel.setLayout(new GridLayout(1, 5));
            for(int letter = 0; letter < NUM_OF_LETTERS; letter++) {
                String guessLetter = "A"; //TODO get guessLetter[guess][letter]            
                letterLabel = new JLabel(guessLetter);
                letterLabel.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
                letterLabel.setHorizontalAlignment(JLabel.CENTER);
                letterLabel.setOpaque(true);
                letterLabel.setBackground(Color.BLACK); //TODO get backGroundColor[guess][letter]
                letterLabel.setForeground(Color.WHITE);
                border = BorderFactory.createLineBorder(Color.DARK_GRAY, BORDER_WIDTH);
                letterLabel.setBorder(border); 
                individualGuessPanel.add(letterLabel);
                individualGuessPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            }
            mainPanel.add(individualGuessPanel);
        }
        
        blankLabel = new JLabel("Enter your guess:  ");
        blankLabel.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
        blankLabel.setHorizontalAlignment(JLabel.RIGHT);
        blankLabel.setOpaque(true);
        blankLabel.setBackground(Color.darkGray);
        blankLabel.setForeground(Color.white);
        border = BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH);
        blankLabel.setBorder(border);

        guessTextField = new JTextField(5);
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
    }


    /**
     * Starts up Wordle game
     * @param args args[0] optional argument used for testing
     */
    public static void main(String[] args) {

        try {
            new WordleGUI();
        } catch (NumberFormatException e) {
            System.out.println("Usage: java -cp bin VideoPokerGUI <seed>");
        }
        
    }
}