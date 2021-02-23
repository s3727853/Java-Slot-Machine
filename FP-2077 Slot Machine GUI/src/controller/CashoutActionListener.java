package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import model.Actions;
import model.ViewModel;

public class CashoutActionListener implements ActionListener {
	
	private JDialog dialog;
	private ViewModel model;
	
	public CashoutActionListener(JDialog dialog, ViewModel model) {
		this.dialog = dialog;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand() == Actions.SHOW_DIALOG.getAction()) {
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		}
		
		
		if(e.getActionCommand() == Actions.SUBMIT_DIALOG.getAction()) {
			model.cashOutPlayer();
			dialog.dispose();
		}
		
		if(e.getActionCommand() == Actions.HIDE_DIALOG.getAction()) {
			dialog.dispose();
		}	
	}
}
