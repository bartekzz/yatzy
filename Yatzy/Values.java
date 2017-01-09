package Yatzy;

/**
 * Class to determine value of labels in scoreboard
 *
 * @author Anneli, Marcus
 */
public class Values {

    public static String tellItLikeItIs(int value) {
        switch (value) {
            case 0:
                return "Ones";
            case 1:
                return "Twos";
            case 2:
                return "Threes";
            case 3:
                return "Fours";
            case 4:
                return "Fives";
            case 5:
                return "Sixes";
            case 6:
                return "One Pair";
            case 7:
                return "Two Pairs";
            case 8:
                return "Three of a Kind";
            case 9:
                return "Four of a Kind";
            case 10:
                return "Small Straight";
            case 11:
                return "Large Straight";
            case 12:
                return "Full House";
            case 13:
                return "Chance";
            case 14:
                return "Yahtzee";
            default:
        }
        return "something went wrong";
    }
}
