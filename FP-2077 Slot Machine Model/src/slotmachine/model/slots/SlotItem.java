package slotmachine.model.slots;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Supporting enum used in the <b>Further Programming Assignment</b>
 * <p>
 * The values in this enum are use to represent a single slot item on a wheel.
 * <p>
 * Each {@link Wheel} is made up of 100 {@link SlotItem} with {@link #count}
 * number of each {@link SlotItem} value. e.g. There are two {@link #GOLD} 
 * and 7 {@link #LIME} on each wheel
 * <p>
 * <b>Note:</b> You are <b>not</b> permitted to change this enum in any way
 * (aside from during your own testing). The submitted enum must exactly match
 * the start up code.
 * 
 *
 * 
 * @author Ross Nye
 */
public enum SlotItem
{
	ONE("1", 22,"images/one.png"),
	THREE("3", 18, "images/three.png"),
	FIVE("5", 14, "images/five.png"),
	SEVEN("7", 11, "images/seven.png"),
	LEMON("Lemon", 8, "images/lemon.png"),
	LIME("Lime", 7, "images/lime.png"),	
	BERRY("Berry",6, "images/berry.png"),
	CHERRY("Cherry", 5, "images/cherry.png"),
	MELON("Melon", 4, "images/melon.png"),
	SILVER("Silver Bar", 3, "images/silver.png"),
	GOLD("Gold Bar", 2, "images/gold.png");

	private String label;
	private int count;
	private Icon icon;
	
	private SlotItem(String label, int count, String iconFilename)
	{
		this.label = label;
		this.count = count;
		this.icon = new ImageIcon(iconFilename, label);
	}
	
	@Override
	public String toString()
	{
		return label;
	}
	
	public int getCount()
	{
		return count;
	}
	public Icon getIcon() {
		return icon;
	}
	
	public static SlotItem getRandomSlotItem()
	{
		return values()[(int)(Math.random() * values().length)];
	}
}
