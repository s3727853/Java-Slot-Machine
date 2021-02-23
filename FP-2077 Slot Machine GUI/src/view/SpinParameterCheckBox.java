package view;

import javax.swing.JCheckBoxMenuItem;

import model.SpinParams;

@SuppressWarnings("serial")
public class SpinParameterCheckBox extends JCheckBoxMenuItem {
	
	private SpinParams spinParams;	

	public SpinParameterCheckBox(String title, SpinParams spinParams) {
		super(title);
		this.spinParams = spinParams;	
	}
	
	public SpinParams getSpinParam() {
		return this.spinParams;
	}
}