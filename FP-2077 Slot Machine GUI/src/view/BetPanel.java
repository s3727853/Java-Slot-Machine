package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EnumMap;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controller.BetPanelActionListener;
import model.ViewModel;
import slotmachine.model.slots.LineNum;

@SuppressWarnings("serial")
public class BetPanel extends JPanel implements PropertyChangeListener {
	
	private JFormattedTextField bet1, bet2, bet3, bet4, bet5;
	private JButton placeBet;
	private EnumMap<LineNum, JFormattedTextField> betInputs = new EnumMap<>(LineNum.class);
	
	public BetPanel(ViewModel viewmodel, GuiCallback callback) {
		
		BetPanelActionListener al = new BetPanelActionListener(viewmodel, betInputs, callback);
		callback.addPropertyChangeListener(this);

		add(new JLabel("Line 1"));
		bet1 = new BetInputTextField();
		add(bet1);
		betInputs.put(LineNum.LINE1, bet1);
		
		add(new JLabel("Line 2"));
		bet2 = new BetInputTextField();
		add(bet2);
		betInputs.put(LineNum.LINE2, bet2);
		
		add(new JLabel("Line 3"));
		bet3 = new BetInputTextField();
		add(bet3);
		betInputs.put(LineNum.LINE3, bet3);
		
		add(new JLabel("Line 4"));
		bet4 = new BetInputTextField();
		add(bet4);
		betInputs.put(LineNum.LINE4, bet4);
		
		add(new JLabel("Line 5"));
		bet5 = new BetInputTextField();
		add(bet5);
		betInputs.put(LineNum.LINE5, bet5);
		
		placeBet = new JButton("Place Bet");
		placeBet.setEnabled(false);
		add(placeBet);
		placeBet.addActionListener(al);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		switch(evt.getPropertyName()) {
			case GuiCallback.Events.NEW_PLAYER:
				// Enable UI fields
				bet1.setEditable(true);
				bet2.setEditable(true);
				bet3.setEditable(true);
				bet4.setEditable(true);
				bet5.setEditable(true);
				placeBet.setEnabled(true);
				break;
				
			case GuiCallback.Events.SPIN_START:
				placeBet.setEnabled(false);
				break;
				
			case GuiCallback.Events.SPIN_COMPLETE:
				placeBet.setEnabled(true);
				break;
				
			case GuiCallback.Events.CREDITS_ADDED:
				placeBet.setEnabled(true);
				break;
				
			case GuiCallback.Events.RESET_BET:
				betInputs.forEach((key, value) -> {
					value.setText("0");
				});
				
				JOptionPane.showMessageDialog(this, "Bets have been reset.");
				break;
			
			case GuiCallback.Events.CASHOUT_PLAYER:
				placeBet.setEnabled(false);
		}
	}	
}