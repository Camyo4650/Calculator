package view;

import java.util.ArrayList;
import java.awt.*;

import javax.swing.*;


public class Screen {

    private JFrame frame;
    private GridBagLayout layout;
    private JTextField display;
    private ArrayList<JButton> buttons;

    public Screen() {
        frame = new JFrame();
        frame.setTitle("Calculator");
        frame.setSize(600, 480);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        layout = new GridBagLayout();
        JPanel displayPanel = new JPanel(layout);
        display = new JTextField("Test");
        buttons = new ArrayList<>();
        displayPanel.add(display);
        for (Buttons button : Buttons.values()) {
            JButton buttonObj = new JButton(button.getText());
            buttons.add(buttonObj);
            displayPanel.add(buttonObj);
        }
        frame.add(displayPanel);
        frame.setVisible(true);
    }

}
