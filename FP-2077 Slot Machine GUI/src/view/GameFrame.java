package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JFrame;

import model.ViewModel;
import slotmachine.model.SlotMachine;
import slotmachine.model.SlotMachineImpl;
import slotmachine.model.slots.Wheel;
import slotmachine.model.slots.WheelImpl;
import slotmachine.model.slots.WinSettings;
import slotmachine.model.slots.WinSettingsImpl;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	public GameFrame() {
		
		super("FP-2077 Slot Machine");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(600, 700));
		setLocationRelativeTo(null);
		
		Wheel wheel1 = WheelImpl.createWheel(1);
		Wheel wheel2 = WheelImpl.createWheel(2);
		Wheel wheel3 = WheelImpl.createWheel(3);
		WinSettings winsettings = new WinSettingsImpl();
		
		//GUI callback object for use in views
		GuiCallback callback = new GuiCallback(wheel1, wheel2, wheel3);
		
	
		// Instantiate the Slot Machine Model
		SlotMachine sm = new SlotMachineImpl(wheel1, wheel2, wheel3, winsettings);
		
		
		// Instantiate the View Model
		ViewModel viewmodel = new ViewModel(sm, callback);
		
		// Add our GUI callback
		sm.addCallback(callback);
		
		
		// Create Dialogs and pass in to methods that use them. NOTE: Re visit this for A3		
		JDialog registerDialog = new RegisterPlayerDialog(this, viewmodel, callback);
		JDialog aboutDialog = new AboutDialog(this);
		JDialog addCreditsDialog = new AddCreditsDialog(this, viewmodel);
		JDialog cashoutDialog = new CashoutDialog(this, viewmodel, callback);
		JDialog spinResultDialog = new SpinResultDialog(this, viewmodel, callback);
		JDialog previousOutcomes = new PreviousOutcomesDialog(this, callback);
		JDialog winOdds = new WinOddsDialog(this);
		
		HashMap<String, JDialog> dialogs = new HashMap<>();
		dialogs.put("registerPlayer", registerDialog);
		dialogs.put("about", aboutDialog);
		dialogs.put("addCredits", addCreditsDialog);
		dialogs.put("cashout", cashoutDialog);
		dialogs.put("spinResult", spinResultDialog);
		dialogs.put("previousOutcomes", previousOutcomes);
		dialogs.put("winOdds", winOdds);
		
		setLayout(new BorderLayout());
	
		setJMenuBar(new MenuBar(dialogs, viewmodel, callback));
		
		add(new CenterPanel(dialogs, callback, viewmodel), BorderLayout.CENTER);

		add(new StatusBar(callback), BorderLayout.SOUTH);	
	}
}