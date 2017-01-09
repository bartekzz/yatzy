package Yatzy;

import java.util.Vector;
import javax.swing.JOptionPane;

/**
 * Class that handles creation and management of players
 *
 * @author max
 */
public class Player {

    Dice[] dices;
    Vector savedValuesDiceCast;
    Vector markedDices;
    int[] pokerHands;
    int[] savedPokerHands;
    int[] markedDicesArray;
    int currentRoll;
    int numberOnes;
    int numberTwos;
    int numberThrees;
    int numberFours;
    int numberFives;
    int numberSixes;
    int onePair;
    int twoPair;
    int threeOfAKind;
    int fourOfAKind;
    int smallStraight;
    int largeStraight;
    int fullHouse;
    int chance;
    int yatzy;
    boolean isAvailablePokerHand;
    boolean pokerHandsToBeMade;
    boolean madeHandsAvailableForSave;
    boolean playedTop;
    int numbersScore;
    int bonus;
    int numbersTotalScore;
    int pokerScore;
    int grandTotal;
    private String playerName;

    /**
     * Constructor for Player
     * Gives player variables used in score-keeping
     */
    public Player() {

        currentRoll = 0;
        playedTop = false;
        savedValuesDiceCast = new Vector();
        markedDices = new Vector();
        markedDicesArray = new int[5];
        pokerHands = new int[15];
        savedPokerHands = new int[15];

        for (int i = 0; i < savedPokerHands.length; i++) {
            savedPokerHands[i] = 100;
        }
    }

    /**
     * Method to return the name of the player
     *
     * @return The playername specified in setPlayerName()
     * @see #setPlayerName()
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * prompt input from player to set a name currently only does A-Z prints
     * error if input is out of range.
     */
    public void setPlayerName() {
        String message = "Enter playername";
        do {
            this.playerName = JOptionPane.showInputDialog(message);
            message = "<html><b style='color:red'>Enter playername:</b><br>"
                    + "Use a-z";
            if (playerName == null) {
                int confirmExit = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit?", null,
                        JOptionPane.YES_NO_OPTION);
                if (confirmExit == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                else {
                    this.playerName = "default";
                }
            }
        } while (playerName != null && !playerName.matches("[a-zA-Z]+"));
    }

    /**
     * Method for printing out a playerName
     *
     * @return A string with the player name.
     */
    public String toString() {
        return playerName;
    }

    /**
     * Method used to create dice to be used
     */
    void createDices() {
        // Create dices and add to array
        dices = new Dice[5];
        for (int i = 0; i < dices.length; i++) {
            dices[i] = new Dice();
            System.out.println(dices[i]);
        }
    }

    /**
     * Method used to throw dice
     */
    void castDices() {
        for (int i = 0; i < dices.length; i++) {
            if (dices[i].castValue != 0) {
                int diceValue = dices[i].throwDice();
                System.out.println(diceValue);
            }
        }
        System.out.println("----------------");
    }
}
