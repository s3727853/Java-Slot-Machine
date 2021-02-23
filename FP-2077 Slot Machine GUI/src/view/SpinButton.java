package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;

import controller.SpinActionListener;
import model.ViewModel;

@SuppressWarnings("serial")
public class SpinButton extends JButton implements PropertyChangeListener{
	
	public SpinButton(String title, GuiCallback callback, ViewModel model) {
		super(title);
		this.setEnabled(false);
		callback.addPropertyChangeListener(this);
		this.addActionListener(new SpinActionListener(model));
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		switch(evt.getPropertyName()) {
		
		case GuiCallback.Events.NEW_BET:
			this.setEnabled(true);
			break;
		case GuiCallback.Events.SPIN_COMPLETE:
			this.setEnabled(true);
			break;
		case GuiCallback.Events.CREDITS_ADDED:
			//this.setEnabled(true);
			break;
		case GuiCallback.Events.RESET_BET:
			this.setEnabled(false);
			break;
		case GuiCallback.Events.SPIN_START:
			this.setEnabled(false);
			break;
		case GuiCallback.Events.CASHOUT_PLAYER:
			this.setEnabled(false);
			break;
		case GuiCallback.Events.DISABLE_SPINBTN:
			this.setEnabled(false);
			break;
		}	
	}
}