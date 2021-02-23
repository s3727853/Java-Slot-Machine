package client;

import javax.swing.SwingUtilities;
import view.GameFrame;


public class GuiSlotMachine {

	
	public static void main(String[] args) {

		new Thread() {
			@Override
			public void run() {
		
			}
		}.start();
		
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				new GameFrame().setVisible(true);
			}
			
		});
		

	}
}
