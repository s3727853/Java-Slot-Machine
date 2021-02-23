package view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import controller.CashoutActionListener;
import model.Actions;
import model.ViewModel;
import slotmachine.model.Player;

@SuppressWarnings("serial")
public class CashoutDialog extends JDialog implements PropertyChangeListener{
	private JLabel idDesc, nameDesc, creditsDesc, id, name, credits;
	private JButton submit, cancel;
	private ActionListener cashoutListener;
	
	public CashoutDialog(GameFrame gameFrame, ViewModel model, GuiCallback callback) {
		super(gameFrame, "Cashout Player");
		
		callback.addPropertyChangeListener(this);
		setLayout(new GridLayout(4,2));
		
		idDesc = new JLabel("ID");
		nameDesc = new JLabel("Name");
		creditsDesc = new JLabel("Credits");
		
		id = new JLabel();
		name = new JLabel();
		credits = new JLabel();
		
		submit = new JButton("Cash Out");
		cancel = new JButton("Cancel");
		
		cashoutListener = new CashoutActionListener(this, model);
		
		submit.setActionCommand(Actions.SUBMIT_DIALOG.getAction());
		submit.addActionListener(cashoutListener);
		
		cancel.setActionCommand(Actions.HIDE_DIALOG.getAction());
		cancel.addActionListener(cashoutListener);
		
		add(idDesc);
		add(id);
		add(nameDesc);
		add(name);
		add(creditsDesc);
		add(credits);
		add(submit);
		add(cancel);
		
		pack();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if(evt.getPropertyName() == GuiCallback.Events.BET_TOTALS || evt.getPropertyName() == GuiCallback.Events.NEW_PLAYER) {
			Player player = (Player) evt.getNewValue();
			id.setText(player.getId());
			name.setText(player.getName());
			credits.setText(Integer.toString(player.getCredits()));
		}
	}
}