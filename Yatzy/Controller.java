package Yatzy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Class to control the elements of the program
 *
 * @author Bartek, Max
 */
public class Controller {

    Model model;
    View view;
    DiceListener diceListener;
    ScoreListener scoreListener;

    /**
     * Constructor for controller.
     * Adds all elements to the controller and sets properties
     */
    public Controller() {
        View view = new View();
        this.view = view;
        Model model = new Model(view);
        this.model = model;
        DiceListener diceListener = new DiceListener(view, model);
        this.diceListener = diceListener;
        ScoreListener scoreListener = new ScoreListener(view, model);
        this.scoreListener = scoreListener;

        System.out.println("Model: " + model);
        System.out.println("DiceListener: " + diceListener);
        System.out.println("ScoreListener: " + scoreListener);

        view.newGameButton.addActionListener(e -> {
            int confirmExit = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to start new game?", null, JOptionPane.YES_NO_OPTION);
            if (confirmExit == JOptionPane.YES_OPTION) {
                //model.restartApplication();
                model.newGame();
            }
        });

        view.exitButton.addActionListener(e -> {
            int confirmExit = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to exit?", null, JOptionPane.YES_NO_OPTION);
            if (confirmExit == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        view.getRollButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.roll();
                view.setImage();
            }
        });

        view.getZeroButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setHandsAvailableForZero();
            }

        });

        // Add listener to all dice buttons
        for (int i = 0; i < view.diceButtons.length; i++) {
            view.diceButtons[i].addActionListener(diceListener);
        }

        // Add listener to all dice buttons
        for (int i = 0; i < view.scoreButtons.length; i++) {
            view.scoreButtons[i].addActionListener(scoreListener);
        }
    }

    public static void main(String[] arg) {
        try {
            // select Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
            // start application
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Controller controller = new Controller();
    }

    /**
     * Class that adds an actionListener to dice
     *
     * @author Bartek
     */
    public static class DiceListener implements ActionListener {

        View view;
        Model model;

        public DiceListener(View view, Model model) {
            this.view = view;
            this.model = model;
        }

        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < view.diceButtons.length; i++) {
                if (view.diceButtons[i].getModel().isEnabled()) {
                    if (e.getSource() == view.diceButtons[i]) {
                        model.toggleDice(i);
                    }
                }
            }
        }
    }

    /**
     * Class that adds an ActionListener for score-keeping
     *
     * @author Bartek
     */
    public static class ScoreListener implements ActionListener {

        View view;
        Model model;

        public ScoreListener(View view, Model model) {
            this.view = view;
            this.model = model;
        }

        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < view.scoreButtons.length; i++) {
                if (view.scoreButtons[i].getModel().isEnabled()) {
                    if (e.getSource() == view.scoreButtons[i]) {
                        model.setScore(i);
                        model.showScore();
                        model.displayScore();
                        model.displayAllScores();
                        model.changePlayer();
                    }
                }
            }
        }
    }
}
