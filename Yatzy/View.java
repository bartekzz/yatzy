package Yatzy;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class handling the viewport.
 *
 * Creates and morphs JFrames, JPanels and JButtons
 *
 * @author Bartek, Max, Marcus, Anneli
 *
 *         inspiration taken from https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 *        
 *        
 */
public class View {

    JButton rollButton;
    JButton zeroButton;
    JButton diceButtons[];
    JButton scoreButtons[];
    JButton exitButton;
    JButton newGameButton;
    JLabel boxes[][][];
    JLabel numbersTotalBoxes[][][];
    JLabel pokerTotalBoxes[][][];
    JLabel rollLabel;
    JPanel south, north, east, west, center, centerE, centerW, centerS,
    centerWB, centerWT, one, centerEN, centerEast;
    JPanel centerEC;
    GridBagConstraints gc = new GridBagConstraints();
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public View() {
        createWindow();
        createDiceButtons();
        createScoreButtons();
        createNewGameButton();
        createExitButton();

    }
    
    /**
	 * Creates the frame and layouts of the game.
	 */

    public void createWindow() {
        // Create JFrame (container window)
        mainFrame = new JFrame("Yahtzee");

        try {
            mainFrame.setIconImage(ImageIO.read(getClass().getResource("/images/dice.png")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mainFrame.setSize(900, 1000);
        mainFrame.setLayout(new BorderLayout());

        mainFrame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        // Create JPanel
        controlPanel = new JPanel();
        north = new JPanel();
        south = new JPanel();
        east = new JPanel();
        west = new JPanel();
        center = new JPanel();
        centerS = new JPanel();
        centerW = new JPanel();
        centerE = new JPanel();
        centerWB = new JPanel();
        centerWT = new JPanel();
        centerEN = new JPanel();
        centerEast = new JPanel();
        centerEC = new JPanel();

        controlPanel.setLayout(new BoxLayout(north, BoxLayout.LINE_AXIS));
        controlPanel.setPreferredSize(new Dimension(150, 150));
        controlPanel.setBackground(new Color(40, 40, 40));

        south.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 30));
        south.setPreferredSize(new Dimension(200, 120));
        south.setBackground(new Color(40, 40, 40));

        east.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        east.setPreferredSize(new Dimension(0, 0));
        east.setBackground(new Color(20, 20, 20));

        west.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        west.setPreferredSize(new Dimension(0, 0));
        west.setBackground(new Color(47, 46, 46));

        center.setLayout(new BorderLayout());
        center.setBackground(new Color(47,47,47));

        centerS.setLayout(new GridLayout(5, 1));
        centerS.setBackground(new Color(0, 0, 0));

        centerW.setLayout(new GridBagLayout());
        centerW.setBorder(new MatteBorder(30, 0, 20, 20, new Color(47,47,47)));
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridx = 0; 
        gc.gridy = -1;

        gc.weighty = 50;
        
       

        centerW.setBackground(new Color(47, 47, 47));

        centerWB.setLayout(new GridLayout(2, 1));
        centerWB.setBackground(new Color(47, 47, 47));
        

        centerWT.setLayout(new GridLayout(15, 1));
        centerWT.setBackground(new Color(47, 46, 47));

        centerEN.setLayout(new GridLayout(1, 6));
        centerEN.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        centerEN.setBackground(new Color(47, 47, 47));

        centerEC.setLayout(new GridLayout(1, 6));
        centerEC.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        centerEC.setBackground(new Color(47, 47, 47));

        centerEast.setLayout(new BorderLayout());
        centerEast.setBackground(new Color(0, 0, 0));

        controlPanel.setLayout(new FlowLayout());
        //Create JLabel
        headerLabel = new JLabel("HeaderLabel", JLabel.CENTER);
        headerLabel.setSize(400, 100);
        statusLabel = new JLabel("StatusLabel", JLabel.CENTER);
        statusLabel.setSize(400, 100);

        // Add borders to labels
        headerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        statusLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // Add label to display current roll
        rollLabel = new JLabel();
        rollLabel.setVisible(true);
        controlPanel.add(rollLabel);

        // Create button and add to JPanel
        rollButton = new JButton("Roll Dice");
        Button.actionButton(rollButton, new Color(255, 0, 0), new Color(255, 255, 255));
        south.add(rollButton);

        zeroButton = new JButton("Zero");
        Button.actionButton(zeroButton, new Color(188, 188, 188), new Color(0, 0, 0));
        south.add(zeroButton);
        
        // Add JPanel and JLabels to JFrame
        mainFrame.add(controlPanel, BorderLayout.NORTH);
        mainFrame.add(south, BorderLayout.SOUTH);
        mainFrame.add(center, BorderLayout.CENTER);
        mainFrame.add(east, BorderLayout.EAST);
        mainFrame.add(west, BorderLayout.WEST);
        center.add(centerW, BorderLayout.WEST);
        center.add(centerEast, BorderLayout.CENTER);
        centerEast.add(centerEN, BorderLayout.NORTH);
        centerEast.add(centerEC, BorderLayout.CENTER);

        centerW.add(centerWT, gc);
        gc.anchor = GridBagConstraints.SOUTH;
        
        centerW.add(centerWB, gc);

        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);

    }
    
    /**
    * Creates containers for the dices of the game 
    */

    public void createDiceButtons() {
        diceButtons = new JButton[5];
        for (int i = 0; i < 5; i++) {
            diceButtons[i] = new JButton();
            diceButtons[i].setContentAreaFilled(false);
            diceButtons[i].setBorderPainted(false);
            controlPanel.add(diceButtons[i]);
        }
    }
    
    /**
     *Creates containers for the scorebuttons of the game 
     */

    public void createScoreButtons() {
        //Create scoreButtons and scoreBoxes
        scoreButtons = new JButton[15];
        for (int i = 0; i < scoreButtons.length; i++) {
            scoreButtons[i] = new JButton();
            scoreButtons[i].setFont(new Font("Tahoma", Font.PLAIN, 20));
            scoreButtons[i].setBackground(new Color(0, 204, 0));
            scoreButtons[i].putClientProperty("id", i);
            scoreButtons[i].getModel().setEnabled(false);

            centerWT.add(scoreButtons[i]);
        }
        // Set names for scoreButtons
        scoreButtons[0].setText("Ones");
        scoreButtons[1].setText("Twos");
        scoreButtons[2].setText("Threes");
        scoreButtons[3].setText("Fours");
        scoreButtons[4].setText("Fives");
        scoreButtons[5].setText("Sixes");
        scoreButtons[6].setText("One Pair");
        scoreButtons[7].setText("Two Pair");
        scoreButtons[8].setText("Three of a Kind");
        scoreButtons[9].setText("Four of a Kind");
        scoreButtons[10].setText("Small Straight");
        scoreButtons[11].setText("Large Straight");
        scoreButtons[12].setText("Full House");
        scoreButtons[13].setText("Chance");
        scoreButtons[14].setText("Yahtzee");
    }
    
    /**
     * Creates containers for the scoreboard. 
     * @param numberOfPlayers  number of players in the game 
     * @param playerlist  playerlist with all playernames within an arraylist 
     */
    
    public void createBoxes(int numberOfPlayers, ArrayList<Player> playerlist) {
        // Create boxes
        boxes = new JLabel[15][2][numberOfPlayers];
        // Create numbers score box
        // Create bonus box
        // Create numbers total score box
        numbersTotalBoxes = new JLabel[3][2][numberOfPlayers];
        // Create poker total score box
        // Crate grand total box
        pokerTotalBoxes = new JLabel[2][2][numberOfPlayers];

        for (int j = 0; j < numberOfPlayers; j++) {
            one = new JPanel();
            one.setLayout(new GridLayout(21, 1));
            one.setBackground(new Color(80 + ((j * 5)), 80 + (j * 5), 80 + (j * 5)));
            one.setBorder(new MatteBorder(0, 0,20,20, new Color(47,47,47)));

            String name = playerlist.get(j).getPlayerName();
            Label namelabel = new Label(name);
            namelabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
            centerEN.add(namelabel);

            JLabel l0;
            JLabel l1;
            for (int i = 0; i < boxes.length; i++) {
                l0 = new JLabel(Values.tellItLikeItIs(i));
                l1 = new JLabel();
                //l0.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
                //l1.setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK));
                boxes[i][0][j] = l0;
                boxes[i][1][j] = l1;
                one.add(boxes[i][0][j]);
                one.add(boxes[i][1][j]);
            }
            JLabel l2;
            JLabel l3;

            for (int i = 0; i < numbersTotalBoxes.length; i++) {
                l2 = new JLabel();
                l3 = new JLabel();
               
                numbersTotalBoxes[i][0][j] = l2;
                numbersTotalBoxes[i][1][j] = l3;
                one.add(numbersTotalBoxes[i][0][j]);
                one.add(numbersTotalBoxes[i][1][j]);
            }

            numbersTotalBoxes[0][0][j].setText(" Numbers Score");
            numbersTotalBoxes[1][0][j].setText(" Bonus");
            numbersTotalBoxes[2][0][j].setText(" Total Numbers Score");

            JLabel l4;
            JLabel l5;
            for (int i = 0; i < pokerTotalBoxes.length; i++) {
                l4 = new JLabel();
                l5 = new JLabel();
               
                pokerTotalBoxes[i][0][j] = l4;
                pokerTotalBoxes[i][1][j] = l5;
                one.add(pokerTotalBoxes[i][0][j]);
                one.add(pokerTotalBoxes[i][1][j]);
            }
            pokerTotalBoxes[0][0][j].setText(" Poker Score");
            pokerTotalBoxes[1][0][j].setText(" Grand Total");

            centerEC.add(one, j);

        }
    }
    
    /**
     * Creates a button that starts a new Game 
     */

    public void createNewGameButton() {
        //create exit button
        newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Button.actionButton(newGameButton, new Color(30, 30, 30), new Color(200, 200, 200));
        centerWB.add(newGameButton);
    }
    
    /**
    * Creates a button that exits the game  
    */

    public void createExitButton() {
        //create exit button
        exitButton = new JButton("  Exit game  ");
        exitButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
        Button.actionButton(exitButton, new Color(30, 30, 30), new Color(200, 200, 200));
        centerWB.add(exitButton);
    }

    /**
     * Switches the images in a group of Jbuttons
     * 
     */
    
    public void setImage() {

        for (int i = 0; i < 5; i++) {
            if (diceButtons[i].getModel().isSelected() == false) {
                setImageToOriginal(diceButtons[i]);
            } else if (diceButtons[i].getModel().isSelected() == true) {
                setImageToClicked(diceButtons[i]);
            }
        }
    }
    
    /**
    * This method gets the text from a Jbutton, and then returns an image depending on the text.  
    *  
    * @param diceButtons JButton containing the dices of the game 
    * @exception NullPointerException  if images are not found 
    */

    public void setImageToClicked(JButton diceButtons) {
        try {
            String number = diceButtons.getText();
            Image img = ImageIO.read(getClass().getResource("/images/dice-" + number + "-clicked" + ".png"));
            diceButtons.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
        }
    }

    /**
     * This method gets the text from a Jbutton, and then returns an image depending on the text.  
     *  
     * @param diceButtons JButton containing the dices of the game 
     * @exception NullPointerException  if images are not found 
     */
    public void setImageToOriginal(JButton diceButtons) {
        try {
            String number = diceButtons.getText();
            Image img = ImageIO.read(getClass().getResource("/images/dice-" + number + ".png"));
            diceButtons.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
        }
    }

    JButton getRollButton() {
        return rollButton;
    }

    JButton getZeroButton() {
        return zeroButton;
    }

    JButton getDiceButton(int i) {
        return diceButtons[i];
    }
}
