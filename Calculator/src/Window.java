import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Window class that creates the gui system the application runs off of
 * @author Fizzor
 */
public class Window {
	
	//creating all of the different objects meant to be used by the constructor
	JFrame mainFrame;
	JPanel mainPanel;
	JButton equalsButton;
	JButton helpButton;
	String[] operationList = {"+", "-", "*", "/", "%", "^", "sqrt"};
	JComboBox operationBox = new JComboBox(operationList);
	JLabel resultsLabel;
	JTextField textFieldX, textFieldY;
	
	/**
	 * Constructor that builds the gui system and then calls for the calculations
	 * @author Fizzor
	 */
	public Window() {
		//creates the window the application displays in
		mainFrame = new JFrame("Calculator");
		//creates the panel that aligns all of the components together
		mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(Color.white);
		//creates the two input fields for the calculations
		textFieldX = new JTextField("Input 1",7);
	    textFieldY = new JTextField("Input 2",7);
	    //creates the equals button that calls the calculator to begin
	    equalsButton = new JButton("=");
	    equalsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculate(textFieldX.getText(), operationBox, textFieldY.getText());
			}
		});
		//creates the help button for some pointers
		helpButton = new JButton("Help");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayHelp();
			}
		});
		//creates the display field for the output
		resultsLabel = new JLabel("Results");
		
		//appends all the objects to the panel and then the frame to be displayed
		mainPanel.add(helpButton);
		mainPanel.add(textFieldX);
		mainPanel.add(operationBox);
		mainPanel.add(textFieldY);
		mainPanel.add(equalsButton);
		mainPanel.add(resultsLabel);
		mainFrame.add(mainPanel);
		//sets the frame to be visible, a certain size and to close when the 'x' button is pressed
		mainFrame.setVisible(true);
		mainFrame.setSize(600,200);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Performs the calculation with the given inputs from the gui
	 * @author Fizzor
	 * @param x String: contains the first input value to be converted to a double
	 * @param combo JComboBox: the operation type to determine what type of calculation to make
	 * @param y String: contains the second input value to be converted to a double
	 */
	public void calculate(String x, JComboBox operationBox, String y) {
		//converts the combo's value to a string
		String modifier = operationBox.getSelectedItem().toString();
		try {
			//migrates the value of the two inputs into doubles and checks to see if the value is PI
			double a, b;
			if (x.replaceAll("\\s", "").equals("PI")) { 
				a = Math.PI; 
			}
			else { 
				a = Double.parseDouble(x); 
			}
			
			if (y.replaceAll("\\s", "").equals("PI")) { 
				b =Math.PI; 
			}
			else { 
				b = Double.parseDouble(y); 
			}
			
			//determines which operation to perform and executes
			if (modifier == "+") { 
				resultsLabel.setText(Double.toString(a+b)); 
			}
			else if (modifier == "-") { 
				resultsLabel.setText(Double.toString(a-b)); 
			}
			else if (modifier == "*") { 
				resultsLabel.setText(Double.toString(a*b)); 
			}
			else if (modifier == "/") { 
				resultsLabel.setText(Double.toString(a/b)); 
			}
			else if (modifier == "%") { 
				resultsLabel.setText(Double.toString(a%b)); 
			}
			else if (modifier == "^") { 
				resultsLabel.setText(Double.toString(Math.pow(a,b))); 
			}
			else if (modifier == "sqrt") {
				//converts the sqrt value to a reversed version of itself to produce the same type of calculation as a root
				b = 1/b;
				resultsLabel.setText(Double.toString(Math.pow(a,b)));
			}
		} 
		//catches an exception if the input was not a number to be calculated
		catch (NumberFormatException e) {
			resultsLabel.setText("Invalid input");
		}
	}
	
	/**
	 * Creates the help window to display the help information
	 * @author Fizzor
	 */
	private void displayHelp() {
		JOptionPane.showMessageDialog(null, "Input a value into both input slots, then select what operation you would \nlike to perform and press '=' to calculate. If you type 'PI' as an input, \nthe calculator will use the mathematical constant of PI.");
	}
}
