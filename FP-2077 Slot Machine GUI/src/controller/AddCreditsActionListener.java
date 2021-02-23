package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import model.Actions;
import model.ViewModel;

public class AddCreditsActionListener implements ActionListener {
	
	private ViewModel model;
	private JDialog dialog;
	private JFormattedTextField betInput;
	 
	public AddCreditsActionListener(ViewModel model, JDialog dialog, JFormattedTextField betInput) {
		this.model = model;
		this.dialog = dialog;
		this.betInput = betInput;
	}

	public AddCreditsActionListener(JDialog addCredits) {
		this.dialog = addCredits;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand() == Actions.SHOW_DIALOG.getAction()) {
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		}

		if(e.getActionCommand() == Actions.HIDE_DIALOG.getAction()) {
			dialog.setVisible(false);
			dialog.setLocationRelativeTo(null);
			betInput.setText("0");
		}
		
		if(e.getActionCommand() == Actions.SUBMIT_DIALOG.getAction()) {
			
			String credits = betInput.getText();

			// Strip invalid chars from number 
		
			credits = credits.replaceAll("[^0-9]", "");
		
			if(credits.equals("")) {
				credits = "0";
			}
		
			if(Integer.parseInt(credits) > 0) {
				
				model.addCredits(Integer.parseInt(credits));
				System.out.print("Adding crdits: " + credits);
			}
			betInput.setText("0");
			dialog.setVisible(false);
			dialog.setLocationRelativeTo(null);
		}	
	}
}