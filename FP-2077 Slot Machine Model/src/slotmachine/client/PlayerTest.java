package slotmachine.client;

import slotmachine.model.SlotMachine;
import slotmachine.model.SlotMachineImpl;
import slotmachine.model.slots.LineNum;
import slotmachine.model.slots.WheelImpl;
import slotmachine.view.ConsoleLoggerCallback;

public class PlayerTest {

	public static void main(String[] args) {
		
		SlotMachine sm = new SlotMachineImpl();
		
		sm.addCallback(new ConsoleLoggerCallback());
		
		sm.registerPlayer("12345A", "Wally", 50);


		try
		{
//		sm.addCredits(50);
//			sm.cashOut();
//			sm.spin(1, 900);
//			sm.placeBet();
		//	sm.placeBet(50);
		//	sm.spinToWin(1, 23);
//			sm.spinToWin(2, 4);
			sm.placeBet(26, LineNum.LINE1);
//			sm.placeBet(11, LineNum.LINE1);
//			//sm.placeBet(5, LineNum.LINE1, LineNum.LINE2, LineNum.LINE3, LineNum.LINE4, LineNum.LINE5);
//			//sm.placeBet(20, LineNum.LINE5);
//			//sm.placeBet(100, LineNum.LINE5, LineNum.LINE3);
			sm.spinToWin(2, 2);
//			sm.spinToWin(103, 2);
////			
////			sm.resetBets();
////			
////			sm.placeBet(20);
////			sm.placeBet(10, LineNum.LINE2, LineNum.LINE3);
////			sm.spinToWin(10, 5);
////			
//			//sm.addCredits(10);
////			sm.spinToWin(20, 50);
		}
		catch (Exception e)
		{
//			// Demo catching exceptions and use of ConsoleLoggerCallback.LOGGER
//			// You can use the Logger in this way to help you debug your code
//			// but you should make use of the callback methods in your Slot Machine
//			
			ConsoleLoggerCallback.LOGGER.warning(String.format("Exception thrown by Slot Machine: %s", e.getMessage()));
		}
	}

}
