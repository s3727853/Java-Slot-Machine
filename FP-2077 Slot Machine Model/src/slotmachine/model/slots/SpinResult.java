package slotmachine.model.slots;

import slotmachine.model.SlotMachine;

/**
 * Supporting interface used in the <b>Further Programming Assignment</b>
 * <p>
 * This interface should be implemented as
 * {@link slotmachine.model.slots.SpinResultImpl} and represents the five
 * {@link SlotLine} that exist as a result of spinning the 3 wheels.
 * <p>
 * The class should provide a single constructor that with the following
 * signature
 * <pre>public SpinResultImpl(Wheel wheel1, Wheel wheel2, Wheel wheel3)</pre>
 * <p>
 * <b>Hint:</b> This class is not complex, requiring a simple constructor and
 * just two even simpler methods (if you use the API effectively).
 *
 * @see Wheel
 * @see SlotLine
 * @see SlotMachine#spin(int, int)
 * 
 * @author Ross Nye
 */
public interface SpinResult extends Iterable<SlotLine>
{
	/**
	 * <p>
	 * Returns a {@link String} representing the {@link SpinResult} in format shown
	 * below and seen in the output trace.
	 * <pre>
	 * Line #2 | Cherry     | 5          | Lemon      |
	 * Line #1 | 1          | 7          | Melon      |
	 * Line #3 | 1          | Melon      | 7          |
	 * </pre>
	 * <p>
	 * <b>Note:</b> Only one toString() result is displayed above.
	 * <p>
	 * <b>Note:</b> Line 4 and 5 are not included in the String as they can be
	 * inferred as below
	 * <pre>
	 * Line #4 | Cherry     | 7          | 7          |
	 * Line #5 | 1          | 7          | Lemon      |
	 * </pre>
	 * 
	 * @return a {@link String} representing the {@link SpinResult}
	 */
	@Override
	public String toString();
}
