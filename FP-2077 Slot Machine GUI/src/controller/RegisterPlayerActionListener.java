package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import model.Actions;
import model.ViewModel;

public class RegisterPlayerActionListener implements ActionListener {
	private ViewModel model;
	private JDialog dialog = null;
	private HashMap<String, JFormattedTextField> inputs;
	private Boolean inputValid;

	public RegisterPlayerActionListener(ViewModel model, JDialog dialog, HashMap<String, JFormattedTextField> inputs) {
		this.model = model;
		this.dialog = dialog;
		this.inputs = inputs;
	}
	
	public RegisterPlayerActionListener(JDialog dialog, ViewModel model) {
		this.dialog = dialog;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Show the Dialog Box
		if(e.getActionCommand() == Actions.SHOW_DIALOG.getAction()) {
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		}
		
		// Hide and clear the box (Cancel Button)
		if(e.getActionCommand() == Actions.HIDE_DIALOG.getAction()) {
			dialog.setVisible(false);
			dialog.setLocationRelativeTo(null);
			inputs.get("IDinput").setText("");
			inputs.get("NameInput").setText("");
			inputs.get("PointsInput").setText("");
		}
		
		// Process and the inputs (Submit Button)
		if(e.getActionCommand() == Actions.SUBMIT_DIALOG.getAction()) {
			
			inputValid = true;
			String id = inputs.get("IDinput").getText();
			String name = inputs.get("NameInput").getText();
			String points = inputs.get("PointsInput").getText();

			// Strip invalid chars from number 
		
			points = points.replaceAll("[^0-9]", "");
		
			if(points.equals("")) {
				points = "0";
			}
		
			if(Integer.parseInt(points) <= 0) {
				inputs.get("PointsInput").setBorder(BorderFactory.createLineBorder(Color.RED));
				inputs.get("PointsInput").setText("");
				inputValid = false;
			}
			
			// Check ID in correct format
			if(!id.matches("[1-9]{5}[A-Z]{1}")){
				inputValid = false;
				JFormattedTextField id1 = inputs.get("IDinput");
				id1.setBorder(BorderFactory.createLineBorder(Color.RED));
				id1.setText("Must be in format 12345A");
			}
		
			if(inputValid) {
				model.registerPlayer(id, name, Integer.parseInt(points));
				inputs.get("IDinput").setText("");
				inputs.get("NameInput").setText("");
				inputs.get("PointsInput").setText("");
				dialog.setVisible(false);
			}
		}
	}
}