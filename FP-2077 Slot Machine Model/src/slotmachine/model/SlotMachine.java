package slotmachine.model;

import java.util.Set;

import slotmachine.model.slots.LineNum;
import slotmachine.model.slots.SlotLine;
import slotmachine.model.slots.SpinResult;
import slotmachine.model.slots.WinSettings;
import slotmachine.view.GameCallback;
import slotmachine.view.GameCallbackCollection;

/**
 * The main interface in <b>Further Programming Assignment</b>.
 * <p>
 * This interface should be implemented as
 * {@link slotmachine.model.SlotMachineImpl} which has a two constructors.
 * <pre>public SlotMachineImpl()</pre>
 * <pre>public SlotMachineImpl(Wheel wheel1, Wheel wheel2, Wheel wheel3, WinSettings winSettings)</pre>
 * <p>
 * All the methods in the interface need to be carefully implemented as per the
 * JavaDoc below.
 * 
 * @see slotmachine.view.GameCallbackCollection
 * @see slotmachine.model.Player
 * 
 * @author Ross Nye
 */
public interface SlotMachine extends GameCallbackCollection
{
	/**
	 * Registers a player to the Slot Machine.
	 * <p>
	 * This method should notify <b>all</b> the current callbacks of this event via
	 * the {@link GameCallback#registerPlayer(Player)} method.
	 * 
	 * 
	 * @param id             the id of the {@link Player}
	 * @param name           the name of the {@link Player}
	 * @param initialCredits the initial credits for the {@link Player}
	 * @return the registered {@link Player}
	 * 
	 * @throws IllegalArgumentException in the following cases:
	 *                                  <ul>
	 *                                  <li>the supplied id is empty or null
	 *                                  <li>the supplied name is empty or null
	 *                                  <li>the supplied initialCredits is negative
	 *                                  <li>the supplied id is invalid as its not
	 *                                  the correct format
	 *                                  </ul>
	 * @see slotmachine.model.Player
	 */
	public Player registerPlayer(String id, String name, int initialCredits);

	/**
	 * Cashes out the currently registered player and removes them from the game.
	 * <p>
	 * This method should notify <b>all</b> the current callbacks of this event via
	 * the {@link GameCallback#cashOutPlayer(Player)} method.
	 * <p>
	 * To maintain proper state across the Slot Machine (and all it's callacks) this
	 * method should call {@link #resetBets()}.
	 * 
	 * @return the previously registered {@link Player}
	 * @throws IllegalStateException if there is no registered {@link Player}
	 */
	public Player cashOut();

	/**
	 * Adds credits to the currently registered player.
	 * <p>
	 * This method should notify <b>all</b> the current callbacks of this event via
	 * the {@link GameCallback#addCredits(Player, int)} method.
	 * 
	 * @param credits the credits to add
	 * @throws IllegalStateException    if there is no registered {@link Player}
	 * @throws IllegalArgumentException if the supplied credits is not a positive
	 *                                  amount
	 */
	public void addCredits(int credits) throws IllegalStateException, IllegalArgumentException;

	/**
	 * Places a bet of the specified amount on line #1
	 * <p>
	 * This method should notify <b>all</b> the current callbacks of this event via
	 * the {@link GameCallback#betUpdated(Player, int, LineNum)} method.
	 * <p>
	 * <b>Hint:</b> Avoid code duplication with the overloaded placeBet methods
	 * 
	 * @param amount the amount to be bet
	 * 
	 * @throws IllegalArgumentException in the following cases:
	 *                                  <ul>
	 *                                  <li>the amount is not a positive number
	 *                                  <li>the registered player does not have
	 *                                  enough credits available
	 *                                  <li>there is already a bet for line #1
	 *                                  exceeds the amount
	 *                                  </ul>
	 * @throws IllegalStateException    if there is no registered player
	 */
	public void placeBet(int amount) throws IllegalArgumentException, IllegalStateException;

	/**
	 * Places a bet of the specified amount on the specified line number
	 * <p>
	 * This method should notify <b>all</b> the current callbacks of this event via
	 * the {@link GameCallback#betUpdated(Player, int, LineNum)} method.
	 * <p>
	 * <b>Hint:</b> Avoid code duplication with the overloaded placeBet methods
	 * 
	 * @param amount the amount to be bet
	 * @param line   the line to place the bet on
	 * 
	 * @throws IllegalArgumentException in the following cases:
	 *                                  <ul>
	 *                                  <li>the amount is not a positive number
	 *                                  <li>the registered player does not have
	 *                                  enough credits available
	 *                                  <li>there is already a bet for the specified
	 *                                  line that exceeds the amount
	 *                                  <li>if no line number (null) is supplied
	 *                                  </ul>
	 * @throws IllegalStateException    if there is no registered player
	 */
	void placeBet(int amount, LineNum line) throws IllegalArgumentException, IllegalStateException;

	/**
	 * Places a bet of the specified amount on the specified line numbers
	 * <p>
	 * This method should notify <b>all</b> the current callbacks of this event via
	 * the {@link GameCallback#betUpdated(Player, int, java.util.Collection)}
	 * method.
	 * <p>
	 * <b>Note:</b> This should be an atomic operation, where a bet must be place on
	 * <b>all</b> lines requested or then entire request should fail (and
	 * potentially reset to what they were previously)
	 * <p>
	 * <b>Hint:</b> Avoid code duplication with the overloaded placeBet methods
	 * 
	 * @param amount the amount to be bet on each line
	 * @param lines  the set of line numbers to place the bet on
	 * @throws IllegalArgumentException in the following cases:
	 *                                  <ul>
	 *                                  <li>the amount is not a positive number
	 *                                  <li>the registered player does not have
	 *                                  enough credits available to place a bet on
	 *                                  <b>all</b> of the supplied lines
	 *                                  <li>there is already a bet for <b>any</b> of
	 *                                  the specified lines that exceeds the amount
	 *                                  <li>if no line numbers (null) are supplied
	 *                                  <li>if the set of line numbers is empty
	 *                                  </ul>
	 * @throws IllegalStateException    if there is no registered player
	 */
	void placeBet(int amount, Set<LineNum> lines) throws IllegalArgumentException, IllegalStateException;

	/**
	 * Places a bet of the specified amount on the specified line numbers
	 * <p>
	 * This method should notify <b>all</b> the current callbacks of this event via
	 * the {@link GameCallback#betUpdated(Player, int, java.util.Collection)}
	 * method.
	 * <p>
	 * <b>Note:</b> This should be an atomic operation, where a bet must be place on
	 * <b>all</b> lines requested or then entire request should fail (and
	 * potentially reset to what they were previously)
	 * <p>
	 * <b>Hint:</b> Avoid code duplication with the overloaded placeBet methods
	 * <p>
	 * <b>Hint:</b> Inside the method lines is an array. See
	 * {@link slotmachine.client.SlotMachineTest} for use of this method
	 * 
	 * @param amount the amount to be bet on each line
	 * @param lines  the line numbers to place the bet on
	 * 
	 * @throws IllegalArgumentException in the following cases:
	 *                                  <ul>
	 *                                  <li>the amount is not a positive number
	 *                                  <li>the registered player does not have
	 *                                  enough credits available to place a bet on
	 *                                  <b>all</b> of the supplied lines
	 *                                  <li>there is already a bet for <b>any</b> of
	 *                                  the specified lines that exceeds the amount
	 *                                  <li>if no line numbers (null) are supplied
	 *                                  <li>if the set of line numbers is empty
	 *                                  </ul>
	 * @throws IllegalStateException    if there is no registered player
	 * 
	 */
	public void placeBet(int amount, LineNum... lines) throws IllegalArgumentException, IllegalStateException;

	/**
	 * Resets all the bets for all the line numbers and updates the {@link Player}
	 * bet information.
	 * <p>
	 * This method should notify <b>all</b> the current callbacks of this event via
	 * the {@link GameCallback#betUpdated(Player, int, java.util.Collection)} method
	 */
	public void resetBets();

	/**
	 * Spin the wheels of the slot machine and applies the current bets at
	 * completion.
	 * <p>
	 * With the exception of validating the player and bets (see below) this method
	 * should have <b>exactly</b> the same functionality as separately calling
	 * {@link #spin(int, int)} and {@link #applySpinResult(SpinResult)}.
	 * 
	 * @param turns the number of turns of the wheels
	 * @param delay the delay between each turn
	 * 
	 * @return the {@link SpinResult} that represent the lines at the end of the
	 *         spin
	 * 
	 * @throws IllegalArgumentException if turn less than 1, delay less than 0 or
	 *                                  delay greater than 2000
	 * @throws IllegalStateException    if there is no registered player or valid
	 *                                  bet
	 * 
	 * @see #spin(int, int)
	 * @see #applySpinResult(SpinResult)
	 */
	public SpinResult spinToWin(int turns, int delay) throws IllegalArgumentException, IllegalStateException;

	/**
	 * Spin the wheels of the slot machine. This methods may be run without any
	 * player or betting consequences (and can be used for testing).
	 * <p>
	 * A turn is defined a advancing the slots of a wheel <b>one</b> position. An
	 * new (unseen) slot item will appear as the top of the wheel. The slot
	 * previously at the top moves to the centre and the previous centre slot moves
	 * to the bottom. The previous bottom slot is not longer visible.
	 * <p>
	 * The turns parameter is used to determine how many times each wheel spins. All
	 * wheels must spin <b>at least once</b>. Wheel #1 should turn the same amount
	 * as specified by the turns parameter. Wheel #2 should turn 66% of that figure.
	 * Wheel #3 should turn 33% of the turns parameter.
	 * <p>
	 * All wheels turn at the start of the spin, before Wheel #3 stops, leaving
	 * Wheel #1 and #2 spinning. Next Wheel #2 will stops turning leaving only Wheel
	 * #1 spinning until all the turns are complete.
	 * <p>
	 * Between each turn "set" a delay of the supplied delay (in ms) should occur.
	 * the delay should occur turns - 1 times.
	 * <p>
	 * Refer to the output trace to see spin in action.
	 * <p>
	 * This method should notify <b>all</b> the current callbacks of each wheel turn
	 * via {@link GameCallback#turnWheel(slotmachine.model.slots.Wheel, int)} method
	 * and also of the final state of the three wheels via
	 * {@link GameCallback#spinComplete(SpinResult)}
	 * 
	 * @param turns the number of turns of the wheels
	 * @param delay the delay between each turn
	 * 
	 * @return the {@link SpinResult} that represent the lines at the end of the
	 *         spin
	 * 
	 * @throws IllegalArgumentException if turn less than 1, delay less than 0 or
	 *                                  delay greater than 2000
	 * 
	 * @see #spinToWin(int, int)
	 * @see Thread#sleep(long)
	 */
	public SpinResult spin(int turns, int delay) throws IllegalArgumentException;

	/**
	 * Applies the results of the players bets after the completion of a spin.
	 * <p>
	 * In normal situations this method would be private, but is public to
	 * facilitate testing (both for yourself and in marking).
	 * <p>
	 * This method will examine <b>all</b> the {@link SlotLine} objects from
	 * {@link SpinResult} parameter. Using {@link WinSettings#getWinOdds(SlotLine)}
	 * to determine the outcome (win or loss) of each line.
	 * <p>
	 * This method should notify <b>all</b> the current callbacks of the outcome of
	 * each line (whether bet on or not) via
	 * {@link GameCallback#lineResult(Player, boolean, int, slotmachine.model.slots.SlotLine)}
	 * method.
	 * <p>
	 * The total outcome of the spin's bets should also be notified to the callbacks
	 * via {@link GameCallback#betTotals(Player, int)}
	 * <p>
	 * Prior to this the players current bets should be reset if they can not be
	 * serviced (the player won't have enough credits) after the outcome (total) is
	 * applied. Otherwise the current bets remain unchanged.
	 * 
	 * @param spinResult the {@link SpinResult} created during a spin
	 * @return the total credits won (or 0)
	 * 
	 * @throws NullPointerException if slotDisplay is null
	 * 
	 * @see #spinToWin(int, int)
	 * @see #resetBets()
	 */
	public int applySpinResult(SpinResult spinResult);

}
