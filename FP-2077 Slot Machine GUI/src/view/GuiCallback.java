package view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.Icon;

import slotmachine.model.Player;
import slotmachine.model.slots.LineNum;
import slotmachine.model.slots.SlotLine;
import slotmachine.model.slots.SpinResult;
import slotmachine.model.slots.Wheel;
import slotmachine.view.GameCallback;

public class GuiCallback implements GameCallback {
	
	private Boolean cashOut = false;
	private Boolean betUpdated = false;
	private int lineResultCounter = 0;
	private ArrayList<Object> lineResultArray = new ArrayList<>();
	
	public static class Events{
		public static final String NEW_PLAYER = "Player";	
		public static final String SET_AVATAR = "avatar";
		public static final String NEW_BET = "New Bet";
		public static final String CASHOUT_PLAYER = "Cashout Player";
		public static final String SPIN_START = "Spin Start";
		public static final String UPDATE_WHEEL = "Update Wheel";
		public static final String BET_TOTALS = "Bet Totals";
		public static final String SPIN_COMPLETE = "Spin Complete";
		public static final String CREDITS_ADDED = "Credits Added";
		public static final String LINE_RESULT = "Line Result";
		public static final String RESET_BET = "Reset Bet";
		public static final String DISABLE_SPINBTN = "Disable spin button";
	}

	private HashMap<String, Wheel> wheels;
	
	final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public GuiCallback(Wheel wheel1, Wheel wheel2, Wheel wheel3) {
		wheels = new HashMap<>();
		wheels.put("Wheel1", wheel1);
		wheels.put("Wheel2", wheel2);
		wheels.put("Wheel3", wheel3);
		
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}
	
	
	@Override
	public void registerPlayer(Player player) {
		this.pcs.firePropertyChange(Events.NEW_PLAYER, null, player);	
		cashOut = false;
	}

	@Override
	public void cashOutPlayer(Player player) {
		cashOut = true;
		this.pcs.firePropertyChange(Events.CASHOUT_PLAYER, null, player);
	}

	@Override
	public void addCredits(Player player, int credits) {
		
		this.pcs.firePropertyChange(Events.BET_TOTALS, null, player);	
		this.pcs.firePropertyChange(Events.CREDITS_ADDED, null, credits);	
	}

	@Override
	public void betUpdated(Player player, int amount, LineNum line) {
		this.pcs.firePropertyChange(Events.NEW_BET, null, player);	
	}

	@Override
	public void betUpdated(Player player, int amount, Collection<LineNum> lines) {
		betUpdated = true;
		if(!cashOut) {
			this.pcs.firePropertyChange(Events.RESET_BET, null, player);
		}
	}

	@Override
	public void turnWheel(Wheel wheel, int turnNum) {
		this.pcs.firePropertyChange(Events.UPDATE_WHEEL, null, wheels);		
	}

	@Override
	public void spinComplete(SpinResult spinResult) {
	}

	@Override
	public void lineResult(Player player, boolean hasBet, int lineOutcome, SlotLine slotLine) {

		HashMap<String, Object> lineResult = new HashMap<>();
		lineResult.put("bet", hasBet);
		lineResult.put("outcome", lineOutcome);
		lineResult.put("slotline", slotLine);
		lineResultArray.add(lineResult);
		lineResultCounter ++;
		
		if(lineResultCounter == 5) {
			this.pcs.firePropertyChange(Events.LINE_RESULT, null, lineResultArray);
			lineResultCounter = 0;
			lineResultArray.clear();
		}
	}

	@Override
	public void betTotals(Player player, int total) {
		this.pcs.firePropertyChange(Events.BET_TOTALS, null, player);	
		this.pcs.firePropertyChange(Events.SPIN_COMPLETE, null, total);
		
		if(betUpdated) {
			this.pcs.firePropertyChange(Events.DISABLE_SPINBTN, null, 0);
		} 
	
		betUpdated = false;
	}

	// This event is used to disable UI elements while spin is progress
	public void fireSpin() {
		this.pcs.firePropertyChange(Events.SPIN_START, null, null);
	}
	
	public void setIcon(Icon avatar) {
		this.pcs.firePropertyChange(Events.SET_AVATAR, null, avatar);
	}
	
}