package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.GenericDialogActionListener;
import model.Actions;
import model.ViewModel;


@SuppressWarnings("serial")
public class SpinResultDialog extends JDialog implements PropertyChangeListener {

	private JLabel lineTitle, resultTitle, betTitle, line1, line2, line3, line4, line5, result1, result2, result3, result4, result5, bet1, bet2, bet3, bet4, bet5;
	private JButton close;
	private ArrayList<JLabel> resultLabels, betLabels;
	private String bet, outcome;
	
	public SpinResultDialog(GameFrame gameFrame, ViewModel model, GuiCallback callback) {
		super(gameFrame, "Spin Result");
		
		setLayout(new GridLayout(7,3));
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(300, 200));
		
		callback.addPropertyChangeListener(this);
		
		lineTitle = new JLabel("Line Number");
		
		betTitle = new JLabel("Bet");

		resultTitle = new JLabel("Result");
		
		close = new JButton("Close");
		close.setActionCommand(Actions.HIDE_DIALOG.getAction());
		close.addActionListener(new GenericDialogActionListener(this));
		
		resultLabels = new ArrayList<>();
		betLabels = new ArrayList<>();
		
		line1 = new JLabel("1", SwingConstants.CENTER);
		line2 = new JLabel("2", SwingConstants.CENTER);
		line3 = new JLabel("3", SwingConstants.CENTER);
		line4 = new JLabel("4", SwingConstants.CENTER);
		line5 = new JLabel("5", SwingConstants.CENTER);
		
		result1 = new JLabel("result");
		result2 = new JLabel("result");
		result3 = new JLabel("result");
		result4 = new JLabel("result");
		result5 = new JLabel("result");
		
		resultLabels.add(result1);
		resultLabels.add(result2);
		resultLabels.add(result3);
		resultLabels.add(result4);
		resultLabels.add(result5);
		
		bet1 = new JLabel();
		bet2 = new JLabel();
		bet3 = new JLabel();
		bet4 = new JLabel();
		bet5 = new JLabel();
		
		betLabels.add(bet1);
		betLabels.add(bet2);
		betLabels.add(bet3);
		betLabels.add(bet4);
		betLabels.add(bet5);
		
		add(lineTitle);
		add(betTitle);
		add(resultTitle);
		add(line1);
		add(bet1);
		add(result1);
		add(line2);
		add(bet2);
		add(result2);
		add(line3);
		add(bet3);
		add(result3);
		add(line4);
		add(bet4);
		add(result4);
		add(line5);
		add(bet5);
		add(result5);
		
		add(close);
		this.setVisible(false);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	
		// If I was to do this again it would be better to do this outside of the view and pass in relevant strings
		if(evt.getPropertyName() == GuiCallback.Events.LINE_RESULT) {
			
			ArrayList<Object> LineResultArray = (ArrayList<Object>) evt.getNewValue();
			
			for(int i = 0; i < LineResultArray.size(); i++) {
				HashMap<String, Object> lineresult = (HashMap<String, Object>) LineResultArray.get(i);
				
				boolean hasBet = (boolean) lineresult.get("bet");
				int lineOutcome = (int) lineresult.get("outcome");
			
				if(hasBet == true && lineOutcome == 0) {
					bet = "Bet Placed";
					outcome = "No Win";
				}
				
				if(!hasBet) {
					bet = "No Bet";
					outcome = "";
				}
				if(hasBet == true && lineOutcome > 0) {
					bet = "Bet Placed";
					outcome = "Win! " + lineOutcome + " credits";
				}
				resultLabels.get(i).setText(outcome);
				betLabels.get(i).setText(bet);
			}
			
			this.setVisible(true);
		}	
	}
}