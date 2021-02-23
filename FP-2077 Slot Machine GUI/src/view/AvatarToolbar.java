package view;


import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.EnumMap;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

import controller.AvatarActionListener;
import model.PlayerIcon;

@SuppressWarnings("serial")
public class AvatarToolbar extends JToolBar{
	
	private JButton spy, ninja, woman, man, genderNeutral;
	private EnumMap<PlayerIcon, JButton> buttons;
	private ActionListener al;

	
	
	public AvatarToolbar(GuiCallback callback) {
	
		setFloatable(false);
		buttons = new EnumMap<>(PlayerIcon.class);
		
		
		spy = new JButton(PlayerIcon.SPY.getIcon());
		ninja = new JButton(PlayerIcon.NINJA.getIcon());
		woman = new JButton(PlayerIcon.WOMAN.getIcon());
		man = new JButton(PlayerIcon.MAN.getIcon());
		genderNeutral = new JButton(PlayerIcon.NEUTRAL.getIcon());
		
		buttons.put(PlayerIcon.SPY, spy);
		buttons.put(PlayerIcon.NINJA, ninja);
		buttons.put(PlayerIcon.WOMAN, woman);
		buttons.put(PlayerIcon.MAN, man);
		buttons.put(PlayerIcon.NEUTRAL, genderNeutral);
		
		spy.setBorder(new LineBorder(Color.GRAY,2));
		ninja.setBorder(new LineBorder(Color.GRAY,2));
		woman.setBorder(new LineBorder(Color.GRAY,2));
		man.setBorder(new LineBorder(Color.GRAY,2));
		genderNeutral.setBorder(new LineBorder(Color.GRAY,2));
		
		
		al = new AvatarActionListener(buttons, callback);
		
		spy.setActionCommand("spy");
		spy.addActionListener(al);
		ninja.setActionCommand("ninja");
		ninja.addActionListener(al);
		woman.setActionCommand("woman");
		woman.addActionListener(al);
		man.setActionCommand("man");
		man.addActionListener(al);
		genderNeutral.setActionCommand("neutral");
		genderNeutral.addActionListener(al);		
		
		add(spy);
		add(ninja);
		add(woman);
		add(man);
		add(genderNeutral);
	}
}
