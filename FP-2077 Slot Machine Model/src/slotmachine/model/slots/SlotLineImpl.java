package slotmachine.model.slots;

import java.util.Objects;

public class SlotLineImpl implements SlotLine {

	private LineNum lineNum;
	private SlotItem slot1, slot2, slot3;
	
	public SlotLineImpl(LineNum lineNum, SlotItem slot1, SlotItem slot2, SlotItem slot3) {
		this.lineNum = lineNum;
		this.slot1 = slot1;
		this.slot2 = slot2;
		this.slot3 = slot3;
	}
	
	@Override
	public LineNum getLineNum() {
		return this.lineNum;
	}

	@Override
	public SlotItem getSlot1() {
		return this.slot1;
	}

	@Override
	public SlotItem getSlot2() {
		return this.slot2;
	}

	@Override
	public SlotItem getSlot3() {
		return this.slot3;
	}
	
	
	@Override
	public boolean equals(SlotLine slotLine) {
		return (slotLine != null) 
				&& (this.slot1 == slotLine.getSlot1())
				&& (this.slot2 == slotLine.getSlot2())
				&& (this.slot3 == slotLine.getSlot3());
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof SlotLine && equals((SlotLine) obj);
				
	}

	@Override
	public int hashCode() {
		return Objects.hash(slot1, slot2, slot3);
		
	}
	
	
	@Override
	public String toString() {
		return String.format("%s%s%-10s%S%-10s%s%-10s%S",this.lineNum, " | ",this.slot1, " | ", this.slot2, " | ", this.slot3,  "|");
	}
	

}
