package Yatzy;

import java.util.Collections;
import java.util.Vector;

/**
 * Class handling score-keeping
 *
 * @author Bartek
 */
public class Score {

    Player currentPlayer;

    private boolean isOne;
    private boolean isTwo;
    private boolean isThree;
    private boolean isFour;
    private boolean isFive;
    private boolean isSix;
    private boolean isOnePair;
    private boolean isTwoPair;
    private boolean isThreeOfAKind;
    private boolean isFourOfAKind;
    private boolean isSmallStraight;
    private boolean isLargeStraight;
    private boolean isFullHouse;
    private boolean isChance;
    private boolean isYatzy;

    /**
     * Constructor for playerScore
     *
     * @param currentPlayer Assigns a scoreTable to a player
     */
    public Score(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Checks if dice can be set to Ones
     *
     * @return true if Ones is available
     */
    boolean isOne() {
        isOne = false;
        Collections.sort(currentPlayer.savedValuesDiceCast);
        if (currentPlayer.savedValuesDiceCast.size() == 5) {
            for (int i = 0; i < currentPlayer.savedValuesDiceCast.size(); i++) {
                if ((Integer) currentPlayer.savedValuesDiceCast.get(i) == 1) {
                    currentPlayer.numberOnes += ((Integer) currentPlayer.savedValuesDiceCast.get(i));
                }
            }
            if (currentPlayer.numberOnes != 0) {
                currentPlayer.pokerHands[0] = currentPlayer.numberOnes;
                isOne = true;
            }
        }
        return isOne;
    }

    /**
     * Checks if dice can be set to Twos
     *
     * @return true if Twos is available
     */
    boolean isTwo() {
        isTwo = false;
        Collections.sort(currentPlayer.savedValuesDiceCast);
        if (currentPlayer.savedValuesDiceCast.size() == 5) {
            for (int i = 0; i < currentPlayer.savedValuesDiceCast.size(); i++) {
                if ((Integer) currentPlayer.savedValuesDiceCast.get(i) == 2) {
                    currentPlayer.numberTwos += ((Integer) currentPlayer.savedValuesDiceCast.get(i));
                }
            }
            if (currentPlayer.numberTwos != 0) {
                currentPlayer.pokerHands[1] = currentPlayer.numberTwos;
                isTwo = true;
            }
        }
        return isTwo;
    }

    /**
     * Checks if dice can be set to Threes
     *
     * @return true if Threes is available
     */
    boolean isThree() {
        isThree = false;
        Collections.sort(currentPlayer.savedValuesDiceCast);
        if (currentPlayer.savedValuesDiceCast.size() == 5) {
            for (int i = 0; i < currentPlayer.savedValuesDiceCast.size(); i++) {
                if ((Integer) currentPlayer.savedValuesDiceCast.get(i) == 3) {
                    currentPlayer.numberThrees += ((Integer) currentPlayer.savedValuesDiceCast.get(i));
                }
            }
            if (currentPlayer.numberThrees != 0) {
                currentPlayer.pokerHands[2] = currentPlayer.numberThrees;
                isThree = true;
            }
        }
        return isThree;
    }

    /**
     * Checks if dice can be set to Fours
     *
     * @return true if Fours is available
     */
    boolean isFour() {
        isFour = false;
        Collections.sort(currentPlayer.savedValuesDiceCast);
        if (currentPlayer.savedValuesDiceCast.size() == 5) {
            for (int i = 0; i < currentPlayer.savedValuesDiceCast.size(); i++) {
                if ((Integer) currentPlayer.savedValuesDiceCast.get(i) == 4) {
                    currentPlayer.numberFours += ((Integer) currentPlayer.savedValuesDiceCast.get(i));
                }
            }
            if (currentPlayer.numberFours != 0) {
                currentPlayer.pokerHands[3] = currentPlayer.numberFours;
                isFour = true;
            }
        }
        return isFour;
    }

    /**
     * Checks if dice can be set to Fives
     *
     * @return true if Fives is available
     */
    boolean isFive() {
        isFive = false;
        Collections.sort(currentPlayer.savedValuesDiceCast);
        if (currentPlayer.savedValuesDiceCast.size() == 5) {
            for (int i = 0; i < currentPlayer.savedValuesDiceCast.size(); i++) {
                if ((Integer) currentPlayer.savedValuesDiceCast.get(i) == 5) {
                    currentPlayer.numberFives += ((Integer) currentPlayer.savedValuesDiceCast.get(i));
                }
            }
            if (currentPlayer.numberFives != 0) {
                currentPlayer.pokerHands[4] = currentPlayer.numberFives;
                isFive = true;
            }
        }
        return isFive;
    }

    /**
     * Checks if dice can be set to Sixes
     *
     * @return true if Sixes is available
     */
    boolean isSix() {
        isSix = false;
        Collections.sort(currentPlayer.savedValuesDiceCast);
        if (currentPlayer.savedValuesDiceCast.size() == 5) {
            for (int i = 0; i < currentPlayer.savedValuesDiceCast.size(); i++) {
                if ((Integer) currentPlayer.savedValuesDiceCast.get(i) == 6) {
                    currentPlayer.numberSixes += ((Integer) currentPlayer.savedValuesDiceCast.get(i));
                }
            }
            if (currentPlayer.numberSixes != 0) {
                currentPlayer.pokerHands[5] = currentPlayer.numberSixes;
                isSix = true;
            }
        }
        return isSix;
    }

    /**
     * Checks if dice can be set to One Pair
     *
     * @return true if One Pair is available
     */
    boolean isOnePair() {
        isOnePair = false;
        Vector onePairTemp = new Vector();
        Collections.sort(currentPlayer.savedValuesDiceCast);
        if (currentPlayer.savedValuesDiceCast.size() == 5) {
            for (int i = 0; i < currentPlayer.savedValuesDiceCast.size() - 1; i++) {
                if (currentPlayer.savedValuesDiceCast.get(i) == currentPlayer.savedValuesDiceCast.get(i + 1)) {
                    onePairTemp.add((Integer) currentPlayer.savedValuesDiceCast.get(i) + (Integer) currentPlayer.savedValuesDiceCast.get(i + 1));
                }
            }
            //Find highest pair
            if (onePairTemp.size() > 0) {
                Object highestPair = Collections.max(onePairTemp);
                currentPlayer.onePair = (Integer) highestPair;
                currentPlayer.pokerHands[6] = currentPlayer.onePair;
                isOnePair = true;
            }
        }
        return isOnePair;
    }

    /**
     * Checks if dice can be set to Two Pairs
     *
     * @return true if Two Pairs is available
     */
    boolean isTwoPair() {
        isTwoPair = false;
        Object highestPair = null;
        Object nextHighestPair = null;
        Vector twoPairTemp = new Vector();
        Collections.sort(currentPlayer.savedValuesDiceCast);
        if (currentPlayer.savedValuesDiceCast.size() == 5) {
            for (int i = 0; i < currentPlayer.savedValuesDiceCast.size() - 1; i++) {
                if (currentPlayer.savedValuesDiceCast.get(i) == currentPlayer.savedValuesDiceCast.get(i + 1)) {
                    twoPairTemp.add((Integer) currentPlayer.savedValuesDiceCast.get(i) + (Integer) currentPlayer.savedValuesDiceCast.get(i + 1));
                }
            }
            //Find highest pair
            if (twoPairTemp.size() > 0) {
                highestPair = Collections.max(twoPairTemp);
                // Remove highest pair
                for (int i = 0; i < twoPairTemp.size(); i++) {
                    twoPairTemp.removeElement(highestPair);
                }
                // If there are still more values (pairs)
                if (twoPairTemp.size() > 0) {
                    // Find highest pair (now next highest pair)
                    nextHighestPair = Collections.max(twoPairTemp);
                }
            }
            // If both objects "highestPair" and "nextHighestPair" exist and  highestPair != nextHighestPair (i.e three-of-a-kind makes two identical pairs)
            if (highestPair != null && nextHighestPair != null && highestPair != nextHighestPair) {
                // Calculate sum of pair
                currentPlayer.twoPair = (Integer) highestPair + (Integer) nextHighestPair;
                currentPlayer.pokerHands[7] = currentPlayer.twoPair;
                isTwoPair = true;
            }
        }
        return isTwoPair;
    }

    /**
     * Checks if dice can be set to three of a Kind
     *
     * @return true if Three of a Kind is available
     */
    boolean isThreeOfAKind() {
        isThreeOfAKind = false;
        Collections.sort(currentPlayer.savedValuesDiceCast);
        // Check if 5 dice values are saved
        if (currentPlayer.savedValuesDiceCast.size() == 5) {
            // First three dices are alike
            if (currentPlayer.savedValuesDiceCast.get(0) == currentPlayer.savedValuesDiceCast.get(1) && currentPlayer.savedValuesDiceCast.get(1) == currentPlayer.savedValuesDiceCast.get(2)) {
                currentPlayer.threeOfAKind = (Integer) currentPlayer.savedValuesDiceCast.get(0) + (Integer) currentPlayer.savedValuesDiceCast.get(1) + (Integer) currentPlayer.savedValuesDiceCast.get(2);
                currentPlayer.pokerHands[8] = currentPlayer.threeOfAKind;
                isThreeOfAKind = true;
            } // Dices 2-4 are alike
            else if (currentPlayer.savedValuesDiceCast.get(1) == currentPlayer.savedValuesDiceCast.get(2) && currentPlayer.savedValuesDiceCast.get(2) == currentPlayer.savedValuesDiceCast.get(3)) {
                currentPlayer.threeOfAKind = (Integer) currentPlayer.savedValuesDiceCast.get(1) + (Integer) currentPlayer.savedValuesDiceCast.get(2) + (Integer) currentPlayer.savedValuesDiceCast.get(3);
                currentPlayer.pokerHands[8] = currentPlayer.threeOfAKind;
                isThreeOfAKind = true;
            } // Last three dices are alike
            else if (currentPlayer.savedValuesDiceCast.get(2) == currentPlayer.savedValuesDiceCast.get(3) && currentPlayer.savedValuesDiceCast.get(3) == currentPlayer.savedValuesDiceCast.get(4)) {
                currentPlayer.threeOfAKind = (Integer) currentPlayer.savedValuesDiceCast.get(2) + (Integer) currentPlayer.savedValuesDiceCast.get(3) + (Integer) currentPlayer.savedValuesDiceCast.get(4);
                currentPlayer.pokerHands[8] = currentPlayer.threeOfAKind;
                isThreeOfAKind = true;
            }
        }
        return isThreeOfAKind;
    }

    /**
     * Checks if dice can be set to Four of a Kind
     *
     * @return true if Four of a Kind is available
     */
    boolean isFourOfAKind() {
        isFourOfAKind = false;
        Collections.sort(currentPlayer.savedValuesDiceCast);
        if (currentPlayer.savedValuesDiceCast.size() == 5) {
            if (currentPlayer.savedValuesDiceCast.get(0) == currentPlayer.savedValuesDiceCast.get(1) && currentPlayer.savedValuesDiceCast.get(1) == currentPlayer.savedValuesDiceCast.get(2)
                    && currentPlayer.savedValuesDiceCast.get(2) == currentPlayer.savedValuesDiceCast.get(3)) {
                currentPlayer.fourOfAKind = (Integer) currentPlayer.savedValuesDiceCast.get(0) + (Integer) currentPlayer.savedValuesDiceCast.get(1) + (Integer) currentPlayer.savedValuesDiceCast.get(2)
                        + (Integer) currentPlayer.savedValuesDiceCast.get(3);
                currentPlayer.pokerHands[9] = currentPlayer.fourOfAKind;
                isFourOfAKind = true;
            } else if (currentPlayer.savedValuesDiceCast.get(1) == currentPlayer.savedValuesDiceCast.get(2) && currentPlayer.savedValuesDiceCast.get(2) == currentPlayer.savedValuesDiceCast.get(3)
                    && currentPlayer.savedValuesDiceCast.get(3) == currentPlayer.savedValuesDiceCast.get(4)) {
                currentPlayer.fourOfAKind = (Integer) currentPlayer.savedValuesDiceCast.get(1) + (Integer) currentPlayer.savedValuesDiceCast.get(2) + (Integer) currentPlayer.savedValuesDiceCast.get(3)
                        + (Integer) currentPlayer.savedValuesDiceCast.get(4);
                currentPlayer.pokerHands[9] = currentPlayer.fourOfAKind;
                isFourOfAKind = true;
            }
        }
        return isFourOfAKind;

    }

    /**
     * Checks if dice can be set to Small Straight
     *
     * @return true if Small Straight is available
     */
    boolean isSmallStraight() {
        Collections.sort(currentPlayer.savedValuesDiceCast);
        if (currentPlayer.savedValuesDiceCast.size() == 5) {
            isSmallStraight = true;
            for (int i = 0; i < currentPlayer.savedValuesDiceCast.size(); i++) {
                if ((Integer) currentPlayer.savedValuesDiceCast.get(i) != i + 1) {
                    isSmallStraight = false;
                    break;
                }
            }
        }
        if (isSmallStraight) {
            for (int i = 0; i < currentPlayer.savedValuesDiceCast.size(); i++) {
                currentPlayer.smallStraight += (Integer) currentPlayer.savedValuesDiceCast.get(i);
            }
            currentPlayer.pokerHands[10] = currentPlayer.smallStraight;
        }
        return isSmallStraight;
    }

    /**
     * Checks if dice can be set to Large Straight
     *
     * @return true if Large Straight is available
     */
    boolean isLargeStraight() {
        Collections.sort(currentPlayer.savedValuesDiceCast);
        if (currentPlayer.savedValuesDiceCast.size() == 5) {
            isLargeStraight = true;
            for (int i = 0; i < currentPlayer.savedValuesDiceCast.size(); i++) {
                if ((Integer) currentPlayer.savedValuesDiceCast.get(i) != i + 2) {
                    isLargeStraight = false;
                    break;
                }
            }
        }
        if (isLargeStraight) {
            for (int i = 0; i < currentPlayer.savedValuesDiceCast.size(); i++) {
                currentPlayer.largeStraight += (Integer) currentPlayer.savedValuesDiceCast.get(i);
            }
            currentPlayer.pokerHands[11] = currentPlayer.largeStraight;
        }
        return isLargeStraight;
    }

    /**
     * Checks if dice can be set to Full House
     *
     * @return true if Full House is available
     */
    boolean isFullHouse() {
        isFullHouse = false;
        Collections.sort(currentPlayer.savedValuesDiceCast);
        if (currentPlayer.savedValuesDiceCast.size() == 5) {
            if (currentPlayer.savedValuesDiceCast.get(0) == currentPlayer.savedValuesDiceCast.get(1) && currentPlayer.savedValuesDiceCast.get(1) == currentPlayer.savedValuesDiceCast.get(2)
                    && currentPlayer.savedValuesDiceCast.get(3) == currentPlayer.savedValuesDiceCast.get(4)) {
                currentPlayer.fullHouse = (Integer) currentPlayer.savedValuesDiceCast.get(0) + (Integer) currentPlayer.savedValuesDiceCast.get(1) + (Integer) currentPlayer.savedValuesDiceCast.get(2)
                        + (Integer) currentPlayer.savedValuesDiceCast.get(3) + (Integer) currentPlayer.savedValuesDiceCast.get(4);
                currentPlayer.pokerHands[12] = currentPlayer.fullHouse;
                isFullHouse = true;
            } else if (currentPlayer.savedValuesDiceCast.get(0) == currentPlayer.savedValuesDiceCast.get(1) && currentPlayer.savedValuesDiceCast.get(2) == currentPlayer.savedValuesDiceCast.get(3)
                    && currentPlayer.savedValuesDiceCast.get(3) == currentPlayer.savedValuesDiceCast.get(4)) {
                currentPlayer.fullHouse = (Integer) currentPlayer.savedValuesDiceCast.get(0) + (Integer) currentPlayer.savedValuesDiceCast.get(1) + (Integer) currentPlayer.savedValuesDiceCast.get(2)
                        + (Integer) currentPlayer.savedValuesDiceCast.get(3) + (Integer) currentPlayer.savedValuesDiceCast.get(4);
                currentPlayer.pokerHands[12] = currentPlayer.fullHouse;
                isFullHouse = true;
            }
        }
        return isFullHouse;
    }

    /**
     * Checks if dice can be set to Chance
     *
     * @return true if Chance is available
     */
    boolean isChance() {
        isChance = false;
        Collections.sort(currentPlayer.savedValuesDiceCast);
        if (currentPlayer.savedValuesDiceCast.size() == 5) {
            for (int i = 0; i < currentPlayer.savedValuesDiceCast.size(); i++) {
                currentPlayer.chance += (Integer) currentPlayer.savedValuesDiceCast.get(i);
            }
            currentPlayer.pokerHands[13] = currentPlayer.chance;
            isChance = true;
        }
        return isChance;
    }

    /**
     * Checks if dice can be set to Yatzy
     *
     * @return true if Yatzy is available
     */
    boolean isYatzy() {
        isYatzy = false;
        if (currentPlayer.savedValuesDiceCast.size() == 5) {
            if (currentPlayer.savedValuesDiceCast.get(0) == currentPlayer.savedValuesDiceCast.get(1) && currentPlayer.savedValuesDiceCast.get(1) == currentPlayer.savedValuesDiceCast.get(2)
                    && currentPlayer.savedValuesDiceCast.get(2) == currentPlayer.savedValuesDiceCast.get(3) && currentPlayer.savedValuesDiceCast.get(3) == currentPlayer.savedValuesDiceCast.get(4)) {
                isYatzy = true;
                currentPlayer.pokerHands[14] = 50;
            }
        }
        return isYatzy;
    }

    /**
     * Method that keeps track of the number score
     *
     * @return total score for single numbers
     */
    int numbersScore() {
        currentPlayer.numbersScore = 0;
        for (int i = 0; i < 6; i++) {
            if (currentPlayer.savedPokerHands[i] != 100) {
                currentPlayer.numbersScore += currentPlayer.savedPokerHands[i];
            }
        }
        return currentPlayer.numbersScore;
    }

    /**
     * Method that keeps track of bonus
     *
     * @return returns bonus if available
     */
    int bonus() {
        currentPlayer.bonus = 0;
        if (currentPlayer.numbersScore >= 63) {
            currentPlayer.bonus = 50;
        }
        return currentPlayer.bonus;
    }

    /**
     * Method that keeps track of the total single digit score incuding bonus
     *
     * @return total score with or without bonus
     */
    int numbersTotalScore() {
        currentPlayer.numbersTotalScore = 0;
        currentPlayer.numbersTotalScore = currentPlayer.numbersScore + currentPlayer.bonus;
        return currentPlayer.numbersTotalScore;
    }

    /**
     * Method that keeps track of poker score i.e One pair -> yatzy
     *
     * @return total poker score
     */
    int pokerScore() {
        currentPlayer.pokerScore = 0;
        for (int i = 6; i < 15; i++) {
            if (currentPlayer.savedPokerHands[i] != 100) {
                currentPlayer.pokerScore += currentPlayer.savedPokerHands[i];
            }
        }
        return currentPlayer.pokerScore;
    }

    /**
     * Method that keeps track of total score
     *
     * @return returns total numberScore + PokerScore
     */
    int grandTotal() {
        currentPlayer.grandTotal = 0;
        for (int i = 0; i < currentPlayer.savedPokerHands.length; i++) {
            if (currentPlayer.savedPokerHands[i] != 100) {
                currentPlayer.grandTotal += currentPlayer.savedPokerHands[i];
            }
        }
        currentPlayer.grandTotal += currentPlayer.bonus;
        return currentPlayer.grandTotal;
    }
}
