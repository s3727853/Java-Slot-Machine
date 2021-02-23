package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import controller.GenericDialogActionListener;
import controller.RegisterPlayerActionListener;
import controller.SpinParamActionListener;
import model.Actions;
import model.SpinParams;
import model.ViewModel;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	
	// As there is little functionality in the menu I will create it all in this class
	private JMenu gameMenu, aboutMenu, setNumTurns, setTurnDelay;
	private JMenuItem registerPlayer, exit, about, spin, winOdds;
	private JCheckBoxMenuItem quickCheck, shortCheck, defaultCheck, longCheck, fastCheck, defaultDelayCheck, slowCheck, verySlowCheck, showPreviousOutcomes;
	private JDialog registerDialog, aboutDialog, previousOutcomesDialog, winOddsDialog;
	private ActionListener registerDialogListener, spinParamListener, aboutListener, previousOutcomesListener, winOddsListener;
	
	public MenuBar(HashMap<String, JDialog> dialogs, ViewModel model, GuiCallback callback) {
		super();
		registerDialog = dialogs.get("registerPlayer");
		aboutDialog = dialogs.get("about");
		previousOutcomesDialog = dialogs.get("previousOutcomes");
		winOddsDialog = dialogs.get("winOdds");
		
		aboutListener = new GenericDialogActionListener(aboutDialog);
		previousOutcomesListener = new GenericDialogActionListener(previousOutcomesDialog);
		registerDialogListener = new RegisterPlayerActionListener(registerDialog, model);
		spinParamListener = new SpinParamActionListener(model);
		winOddsListener = new GenericDialogActionListener(winOddsDialog);
		
		// Menus Top Level
		gameMenu = new JMenu("Game");
		aboutMenu = new JMenu("About");
		
		// Game Menu items
		registerPlayer = new JMenuItem("Register New Player", KeyEvent.VK_R);
		gameMenu.add(registerPlayer);
		
		
		// Sub menu number of turns
		setNumTurns = new JMenu("Number of turns");
		
		quickCheck = new SpinParameterCheckBox("Quick - 5 Turns", SpinParams.FAST);
		quickCheck.setActionCommand(Actions.SET_TURNS.getAction());
		quickCheck.addActionListener(spinParamListener);
		
		shortCheck = new SpinParameterCheckBox("Short - 10 Turns", SpinParams.DEFAULT);
		shortCheck.setActionCommand(Actions.SET_TURNS.getAction());
		shortCheck.addActionListener(spinParamListener);
		
		defaultCheck = new SpinParameterCheckBox("Default - 20 Turns", SpinParams.SLOW);
		defaultCheck.setActionCommand(Actions.SET_TURNS.getAction());
		defaultCheck.addActionListener(spinParamListener);
		defaultCheck.setSelected(true);
		
		longCheck = new SpinParameterCheckBox("Long - 50 Turns", SpinParams.VERY_SLOW);
		longCheck.setActionCommand(Actions.SET_TURNS.getAction());
		longCheck.addActionListener(spinParamListener);
		
		
		ButtonGroup checkBoxGroupTurns = new ButtonGroup();
		checkBoxGroupTurns.add(quickCheck);
		checkBoxGroupTurns.add(shortCheck);
		checkBoxGroupTurns.add(defaultCheck);
		checkBoxGroupTurns.add(longCheck);
		
		setNumTurns.add(quickCheck);
		setNumTurns.add(shortCheck);
		setNumTurns.add(defaultCheck);
		setNumTurns.add(longCheck);
		
		// Sub menu Turn Delay
		setTurnDelay = new JMenu("Turn Delay");
		
		fastCheck = new SpinParameterCheckBox("Fast - 0.05 Seconds", SpinParams.FAST);
		fastCheck.setActionCommand(Actions.SET_DELAY.getAction());
		fastCheck.addActionListener(spinParamListener);
		
		defaultDelayCheck = new SpinParameterCheckBox("Default - 0.1 Second", SpinParams.DEFAULT);
		defaultDelayCheck.setActionCommand(Actions.SET_DELAY.getAction());
		defaultDelayCheck.addActionListener(spinParamListener);
		defaultDelayCheck.setSelected(true);
		
		slowCheck = new SpinParameterCheckBox("Slow - 0.25 Seconds", SpinParams.SLOW);
		slowCheck.setActionCommand(Actions.SET_DELAY.getAction());
		slowCheck.addActionListener(spinParamListener);
		
		verySlowCheck = new SpinParameterCheckBox("Long - 1 Second", SpinParams.VERY_SLOW);
		verySlowCheck.setActionCommand(Actions.SET_DELAY.getAction());
		verySlowCheck.addActionListener(spinParamListener);
		
		
		ButtonGroup checkBoxGroupDelay = new ButtonGroup();
		checkBoxGroupDelay.add(fastCheck);
		checkBoxGroupDelay.add(defaultDelayCheck);
		checkBoxGroupDelay.add(slowCheck);
		checkBoxGroupDelay.add(verySlowCheck);
		
		setTurnDelay.add(fastCheck);
		setTurnDelay.add(defaultDelayCheck);
		setTurnDelay.add(slowCheck);
		setTurnDelay.add(verySlowCheck);
		
		gameMenu.add(setNumTurns);
		gameMenu.add(setTurnDelay);
		
		gameMenu.setMnemonic(KeyEvent.VK_G);
		add(gameMenu);
		
		// Show / Hide previous outcomes menu item(check box)
		showPreviousOutcomes = new JCheckBoxMenuItem("Show Previous Outcomes");
		showPreviousOutcomes.setActionCommand(Actions.CHECKBOX.getAction());
		showPreviousOutcomes.addActionListener(previousOutcomesListener);
		showPreviousOutcomes.setMnemonic(KeyEvent.VK_S);
		gameMenu.add(showPreviousOutcomes);
		
		// Show win Odds dialog
		winOdds = new JMenuItem("Show win odds", KeyEvent.VK_W);
		winOdds.setActionCommand(Actions.SHOW_DIALOG.getAction());
		winOdds.addActionListener(winOddsListener);
		gameMenu.add(winOdds);
		
		// Spin Button
		spin = new SpinMenuItem(callback, model);
		spin.setMnemonic(KeyEvent.VK_S);
		gameMenu.add(spin);
		
		// Exit
		exit = new JMenuItem("Exit", KeyEvent.VK_E);
		gameMenu.add(exit);
		
		// About Menu items
		about = new JMenuItem("Author", KeyEvent.VK_A);
		aboutMenu.add(about);
		aboutMenu.setMnemonic(KeyEvent.VK_A);
		add(aboutMenu);
		
		// Register Dialog
		registerPlayer.setActionCommand(Actions.SHOW_DIALOG.getAction());
		registerPlayer.addActionListener(registerDialogListener);
		
		// About Dialog
		about.setActionCommand(Actions.SHOW_DIALOG.getAction());
		about.addActionListener(aboutListener);
		
		// Exit Game. Note I am using a dialog listener that has system.exit built in. In a larger app I should probably have a menu listener for this.
		exit.setActionCommand(Actions.EXIT_PROGRAM.getAction());
		exit.addActionListener(aboutListener);
	}
}