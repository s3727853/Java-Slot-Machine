package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JMenuItem;
import controller.SpinActionListener;
import model.ViewModel;

@SuppressWarnings("serial")
public class SpinMenuItem extends JMenuItem implements PropertyChangeListener{

	public SpinMenuItem(GuiCallback callback, ViewModel model) {
		super("Spin");
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
		}	
	}	
}