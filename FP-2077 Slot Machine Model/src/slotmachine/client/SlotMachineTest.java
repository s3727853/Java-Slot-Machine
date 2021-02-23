package slotmachine.client;

import slotmachine.model.SlotMachine;
import slotmachine.model.SlotMachineImpl;
import slotmachine.model.slots.LineNum;
import slotmachine.view.ConsoleLoggerCallback;

/**
 * Supplied supporting class used in the <b>Further Programming Assignment</b>
 * <p>
 * This class is used to run simple tests of the Slot Machine. You're encouraged
 * to write your own, perhaps based on this class, but leave this intact as 
 * it was what was used to produce the output trace.
 * 
 * @author Ross Nye
 *
 */
public class SlotMachineTest
{

	public static void main(String[] args)
	{
		SlotMachine sm = new SlotMachineImpl();
		
		sm.addCallback(new ConsoleLoggerCallback());

		sm.registerPlayer("12345A", "Wally", 80);

		sm.addCredits(10);
		
		try
		{
			sm.placeBet(5, LineNum.LINE1);
			sm.placeBet(10, LineNum.LINE2);
			sm.placeBet(15, LineNum.LINE4, LineNum.LINE5);
			sm.spinToWin(10, 100);
			
			sm.resetBets();
			
			sm.placeBet(20);
			sm.placeBet(10, LineNum.LINE2, LineNum.LINE3);
			sm.spinToWin(10, 100);
			
			sm.spinToWin(20, 50);
		}
		catch (Exception e)
		{
			// Demo catching exceptions and use of ConsoleLoggerCallback.LOGGER
			// You can use the Logger in this way to help you debug your code
			// but you should make use of the callback methods in your Slot Machine
			
			ConsoleLoggerCallback.LOGGER.warning(String.format("Exception thrown by Slot Machine: %s", e.getMessage()));
		}
		
		sm.cashOut();
		
		try
		{
			// testing invalid player id - expected to fail and throw exception
			sm.registerPlayer("invalid", "Odlaw", 10);
		}
		catch (Exception e)
		{
			ConsoleLoggerCallback.LOGGER.info(String.format("Exception thrown (as expected) by Slot Machine: %s", e.getMessage()));
		}

	}

}
