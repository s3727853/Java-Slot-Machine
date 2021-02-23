package slotmachine.model.slots;

/**
 * Supporting interface used in the <b>Further Programming Assignment</b>
 * <p>
 * This interface is be implemented as
 * {@link slotmachine.model.slots.WinSettingsImpl} and is supplied in the start
 * up code.
 * <p>
 * The concrete class is a helper class which provides a means to obtain the
 * winning odds of a given {@link SlotLine}. These odds can than be used to
 * calculate the outcome of a bet (per line).
 * 
 * @see slotmachine.model.SlotMachine#applySpinResult(SpinResult)
 * @see SpinResult
 * @see SlotLine
 * @see SlotItem
 * 
 * @author Ross Nye
 */
public interface WinSettings
{
	/**
	 * Returns the winning odds of the supplied line, or zero if it's not winning
	 * line.
	 * <p>
	 * <b>Note: </b> You will need your {@link SlotLine#equals(Object)} method
	 * functioning correctly in order for this method to return anything other than
	 * zero.
	 * <p>
	 * You <b>may</b> used {@link #getWinSimplified(SlotItem, SlotItem, SlotItem)}
	 * if your {@link SlotLine#equals(Object)} does not function correctly. You
	 * <b>will</b> however loose marks in doing so.
	 * 
	 * @param slotLine the {@link SlotLine} to obtain the odds for
	 * @return the winning odds of the supplied line, or 0
	 */
	public int getWinOdds(SlotLine slotLine);

	/**
	 * The simplified version of {@link #getWinOdds(SlotLine)}.
	 * <p>
	 * This method functions in the same manner at {@link #getWinOdds(SlotLine)},
	 * but does not rely on {@link SlotLine#equals(Object)}.
	 * <p>
	 * <b>Note:</b> You will loose marks for invoking this method.
	 * 
	 * @param slot1 the {@link SlotItem} from wheel #1 in the line
	 * @param slot2 the {@link SlotItem} from wheel #2 in the line
	 * @param slot3 the {@link SlotItem} from wheel #3 in the line
	 * @return the winning odds of the supplied line, or 0
	 */
	public int getWinSimplified(SlotItem slot1, SlotItem slot2, SlotItem slot3);

}
