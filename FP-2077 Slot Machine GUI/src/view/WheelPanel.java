package view;

import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import slotmachine.model.slots.SlotItem;
import slotmachine.model.slots.Wheel;

@SuppressWarnings("serial")
public class WheelPanel extends JPanel implements PropertyChangeListener {

	private Icon w1TopIcon, w1CentreIcon, w1BottomIcon, w2TopIcon, w2CentreIcon, w2BottomIcon, w3TopIcon, w3CentreIcon, w3BottomIcon;
	
	public WheelPanel(GuiCallback callback) {
		
		callback.addPropertyChangeListener(this);

		// Wheel 1
		w1TopIcon = SlotItem.getRandomSlotItem().getIcon();
		w1CentreIcon = SlotItem.getRandomSlotItem().getIcon();
		w1BottomIcon = SlotItem.getRandomSlotItem().getIcon();
		
		// Wheel 2
		w2TopIcon = SlotItem.getRandomSlotItem().getIcon();
		w2CentreIcon = SlotItem.getRandomSlotItem().getIcon();
		w2BottomIcon = SlotItem.getRandomSlotItem().getIcon();
		
		// Wheel 3
		w3TopIcon = SlotItem.getRandomSlotItem().getIcon();
		w3CentreIcon = SlotItem.getRandomSlotItem().getIcon();
		w3BottomIcon = SlotItem.getRandomSlotItem().getIcon();
	}
		
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		// Each squares width and height, 1 third of available for each
        int width = getWidth()/3 ;
        int height = getHeight()/3;
        
        int row1 = 0;
        int row2 = width;
        int row3 = width + width;
        
        int column1 = 0;
        int column2 = height;
        int column3 = height + height;
        
        // Wheel 1
        g.drawImage(((ImageIcon) w1TopIcon).getImage(), row1, column1, width, height, this);
        g.drawImage(((ImageIcon) w1CentreIcon).getImage(), row1, column2, width, height, this);
        g.drawImage(((ImageIcon) w1BottomIcon).getImage(), row1, column3, width, height, this);
        
        // Wheel 2 
        g.drawImage(((ImageIcon) w2TopIcon).getImage(), row2, column1, width, height, this);
        g.drawImage(((ImageIcon) w2CentreIcon).getImage(), row2, column2, width, height, this);
        g.drawImage(((ImageIcon) w2BottomIcon).getImage(), row2, column3, width, height, this);
        
        // Wheel 3
        g.drawImage(((ImageIcon) w3TopIcon).getImage(), row3, column1, width, height, this);
        g.drawImage(((ImageIcon) w3CentreIcon).getImage(), row3, column2, width, height, this);
        g.drawImage(((ImageIcon) w3BottomIcon).getImage(), row3, column3, width, height, this);
	}
	
	
	// Update slots when wheel is spinning
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			
			if(evt.getPropertyName() == GuiCallback.Events.UPDATE_WHEEL) {
				
				@SuppressWarnings("unchecked")
				HashMap<String, Wheel> wheels = (HashMap<String, Wheel>) evt.getNewValue();
				
				// Wheel 1
				w1TopIcon = wheels.get("Wheel1").getTopSlot().getIcon();
				w1CentreIcon = wheels.get("Wheel1").getCentreSlot().getIcon();
				w1BottomIcon = wheels.get("Wheel1").getBottomSlot().getIcon();
				
				// Wheel 2
				w2TopIcon = wheels.get("Wheel2").getTopSlot().getIcon();
				w2CentreIcon = wheels.get("Wheel2").getCentreSlot().getIcon();
				w2BottomIcon = wheels.get("Wheel2").getBottomSlot().getIcon();
				
				// Wheel 3
				w3TopIcon = wheels.get("Wheel3").getTopSlot().getIcon();
				w3CentreIcon = wheels.get("Wheel3").getCentreSlot().getIcon();
				w3BottomIcon = wheels.get("Wheel3").getBottomSlot().getIcon();
				
				repaint();
			}
		}
}
