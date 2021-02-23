package view;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JToolBar;

import controller.AddCreditsActionListener;
import controller.CashoutActionListener;
import controller.RegisterPlayerActionListener;
import controller.ResetBetActionListener;
import model.Actions;
import model.ViewModel;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar implements PropertyChangeListener{

	private JDialog registerDialog, addCreditsDialog;
	private ButtonGroup group = new ButtonGroup();
	private ActionListener addPlayerListener, addCreditsListener, cashoutListener, resetBetListener;
	private JButton registerPlayerBtn, addCreditsBtn, cashOutBtn, resetBetBtn;
	
	public ToolBar(HashMap<String, JDialog> dialogs, GuiCallback callback, ViewModel model) {
		callback.addPropertyChangeListener(this);
		registerDialog = dialogs.get("registerPlayer");
		addCreditsDialog = dialogs.get("addCredits");
		
		registerPlayerBtn = new JButton("Register Player");
		addCreditsBtn = new JButton("Add Credits");
		cashOutBtn = new JButton("Cashout Player");
		resetBetBtn = new JButton("Reset Bets");
		
		addPlayerListener = new RegisterPlayerActionListener(registerDialog, model);
		addCreditsListener = new AddCreditsActionListener(addCreditsDialog);
		cashoutListener = new CashoutActionListener(dialogs.get("cashout"), model);
		resetBetListener = new ResetBetActionListener(model);
		
		setFloatable(false);
		add(registerPlayerBtn);
		
		registerPlayerBtn.setActionCommand(Actions.SHOW_DIALOG.getAction());
		registerPlayerBtn.addActionListener(addPlayerListener);
		
		addCreditsBtn.setActionCommand(Actions.SHOW_DIALOG.getAction());
		addCreditsBtn.addActionListener(addCreditsListener);
		
		cashOutBtn.setActionCommand(Actions.SHOW_DIALOG.getAction());
		cashOutBtn.addActionListener(cashoutListener);
		
		resetBetBtn.setActionCommand(Actions.SUBMIT_DIALOG.getAction());
		resetBetBtn.addActionListener(resetBetListener);
		
		add(addCreditsBtn);
		add(cashOutBtn);
		add(resetBetBtn);
		
		group.add(addCreditsBtn);
		group.add(registerPlayerBtn);
		group.add(cashOutBtn);
		group.add(resetBetBtn);
		
		addCreditsBtn.setEnabled(false);
		cashOutBtn.setEnabled(false);
		resetBetBtn.setEnabled(false);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// Disable Register player button when new player is added and enable cash out and add credits
		if(evt.getPropertyName() == GuiCallback.Events.NEW_PLAYER) {
			registerPlayerBtn.setEnabled(false);
			addCreditsBtn.setEnabled(true);
			cashOutBtn.setEnabled(true);
		}
		
		// Enable register button when current player is cashed out and disable cash out + add credits 
		if(evt.getPropertyName() == GuiCallback.Events.CASHOUT_PLAYER) {
			registerPlayerBtn.setEnabled(true);
			addCreditsBtn.setEnabled(false);
			cashOutBtn.setEnabled(false);
			resetBetBtn.setEnabled(false);
		}
		
		if(evt.getPropertyName() == GuiCallback.Events.SPIN_START) {
			addCreditsBtn.setEnabled(false);
			cashOutBtn.setEnabled(false);
			resetBetBtn.setEnabled(false);
		}
		if(evt.getPropertyName() == GuiCallback.Events.SPIN_COMPLETE) {
			addCreditsBtn.setEnabled(true);
			cashOutBtn.setEnabled(true);
			resetBetBtn.setEnabled(true);
		}
		if(evt.getPropertyName() == GuiCallback.Events.NEW_BET) {
			resetBetBtn.setEnabled(true);
		}
		if(evt.getPropertyName() == GuiCallback.Events.RESET_BET) {
			resetBetBtn.setEnabled(false);
		}
	}
}