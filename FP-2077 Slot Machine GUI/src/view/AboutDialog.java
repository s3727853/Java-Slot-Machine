package view;

import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import controller.GenericDialogActionListener;
import model.Actions;

@SuppressWarnings("serial")
public class AboutDialog extends JDialog {
	
	private JLabel author, studentID;
	private JTextPane authorText, idText;
	private JButton closeDialog;
	private ActionListener genericDialogListener;

	public AboutDialog(GameFrame gameFrame) {	
		super(gameFrame, "About Game");
		genericDialogListener = new GenericDialogActionListener(this);
		setLayout(new GridLayout(3,2));
		
		author = new JLabel("Author:");
		authorText = new JTextPane();
		authorText.setText("Jack Edwards");
		authorText.setEditable(false);
		authorText.setBackground(getForeground());
		
		studentID = new JLabel("Student Number:");
		idText = new JTextPane();
		idText.setText("S3727853");
		idText.setEditable(false);
		idText.setBackground(getForeground());
		
		closeDialog = new JButton("Ok");
		
		// Close(hide) dialog
		closeDialog.setActionCommand(Actions.HIDE_DIALOG.getAction());
		closeDialog.addActionListener(genericDialogListener);
	
		add(author);
		add(authorText);
		
		add(studentID);
		add(idText);
		
		add(closeDialog);

		setMinimumSize(new Dimension(250,100));
	}
}