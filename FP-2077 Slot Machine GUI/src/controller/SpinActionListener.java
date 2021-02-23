package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ViewModel;

public class SpinActionListener implements ActionListener{
	
	private ViewModel model;
	
	public SpinActionListener(ViewModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.Spin();
	}
}