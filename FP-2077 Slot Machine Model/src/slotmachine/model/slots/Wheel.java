package slotmachine.model.slots;

import java.util.List;

/**
 * Supporting interface used in the <b>Further Programming Assignment</b>
 * <p>
 * This interface should be implemented as
 * {@link slotmachine.model.slots.WheelImpl}.
 * <p>
 * Each {@link Wheel} object represents one of the three wheels of the Slot
 * Machine.
 * <p>
 * Each Wheel has 3 visible slots; centre, top and bottom. Each Wheel can be
 * turned, advancing to the next slot, using {@link Wheel#nextSlot()}
 * <p>
 * The class should have no visible constructor (must be private) and should
 * provide the following method to support the creation of a {@link Wheel}
 * 
 * <pre>
 * public static Wheel createWheel(int wheelNum)
 * </pre>
 * <p>
 * To aid testing/marking your class should also provide the following method
 * (which should be be called be the one above). This methods should generate a
 * {@link List} of {@link SlotItem} containing 100 slots based on the
 * {@link SlotItem#getCount()} for each {@link SlotItem} value. The returned
 * {@link List} should be of a random order of slots and should be unmodifiable.
 * 
 * <pre>
 * public static List&lt;SlotItem&gt; generateSlots()
 * </pre>
 * 
 * @author Ross Nye
 * @see SlotItem
 * @see List
 */
public interface Wheel
{
	// public static Wheel createWheel(int wheelNum);

	// public static List<SlotItem> generateSlots();

	/**
	 * Simple getter to return the top (visible) slot on the wheel
	 * 
	 * @return the top (visible) slot on the wheel
	 */
	public SlotItem getTopSlot();

	/**
	 * Simple getter to return the centre (visible) slot on the wheel
	 * 
	 * @return the centre (visible) slot on the wheel
	 */
	public SlotItem getCentreSlot();

	/**
	 * Simple getter to return the bottom (visible) slot on the wheel
	 * 
	 * @return the bottom (visible) slot on the wheel
	 */
	public SlotItem getBottomSlot();

	/**
	 * This method turns the {@link Wheel} one slot.
	 * <p>
	 * A turn is defined a advancing the slots of a wheel <b>one</b> position. An
	 * new (unseen) slot item will appear as the top of the wheel. The slot
	 * previously at the top moves to the centre and the previous centre slot moves
	 * to the bottom. The previous bottom slot is not longer visible.
	 * 
	 * @return the same wheel object (with its state updated).
	 */
	public Wheel nextSlot();

	/**
	 * Returns a {@link String} representing the {@link Wheel} in format shown in
	 * the two examples below and seen in the output trace.
	 * 
	 * <pre>
	 * Wheel #1: 3          / 5          / Silver Bar
	 * </pre>
	 * 
	 * <pre>
	 * Wheel #3: Berry      / 3          / 3
	 * </pre>
	 * 
	 * @return a {@link String} representing the {@link Wheel}
	 */
	@Override
	public String toString();

}
