package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Calculator;
import model.StringProcessor;
import model.arithmetic.Expression;
import java.awt.Font;

/**
 * This class utilizes the {@link javax.swing javax.swing} libraries.
 * This was built using an Eclipse plugin called WindowBuilder, similar
 * to the form builder used in Visual Studio for C#.
 * @author Eli Forssberg
 * @author Alex Whipple
 * @author Cameron Pilchard
 */
public class Window extends JFrame {

	private JPanel contentPane;
	private JTextField input;
	private String textVal;
	private JTextField textField;

    private Calculator calc;
    private double roundOff;

	/**
	 * Create the frame.
	 * Uses WindowBuilder plugin
	 * 
	 * @param calc This parameter stores the Calculator for ease of access. 
	 * This class requires the has-a relationship with Calculator because we need a way to know
	 * when the return key is pressed or when the equals button is pressed without a while loop.
	 */
	public Window(Calculator calc) {
		this.calc = calc;
        roundOff = 3e-13;

		setTitle("Complex Calculator");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 442);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setBackground(new Color(47, 79, 79));
		contentPane_1.setBounds(5, 5, 368, 404);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPane_1);
		GridBagLayout gbl_contentPane_1 = new GridBagLayout();
		gbl_contentPane_1.columnWidths = new int[] {0};
		gbl_contentPane_1.rowHeights = new int[] {0, 0, 0};
		gbl_contentPane_1.columnWeights = new double[]{1.0};
		gbl_contentPane_1.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0};
		contentPane_1.setLayout(gbl_contentPane_1);
		
		JButton btnNewButton = new JButton("=");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		btnNewButton.addActionListener(new ActionListener() { // waits for when the equals button is hit
			public void actionPerformed(ActionEvent e) {
				solve();
			}
		});
		
		textField = new JTextField();
		textField.setOpaque(true);
		textField.setEditable(false);
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridheight = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		contentPane_1.add(textField, gbc_textField);
		
		input = new JTextField();
		input.addActionListener(new ActionListener() { // waits for when return is hit
			public void actionPerformed(ActionEvent e) {
				solve();
			}
		});
		GridBagConstraints gbc_input = new GridBagConstraints();
		gbc_input.fill = GridBagConstraints.HORIZONTAL;
		gbc_input.insets = new Insets(0, 0, 5, 0);
		gbc_input.gridx = 0;
		gbc_input.gridy = 2;
		contentPane_1.add(input, gbc_input);
		input.setHorizontalAlignment(SwingConstants.LEADING);
		input.setOpaque(true);
		input.setColumns(10);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 3;
		contentPane_1.add(btnNewButton, gbc_btnNewButton);

		// first message
		println("Welcome to the Complex Calculator! See README!");
	}
	
	/**
	 * This method is called when return is hit in the text form or the equals button is pressed.
	 * This method calculates the answer of a math expression entered into the textfield.
	 * Errors are handled here so we can see problems on the screen.
	 */
	public void solve() {
		textVal = input.getText();
		try {
			if (textVal.toLowerCase().charAt(textVal.length()-1) == 'p')
				displayAnswer(calc.calculate(textVal.substring(0, textVal.length()-1), true), false);
			else
				displayAnswer(calc.calculate(textVal, false), true);
		} catch (Exception ex) {
			displayError(ex.getMessage());
		}
	}

	/**
	 * This method displays the answer of a problem to the console.
	 * @param ans The answer
	 * @param isRect Whether or not the answer is in cartesian form or polar form
	 */
    public void displayAnswer(double[] ans, boolean isRect) {
        char Pi = '\u03C0';
        char Sum = '\u03A3';
        if (isRect) {
            String real = "";
            String imag = "";
            if (Math.abs(ans[0]) > roundOff) real = (""+ans[0]);
            else real = "0";
            if (Math.abs(ans[1]) != 1) {
                if (ans[1] < 0) imag = " - "+(""+ans[1]).substring(1);
                else imag = " + "+ans[1];
                imag += "i";
            } else {
                if (ans[1] > 0) imag += " + i";
                else imag += " - i";
            }
            if (Math.abs(ans[1]) > roundOff) { // just to round down to zero
                println(real + imag);
            } else {
                println(real);
            }
        } else
            println(String.format("%.9f", ans[0]) + " * exp(i"+String.format("%.9f", ans[1])+")");
    }

	/**
	 * The method used to display a message when an error occurred
	 * @param msg The message
	 */
    public void displayError(String msg) {
        println("ERROR: " + msg);
    }
	
	/**
	 * The method used to display any sort of message. 
	 * Clears the console every time to reduce clutter
	 * @param msg The message
	 */
	public void println(String msg) {
		textField.setText(msg);
	}
}
