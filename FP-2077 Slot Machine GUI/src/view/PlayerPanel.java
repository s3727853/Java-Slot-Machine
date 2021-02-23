package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.PlayerIcon;
import slotmachine.model.Player;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel implements PropertyChangeListener {
	
	private JLabel playerName, playerAvailableCredits, playerCurrentCredtis, playerCurrentBet, avatar;
	private JTextField nameText, availableCreditsText, currentCreditsText, currentBetText;
	private JPanel playerInfo, playerAvatar;
	
	public PlayerPanel(GuiCallback callback) {
		
		playerInfo = new JPanel();
		playerAvatar = new JPanel();
		setLayout(new BorderLayout());
		
		playerInfo.setLayout(new GridLayout(2,4));
		
		callback.addPropertyChangeListener(this);
		
		//Labels
		playerName = new JLabel("Player Name");
		playerAvailableCredits = new JLabel("Available Credits");
		playerCurrentCredtis = new JLabel("Current Credits");
		playerCurrentBet = new JLabel("Current Bet");
		
		//Text Fields
		nameText = new JTextField();
		
		nameText.setEditable(false);
		
		availableCreditsText = new JTextField();
		
		availableCreditsText.setEditable(false);
		
		currentCreditsText = new JTextField();

		currentCreditsText.setEditable(false);
		
		currentBetText = new JTextField();
	
		currentBetText.setEditable(false);
		
		// Avatar 
		avatar = new JLabel(PlayerIcon.SPY.getIcon());
		
		playerAvatar.add(avatar);
		playerInfo.add(playerName);
		playerInfo.add(nameText);
		
		playerInfo.add(playerAvailableCredits);
		playerInfo.add(availableCreditsText);
		
		playerInfo.add(playerCurrentCredtis);
		playerInfo.add(currentCreditsText);
		
		playerInfo.add(playerCurrentBet);
		playerInfo.add(currentBetText);
		
		add(playerInfo, BorderLayout.EAST);
		add(playerAvatar, BorderLayout.WEST);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName() == GuiCallback.Events.NEW_PLAYER) {
			
			// Show Player details in the view
			Player player = (Player) evt.getNewValue();
			nameText.setText(player.getName());
			availableCreditsText.setText(Integer.toString(player.getAvailableCredits()));
			currentCreditsText.setText(Integer.toString(player.getCredits()));
			currentBetText.setText(Integer.toString(player.getBet()));
		}
		
		// Update bet credit fields when new bets are placed or end of spin
		if(evt.getPropertyName() == GuiCallback.Events.NEW_BET || evt.getPropertyName() == GuiCallback.Events.BET_TOTALS || evt.getPropertyName() == GuiCallback.Events.RESET_BET ) {
			Player player = (Player) evt.getNewValue();
			currentCreditsText.setText(Integer.toString(player.getCredits()));
			availableCreditsText.setText(Integer.toString(player.getAvailableCredits()));
			currentBetText.setText(Integer.toString(player.getBet()));
		}
		
		if(evt.getPropertyName() == GuiCallback.Events.CASHOUT_PLAYER) {
			nameText.setText("");
			currentCreditsText.setText("");
			availableCreditsText.setText("");
			currentBetText.setText("");
		}
		
		if(evt.getPropertyName() == GuiCallback.Events.SET_AVATAR) {
			avatar.setIcon((Icon) evt.getNewValue());
		}
	}
}