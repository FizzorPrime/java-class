import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Window class that creates the gui system the application runs off of
 * @author Fizzor
 */
public class Window {
	
	//creating all of the different objects meant to be used by the constructor
	JFrame f;
	JPanel p;
	JButton b1;
	JButton help;
	String[] operations = {"+", "-", "*", "/", "%", "^", "sqrt"};
	JComboBox combo = new JComboBox(operations);
	JLabel lab;
	JTextField textfield1, textfield2;
	
	/**
	 * Constructor that builds the gui system and then calls for the calculations
	 * @author Fizzor
	 */
	public Window() {
		//creates the window the application displays in
		f = new JFrame("Calculator");
		//creates the panel that aligns all of the components together
		p = new JPanel(new GridBagLayout());
		p.setBackground(Color.white);
		//creates the two input fields for the calculations
		textfield1 = new JTextField("Input 1",7);
	    textfield2 = new JTextField("Input 2",7);
	    //creates the equals button that calls the calculator to begin
		b1 = new JButton("=");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculate(textfield1.getText(), combo, textfield2.getText());
			}
		});
		//creates the help button for some pointers
		help = new JButton("Help");
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayHelp();
			}
		});
		//creates the display field for the output
		lab = new JLabel("Results");
		
		//appends all the objects to the panel and then the frame to be displayed
		p.add(help);
		p.add(textfield1);
		p.add(combo);
	    p.add(textfield2);
		p.add(b1);
		p.add(lab);
		f.add(p);
		//sets the frame to be visible, a certain size and to close when the 'x' button is pressed
		f.setVisible(true);
		f.setSize(600,200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Performs the calculation with the given inputs from the gui
	 * @author Fizzor
	 * @param x String: contains the first input value to be converted to a double
	 * @param combo JComboBox: the operation type to determine what type of calculation to make
	 * @param y String: contains the second input value to be converted to a double
	 */
	public void calculate(String x, JComboBox combo, String y) {
		//converts the combo's value to a string
		String modifier = combo.getSelectedItem().toString();
		try {
			//migrates the value of the two inputs into doubles and checks to see if the value is PI
			double a, b;
			if(x.replaceAll("\\s", "").equals("PI")) { a = Math.PI; }
			else { a = Double.parseDouble(x); }
			if(y.replaceAll("\\s", "").equals("PI")) { b =Math.PI; }
			else{ b = Double.parseDouble(y); }
			//determines which operation to perform and executes
			if     (modifier == "+") { lab.setText(Double.toString(a+b)); }
			else if(modifier == "-") { lab.setText(Double.toString(a-b)); }
			else if(modifier == "*") { lab.setText(Double.toString(a*b)); }
			else if(modifier == "/") { lab.setText(Double.toString(a/b)); }
			else if(modifier == "%") { lab.setText(Double.toString(a%b)); }
			else if(modifier == "^") { lab.setText(Double.toString(Math.pow(a,b))); }
			else if(modifier == "sqrt") {
				//converts the sqrt value to a reversed version of itself to produce the same type of calculation as a root
				b = 1/b;
				lab.setText(Double.toString(Math.pow(a,b)));
			}
		} 
		//catches an exception if the input was not a number to be calculated
		catch (NumberFormatException e) {
			lab.setText("Invalid input");
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
