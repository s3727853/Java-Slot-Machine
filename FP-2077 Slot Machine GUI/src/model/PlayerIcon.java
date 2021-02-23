package model;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public enum PlayerIcon {
	
	NEUTRAL("images/player/genderNeutral.png"),
	WOMAN("images/player/woman.png"),
	NINJA("images/player/ninja.png"),
	SPY("images/player/spy.png"),
	MAN("images/player/man.png");

	private Icon icon;
	
	private PlayerIcon(String iconFilename)
	{
		this.icon = new ImageIcon(iconFilename);
	}
		
	public Icon getIcon() {
		return icon;
	}
}
