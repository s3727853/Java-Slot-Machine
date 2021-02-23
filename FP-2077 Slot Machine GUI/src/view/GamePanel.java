package view;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JPanel;

import model.ViewModel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	public GamePanel(ViewModel viewmodel, GuiCallback callback, HashMap<String, JDialog> dialogs) {
		setLayout(new BorderLayout());
		
		add(new WheelPanel(callback), BorderLayout.CENTER);
		add(new BetPanel(viewmodel, callback), BorderLayout.NORTH);
		add(new SpinButton("Spin", callback, viewmodel), BorderLayout.SOUTH);
		
		add(new LeftWheelLegend(), BorderLayout.WEST);
	}
}