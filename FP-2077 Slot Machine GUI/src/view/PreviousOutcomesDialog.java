package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;



import slotmachine.model.slots.SlotLine;

@SuppressWarnings("serial")
public class PreviousOutcomesDialog extends JDialog implements PropertyChangeListener {
	private String outcome;
	private int uiRows = 0;
	private int uiColumns = 5; 
	
	public PreviousOutcomesDialog(GameFrame gameFrame, GuiCallback callback) {
		super(gameFrame, "Previous Outcomes");
	
		callback.addPropertyChangeListener(this);
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(500, 500));
		this.setVisible(false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName() == GuiCallback.Events.LINE_RESULT) {
			
			ArrayList<Object> LineResultArray = (ArrayList<Object>) evt.getNewValue();
			
			for(int i = 0; i < LineResultArray.size(); i++) {
				HashMap<String, Object> lineresult = (HashMap<String, Object>) LineResultArray.get(i);
				
				boolean hasBet = (boolean) lineresult.get("bet");
				int lineOutcome = (int) lineresult.get("outcome");
				SlotLine slotline = (SlotLine) lineresult.get("slotline");
				
				
				if(hasBet) {
					
					uiRows ++;
					add(new JLabel(slotline.getLineNum().toString()));
					revalidate();
					
					ImageIcon imageIcon = new ImageIcon(((ImageIcon) slotline.getSlot1().getIcon()).getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
					add(new JLabel(imageIcon));
					revalidate();
					
					imageIcon = new ImageIcon(((ImageIcon) slotline.getSlot2().getIcon()).getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
					add(new JLabel(imageIcon));
					revalidate();
					
					imageIcon = new ImageIcon(((ImageIcon) slotline.getSlot3().getIcon()).getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
					add(new JLabel(imageIcon));
					revalidate();
					
					outcome = "No Win";
					if(lineOutcome > 0) {
						outcome = "Win " + lineOutcome + " credits";
					}
					
					add(new JLabel(outcome));
					setMinimumSize(new Dimension(500, 500));
					setLayout(new GridLayout(uiRows, uiColumns));
					revalidate();
				
					repaint();
				}
			}
		}	
	}
}