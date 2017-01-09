package Yatzy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

/**
 * Class for creating a pre-defined button
 *
 * @author max
 */
public class Button extends JButton {

    public Button() {
    }

    public static JButton actionButton(JButton name, Color color, Color textcolor) {
        name.setBackground(color);
        name.setPreferredSize(new Dimension(150, 40));
        name.setForeground(textcolor);
        name.setBorderPainted(false);
        name.setFocusPainted(false);
        name.setFont(new Font("Tahoma", Font.PLAIN, 20));
        return name;
    }
}
