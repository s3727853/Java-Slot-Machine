package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import controller.AddCreditsActionListener;
import model.Actions;
import model.ViewModel;

@SuppressWarnings("serial")
public class AddCreditsDialog extends JDialog {
	
	private JLabel inputLabel;
	private JFormattedTextField creditInput;
	private JButton exitDialog, addCredits;

	public AddCreditsDialog(GameFrame gameFrame, ViewModel model) {	
		super(gameFrame, "Add Credits");
		
		setLayout(new GridLayout(2,2));
		
		inputLabel = new JLabel("Credits: ");
		creditInput = new BetInputTextField();
		creditInput.setEditable(true);
		addCredits = new JButton("Add Credits");
		exitDialog = new JButton("Cancel");
		
		AddCreditsActionListener al = new AddCreditsActionListener(model, this, creditInput);
		addCredits.setActionCommand(Actions.SUBMIT_DIALOG.getAction());
		addCredits.addActionListener(al);
		
		exitDialog.setActionCommand(Actions.HIDE_DIALOG.getAction());
		exitDialog.addActionListener(al);
		
		add(inputLabel);
		add(creditInput);
		add(addCredits);
		add(exitDialog);

		pack();
	}
}