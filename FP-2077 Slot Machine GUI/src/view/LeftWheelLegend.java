package view;


import java.awt.Image;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class LeftWheelLegend extends JPanel {
	
	private ImageIcon imageIcon;
	JLabel line1, line2, line3, line4, line5;
	
	public LeftWheelLegend() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		imageIcon = new ImageIcon(new ImageIcon("images/arrow-135.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
	
		line4 = new JLabel("Line 4",  imageIcon, SwingConstants.LEFT);
		add(line4);

		imageIcon = new ImageIcon(new ImageIcon("images/arrow-90.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		line2 = new JLabel("Line 2",  imageIcon, SwingConstants.LEFT);
		add(line2);
		
		add(Box.createVerticalGlue());
	
		imageIcon = new ImageIcon(new ImageIcon("images/arrow-90.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		line1 = new JLabel("Line 1",  imageIcon, SwingConstants.LEFT);
		add(line1);
		
		add(Box.createVerticalGlue());
		
		imageIcon = new ImageIcon(new ImageIcon("images/arrow-90.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		line3 = new JLabel("Line 3",  imageIcon, SwingConstants.LEFT);
		add(line3);
	
		imageIcon = new ImageIcon(new ImageIcon("images/arrow-45.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		line5 = new JLabel("Line 5",  imageIcon, SwingConstants.LEFT);
		add(line5);
	}
}