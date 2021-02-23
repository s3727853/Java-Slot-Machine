package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EnumMap;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import model.ViewModel;
import slotmachine.model.slots.LineNum;
import view.GuiCallback;

public class BetPanelActionListener implements ActionListener, PropertyChangeListener {
	private ViewModel model;
	private EnumMap<LineNum, JFormattedTextField> betInputs;
	
	public BetPanelActionListener(ViewModel model, EnumMap<LineNum, JFormattedTextField> betInputs, GuiCallback callback) {
		this.model = model;
		this.betInputs = betInputs;
		callback.addPropertyChangeListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		betInputs.forEach((key, value) -> {
			if(value.getText().equals("")) {
				value.setText("0");
			}
			// Reset all borders to default in case one was highlighted.
			value.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
			
			// Remove any chars from input
			String credits  = value.getText(); 
			credits = credits.replaceAll("[^0-9]", "");
			
			// If a bet is less than previous model will return the last good value
			int modelReturn = model.placeBet(Integer.parseInt(credits), key);
			
			if (modelReturn > 0) {
				value.setText(Integer.toString(modelReturn));
				value.setBorder(BorderFactory.createLineBorder(Color.RED));
				
				JOptionPane.showMessageDialog((Component) e.getSource(), "Bet must not be less then the lines previous bet.\nInvalid values have been reset to previous state.");
			};
			
			// Not enough Bitcoins
						
			if (modelReturn == -1) {		
				JOptionPane.showMessageDialog((Component) e.getSource(), "Not enough Bitcoins to service bet. \nPlease lower bet or add more Bitcoins");
				value.setText("0");
			}			
		});
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		// Reset the previous bets stored in View Model
		if(evt.getPropertyName() == GuiCallback.Events.RESET_BET) {
			model.resetBet();
		}
	}
}
