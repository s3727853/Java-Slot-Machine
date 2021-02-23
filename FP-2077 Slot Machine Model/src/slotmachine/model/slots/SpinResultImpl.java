package slotmachine.model.slots;

import java.util.ArrayList;
import java.util.Iterator;

public class SpinResultImpl implements SpinResult {
	
	private SlotLine line1, line2, line3, line4, line5;
	
	
	public SpinResultImpl(Wheel wheel1, Wheel wheel2, Wheel wheel3) {
		this.line1 = new SlotLineImpl(LineNum.LINE1, wheel1.getCentreSlot(), wheel2.getCentreSlot(), wheel3.getCentreSlot());
		this.line2 = new SlotLineImpl(LineNum.LINE2, wheel1.getTopSlot(), wheel2.getTopSlot(), wheel3.getTopSlot());
		this.line3 = new SlotLineImpl(LineNum.LINE3, wheel1.getBottomSlot(), wheel2.getBottomSlot(), wheel3.getBottomSlot());
		this.line4 = new SlotLineImpl(LineNum.LINE4, wheel1.getTopSlot(), wheel2.getCentreSlot(), wheel3.getBottomSlot());
		this.line5 = new SlotLineImpl(LineNum.LINE5, wheel1.getBottomSlot(), wheel2.getCentreSlot(), wheel3.getTopSlot());
	}

	@Override
	public Iterator<SlotLine> iterator() {
		ArrayList<SlotLine> slotlines = new ArrayList<>();
		slotlines.add(this.line1);
		slotlines.add(this.line2);
		slotlines.add(this.line3);
		slotlines.add(this.line4);
		slotlines.add(this.line5);
		
		Iterator<SlotLine> iterator = slotlines.iterator();

		return iterator;
	}
	
	@Override
	public String toString() {
		return String.format("%s\n%s\n%s", line2, line1, line3);
	}
	
}
