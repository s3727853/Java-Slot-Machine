package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import model.PlayerIcon;
import view.GuiCallback;

public class AvatarActionListener implements ActionListener{
	
	private EnumMap<PlayerIcon, JButton> buttons;
	private GuiCallback callback;
	
	public AvatarActionListener(EnumMap<PlayerIcon, JButton> buttons, GuiCallback callback) {
		this.buttons = buttons;
		this.callback = callback;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand() == "ninja") {
			
			buttons.get(PlayerIcon.SPY).setBorder(new LineBorder(Color.GRAY,2));
			buttons.get(PlayerIcon.MAN).setBorder(new LineBorder(Color.GRAY,2));
			buttons.get(PlayerIcon.NEUTRAL).setBorder(new LineBorder(Color.GRAY,2));
			buttons.get(PlayerIcon.WOMAN).setBorder(new LineBorder(Color.GRAY,2));
			buttons.get(PlayerIcon.NINJA).setBorder(new LineBorder(Color.RED,2));
			callback.setIcon(PlayerIcon.NINJA.getIcon());
		}
		
		if(e.getActionCommand() == "spy") {
			buttons.get(PlayerIcon.SPY).setBorder(new LineBorder(Color.RED,2));
			buttons.get(PlayerIcon.MAN).setBorder(new LineBorder(Color.GRAY,2));
			buttons.get(PlayerIcon.NEUTRAL).setBorder(new LineBorder(Color.GRAY,2));
			buttons.get(PlayerIcon.WOMAN).setBorder(new LineBorder(Color.GRAY,2));
			buttons.get(PlayerIcon.NINJA).setBorder(new LineBorder(Color.GRAY,2));
			callback.setIcon(PlayerIcon.SPY.getIcon());
		}

		if(e.getActionCommand() == "man") {
			buttons.get(PlayerIcon.SPY).setBorder(new LineBorder(Color.GRAY,2));
			buttons.get(PlayerIcon.MAN).setBorder(new LineBorder(Color.RED,2));
			buttons.get(PlayerIcon.NEUTRAL).setBorder(new LineBorder(Color.GRAY,2));
			buttons.get(PlayerIcon.WOMAN).setBorder(new LineBorder(Color.GRAY,2));
			buttons.get(PlayerIcon.NINJA).setBorder(new LineBorder(Color.GRAY,2));
			callback.setIcon(PlayerIcon.MAN.getIcon());
		}

		if(e.getActionCommand() == "woman") {
			buttons.get(PlayerIcon.SPY).setBorder(new LineBorder(Color.GRAY,2));
			buttons.get(PlayerIcon.MAN).setBorder(new LineBorder(Color.GRAY,2));
			buttons.get(PlayerIcon.NEUTRAL).setBorder(new LineBorder(Color.GRAY,2));
			buttons.get(PlayerIcon.WOMAN).setBorder(new LineBorder(Color.RED,2));
			buttons.get(PlayerIcon.NINJA).setBorder(new LineBorder(Color.GRAY,2));
			callback.setIcon(PlayerIcon.WOMAN.getIcon());
		}
		
		if(e.getActionCommand() == "neutral") {
			buttons.get(PlayerIcon.SPY).setBorder(new LineBorder(Color.GRAY,2));
			buttons.get(PlayerIcon.MAN).setBorder(new LineBorder(Color.GRAY,2));
			buttons.get(PlayerIcon.NEUTRAL).setBorder(new LineBorder(Color.RED,2));
			buttons.get(PlayerIcon.WOMAN).setBorder(new LineBorder(Color.GRAY,2));
			buttons.get(PlayerIcon.NINJA).setBorder(new LineBorder(Color.GRAY,2));
			callback.setIcon(PlayerIcon.NEUTRAL.getIcon());
		}
	}
}
