package slotmachine.model;

public class PlayerImpl implements Player {

	private final String id;
	private final String name;
	private int initialCredits = 0;
	private int credits = 0;
	private int availableCredits = 0;
	private int bet = 0;
	
	public PlayerImpl(String id, String name, int initialCredits) {
		if(initialCredits < 0 || id == null || name == null) {
			throw new java.lang.IllegalArgumentException("ID or Name cannot be empty and starting credits must be greater than zero.");
		}
		if(!id.matches("[1-9]{5}[A-Z]{1}")) {
			throw new java.lang.IllegalArgumentException("ID must be in format 12345A (5 numbers, 1 capital letter.)");
		}
		this.id = id;
		this.name = name;
		this.initialCredits = initialCredits;
		this.credits = initialCredits;
		this.availableCredits = initialCredits;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getInitalCredits() {
		return initialCredits;
	}

	@Override
	public int getCredits() {
		return credits;
	}

	@Override
	public int getAvailableCredits() {
		return availableCredits;
	}

	@Override
	public void addCredits(int credits) {
		this.credits += credits;
		availableCredits += credits;
	}

	@Override
	public int getBet() {
		return bet;
	}

	@Override
	public void setBet(int bet) {
		this.bet += bet;
		availableCredits -= bet;
	}

	@Override
	public void resetBet() {
		bet = 0;
		availableCredits = credits;
	}

	@Override
	public void applyWin(int winAmount) {
		credits -= bet;
		credits += winAmount;
		availableCredits = credits - bet;
	}
	@Override
	public String toString() {
		return String.format("Player %s, %s, credits %s, bet %s, available %s", id, name, credits, bet, availableCredits);
	}
	
}
