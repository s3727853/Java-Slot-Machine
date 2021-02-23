package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ViewModel;

public class ResetBetActionListener implements ActionListener {

private ViewModel model;
	
	public ResetBetActionListener(ViewModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			model.callResetBet();
	}
}