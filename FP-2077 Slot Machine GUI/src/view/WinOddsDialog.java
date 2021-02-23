package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.GenericDialogActionListener;
import model.Actions;
import slotmachine.model.slots.SlotItem;

@SuppressWarnings("serial")
public class WinOddsDialog extends JDialog {

	private JLabel intro;
	JPanel slotsPanel;
	private JButton closeBtn;
	private ActionListener genericDialogListener;
	
	public WinOddsDialog(GameFrame gameframe) {
		super(gameframe, "Winning Odds");
		
		intro = new JLabel("Sample of winning slot combinations");	
	
		setLayout(new BorderLayout());
		slotsPanel = new JPanel();
		slotsPanel.setLayout(new GridLayout(6,4));
		
		genericDialogListener = new GenericDialogActionListener(this);
		closeBtn = new JButton("Close");
		closeBtn.setActionCommand(Actions.HIDE_DIALOG.getAction());
		closeBtn.addActionListener(genericDialogListener);
		add(closeBtn, BorderLayout.SOUTH);
		
		add(intro, BorderLayout.NORTH);

		slotsPanel.add(new JLabel(SlotItem.ONE.getIcon()));
		slotsPanel.add(new JLabel(SlotItem.ONE.getIcon()));
		slotsPanel.add(new JLabel(SlotItem.ONE.getIcon()));
		slotsPanel.add(new JLabel("Wins 2", SwingConstants.CENTER));
		
		slotsPanel.add(new JLabel(SlotItem.THREE.getIcon()));
		slotsPanel.add(new JLabel(SlotItem.ONE.getIcon()));
		slotsPanel.add(new JLabel(SlotItem.SILVER.getIcon()));
		slotsPanel.add(new JLabel("WIns 3", SwingConstants.CENTER));
		
		slotsPanel.add(new JLabel(SlotItem.LIME.getIcon()));
		slotsPanel.add(new JLabel(SlotItem.LIME.getIcon()));
		slotsPanel.add(new JLabel(SlotItem.LIME.getIcon()));
		slotsPanel.add(new JLabel("WIns 12", SwingConstants.CENTER));
		
		slotsPanel.add(new JLabel(SlotItem.MELON.getIcon()));
		slotsPanel.add(new JLabel(SlotItem.MELON.getIcon()));
		slotsPanel.add(new JLabel(SlotItem.MELON.getIcon()));
		slotsPanel.add(new JLabel("WIns 18", SwingConstants.CENTER));
		
		slotsPanel.add(new JLabel(SlotItem.SILVER.getIcon()));
		slotsPanel.add(new JLabel(SlotItem.SILVER.getIcon()));
		slotsPanel.add(new JLabel(SlotItem.SILVER.getIcon()));
		slotsPanel.add(new JLabel("WIns 33", SwingConstants.CENTER));
		
		slotsPanel.add(new JLabel(SlotItem.GOLD.getIcon()));
		slotsPanel.add(new JLabel(SlotItem.GOLD.getIcon()));
		slotsPanel.add(new JLabel(SlotItem.GOLD.getIcon()));
		slotsPanel.add(new JLabel("WIns 50", SwingConstants.CENTER));
	
//		< 1            | 1            | 1            >    wins  2
//		< 3            | 1            | Silver Bar   >    wins  3
//		< Lime         | Lime         | Lime         >    wins 12
//		< Melon        | Melon        | Melon        >    wins 18
//		< Silver Bar   | Silver Bar   | Silver Bar   >    wins 33
//		< Gold Bar     | Gold Bar     | Gold Bar     >    wins 50
		
		add(slotsPanel, BorderLayout.CENTER);
		setMinimumSize(new Dimension(300,620));
		setVisible(false);
	}
}