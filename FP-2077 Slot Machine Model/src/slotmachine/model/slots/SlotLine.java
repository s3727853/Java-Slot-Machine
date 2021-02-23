package slotmachine.model.slots;

/**
 * Supporting interface used in the <b>Further Programming Assignment</b>
 * <p>
 * This interface should be implemented as
 * {@link slotmachine.model.slots.SlotLineImpl}.
 * <p>
 * Each {@link SlotLine} object represents one of the five lines of the Wheel,
 * each containing three {@link SlotItem} (one from each {@link Wheel}).
 * <p>
 * The class should provide a single constructor that with the following
 * signature
 * <pre>public SlotLineImpl(LineNum lineNum, SlotItem slot1, SlotItem slot2, SlotItem slot3)</pre>
 * <p>
 * <b>Note:</b> You <b>will need to ensure</b> you correctly override the
 * {@link java.lang.Object#equals(Object)} and
 * {@link java.lang.Object#hashCode()} methods in order to be able to
 * retrieve
 * <p>
 * <b>Hint:</b> The five {@link SlotLine} objects should be instantiated in the
 * {@link slotmachine.model.slots.SpinResultImpl} constructor.
 * 
 * @author Ross Nye
 */
public interface SlotLine
{
	/**
	 * Simple getter for the {@link LineNum}
	 * 
	 * @return the LineNum
	 */
	LineNum getLineNum();

	/**
	 * Simple getter for the {@link SlotItem} from the 1st {@link Wheel}
	 * 
	 * @return the {@link SlotItem} from the 1st {@link Wheel}
	 */
	SlotItem getSlot1();

	/**
	 * Simple getter for the {@link SlotItem} from the 2nd {@link Wheel}
	 * 
	 * @return the {@link SlotItem} from the 2nd {@link Wheel}
	 */
	SlotItem getSlot2();

	/**
	 * Simple getter for the {@link SlotItem} from the 3rd {@link Wheel}
	 * 
	 * @return the {@link SlotItem} from the 3rd {@link Wheel}
	 */
	SlotItem getSlot3();

	/**
	 * A convenience method to compare two {@link SlotLine} objects.
	 * <p>
	 * If all three {@link SlotItem} of this object as the same as the corresponding
	 * {@link SlotItem} objects from the {@link SlotLine} parameter then the two
	 * {@link SlotLine} objects are deemed to be equal and the method returns true.
	 * Otherwise if any slots do not match the method should return false.
	 * <p>
	 * <b>Note</b> The {@link LineNum} are not used to determine equality.
	 * 
	 * @param slotLine the reference {@link SlotLine} with which to compare
	 * @return true if all three equivalent slots are equal
	 * 
	 * @see SlotLine#equals(Object)
	 * @see SlotLine#hashCode()
	 */
	public boolean equals(SlotLine slotLine);

	/**
	 * This method is used internally by the JCF and should work for all Objects. If
	 * the supplied parameter does not implement {@link SlotLine} interface the
	 * Objects cannot be equal.
	 * <p>
	 * If the supplied parameter is a {@link SlotLine} object the same rules apply
	 * as outlined in {@link SlotLine#equals(SlotLine)}
	 * 
	 * @param obj the reference object with which to compare
	 * @return if the two objects are equal
	 */
	@Override
	public boolean equals(Object obj);

	/**
	 * Returns a hash code value for the object.
	 * 
	 * @return a hash code value for this object.
	 */
	@Override
	public int hashCode();
	
	/**
	 * Returns a {@link String} representing the {@link SlotLine} in format shown in
	 * the examples below and seen in the output trace.
	 * 
	 * 
	 * 
	 * @return a {@link String} representing the {@link SlotLine}
	 */
	@Override
	public String toString();

}
