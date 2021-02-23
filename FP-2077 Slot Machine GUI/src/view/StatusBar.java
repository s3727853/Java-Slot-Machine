package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class StatusBar extends JPanel  implements PropertyChangeListener{
	
	private JLabel labelLeft = new JLabel("Status Bar Section 1", SwingConstants.CENTER);
	private JLabel labelRight = new JLabel("Status Bar Section 1", SwingConstants.CENTER);
	private Border border = BorderFactory.createLineBorder(Color.black);
	
	public StatusBar(GuiCallback callback) {
		
		callback.addPropertyChangeListener(this);
		setLayout(new GridLayout());
		
		labelLeft.setBorder(border);
		labelRight.setBorder(border);
		
		labelLeft.setText("Welcome to the GUI slotmachine");
		labelRight.setText("CPT-222 Assignment 3");
		add(labelLeft);
		add(labelRight);
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		
		switch(evt.getPropertyName()) {
		
		case GuiCallback.Events.SPIN_COMPLETE:
			int winnings = (int) evt.getNewValue();
			if(winnings > 0) {
				labelLeft.setText("Win! " + winnings + " Credits");
			} else {
				labelLeft.setText("No Winning lines");
			}
			break;
			
		case GuiCallback.Events.RESET_BET:
			labelLeft.setText("Bets reset");
			break;
		case GuiCallback.Events.CASHOUT_PLAYER:
			labelLeft.setText("Player Cashed out");
			labelRight.setText("Register a new player to play again");
			break;
		case GuiCallback.Events.CREDITS_ADDED:
			labelRight.setText("Credits added");
			break;
		case GuiCallback.Events.NEW_PLAYER:
			labelRight.setText("New player added");
			break;
		case GuiCallback.Events.NEW_BET:
			labelLeft.setText("Bet placed");
			labelRight.setText("");
			break;
		}
	}
}