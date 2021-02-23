package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JDialog;

import model.Actions;

public class GenericDialogActionListener implements ActionListener{

	private JDialog dialog;
	
	public GenericDialogActionListener(JDialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Show the Dialog Box
		if(e.getActionCommand() == Actions.SHOW_DIALOG.getAction()) {
			dialog.setVisible(true);
		}
		
		// Hide 
		if(e.getActionCommand() == Actions.HIDE_DIALOG.getAction()) {
			dialog.setVisible(false);
		}	
		
		// Accept Check box input actions for show hide
		if(e.getActionCommand() == Actions.CHECKBOX.getAction()) {
			
			AbstractButton checkbox = (AbstractButton) e.getSource();
			boolean isChecked = checkbox.getModel().isSelected();
			
			if(isChecked) {
				dialog.setVisible(true);
			} 
			else {
				dialog.setVisible(false);
			}
		}
		
		// Exit App
		if(e.getActionCommand() == Actions.EXIT_PROGRAM.getAction()) {
			System.exit(0);
		}	
	}
}