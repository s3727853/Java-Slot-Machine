package view;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

@SuppressWarnings("serial")
public class BetInputTextField extends JFormattedTextField {
	static NumberFormat betFormat = NumberFormat.getNumberInstance();
	
	public BetInputTextField() {
		super(betFormat);
		this.setColumns(3);
		this.setText("0");
		this.setEditable(false);
	}
}