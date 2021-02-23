package model;

import java.util.EnumMap;

import slotmachine.model.SlotMachine;
import slotmachine.model.slots.LineNum;
import view.GuiCallback;

public class ViewModel {
	private EnumMap<LineNum, Integer> previousBet = new EnumMap<>(LineNum.class);
	private SlotMachine sm;
	private int turns;
	private int delay;
	private GuiCallback cb;
	
	public ViewModel(SlotMachine sm, GuiCallback cb) {
		this.sm = sm;
		this.cb = cb;
		
		// Set Default turn/delay properties
		this.turns = SpinParams.DEFAULT.getTurns();
		this.delay = SpinParams.DEFAULT.getDelay();
		
		for(LineNum line : LineNum.values()) {
			previousBet.put(line, 0);
		}
	}

	// RegisterPlayer
	public void registerPlayer(String id, String name, int credtis) {
		sm.registerPlayer(id, name, credtis);
	}
	
	// Reset Bet
	public void resetBet() {
		for(LineNum line : LineNum.values()) {
			previousBet.put(line, 0);
		}
	}

	// PlaceBet 
	public Integer placeBet(int amount, LineNum line) throws IllegalStateException {
		
		// Bet cannot be same as previous amount. Return the last value for UI to use
		if(amount < previousBet.get(line)) {
			return previousBet.get(line);
		}
		
		// No change in bet, do nothing
		if(amount == previousBet.get(line)) {
			return 0;
		}
		
		// New bet
		if(amount > previousBet.get(line)) {
			try {
				sm.placeBet(amount, line);
			} 
			// Test purposes only
			catch(Exception e) {
				//System.out.println(e);
				return -1;
			}
			previousBet.put(line, amount);
			return 0;
		}
		return 0;
	}

	// Spin
	public void Spin() {
		
		cb.fireSpin();
		
		new Thread() {
			@Override
			public void run() {
				sm.spinToWin(turns, delay);
			}
		}.start();
	}
	
	// updateSpinDelay 
	public void updateSpinDelay(SpinParams spinParams) {
		this.delay = spinParams.getDelay();
	}
	
	// updateSpinTurns 
	public void updateSpinTurns(SpinParams spinParams) {
		this.turns = spinParams.getTurns();
	}

	public void addCredits(int credits) {
		sm.addCredits(credits);
	}

	public void cashOutPlayer() {
		sm.cashOut();
		
	}

	public void callResetBet() {
		sm.resetBets();
	}

	}