package net.jordaria.item;

/**
 * An enum of different slots for armor.
 * 
 * @author Ches Burks
 *
 */
public enum ArmorSlot {
	HELMET(0),
	CHESTPLATE(1),
	LEGGINGS(2),
	BOOTS(3),
	RING(4),
	NECKLACE(5),
	CAPE(6);
	
	private int index;
	/**
	 * Constructs a new {@link ArmorSlot}.
	 * 
	 * @param index The index to use
	 */
	private ArmorSlot(int index){
		this.index = index;
	}
	
	/**
	 * Returns the index of the slot.
	 * 
	 * @return The index
	 */
	public int getIndex(){
		return index;
	}
}
