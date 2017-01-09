package Yatzy;

import java.util.Random;

/**
 * Class handling dice
 *
 * @author max
 */
public class Dice {

    int castValue;
    String defaultValue;

    /**
     * Constructor for Dice Set's an initial value only used for output reasons
     */
    public Dice() {
        defaultValue = "?";
        castValue = 100;
    }

    /**
     * Method for throwing dice
     *
     * @return Value used to get value of dice
     */
    int throwDice() {
        Random generator = new Random();
        int rnd = generator.nextInt(6) + 1;
        castValue = rnd;
        return castValue;
    }
}
