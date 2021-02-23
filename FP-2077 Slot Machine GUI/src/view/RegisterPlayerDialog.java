package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import controller.RegisterPlayerActionListener;
import model.Actions;

import model.ViewModel;

@SuppressWarnings("serial")
public class RegisterPlayerDialog extends JDialog{
	
	private JLabel id, name, credits;
	private JButton submit, cancel;	
	private JFormattedTextField idInput, nameInput, pointsInput;
	private HashMap<String,JFormattedTextField> inputs = new HashMap<>();
	JPanel textInputs, buttons;
	
	public RegisterPlayerDialog(GameFrame gameFrame, ViewModel model, GuiCallback callback) {	
		super(gameFrame, "Register Player");
		
		textInputs = new JPanel();
		textInputs.setLayout(new GridLayout(5,2,1,1));
		buttons = new JPanel();
		
		setLayout(new BorderLayout());
		//setLayout(new GridLayout(5,2,1,1));

		id = new JLabel("Player ID");
		idInput = new JFormattedTextField();
		
		name = new JLabel("Player Name");
		nameInput = new JFormattedTextField();
		
		credits = new JLabel("Initial Credits");
		pointsInput = new JFormattedTextField();
		
		submit = new JButton("Submit");
		cancel = new JButton("Cancel");
		
		textInputs.add(id);
		textInputs.add(idInput);
		
		inputs.put("IDinput",idInput);
		
		textInputs.add(name);
		textInputs.add(nameInput);
		
		inputs.put("NameInput", nameInput);
		
		textInputs.add(credits);
		textInputs.add(pointsInput);
		
		inputs.put("PointsInput", pointsInput);
		
		textInputs.setMinimumSize(new Dimension(250, 180));
		add(textInputs, BorderLayout.NORTH);
		
		add(submit, BorderLayout.SOUTH);
		add(cancel, BorderLayout.SOUTH);
		
		// Avatar combo box
		JToolBar avatars = new AvatarToolbar(callback);
		
		buttons.add(submit);
		buttons.add(cancel);
		
		add(avatars, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		
		RegisterPlayerActionListener dl = new RegisterPlayerActionListener(model, this, inputs);
		submit.setActionCommand(Actions.SUBMIT_DIALOG.getAction());
		submit.addActionListener(dl);
		
		cancel.setActionCommand(Actions.HIDE_DIALOG.getAction());
		cancel.addActionListener(dl);

		setMinimumSize(new Dimension(420,250));
	}
}