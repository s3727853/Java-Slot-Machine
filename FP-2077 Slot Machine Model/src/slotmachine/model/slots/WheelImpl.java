package slotmachine.model.slots;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WheelImpl implements Wheel{
	
	public static List<SlotItem> slots;
	
    private int wheelNumber;
//  This number keeps track of the amount of times a wheel has turned. See the turnWheel method.
//	if I was to do this again I would use an iterator instead. Would be a lot tidier.
    private int wheelTurnCount = 0;
    
	List<SlotItem> currentWheel;

	List<SlotItem> slot;
	
	
	
//	Private constructor
	private WheelImpl(List<SlotItem> list, int wheelNum) {
		this.wheelNumber = wheelNum;
		this.slot = list;
		// Each wheel contains only the 3 visible slots at any given time. These values are pulled from
		// each WheelImpl's instance of the 100 slots in the slot list as the wheel turns.
		currentWheel = new ArrayList<SlotItem>();	

		this.currentWheel.add(this.slot.get(0));
		this.currentWheel.add(this.slot.get(1));
		this.currentWheel.add(this.slot.get(2));
	}


	 public static Wheel createWheel(int wheelNum) {
		 return new WheelImpl(generateSlots(), wheelNum);
	 }
	
	 public static List<SlotItem> generateSlots(){
		 slots = new ArrayList<SlotItem>();
		
		 SlotItem slotitems[] = SlotItem.values();
		 for(SlotItem slotitem : slotitems) {
			 for(int i = 0; i < slotitem.getCount(); i++) {
				 slots.add(i, slotitem);
			 }
		 }
	
		 Collections.shuffle(slots);
		 
		 return slots;
	 }
	 
	@Override
	public SlotItem getTopSlot() {
		return this.currentWheel.get(2);
	}

	@Override
	public SlotItem getCentreSlot() {
		return this.currentWheel.get(1);
	}

	@Override
	public SlotItem getBottomSlot() {
		return this.currentWheel.get(0);
	}

	@Override
	public Wheel nextSlot() {
		this.turnWheel();
		return this;
		
	}
	
	
	/**
	 * This method handles the actual turning of the slot machines wheels
	 * 
	 * For each turn the Current wheel iterates through the list one step for each visible element
	 * 
	 * The switch statement handles the transition from the last elements of the slot list back to the start of the list
	 * @param wheelTurnCount	Keeps track of how many times the wheel has turned and is reset once it has cleanly transitioned back
	 * 							to the start of the list.
	 * 
	 * NOTE: in hindsight I think the JCF API could have been utilised to make this a little cleaner (iterator)
	 */
	private void turnWheel() {
		
		switch(wheelTurnCount) {
			case 98: 
				this.currentWheel.set(2, this.slot.get(98));
				this.currentWheel.set(1, this.slot.get(99));
				this.currentWheel.set(0, this.slot.get(0));
				this.wheelTurnCount ++;
				break;
			case 99:
				this.currentWheel.set(2, this.slot.get(99));
				this.currentWheel.set(1, this.slot.get(0));
				this.currentWheel.set(0, this.slot.get(1));
				this.wheelTurnCount ++;
				break;
			case 100:
				this.currentWheel.set(2, this.slot.get(0));
				this.currentWheel.set(1, this.slot.get(1));
				this.currentWheel.set(0, this.slot.get(2));
				this.wheelTurnCount = 0;
				break;	
		}
		if(wheelTurnCount <98) {
		this.currentWheel.set(2, this.slot.get(this.wheelTurnCount));
		this.currentWheel.set(1, this.slot.get(this.wheelTurnCount + 1));
		this.currentWheel.set(0, this.slot.get(this.wheelTurnCount + 2));
		this.wheelTurnCount ++;
		}
		
	}
	
	@Override
	public String toString() {
		return String.format("%s%s%s%-10s%s%-10s%s%-10s", " Wheel #", this.wheelNumber, ": ", getBottomSlot(),"/ ", getCentreSlot(), "/ ", getTopSlot());
	}

}
