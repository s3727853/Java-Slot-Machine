package slotmachine.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import slotmachine.model.slots.LineNum;
import slotmachine.model.slots.SlotLine;
import slotmachine.model.slots.SpinResult;
import slotmachine.model.slots.SpinResultImpl;
import slotmachine.model.slots.Wheel;
import slotmachine.model.slots.WheelImpl;
import slotmachine.model.slots.WinSettings;
import slotmachine.model.slots.WinSettingsImpl;
import slotmachine.view.GameCallback;

public class SlotMachineImpl implements SlotMachine {

	private Collection<GameCallback> callbacks = new ArrayList<>();
	public Player player;
	public SpinResultImpl spinresult;
	// Keeps track of current turns for the session
	private int turnCounter = 0;
	
	// Represents the percentage of the the spin amount that wheels 2 and 3 will spin. (Wheel 1 spins 100% of the time)
	private final float WHEEL2_SPIN_PERCENTAGE = 66.0f;
	private final float WHEEL3_SPIN_PERCENTAGE = 33.0f;
	
	private Wheel wheel1; 
	private Wheel wheel2;
	private Wheel wheel3;
	WinSettings winsettings;

	// ArrayList to track current bets
	private List<Integer> betArray = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0)); 
	
	public SlotMachineImpl() {
		wheel1 = WheelImpl.createWheel(1);
		wheel2 = WheelImpl.createWheel(2);
		wheel3 = WheelImpl.createWheel(3);
		winsettings = new WinSettingsImpl();
	}
	
	public SlotMachineImpl(Wheel wheel1, Wheel wheel2, Wheel wheel3, WinSettings winSettings) {
		this.wheel1 = wheel1;
		this.wheel2 = wheel2;
		this.wheel3 = wheel3;
		this.winsettings = winSettings;
	}
	
	
	@Override
	public int addCallback(GameCallback callback) {
		callbacks.add(callback);
	
		return callbacks.size();
	}

	@Override
	public int removeCallback(GameCallback callback) {
		callbacks.remove(callback);
		return callbacks.size();
	}

	@Override
	public Player registerPlayer(String id, String name, int initialCredits) {
		player = new PlayerImpl(id, name, initialCredits);
	
		for(GameCallback callback : callbacks)
			callback.registerPlayer(player);
		return player;
	}

	@Override
	public Player cashOut() {
		if(player == null) {
			throw new IllegalStateException("No registered player");
		}
		resetBets();
		
		for(GameCallback callback : callbacks)
			callback.cashOutPlayer(player);
		
		player = null;
		return player;
	}

	@Override
	public void addCredits(int credits) throws IllegalStateException, IllegalArgumentException {
		if(player == null) {
			throw new IllegalStateException("No registered player");
		}
		if(credits <= 0) {
			throw new IllegalStateException("AddCredits: Credits must be greater than 0");
		}
		player.addCredits(credits);
		
		for(GameCallback callback : callbacks)
			callback.addCredits(player, credits);
	}

	@Override
	public void placeBet(int amount) throws IllegalArgumentException, IllegalStateException {
		placeBet(amount, LineNum.LINE1);

		
	}

	@Override
	public void placeBet(int amount, LineNum line) throws IllegalArgumentException, IllegalStateException {
		// Add line to an array so that the correct method signature gets used
		placeBet(amount, (new LineNum[] {line}));
		
	}

	@Override
	public void placeBet(int amount, Set<LineNum> lines) throws IllegalArgumentException, IllegalStateException {
		placeBet(amount, lines);
		
	}

	@Override
	public void placeBet(int amount, LineNum... lines) throws IllegalArgumentException, IllegalStateException {
		// Main placeBet method
		if(lines.length == 0) {
			throw new IllegalArgumentException("No lines supplied");
		}
		if(player == null) {
			throw new IllegalStateException("No registered player");
		}
		if(player.getAvailableCredits() < amount * lines.length) {
			throw new IllegalStateException("Not enough Bitcoins to service bet");
		}
		if(amount <= 0) {
			throw new IllegalArgumentException("Bet amount must be greater than zero");
		}	
		
		
		player.setBet(amount * lines.length);

		// A set was used as it cannot contain duplicates. This makes it useful for identifying if the line has 
		// already been bet on (E.g We cannot add line1 if it is already in the set, therefore we need to update the bet for the line)
		Set<LineNum> betSet = new TreeSet<>();
		// Loop through the LineNum set, if a Line already exists, ensure new bet is higher 
		for(LineNum line : lines) {
			boolean checkForDuplicate = betSet.add(line);
			if(checkForDuplicate) {
				if(betArray.get(line.ordinal()) != 0 && amount <= betArray.get(line.ordinal())) {
					throw new IllegalStateException("Bet must be higher than previous bet");
				}	
			}
			
			// Declaring integer for use in switch statement 
			int currentBet = 0;
			// Using the ordinal values of the line enum to distinguish lines 1 through to 5 (0 to 4)
			// the bet is set (or added) to the line in the corresponding betArray list.
			switch(line.ordinal()) {
			case 0:
				currentBet = betArray.get(line.ordinal());
				betArray.set(line.ordinal(), currentBet + amount);
				break;
			case 1:			
				currentBet = betArray.get(line.ordinal());
				betArray.set(line.ordinal(), currentBet + amount);
				break;
			case 2:
				currentBet = betArray.get(line.ordinal());
				betArray.set(line.ordinal(), currentBet + amount);
				break;
			case 3:
				currentBet = betArray.get(line.ordinal());
				betArray.set(line.ordinal(), currentBet + amount);
				break;
			case 4:
				currentBet = betArray.get(line.ordinal());
				betArray.set(line.ordinal(), currentBet + amount);
				break;
			}
		}
//	    Determine which callback to use for betUpdated. If bet was on multiple lines use that one, else single line 
		if(lines.length>1) {
			List<LineNum> lineNumList = Arrays.asList(lines);
			
			for(GameCallback callback : callbacks)
				callback.betUpdated(player, amount, lineNumList);
			
		} else {
			
			for(GameCallback callback : callbacks)
				callback.betUpdated(player, amount, lines[0]);
		}
	}

	@Override
	public void resetBets() {
		//Clear the bet array and add new default values of zero
		betArray.clear();
		betArray.addAll(Arrays.asList(0, 0, 0, 0, 0));
		
		player.resetBet();
		
		for(GameCallback callback : callbacks)
			callback.betUpdated(player, 0, Arrays.asList(LineNum.values()));
	}

	@Override
	public SpinResult spinToWin(int turns, int delay) throws IllegalArgumentException, IllegalStateException {
		if(player.getBet() <= 0) {
			throw new IllegalStateException("Bet must be greater than zero for spinToWin to run");
		}
		if(turns < 1 || delay < 0 || delay > 2000) {
			throw new IllegalArgumentException("Turns must be greater than 0. Delay must be greater than 0, less than 2000");
		}
		SpinResult spinresult = spin(turns, delay);
		applySpinResult(spinresult);
		return spinresult;
	}

	@Override
	public SpinResult spin(int turns, int delay) throws IllegalArgumentException {
		if(turns < 1 || delay < 0 || delay > 2000) {
			throw new IllegalArgumentException("Turns must be greater than 0. Delay must be greater than 0, less than 2000");
		}
		
		int wheel1Iterations = turns;
		// All wheels must spin at least once. If turns <=3 wheel 2 & 3 to turn once
		int wheel2Iterations = turns <=3 ? 1 : (int)(turns * (WHEEL2_SPIN_PERCENTAGE/100.0f));
		int wheel3Iterations = turns <=3 ? 1 : (int)(turns * (WHEEL3_SPIN_PERCENTAGE/100.0f));
		
		try {
		for(int i = 0; i < turns; i++) {
			turnCounter ++;	
			
			
			if(i <= wheel1Iterations) {
				wheel1 = wheel1.nextSlot();
				//Thread.sleep(delay);
				
				for(GameCallback callback : callbacks)
					callback.turnWheel(wheel1, turnCounter);
			}
			
			if(i <= wheel2Iterations) {
				wheel2 = wheel2.nextSlot();
				//Thread.sleep(delay);
				
				for(GameCallback callback : callbacks)
					callback.turnWheel(wheel2, turnCounter);
			}
			
			if(i <= wheel3Iterations) {
				wheel3 = wheel3.nextSlot();
				//Thread.sleep(delay);
				
				for(GameCallback callback : callbacks)
					callback.turnWheel(wheel3, turnCounter);
			}
			Thread.sleep(delay);
			
		}
		turnCounter ++;
		} catch(Exception e) {
			throw new IllegalArgumentException(e);
		}

		spinresult = new SpinResultImpl(wheel1, wheel2, wheel3);
		
		for(GameCallback callback : callbacks)
			callback.spinComplete(spinresult);

		return spinresult;
	}

	@Override
	public int applySpinResult(SpinResult spinResult) {	
	
	// Iterate through the 3 wheel lines and 2 horizontal lines or results.
	Iterator<SlotLine> it = spinResult.iterator();	
	int i = 0;
	int totalWin = 0;
	// Here we loop through slotlines and associated betArray entry. If the slotLine has a bet associated with it
	// win settings is called and the bet multiple by the winsettings, then the callback is called for the win.
	// NOTE: This is a bit hacky but it is setup so that the first slotline in the collection(or iterator) corresponds with
	//       index 0 of the bet array. Which will contain either 0 (no bet) or the bet amount.
	while(it.hasNext()) {
		SlotLine loop = it.next();
		if(betArray.get(i) > 0) {
			int lineWin = betArray.get(i) * winsettings.getWinOdds(loop);
			
			for(GameCallback callback : callbacks)
				callback.lineResult(this.player, true, lineWin, loop);
			totalWin += lineWin;
			
		} else {
		 
			for(GameCallback callback : callbacks)
				callback.lineResult(this.player, false, 0, loop);
		}
		i++;
		
		}
		i = 0;

		player.applyWin(totalWin);
		if(player.getAvailableCredits() < 0) {
			resetBets();
		}
		
		for(GameCallback callback : callbacks)
			callback.betTotals(player, totalWin);
		
		return totalWin;
		
	}
}
