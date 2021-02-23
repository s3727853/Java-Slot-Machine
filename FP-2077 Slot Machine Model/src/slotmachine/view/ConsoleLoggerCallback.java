package slotmachine.view;

import java.util.Collection;
import java.util.Iterator;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import slotmachine.model.Player;
import slotmachine.model.slots.LineNum;
import slotmachine.model.slots.SlotLine;
import slotmachine.model.slots.SpinResult;
import slotmachine.model.slots.Wheel;

/**
 * An implementation of GameCallback which uses a Logger to log game events to
 * the console.
 * 
 * <p>
 * <b>Important!</b> DO NOT EDIT THE STATIC BLOCK THAT SETS UP THE LOGGER OR
 * IT'S DECLARATION!
 * 
 * <p>
 * <b>Note:</b> Logging message format should be consistent with the output
 * trace.
 * 
 * 
 * @see slotmachine.view.GameCallback
 * @see slotmachine.view.GameCallbackCollection
 *
 * @author YOU!
 */
public class ConsoleLoggerCallback implements GameCallback
{
	/**
	 * A static {@link java.util.logging.Logger} object used for logging information
	 * (in this case to the console)
	 * 
	 * DO NOT EDIT!
	 */
	public static final Logger LOGGER;

	static
	{
		// DO NOT EDIT THIS STATIC BLOCK!!

		// Creating consoleHandler, add it and set the format and log levels.
		LOGGER = Logger.getLogger(ConsoleLoggerCallback.class.getName());
		LOGGER.setLevel(Level.FINER);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter()
		{
			@Override
			public synchronized String format(LogRecord lr)
			{
				return String.format("[%s] %s %s%n%s%n%n", lr.getLevel().getLocalizedName(), lr.getSourceClassName(),
						lr.getSourceMethodName(), lr.getMessage());
			}
		});
		handler.setLevel(Level.FINER);
		LOGGER.addHandler(handler);
		LOGGER.setUseParentHandlers(false);
	}

	@Override
	public void registerPlayer(Player player)
	{
		LOGGER.log(Level.INFO, String.format("Added player %s", player));
	}

	@Override
	public void cashOutPlayer(Player player)
	{
		LOGGER.log(Level.INFO, String.format("Cash out player %s%n%s", player.getId(), player));
	}

	@Override
	public void addCredits(Player player, int credits)
	{
		LOGGER.log(Level.INFO, String.format("%d credits added to player %s%n%s", credits, player.getId(), player));
	}

	@Override
	public void betUpdated(Player player, int amount, Collection<LineNum> lines)
	{

		StringBuilder sb = new StringBuilder();
		Iterator<LineNum> itr = lines.iterator();

		while (itr.hasNext())
		{
			sb.append(itr.next());
			if (itr.hasNext())
				sb.append(", ");
		}

		betUpdated(player, amount, sb.toString());
	}

	@Override
	public void betUpdated(Player player, int amount, LineNum line)
	{
		betUpdated(player, amount, line.toString());
	}

	private void betUpdated(Player player, int amount, String lineString)
	{
		LOGGER.log(Level.INFO, String.format("Bet updated for %s to %d for %s%n%s", player.getId(), amount, lineString, player));
	}

	@Override
	public void turnWheel(Wheel wheel, int turnNum)
	{
		LOGGER.log(Level.FINE, String.format("Turn #%03d %s", turnNum, wheel));

	}
	

	@Override
	public void lineResult(Player player, boolean hasBet, int lineOutcome, SlotLine slotLine)
	{
		String lineBetInfo =  !hasBet ? "No Bet" : 
			((lineOutcome > 0) ? String.format("Win %3d", lineOutcome) : "Loss");
		
		LOGGER.log(Level.FINE, String.format("%s %-10s", slotLine, lineBetInfo));

	}

	@Override
	public void spinComplete(SpinResult spinResult)
	{
		LOGGER.log(Level.INFO, spinResult.toString());
	}
	
	@Override
	public void betTotals(Player player, int total)
	{
		LOGGER.log(Level.INFO, String.format("Player %s %s%n%s", player.getId(),
				((total > 0) ? String.format("won %d credits", total) : "did not win"), player));
		
	}
}
