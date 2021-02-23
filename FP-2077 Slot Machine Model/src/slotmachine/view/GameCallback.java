
package slotmachine.view;

import java.util.Collection;

import slotmachine.model.Player;
import slotmachine.model.SlotMachine;
import slotmachine.model.slots.LineNum;
import slotmachine.model.slots.SpinResult;
import slotmachine.model.slots.SlotLine;
import slotmachine.model.slots.Wheel;

/**
 * The callback interface in <b>Further Programming Assignment</b>
 * <p>
 * The methods is this interface are called upon as specified in the
 * SlotMachineEngine interface
 * <p>
 * In Assignment 1 this class is implemented as {@link ConsoleLoggerCallback}.
 * 
 * @see slotmachine.model.SlotMachine
 * 
 * @author Ross Nye
 */
public interface GameCallback
{
	/**
	 * Updates view when player is registered
	 * 
	 * @param player the registered player
	 * @see SlotMachine#registerPlayer(String, String, int)
	 */
	public void registerPlayer(Player player);

	/**
	 * Updates view when player cashes out
	 * 
	 * @param player the player cashing out
	 * @see SlotMachine#cashOut()
	 */
	public void cashOutPlayer(Player player);

	/**
	 * Updates the view when player adds credits
	 * 
	 * @param player  the player to which credits were added
	 * @param credits the amount of credits added
	 * @see SlotMachine#addCredits(int)
	 */
	public void addCredits(Player player, int credits);

	/**
	 * Updates the view when the players bets are updated
	 * 
	 * @param player the current player
	 * @param amount the amount bet
	 * @param line   the line number bet on
	 * @see SlotMachine#placeBet(int)
	 * @see SlotMachine#placeBet(int, LineNum)
	 */
	public void betUpdated(Player player, int amount, LineNum line);

	/**
	 * Updates the view when the players bets are updated
	 * 
	 * @param player the current player
	 * @param amount the amount bet
	 * @param lines  the set of line numbers bet on
	 * @see SlotMachine#placeBet(int, java.util.Set)
	 * @see SlotMachine#placeBet(int, LineNum...)
	 * 
	 */
	public void betUpdated(Player player, int amount, Collection<LineNum> lines);

	/**
	 * Updates the view each time one of the wheel spins on turn (slot) during a
	 * spin
	 * 
	 * @param wheel   the wheel that was turned
	 * @param turnNum the turn number of the spin
	 * @see SlotMachine#spin(int, int)
	 * @see SlotMachine#spinToWin(int, int)
	 * 
	 */
	public void turnWheel(Wheel wheel, int turnNum);

	/**
	 * Updates the view with the state of the three wheels (and their five lines) at
	 * the completion of a spin.
	 * 
	 * @param spinResult the {@link SpinResult} used to represent the five lines of
	 *                    the wheel at the completion of a spin
	 */
	public void spinComplete(SpinResult spinResult);

	/**
	 * Updates the view with the state and outcome of each line when applying
	 * betting result for each line
	 * 
	 * @param player      the current player
	 * @param hasBet      true if there was a bet placed on the line, otherwise false
	 * @param lineOutcome the resulting outcome for each line (0 for no win /loss)
	 * @param slotLine    the {@link SlotLine} object representing the line
	 * 
	 * @see SlotMachine#applySpinResult(SpinResult)
	 * @see SlotMachine#spinToWin(int, int)
	 */
	public void lineResult(Player player, boolean hasBet, int lineOutcome, SlotLine slotLine);

	/**
	 * Updates the view with the overall outcome (total) when applying betting
	 * results
	 * 
	 * @param player the current player
	 * @param total  the total amount won (0 if no line bet won)
	 * 
	 * @see SlotMachine#applySpinResult(SpinResult)
	 * @see SlotMachine#spinToWin(int, int)
	 * 
	 */
	public void betTotals(Player player, int total);
}