package view;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JPanel;

import model.ViewModel;

@SuppressWarnings("serial")
public class CenterPanel extends JPanel {
	
	public CenterPanel(HashMap<String, JDialog> dialogs, GuiCallback callback, ViewModel viewmodel) {
		
		setLayout(new BorderLayout());
		
		add(new ToolBar(dialogs, callback, viewmodel), BorderLayout.NORTH);
		
		add(new GamePanel(viewmodel, callback, dialogs), BorderLayout.CENTER);
		
		add(new PlayerPanel(callback), BorderLayout.SOUTH);
	}
}
