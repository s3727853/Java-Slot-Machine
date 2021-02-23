package slotmachine.model;

/**
 * Represents a player in the Slot Machine in <b>Further Programming
 * Assignment</b>
 * <p>
 * Is to be implemented as {@link slotmachine.model.PlayerImpl}.
 * <p>
 * Each player has an id and a name, both of which cannot be changed once
 * assigned. The player has a <em>credits</em> balance which is used in placing
 * bets within the game.
 * 
 * @see slotmachine.model.SlotMachine
 * @see slotmachine.model.SlotMachine#registerPlayer(String, String, int)
 * 
 * @author Ross Nye
 */
public interface Player
{

	/**
	 * Simple getter for player's id
	 * 
	 * @return the player's id
	 */
	public String getId();

	/**
	 * Simple getter for player's name
	 * 
	 * @return the player's name
	 */
	public String getName();

	/**
	 * Simple getter for player's initial credits
	 * 
	 * @return the player's initial credits
	 */
	int getInitalCredits();

	/**
	 * Simple getter for player's current credits
	 * 
	 * @return the player's current credits
	 */
	public int getCredits();

	/**
	 * Returns the available total credits for a player, which excludes any amount
	 * currently bet.
	 * <p>
	 * <b>Hint:</b> If the player doesn't have a current bet this method should
	 * return the current credit, otherwise the amount returned should exclude the
	 * bet amount.
	 * 
	 * @return the player's current available credits.
	 */
	public int getAvailableCredits();

	/**
	 * A mutator method to <b>add</b> to the current credits
	 * 
	 * @param credits credits to be added
	 * @throws IllegalArgumentException if credits are not positive number
	 */
	public void addCredits(int credits);

	/**
	 * Simple getter method for the current amount bet by the player.
	 * 
	 * <p>
	 * <b>Hint:</b>This amount should total all amounts for each
	 * {@link slotmachine.model.slots.SlotLine} which are maintained in the
	 * {@link slotmachine.model.SlotMachine}.
	 * 
	 * @return the current amount that is bet by the player
	 */
	public int getBet();

	/**
	 * Simple setter method for the current amount bet by the player.
	 * <p>
	 * <b>Hint:</b>This amount should total all amounts for each
	 * {@link slotmachine.model.slots.SlotLine} which are maintained in the
	 * {@link slotmachine.model.SlotMachine}. The amount passed in as a parameter
	 * should just be the current total bets for all lines.
	 * 
	 * @param bet the amount bet
	 * @throws IllegalArgumentException if bet is not positive number
	 * 
	 */
	public void setBet(int bet);

	/**
	 * Resets the amount currently bet by the player to zero
	 */
	public void resetBet();

	/**
	 * A mutator method that applies the result of all the wins from a single spin
	 * of the {@link slotmachine.model.SlotMachine}.
	 * <p>
	 * If the winAmount is greater than 0 then the player wins the winAmount and
	 * their credits should be adjusted. Otherwise the player has lost the amount
	 * currently bet.
	 * <p>
	 * <b>Note:</b> Bets should not be automatically reset here.
	 * 
	 * @param winAmount the amount won from the spin
	 */
	public void applyWin(int winAmount);

	/**
	 * Returns a {@link String} representing the {@link Player} in format shown in
	 * the examples below and seen in the output trace.
	 * 
	 * <pre>
	 * Player ABC, Me, credits 30, bet 0, available 30
	 * </pre>
	 * 
	 * <pre>
	 * Player ABC, Me, credits 150, bet 60, available 90
	 * </pre>
	 * 
	 * @return a {@link String} representing the {@link Player}
	 */
	@Override
	public String toString();

}
