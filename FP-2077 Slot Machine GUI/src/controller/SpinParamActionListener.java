package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Actions;
import model.ViewModel;
import view.SpinParameterCheckBox;

public class SpinParamActionListener  implements ActionListener{

	private ViewModel model;
	
	public SpinParamActionListener(ViewModel model) {
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand() == Actions.SET_DELAY.getAction()) {
			SpinParameterCheckBox item = (SpinParameterCheckBox) e.getSource();
			model.updateSpinDelay(item.getSpinParam());
		}
		
		if(e.getActionCommand() == Actions.SET_TURNS.getAction()) {
			SpinParameterCheckBox item = (SpinParameterCheckBox) e.getSource();
			model.updateSpinTurns(item.getSpinParam());
		}
	}
}
