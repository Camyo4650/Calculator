package view;

import java.util.ArrayList;

import javax.swing.*;

public class Screen {

    private JFrame frame;
    private ArrayList<JButton> buttons;

    public Screen() {
        frame = new JFrame();
        frame.setTitle("Calculator");
        frame.setSize(600, 480);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buttons.add(new JButton(i+""));
            frame.add(buttons.get(i));
        }
        frame.setVisible(true);
    }


}
